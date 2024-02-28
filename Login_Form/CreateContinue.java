/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login_Form;

import java.io.File;

/**
 *
 * @author ahmed
 */
public class CreateContinue {
    public static String CurrentContinueFilePath;
    

    public CreateContinue(String ContinueFileName) {
        CurrentContinueFilePath = String.format("Continue/%s.txt", ContinueFileName);
        File file = new File(CurrentContinueFilePath);
        

    try {

      // create a new file with name specified
      // by the file object
      boolean value = file.createNewFile();
    }
    catch(Exception e) {
      e.getStackTrace();
    }
    }
}
