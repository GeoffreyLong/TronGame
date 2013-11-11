/**
 * @author Rishabh Tandon
 * 
 * This is the main class that initializes the JFrame and adds the StartPanel to it.
 * This class is used as the main to run the game. 
 */

package prototype;

public class Main{
        public static void main(String[] args){ 
                StartPanel panel = new StartPanel();
                Frame frame = new Frame();
                Frame.addPanel(panel);
         }
}
