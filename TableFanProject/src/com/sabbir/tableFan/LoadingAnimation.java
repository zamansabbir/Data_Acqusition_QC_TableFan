package com.sabbir.tableFan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
public class LoadingAnimation {

	//Animation triggered by timer will eventually launch Home Interface
	
	public static void showAnimation(){
		ImageIcon icon = new ImageIcon(LoadingAnimation.class.getResource("loadingautomation.gif"));
	    JLabel label=new JLabel(icon);
	    JWindow myJFrame=new JWindow();
	    myJFrame.setLayout(new BorderLayout());
	    myJFrame.add(label,BorderLayout.CENTER);
	    //myJFrame.setLocationRelativeTo(null);
	    Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	    myJFrame.setLocation(d.width/2-54,d.height/2-54);

	   //myJFrame.setOpacity(0.7f);
	   
	    myJFrame.setAlwaysOnTop(true);
	    label.setOpaque(false);
	    myJFrame.setBackground(new Color(0,0,0,0));
	    myJFrame.pack();
	    myJFrame.setVisible(true);
	   
	    try {
		   Thread.sleep(1*5*1000);
	   	} catch (InterruptedException e) {
		
	   }finally{
		   myJFrame.dispose();
	   }
		
	}
	
	public static void main(String[] args)
	{
		showAnimation();
		//System.out.println("Done");
		new LogInInterface().displayGui();
	}
	
}
