
package Login_Form;


public class Player {
    public String name;
    public static int minute;
    public static int second;
    
    public Player(String name,String minutes,String seconds){
        this.name = name;
        minute = Integer.parseInt(minutes);
        second = Integer.parseInt(seconds);
    }
    
    public Player(){}
}
