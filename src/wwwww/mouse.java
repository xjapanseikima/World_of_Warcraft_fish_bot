package wwwww;

import java.awt.MouseInfo;

public class mouse {
public static void main (String args[]) {
	 while(true){
	      //Thread.sleep(100);
	      System.out.println("(" + MouseInfo.getPointerInfo().getLocation().x + 
	              ", " + 
	              MouseInfo.getPointerInfo().getLocation().y + ")");
	    }
}
}
