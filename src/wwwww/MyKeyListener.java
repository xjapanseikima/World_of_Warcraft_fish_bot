package wwwww;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyListener  extends KeyAdapter{
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		 if(e.getKeyCode() == KeyEvent.VK_F5) {
			 System.out.println("F5 pressed");
		 }	        
	}
	


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
