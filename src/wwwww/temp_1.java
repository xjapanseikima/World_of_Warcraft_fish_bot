package wwwww;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class temp_1  {
	static BufferedImage image = null;
	static Robot robot = null;
	static JFrame demo = new JFrame();

	public static void main(String[] args) throws AWTException, InterruptedException, IOException {

		demo.setSize(700, 300);
		demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton start_btn = new JButton("start");
		JButton pause_btn = new JButton("pause");
		demo.getContentPane().add(BorderLayout.NORTH, start_btn);
		demo.getContentPane().add(BorderLayout.SOUTH, pause_btn);
		demo.setLocation(675, 540);
		demo.setVisible(true);
		demo.addKeyListener(new MyKeyListener());
		
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		start_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					start();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		pause_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
	}
	

	private static void start() throws InterruptedException, IOException {
		System.out.println("開始釣魚");
		image = robot.createScreenCapture(new Rectangle(754, 476, 500, 200));
		/* black and white */
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(cal.getTime()));
		//demo.setVisible(false);
		demo.toBack();
		TimeUnit.SECONDS.sleep(2);
		int count = 0;
		int fail_count=0;
		int fail_time = 0;
		while (true) {
			image = robot.createScreenCapture(new Rectangle(0, 0, 1899, 1199));
			BufferedImage result = Blackscale(image);
			int x = demo.getX();
			int y = demo.getY();
			int end_x = x + 700;
			int end_y = y + 300;
			fail_time++;
			for (int i = x; i < end_x; i++) {
				for (int j = y; j < end_y; j++) {
					int pixel = 0;
					// System.out.println(x);
					pixel = result.getRGB(i, j);
					int r = (pixel >> 16) & 0xFF;
					int g = (pixel >> 8) & 0xFF;
					int b = (pixel) & 0xFF;
					/* fishing position */
					// System.out.println("解析度 調高 對比 100 亮度 0 gama 06 , 水體細節良好 例子密度特高 顯著標示 高 其他為低  (為了 吊起來的水波).其他設定高");
					if (r == 255 && g == 255 && b == 255) {
						Thread.sleep(2000);
						count++;
						// System.out.println("x軸是 " + i + "y軸" + j);
						File outputfile = new File("saved.png");
					    ImageIO.write(result, "png", outputfile);
						System.out.println("揮桿第:" + count);
						catching(i, j);
						Thread.sleep(1000);
						fishing();
						image.flush();
						result.flush();
						fail_time = 0;
						Thread.sleep(2000);
						i = end_x + 10;
						j = end_y + 10;
						break;
					}
				}
			}
			//上魚餌
//			if(count%50==0) {
//				lure();
//			}
			if (fail_time > 350) {
				fail_count++;
				System.out.println("重新揮桿"+fail_count);
				fail_time = 0;
				fishing();
			}
		}
	}
	private static void lure() throws InterruptedException {
		Thread.sleep(1000);
		robot.mouseMove(922, 1071); // fishing skill
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(4000);
	}


	private static BufferedImage Blackscale(BufferedImage temp_img) {
		BufferedImage result = new BufferedImage(temp_img.getWidth(), temp_img.getHeight(),
				BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D graphic = result.createGraphics();
		graphic.drawImage(image, 0, 0, Color.WHITE, null);
		graphic.dispose();
		return result;
	}

	private static void fishing() throws InterruptedException {
		robot.mouseMove(1774, 506); // fishing skill
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	private static void catching(int x, int y) throws InterruptedException {
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

	}
	
}
