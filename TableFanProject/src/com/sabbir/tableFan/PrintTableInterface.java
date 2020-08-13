package com.sabbir.tableFan;



import java.awt.*;

import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Arrays;
import java.util.Collections;
import java.awt.print.*;	

public class PrintTableInterface implements Printable{

	
    //private JButton btnPrint;
	  

	   //private GridBagConstraints gbc;

	   // private Random random;
	    private String data1[],data2[];
	    private Double data3[];
	    private int id;
	    public PrintTableInterface(String [] dataA, String[] dataB,Double[] dataC,int testId)
	    {
	    	//String [] dataA, String[] dataB
	    	//
	        data1=dataA;
	        data2=dataB;
	    	data3=dataC;
	    	id=testId;
	    }


	    public void printData()
	    {
	       
	        PrinterJob job = PrinterJob.getPrinterJob();
	         job.setPrintable(this);
	         boolean ok = job.printDialog();
	         if (ok) {
	             try {
	                  job.print();
	             } catch (PrinterException ex) {
	            	 //System.out.println(ex.getMessage());
	             }
	         }
	       
	    }

	   

		@Override
	
		 public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

			 	if (page > 0) 
			 	{ 
			 		return NO_SUCH_PAGE;
			 	}


			 Graphics2D g2d = (Graphics2D)g;
			 
			
			 g2d.translate(pf.getImageableX(), pf.getImageableY());
			 
			 g2d.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,12));
			
			
			 g2d.drawString("Walton Micro-Tec Industries Ltd.",220,72);
			 g2d.drawString("Chandra, Kaliakoir,Gazipur", 235, 84);
			 g2d.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,12));
			 g2d.drawString("Dept. Air Handling Appliance(Fan Project)", 203, 96);
			 g2d.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,12));
			 g2d.drawString("Test ID:"+id, 270, 96+24);//should be x-12,testId
			 g2d.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,11));
			 g2d.drawString("Description", 72, 144);
			 g2d.drawString("Results", 524-72, 144);
			 g2d.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,11));
			
			 String[] title={"Date of Manufacture","Brand","Type","Model Number","Blade","Size","Weight","Switch","Color","Body Material"};
			 int gapy=18;
			 for(int i=0;i<11;i++)
			 {
				 g2d.drawLine(72, 168+(i*gapy), 262, 168+(i*gapy));
				 if(i<title.length){
					 g2d.drawString(title[i], 75, 179+(i*gapy));
					 g2d.drawString(data1[i],172,179+(i*gapy));
				 }
				 
			 }
			 g2d.drawLine(72,144+24,72,348);//2			
			 g2d.drawLine(72+190,144+24,72+190,348);//4
			 g2d.drawLine(72+95,144+24,72+95,348);//5
			 
			 String[] title1={"Date of Test","RMS Voltage","RMS Current","RMS Power","Power Factor","DC Voltage","DC Current","DC Power","Speed","Base Temperature"};
			 for(int i=0;i<11;i++)
			 {
				 g2d.drawLine(310, 168+(i*gapy), 500, 168+(i*gapy));
				 if(i<title1.length)
				 {
					 g2d.drawString(title1[i], 313, 179+(i*gapy));
					 g2d.drawString(data2[i], 410, 179+(i*gapy));
				 }
				 
			 }
			 
			 g2d.drawLine(310,144+24,310,348);//2			
			 g2d.drawLine(310+190,144+24,310+190,348);//4
			 g2d.drawLine(310+95,144+24,310+95,348);//5
			 
			 
			 //Drawing Bar Graph for airFlow
			 g2d.drawString("Air Flow: ",110,396);
			 int Y,baseX=144,scaledHeight,baseY=650;
			 g2d.drawLine(144,650,452,650);//baseLine or X axis
			// Double[] airFlow={120.7,134.5,110.4,112.5,130.0,35.7,89.9,98.8,100.0};
			 Double maxAirFlow=Collections.max(Arrays.asList(data3));
			 g2d.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,8));
			 for(int i=1;i<10;i++)
			 {
				 scaledHeight=(int) ((int)266*(data3[i-1]/maxAirFlow));
				 baseX=baseX+20;
				 Y=baseY-scaledHeight;
				 g2d.drawString(""+(i-5), baseX+6, baseY+9);//position
				 g2d.drawLine(baseX,baseY,baseX,Y);
				 
				 g2d.drawString(""+data3[i-1], baseX+6, Y-12);
				 g2d.drawLine(baseX,Y,baseX+12,Y);
				 baseX=baseX+12;
				 g2d.drawLine(baseX,Y,baseX,baseY);
			 }
			 g2d.drawString("Position", 110, baseY+9);
			 
			 g2d.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,11));
			 g2d.drawString("Experiment By", 72, 720);
			 g2d.drawLine(72,708,144,708);
			 g2d.drawString("Approved By", 452, 720);
			 g2d.drawLine(452,708,520,708);
			 g2d.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,8));
			 g2d.drawString("*N.B:All data, except DC Voltage, Current and Power are acquired automatically ", 72, 770);
			 return PAGE_EXISTS;
		 }
			
	   
}
