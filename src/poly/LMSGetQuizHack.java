/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly;

import Util.Utilities;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.QuizCheck;
import model.QuizCheckQuestion;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import poly.exception.GetQuizHackException;
import request.HttpRequest;
import request.support.HttpRequestHeader;

/**
 *
 * @author Administrator
 */
public class LMSGetQuizHack {

    private Account account;
    private String refId;

    private QuizCheck quizCheck;
    private boolean use;

    public LMSGetQuizHack() {
    }

    public LMSGetQuizHack(Account account, String refId) {
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
    
    public QuizCheck getQuizCheck() throws GetQuizHackException{
        build();
        return quizCheck;
    }
    public void build() throws GetQuizHackException {
        if (use) {
            return;
        }
        use = !use;
        //build quiz check
        String url = account.getServer().toString() + "/ilias.php?ref_id=" + refId + "&active_id=12345&pass=0&cmd=outUserPassDetails&cmdClass=iltestevaluationgui&cmdNode=q4:ll:vx&baseClass=ilRepositoryGUI";
        HttpRequestHeader httpRequestHeader = Utilities.buildHttpRequestHeader(account.getCookie());
        HttpRequest httpRequest = new HttpRequest(url, httpRequestHeader);
        String htmlResp = null;
        try {
            htmlResp = httpRequest.getResponseHTML();
        } catch (IOException e) {
            throw new GetQuizHackException("LMSGetQuizHack->build->ConnectError->" + e.toString());
        }
        quizCheck = new QuizCheck();
        quizCheck.setName(quizCheckParseName(htmlResp));
        //build quizquestion
        Document document = Jsoup.parse(htmlResp);
        Elements elmsLinkAnswer = document.select("td[class='std'] > a");
        if(elmsLinkAnswer.isEmpty()){
            throw new GetQuizHackException("LMSGetQuizHack->build->td[class='std'] > a is empty!");
        }
        QuizCheckQuestion[] quizCheckQuestion = new QuizCheckQuestion[elmsLinkAnswer.size()];
        int i = 0;
        for (Element answer : elmsLinkAnswer) {
            quizCheckQuestion[i] = new QuizCheckQuestion();
            url = account.getServer().toString() + "/" + answer.attr("href");
            HttpRequest checkAnswer = new HttpRequest(url, httpRequestHeader);
            try {
                checkAnswer.connect();
            } catch (IOException ex) {
                continue;
            }
            //code here........
        }
    }

    private static String quizCheckParseName(String htmlResp) throws GetQuizHackException {
        Document document = Jsoup.parse(htmlResp);
        Element name = document.selectFirst("a[id='il_mhead_t_focus']");
        if (name == null) {
            throw new GetQuizHackException("LMSGetQuizHack->quizCheckParseName->a[id='il_mhead_t_focus'] is null!");
        }
        return name.text();
    }
}
