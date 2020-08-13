package com.sabbir.tableFan;


import jssc.*;
public class SoundInterface implements Runnable{
	private static SerialPort serialPort;
	//public static String p;
	public   static Double sound=0.0;
	public void run() {
		//serialPort = new SerialPort(p); 
		serialPort = new SerialPort("COM7"); 
        try {
            serialPort.openPort();//Open port
            serialPort.setParams(9600, 8, 1, 0);//Set params
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            serialPort.setEventsMask(mask);//Set mask
            serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
       

		
	}
	public static void processSound(byte[] data){
 	  // String soundLevelUnit="dB";
 	   //int rowData=(new Integer(data[7]-48)*10^7)+(new Integer(data[8]-48)*10^6);
 	  double rowData=0;
 	   for(int digits=7;digits<=14;digits++)
 	   {
 		   rowData+=((new Integer(data[digits]-48))*Math.pow(10,(14-digits)));
 	   }
 	   if((data[5]-48)>0)
 		   {
 		   	rowData*=(-1);
 		   }
 	   switch((data[6]-48))
 	   {
 	   case 0:
 		   sound=rowData;
 		   System.out.println(rowData);
 		   TestInterface.tFStatic[4].setText(rowData+ " dB");
 		   break;
 	   case 1:
 		  sound=rowData*0.1;
 		  System.out.println(rowData*0.1);
 		 TestInterface.tFStatic[4].setText(rowData*0.1+ " dB");
 		   break;
 	   case 2:
 		  sound=rowData*0.01;
 		  System.out.println(rowData*0.01);
 		 TestInterface.tFStatic[4].setText(rowData*0.01+ " dB");
 		   break;
 	   case 3:
 		  sound=rowData*0.001;
 		  System.out.println(rowData*0.001);
 		 TestInterface.tFStatic[4].setText(rowData*0.001+ " dB");
 		   break;
 	   }
 	   
    }
	static class SerialPortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
        	if (event.isRXCHAR() && event.getEventValue() > 0) {//If data is available
                //int bytesCount = event.getEventValue();
                try
                {
                	try {
						Thread.sleep(200);
					} catch (InterruptedException e) {

					}
                	//String f=serialPort.readString(bytesCount);
                	//f=serialPort.readString();
					//AddWeightInterface.tFGrossWeight.setText(f);
					 processSound(serialPort.readBytes());
						
                }catch (SerialPortException e)
                {
                	;
                }
                }
        }
}
}
