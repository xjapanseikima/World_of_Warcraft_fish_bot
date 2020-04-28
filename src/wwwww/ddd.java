package wwwww;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.event.AWTEventListener;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class ddd {
	static BufferedImage image = null;

	public static void main(String[] args) throws AWTException, InterruptedException, IOException {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // 得到屏幕
		System.out.println(dim);

		TimeUnit.SECONDS.sleep(5);
		image = robot.createScreenCapture(new Rectangle(754, 476, 500, 200));
		/* black and white */
		BufferedImage result = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D graphic = result.createGraphics();
        graphic.drawImage(image, 0, 0, Color.WHITE, null);
        graphic.dispose();
       // File output = new File("black-white.png");
        //ImageIO.write(result, "png", output);
		System.out.println("test");
		int w = 1;
		while (w == 1) {
			//System.out.println("new image");
			image = robot.createScreenCapture(new Rectangle(0, 0, 1899, 1199));
			 result = new BufferedImage(image.getWidth(), image.getHeight(),
					BufferedImage.TYPE_BYTE_BINARY);
			 graphic = result.createGraphics();
			graphic.drawImage(image, 0, 0, Color.WHITE, null);
			graphic.dispose();
			File output = new File("black-white.png");
		        ImageIO.write(result, "png", output);
			for ( int x = 675; x < 1157; x++) {
				for ( int  y = 540; y < 736; y++) {
					int pixel = 0;
					// System.out.println(x);
					pixel = result.getRGB(x, y);
					float hsb[] = new float[3];
					int r = (pixel >> 16) & 0xFF;
					int g = (pixel >> 8) & 0xFF;
					int b = (pixel) & 0xFF;
					/* fishing position */
					if (r == 255 && g == 255 && b == 255) {
						TimeUnit.SECONDS.sleep(5);
						System.out.println("x軸是 " + x + "y軸" + y);
						robot.mouseMove(x, y);
						robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						TimeUnit.SECONDS.sleep(1);
						robot.mouseMove(1851, 508); // fishing skill
						robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						image.flush();
						result.flush();	
						TimeUnit.SECONDS.sleep(2);
						x=1201;
						y=677;
						break;
					}
				}
				
			}
		}
	}
}
