package LogIn;

import Hotel.HotelManApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame{
    private JPanel mainPanel;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton cancelButton;
    private JButton loginButton;

    public login(String login) {
        super("title");
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);
        this.pack();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int re = JOptionPane.showConfirmDialog(login.this,"Are you sure?", "Exit?",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if (re == JOptionPane.YES_NO_OPTION){
                    System.exit(0);
                }
            }
        });
    }
    private void login() {
        String name = txtUsername.getText();
        String pass = String.valueOf(txtPassword.getPassword());
        HotelManApp hotelManApp = null;
        Staff admin = new Staff("admin","123");
        Staff checkUser = new Staff(name,pass);

        boolean login = false;

        if (admin.equals(checkUser)){
            hotelManApp = new HotelManApp(name);
            login = true;
        }
        if (login) {
            hotelManApp.setVisible(true);
            this.setVisible(false);
        }else {
            showMess("Login failed");
        }
    }
    private void showMess(String mess) {
        JOptionPane.showMessageDialog(login.this,mess);
    }

    public static void main(String[] args) {
        JFrame l = new login("Login");
        l.setVisible(true);
    }
}