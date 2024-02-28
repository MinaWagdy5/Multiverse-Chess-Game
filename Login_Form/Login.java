
package Login_Form;

import java.awt.Toolkit;
import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {
    Music music = new Music();

    String soundEffect = "MenuSoundEffect.wav";
    public static String usernamex;
    public static String passwordx;

    public static boolean theme = false;

    public static Settings settings;

    public Login() {
        initComponents();
        setIconImage();
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        UserName_Field = new javax.swing.JTextField();
        Password_Field = new javax.swing.JPasswordField();
        LogIn_Button = new javax.swing.JButton();
        UserName_Icon = new javax.swing.JLabel();
        Password_Icon = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        Login_Background_Video = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Multiverse Of Chess");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("AGA Arabesque", 0, 12)); // NOI18N
        setName("Login_Frame"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Papyrus", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Multiverse Of Chess");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 360, 70));

        UserName_Field.setText("username");
        UserName_Field.setBorder(null);
        UserName_Field.setOpaque(true);
        UserName_Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserName_FieldActionPerformed(evt);
            }
        });

        getContentPane().add(UserName_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 120, 20));

        Password_Field.setText("jPasswordField1");
        Password_Field.setBorder(null);
        Password_Field.setOpaque(true);
        getContentPane().add(Password_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 120, 20));

        LogIn_Button.setText("Login");
        LogIn_Button.setBorder(null);
        LogIn_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogIn_ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(LogIn_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 120, 20));

        UserName_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/icons8-user-16.png"))); // NOI18N
        UserName_Icon.setText("jLabel4");
        getContentPane().add(UserName_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 30, 20));

        Password_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/icons8-password-24 (1).png"))); // NOI18N
        Password_Icon.setText("jLabel2");
        getContentPane().add(Password_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 30, 20));

        jButton2.setText("Register");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 120, 20));

        Login_Background_Video.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/The_Chess_Game_l_Cinematic_Chess_B_Roll_l_Edelkrone_AdobeExpress (1).gif"))); // NOI18N
        Login_Background_Video.setText("jLabel3");
        getContentPane().add(Login_Background_Video, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void UserName_FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserName_FieldActionPerformed
        
    }//GEN-LAST:event_UserName_FieldActionPerformed

    private void LogIn_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogIn_ButtonActionPerformed
           music.PlaySoundEffect(soundEffect);
            if (evt.getSource()==LogIn_Button){
                
           usernamex = UserName_Field.getText();
           passwordx = Password_Field.getText();

           try
           {
               File AccountsData = new File("AccountsData.txt");
               Scanner scan = new Scanner (AccountsData);
               scan.useDelimiter("[,\n]");
               boolean check =false;
          
               while(scan.hasNext()){
                   String name = scan.next();
                   String username = scan.next();
                   String password = scan.next();   

                   if(usernamex.equals(username.trim()) && passwordx.equals(password.trim())){
                        this.dispose();   
                        MainMenu mainmenu = new MainMenu();
                        mainmenu.setVisible(true);
                        settings = new Settings();
                        check = true;
                        break;
                   }
               }
               if(check == false){
                   JOptionPane.showMessageDialog(null,"invalid data");
                   UserName_Field.setText("username");
            }
           }
          
           catch(Exception e)
           {
               JOptionPane.showMessageDialog(null,"an error occured"+e);
           }

            }
    }//GEN-LAST:event_LogIn_ButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        music.PlaySoundEffect(soundEffect);
        this.dispose();
        Register register = new Register();
        register.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed


    public static void main(String args[]) {


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LogIn_Button;
    private javax.swing.JLabel Login_Background_Video;
    private javax.swing.JPasswordField Password_Field;
    private javax.swing.JLabel Password_Icon;
    public javax.swing.JTextField UserName_Field;
    private javax.swing.JLabel UserName_Icon;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8-chess-64 (1).png")));
    }


}


