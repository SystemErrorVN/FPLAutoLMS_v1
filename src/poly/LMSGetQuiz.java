/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly;

import Util.Utilities;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Account;
import model.Quiz;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import poly.exception.GetQuizException;
import request.HttpRequest;
import request.support.HttpRequestHeader;

/**
 *
 * @author Administrator
 */
public class LMSGetQuiz {

    private Account account;
    private String refId;

    private Quiz[] quiz;
    private boolean use;

    public LMSGetQuiz() {
    }

    public LMSGetQuiz(Account account, String refId) {
        this.account = account;
        this.refId = refId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public Quiz[] getQuiz() throws GetQuizException {
        get();
        return quiz;
    }

    public void get() throws GetQuizException {
        if (use) {
            return;
        }
        use = !use;
        String url = account.getServer().toString() + "/ilias.php?ref_id=" + refId + "&cmd=frameset&cmdClass=ilrepositorygui&cmdNode=q4&baseClass=ilRepositoryGUI";
        HttpRequestHeader httpRequestHeader = Utilities.buildHttpRequestHeader(account.getCookie());
        String htmlResp = null;
        try {
            HttpRequest httpRequest = new HttpRequest(url, httpRequestHeader);
            htmlResp = httpRequest.getResponseHTML();
        } catch (IOException ex) {
            throw new GetQuizException("LMSGetQuiz->get->RequestError->" + ex.toString());
        }
        Document document = Jsoup.parse(htmlResp);
        Elements elmsListQuiz = document.select("a[class='il_ContainerItemTitle']");
        if (elmsListQuiz.isEmpty()) {
            throw new GetQuizException("LMSGetQuiz->get->a[class='il_ContainerItemTitle'] empty!");
        }
        ArrayList<Quiz> alQuizs = new ArrayList<>();
        for (Element q : elmsListQuiz) {
            Quiz quiz = new Quiz();
            try {
                quiz.setUrl(account.getServer().toString() + "/" + q.attr("href"));
                quiz.setRefId(getRefIdInUrl(quiz.getUrl()));
                quiz.setName(q.text());
                quiz.setDone(checkQuizDone(account, quiz));
            } catch (GetQuizException e) {
                System.out.println(e.toString());
                continue;
            }
            alQuizs.add(quiz);
        }
        quiz = new Quiz[alQuizs.size()];
        int i=0;
        for(Quiz quiz: alQuizs){
            this.quiz[i++] = quiz;
        }
    }

    private static String getRefIdInUrl(String url) throws GetQuizException {
        String regex = "ref_id=([0-9]+?)&";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(url);
        if (m.find()) {
            int start = m.group().indexOf("=");
            int end = m.group().indexOf("&");
            return m.group().substring(start + 1, end);
        }
        throw new GetQuizException("LMSGetQuiz->getRefIdInUrl->Get RefId Error!");
    }

    private static boolean checkQuizDone(Account account, Quiz quiz) throws GetQuizException {
        String url = quiz.getUrl();
        HttpRequestHeader httpRequestHeader = Utilities.buildHttpRequestHeader(account.getCookie());
        HttpRequest httpRequest = new HttpRequest(url, httpRequestHeader);
        String htmlResp = null;
        try {
            htmlResp = httpRequest.getResponseHTML();
        } catch (IOException e) {
            throw new GetQuizException("LMSGetQuiz->checkQuizDone->RequestError->" + e.toString());
        }
        Document document = Jsoup.parse(htmlResp);
        Elements elmsCheckDone = document.getElementsByClass("alert alert-info");
        if (elmsCheckDone.isEmpty()) {
            throw new GetQuizException("LMSGetQuiz->checkQuizDone->alert alert-info empty!");
        }
        return elmsCheckDone.size() > 0;
    }
}
