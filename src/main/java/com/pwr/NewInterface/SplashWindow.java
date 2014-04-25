package com.pwr.NewInterface;

import java.awt.*;
import java.awt.event.*;

public class SplashWindow extends Window {
	private Image splashImage;
	private int imgWidth, imgHeight;
	private String imgName;
	private static final int BORDERSIZE = 5;
	private static final Color BORDERCOLOR = Color.blue;
	Toolkit tk;

	public SplashWindow(Frame f, String imgName) {
		super(f);
		this.imgName = imgName;
		tk = Toolkit.getDefaultToolkit();
		splashImage = loadSplashImage();
		showSplashScreen();
		f.addWindowListener(new WindowListener());
	}

	public Image loadSplashImage() {
		MediaTracker tracker = new MediaTracker(this);
		Image result;
		result = tk.getImage(imgName);
		tracker.addImage(result, 0);
		try {
			tracker.waitForAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		imgWidth = result.getWidth(this);
		imgHeight = result.getHeight(this);
		return (result);
	}

	public void showSplashScreen() {
		Dimension screenSize = tk.getScreenSize();
		//setBackground(BORDERCOLOR);
		int w = imgWidth + (BORDERSIZE * 2);
		int h = imgHeight + (BORDERSIZE * 2);
		int x = (screenSize.width - w) / 2;
		int y = (screenSize.height - h) / 2;
		setBounds(x, y, w, h);
		setVisible(true);
	}

	public void paint(Graphics g) {
		g.drawImage(splashImage, BORDERSIZE, BORDERSIZE, imgWidth, imgHeight,
				this);
	}

	class WindowListener extends WindowAdapter {
		public void windowOpened(WindowEvent we) {
			setVisible(false);
			dispose();
		}
	}
}