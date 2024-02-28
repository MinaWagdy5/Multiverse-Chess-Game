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
public class CreateHistory {
    

    public CreateHistory(String HistoryFileName) {
        String CurrentHistoryFilePath = String.format("History/%s.txt", HistoryFileName);
        File file = new File(CurrentHistoryFilePath);
        

    try {

      // create a new file with name specified
      // by the file object
      boolean value = file.createNewFile();
      if (value) {
//        System.out.println("New Java File is created.");
      }
      else {
//        System.out.println("The file already exists.");
      }
    }
    catch(Exception e) {
      e.getStackTrace();
    }
    }
    
}
