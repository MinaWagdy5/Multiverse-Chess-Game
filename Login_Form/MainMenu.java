
package Login_Form;

import com.chessgame.Board.PopupExample;
import com.chessgame.Frame.Frame;
import com.chessgame.Frame.LabelTimer;
import com.chessgame.Game.Game;
import com.chessgame.Pieces.Bishop;
import com.chessgame.Pieces.Knight;
import com.chessgame.Pieces.Pawn;
import com.chessgame.Pieces.Queen;
import com.chessgame.Pieces.Rook;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class MainMenu extends javax.swing.JFrame {

    Music music = new Music();
    String filepath= "Bones.wav";
    String soundEffect = "MenuSoundEffect.wav";
    
    public MainMenu() {
        initComponents();
        setIconImage();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        HistoryButton = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        SettingsButton = new javax.swing.JLabel();
        ExitButton = new javax.swing.JLabel();
        NewGameButton = new javax.swing.JLabel();
        ContinueButton = new javax.swing.JLabel();
        Login_Background_Video = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Multiverse Of Chess");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HistoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundedhistory1.png")));// NOI18N
        HistoryButton.setText("jLabel2");
        HistoryButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                music.PlaySoundEffect(soundEffect);
                HistoryButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HistoryButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HistoryButtonMouseExited(evt);
            }
        });
        getContentPane().add(HistoryButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 220, 110));

        jLabel1.setFont(new java.awt.Font("Papyrus", 1, 77)); // NOI18N
        jLabel1.setText("Multiverse Of Chess");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 790, 90));

        SettingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundedsettings1.png")));
        SettingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SettingsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SettingsButtonMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SettingsButtonMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SettingsButtonMouseClicked(evt);
            }
        });
        getContentPane().add(SettingsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 360, 250, -1));

        ExitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundedexit1.png")));
        ExitButton.setText("jLabel2");
        ExitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ExitButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ExitButtonMouseExited(evt);
            }
        });
        getContentPane().add(ExitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 640, 140, -1));

        NewGameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundednewgame1.png")));
        NewGameButton.setText("jLabel2");
        NewGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NewGameButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NewGameButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NewGameButtonMouseExited(evt);
            }
        });
        getContentPane().add(NewGameButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 290, 160));

        ContinueButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundedcontinue1.png")));
        ContinueButton.setText("jLabel2");
        ContinueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    ContinueButtonMouseClicked(evt);
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ContinueButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ContinueButtonMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ContinueButtonMouseReleased(evt);
            }
        });
        getContentPane().add(ContinueButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 250, 90));

        if(Login.theme)
            Login_Background_Video.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/theme/Multiverse Main Menu.png"))); // NOI18N
        else
            Login_Background_Video.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/Backgroundnew.jpg"))); // NOI18N

        Login_Background_Video.setText("");
        getContentPane().add(Login_Background_Video, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -1, 1270, 810));

        setSize(new java.awt.Dimension(1281, 783));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void HistoryButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistoryButtonMouseEntered
        // TODO add your handling code here:
        HistoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundedhistory2.png")));
    }//GEN-LAST:event_HistoryButtonMouseEntered

    private void HistoryButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistoryButtonMouseExited
        // TODO add your handling code here:
        HistoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundedhistory1.png")));
  
    }//GEN-LAST:event_HistoryButtonMouseExited

    private void HistoryButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistoryButtonMouseClicked
        // TODO add your handling code here:
         music.PlaySoundEffect(soundEffect);
        this.dispose();
       History historyPage = new History();
       historyPage.setVisible(true);  
    }//GEN-LAST:event_HistoryButtonMouseClicked

    private void SettingsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SettingsButtonMouseEntered
        // TODO add your handling code here:
        SettingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundedsettings2.png")));
  
    }//GEN-LAST:event_SettingsButtonMouseEntered

    private void SettingsButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SettingsButtonMouseReleased
        SettingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundedsettings1.png")));

    }//GEN-LAST:event_SettingsButtonMouseReleased

    private void ContinueButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ContinueButtonMouseEntered
        ContinueButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundedcontinue2.png")));

    }//GEN-LAST:event_ContinueButtonMouseEntered

    private void ContinueButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ContinueButtonMouseReleased
          ContinueButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundedcontinue1.png")));
    }//GEN-LAST:event_ContinueButtonMouseReleased

    private void SettingsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SettingsButtonMouseExited
        SettingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundedsettings1.png")));
    }//GEN-LAST:event_SettingsButtonMouseExited

    private void ContinueButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ContinueButtonMouseExited
        ContinueButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundedcontinue1.png")));

    }//GEN-LAST:event_ContinueButtonMouseExited

    private void ContinueButtonMouseClicked(java.awt.event.MouseEvent evt) throws UnsupportedAudioFileException, LineUnavailableException, IOException {//GEN-FIRST:event_ContinueButtonMouseClicked
       SetUp.player1 = new Player();
        SetUp.player2 = new Player();
        String s = null;
        try {
            String CurrentContinueFilePath = String.format("Continue/%s.txt", Login.usernamex);
            File file = new File(CurrentContinueFilePath);
            Scanner scan = new Scanner(file);
            s = scan.next();
            scan.useDelimiter("[,\n]");

            SetUp.player1.name = scan.next();
            SetUp.player1.name = scan.next();
            SetUp.player2.name = scan.next();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Warning! no saved games",null,JOptionPane.WARNING_MESSAGE);
        }

        if (s != null){
        String DeadPiecesFilePath = String.format("DeadPieces/%s.txt", Login.usernamex);
        File file = new File(DeadPiecesFilePath);
        String s1 = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            s1 = reader.readLine();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        if(s1 != null){
            String[] parts = s1.split("/");
            for (String s2 : parts) {
                if (s2 != null) {
                    if (Character.isLowerCase(s2.charAt(0))) {
                        addDeadPieces(Character.getNumericValue(s2.charAt(1)), Character.getNumericValue(s2.charAt(2)), s2.charAt(0), false);
                    } else {
                        addDeadPieces(Character.getNumericValue(s2.charAt(1)), Character.getNumericValue(s2.charAt(2)), s2.charAt(0), true);
                    }
                }
            }
        }
        this.dispose();
        music.PlaySoundEffect(soundEffect);
        SetUp.frame = new Frame();
        if(Login.theme)
        { new PopupExample(0);}
        Game.drawDeadPieces();
        }

    }//GEN-LAST:event_ContinueButtonMouseClicked

    private void ExitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitButtonMouseEntered
       ExitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundedexit2.png")));

    }//GEN-LAST:event_ExitButtonMouseEntered

    private void ExitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitButtonMouseExited
        ExitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundedexit1.png")));
    }//GEN-LAST:event_ExitButtonMouseExited

    private void ExitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitButtonMouseClicked
        music.PlaySoundEffect(soundEffect);
        Login.theme = false;
        if( Login.settings.musicPlayeedForOneTimeAtLeast) {
            Login.settings.music.StopMusic(Login.settings.filepath);
        }
        this.dispose();
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_ExitButtonMouseClicked

    private void NewGameButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewGameButtonMouseEntered
       NewGameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundednewgame2.png")));

    }//GEN-LAST:event_NewGameButtonMouseEntered

    private void NewGameButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewGameButtonMouseExited
       NewGameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login_Form/roundednewgame1.png")));

    }//GEN-LAST:event_NewGameButtonMouseExited

    private void NewGameButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewGameButtonMouseClicked
         music.PlaySoundEffect(soundEffect);
        this.dispose();
        SetUp setup = new SetUp();
        setup.setVisible(true);
        String CurrentContinueFilePath = String.format("Continue/%s.txt", Login.usernamex);
                File file = new File(CurrentContinueFilePath);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                            writer.write("");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}
        
    }

    private void SettingsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SettingsButtonMouseExited
        music.PlaySoundEffect(soundEffect);
        Login.settings.setVisible(true);
        new Settings();
        this.dispose();
    }//GEN-LAST:event_NewGameButtonMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ContinueButton;
    private javax.swing.JLabel ExitButton;
    private javax.swing.JLabel HistoryButton;
    private javax.swing.JLabel Login_Background_Video;
    private javax.swing.JLabel NewGameButton;
    private javax.swing.JLabel SettingsButton;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8-chess-64 (1).png")));
    }
        private void addDeadPieces(int x, int y, char c, boolean isWhite) {
        switch (String.valueOf(c).toUpperCase()) {
            case "R" -> Game.board.deadPieces.add(new Rook(isWhite));
            case "N" -> Game.board.deadPieces.add(new Knight(isWhite));
            case "B" -> Game.board.deadPieces.add(new Bishop(isWhite));
            case "Q" -> Game.board.deadPieces.add(new Queen( isWhite));
            case "P" -> Game.board.deadPieces.add(new Pawn(isWhite));
        }
    }
}
