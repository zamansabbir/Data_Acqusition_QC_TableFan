package com.sabbir.tableFan;


import jssc.*;
public class AirFlowInterface implements Runnable{
	private static SerialPort serialPort;
	//public static String p;
	public   static Double airFlow=0.0;
	public void run() {
		//serialPort = new SerialPort(p); 
		serialPort = new SerialPort("COM5"); 
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
	private static void processCmmData(byte[] data)
	{
		//System.out.println("Sabbir");
		int rowdata=new Integer(data[11])*1000+new Integer(data[12])*100+new Integer(data[13])*10+new Integer(data[14]);
		String velOrFlowUnit="";
		if(((data[3]&0XFF)&(1<<4))>0)
			{
				if(((data[4]&0XFF)&1<<0)>0)
				velOrFlowUnit=" CFM";
				else
				velOrFlowUnit=" CMM";	
			}
		else
			{
			switch(data[2]&0XFF)
			{
				case 0:
					velOrFlowUnit=" m/s";
					break;
				case 1:
					velOrFlowUnit=" ft/min";
					break;
				case 2:
					velOrFlowUnit=" knots";
					break;
				case 3:
					velOrFlowUnit=" km/hr";
					break;
				case 4:
					velOrFlowUnit=" MPH";
					break;
			}
				
			}
		switch(new Integer(data[5]))
		{
		case 1:
			//System.out.println(rowdata*100);
			airFlow=(double) (rowdata*100);
			TestInterface.tFStatic[10].setText((rowdata*100)+velOrFlowUnit);
			break;
		case 2:
			//System.out.println(rowdata*10);
			airFlow=(double) (rowdata*10);
			TestInterface.tFStatic[10].setText((rowdata*10)+velOrFlowUnit);
			break;
		case 4:
			//System.out.println(rowdata);
			airFlow=(double) (rowdata);
			TestInterface.tFStatic[10].setText((rowdata)+velOrFlowUnit);
			break;
		case 8:
			//System.out.println(rowdata*0.1);
			airFlow=(double) (rowdata*0.1);
			TestInterface.tFStatic[10].setText((rowdata*0.1)+velOrFlowUnit);
			break;
		case 16:
			//System.out.println(rowdata*0.01);
			airFlow=(double) (rowdata*0.01);
			TestInterface.tFStatic[10].setText((rowdata*0.01)+velOrFlowUnit);
			break;
		case 32:
			//System.out.println(rowdata*0.001);
			airFlow=(double) (rowdata*0.001);
			TestInterface.tFStatic[10].setText((rowdata*0.001)+velOrFlowUnit);
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
                	//System.out.println("Sabbir");;
                	
					processCmmData(serialPort.readBytes());
					//AddWeightInterface.tFGrossWeight.setText(f);
					 
						
                }catch (SerialPortException e)
                {
                	;
                }
                }
        }
}
}