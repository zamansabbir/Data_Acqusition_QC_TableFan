package com.sabbir.tableFan;


import jssc.*;
public class SpeedInterface implements Runnable{
	private static SerialPort serialPort;
	//public static String p;
	public   static Double speed=0.0;
	public void run() {
		//serialPort = new SerialPort(p); 
		serialPort = new SerialPort("COM8"); 
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
	public static void processSpeedData(byte[] data)
	{
		//System.out.println(data);
		int decimalPoint=data[1],data1=data[8],data2=data[7],data3=data[6];
		//Double speedData;
		speed=(double) ((data1*65536+data2*256+data3*1)/(double)(Math.pow(10,decimalPoint)));
		//System.out.println(speed);
		TestInterface.tFStatic[5].setText(speed+"");
	}
	static class SerialPortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
        	if (event.isRXCHAR() && event.getEventValue() > 0) {//If data is available
                //int bytesCount = event.getEventValue();
                try
                {
                	try {
						Thread.sleep(100);
					} catch (InterruptedException e) {

					}
                	//String f=serialPort.readString(bytesCount);
                	processSpeedData(serialPort.readBytes());
					//AddWeightInterface.tFGrossWeight.setText(f);
					 
						
                }catch (SerialPortException e)
                {
                	;
                }
                }
        }
}
}