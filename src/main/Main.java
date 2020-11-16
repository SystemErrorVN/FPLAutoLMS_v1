
package main;

import frames.FrameMain;
import model.Account;

public class Main {

    public static FrameMain frameMain;
    public static Account account;

    public static void main(String[] args) throws Exception {
        frameMain = new FrameMain();
        frameMain.setVisible(true);
    }

}
