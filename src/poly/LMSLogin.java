package poly;

import Util.Utilities;
import java.io.IOException;
import model.Account;
import model.Server;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import poly.exception.LoginException;
import request.HttpRequest;

/**
 *
 * @author Administrator
 */
public class LMSLogin {

    private Server server;
    private String cookie;

    private Account account;
    private boolean use;

    public LMSLogin() {
    }

    public LMSLogin(Server server, String cookie) {
        this.server = server;
        this.cookie = cookie;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public Account getAccount() throws LoginException {
        login();
        return this.account;
    }

    public void login() throws LoginException {
        if (use) {
            return;
        }
        use = !use;
        String url = server.toString() + "/ilias.php?baseClass=ilPersonalDesktopGUI&cmd=jumpToProfile";
        HttpRequest httpRequest = new HttpRequest(url, Utilities.buildHttpRequestHeader(cookie));
        String htmlResponse = null;
        try {
            httpRequest.connect();
            htmlResponse = httpRequest.getResponseHTML();
        } catch (IOException e) {
            throw new LoginException("LMSLogin->login->Request Error(" + e.toString() + ").");
        }
        Document document = Jsoup.parse(htmlResponse);
        account = buildAccount(document);
        account.setCookie(cookie);
        account.setServer(server);
    }

    private static Account buildAccount(Document document) throws LoginException {
        Element elmMail = document.selectFirst("input[id='hiddenne_un']");
        if (elmMail == null) {
            throw new LoginException("LMSLogin->buildAccount-> input[id='hiddenne_un'] is NULL.");
        }
        Element elmName = document.selectFirst("input[id='usr_lastname']");
        if (elmName == null) {
            throw new LoginException("LMSLogin->buildAccount-> input[id='usr_lastname'] is NULL.");
        }
        Element elmBirthday = document.selectFirst("input[name='usr_birthday']");
        if (elmBirthday == null) {
            throw new LoginException("LMSLogin->buildAccount-> input[name='usr_birthday'] is NULL.");
        }
        Element elmSex = document.selectFirst("input[type='radio'][checked='checked']");
        if (elmSex == null) {
            throw new LoginException("LMSLogin->buildAccount-> input[type='radio'][checked='checked'] is NULL.");
        }
        Account account = new Account();
        account.setName(elmName.attr("value"));
        account.setId(elmMail.attr("value"));
        account.setEmail(account.getId() + "@fpt.edu.vn");
        account.setBirthDay(elmBirthday.attr("value"));
        account.setSex(elmSex.attr("value").equals("m") ? "male" : "female");
        return account;
    }
}
