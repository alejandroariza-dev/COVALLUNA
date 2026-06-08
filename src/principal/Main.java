package principal;

import vista.FrmLogin;

public class Main {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            
            new FrmLogin()
                    .setVisible(true);

        });
    }
}