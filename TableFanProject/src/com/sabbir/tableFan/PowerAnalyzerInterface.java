package com.sabbir.tableFan;


import java.math.BigInteger;

import jssc.*;
public class PowerAnalyzerInterface implements Runnable{
	private static SerialPort serialPort;
	//public static String p;
	public static Double voltage=0.0,current=0.0,pf=0.0,freq=0.0,power=0.0 ;
	
	public void run() {
		//serialPort = new SerialPort(p); 
		//System.out.println("Voltage: "+voltage+" Current: "+current+" Power Factor: "+pf+" Frequency: "+freq+" Power: "+power);
		serialPort = new SerialPort("COM4"); 
        try {
            serialPort.openPort();//Open port
            serialPort.setParams(2400, 8, 1, 0);//Set params
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            serialPort.setEventsMask(mask);//Set mask
            serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
            serialPort.writeBytes(new BigInteger("EE",16).toByteArray());
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }

		
	}
	private static void processPowerAnalyzerData(byte[] data)
	{
		//System.out.println(new BigInteger(data).toString(16));
		 voltage=0.0;
		 current=0.0;
		 pf=0.0;
		 freq=0.0;
		 power=0.0;
		try{
			byte[] voltageData={data[4],data[3],data[2],data[1]};
			byte[] currentData={data[8],data[7],data[6],data[5]};
			byte[] powerFactorData={data[12],data[11],data[10],data[9]};
			byte[] frequencyData={data[16],data[15],data[14],data[13]};
			byte[] powerData={data[20],data[19],data[18],data[17]};
			voltage=calculateSinglePrecisionFloatingPointData(voltageData);
			current=calculateSinglePrecisionFloatingPointData(currentData);
			pf=calculateSinglePrecisionFloatingPointData(powerFactorData);
			freq=calculateSinglePrecisionFloatingPointData(frequencyData);
			power=calculateSinglePrecisionFloatingPointData(powerData);
			//System.out.println("Voltage: "+voltage+" Current: "+current+" Power Factor: "+pf+" Frequency: "+freq+" Power: "+power);
			TestInterface.tFStatic[0].setText(voltage+"V");
			TestInterface.tFStatic[1].setText(current+" A");
			TestInterface.tFStatic[2].setText(power+" W");
			TestInterface.tFStatic[3].setText(pf+" ");
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
		try{
			
			Thread.sleep(100);
			serialPort.writeBytes(new BigInteger("EE",16).toByteArray());
		}
		catch(Exception ex)
		{
			;
		}
		}
		
	}
	
	private static Double calculateSinglePrecisionFloatingPointData(byte[] row)
	{
		int sign;
		int exponent;
		String mantisa;
		Double actualData;
		byte rw=(byte) (row[0]&~(1<<8));
		byte[] mantisaData={rw,row[1],row[2]};
		if((((row[0]&0XFF)&1<<8))>0)
		{
			sign=-1;
			//System.out.println("Sign "+sign);
		}
		else{
			sign=1;
			//System.out.println(sign);
		}
		exponent=row[3]&0XFF;
		//System.out.println("Exponent "+exponent);
		mantisa=new BigInteger(mantisaData).toString(10);
		//System.out.println("Mantissa "+mantisa);
		actualData=(sign)*(0.5+(Double.valueOf(mantisa)/Math.pow(2,24)))*(Math.pow(2, exponent-127));
		//System.out.println(Math.round(actualData*10.0)/10.0);
		return Math.round(actualData*10000.00)/10000.00;
		//return actualData*100.00/100.00;
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
                	//f=serialPort.readString();
					//AddWeightInterface.tFGrossWeight.setText(f);
					byte[] data=serialPort.readBytes();
					if((data[0]==0X11)&&(data[21]==0X0D))
					 processPowerAnalyzerData(data);
					else
						serialPort.writeBytes(new BigInteger("EE",16).toByteArray());
					 try {
						Thread.sleep(500);
						serialPort.writeBytes(new BigInteger("EE",16).toByteArray());
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
						
                }catch (SerialPortException e)
                {
                	;
                }
                }
        }
}
}