package com.sabbir.tableFan;


import java.math.BigInteger;
import java.util.ArrayList;
import jssc.*;
public class BaseTemperatureInterface implements Runnable{
	public static SerialPort serialPort;
	private static String functionCode="";
	//public static String p;
	public   static Double temperature=0.0;
	public void run() {
		//serialPort = new SerialPort(p); 
		serialPort = new SerialPort("COM6"); 
        try {
            serialPort.openPort();//Open port
            serialPort.setParams(9600, 8, 1, 0);//Set params
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            serialPort.setEventsMask(mask);//Set mask
            serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
            serialPort.writeBytes(readRequest());
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }

		
	}
	
	private static byte[] readRequest(){
		//Reading purpose variables
		String slaveAddress="01",
		functionCode="04",
		startingAddressHigh="00",
		startingAddressLow="0A",
		quantityOfRegistersHigh="00",
		quantityOfRegistersLow="01";
		//functionCode="04";
	    String requestData=slaveAddress+functionCode+startingAddressHigh+startingAddressLow+quantityOfRegistersHigh+quantityOfRegistersLow;
	    String f=requestData+CRC16Calculation.findCRC16(requestData);
	    //System.out.println(f);
		byte[] s=new BigInteger(f,16).toByteArray();
		return s;
		}


	private static void readResponse(String response){ //processing response message
		String slaveAddress="", functionCode="", byteCount="";
		if(response.length()%2==0)
		{
			//checking if the slave address is of even number of digits, if yes then no manipulation necessary
		}
		else{
			response="0"+response;
			//if yes then no manipulation necessary, adding a zero at the front makes the slave address's digits even
		}
		
		for(int i=0;i<6;i+=2)//separating slave address,function code and byte count from the response
		{
			switch (i)
			{
			case 0: 
				slaveAddress=response.substring(i,i+2);//separating the slave address from response message
				break;
			case 2:
				functionCode=response.substring(i,i+2);//separating the function code from response message
				break;
			case 4:
				byteCount=response.substring(i,i+2); //separating the byte count from response message
				break;
			default :
					break;
			}
		}
		
		int count=Integer.parseInt(byteCount,16); //converting hexadecimal to its decimal form 
		//System.out.println(count);
		ArrayList<String> data=new ArrayList<String>();
		
		response=response.substring(6,response.length()-4);//collecting only data portion from response message
		//System.out.println(response);
		
		if(functionCode.equals("01"))//coil status 
		{
			for(int i=0;i<(response.length());i+=2)
			{
				data.add(response.substring(i, i+2));
				//System.out.println(Integer.parseInt(String.valueOf(data.get(i)),16));
			}
			System.out.println("Response From Slave "+slaveAddress);
			for(int i=0;i<count;i++)
			{
				
				//System.out.println(i+" Register Value:"+Integer.parseInt(String.valueOf(data.get(i)),16));
				System.out.println(i+" Eight Bit starting from: "+Integer.toBinaryString(Integer.parseInt(String.valueOf(data.get(i)),16)));
			}
		}
		else//analog value
		{
			for(int i=0;i<(response.length());i+=4)
			{
				data.add(response.substring(i, i+4));
				//System.out.println(Integer.parseInt(String.valueOf(data.get(i)),16));
			}
			//System.out.println("Response From Slave "+slaveAddress);
			for(int i=0;i<count/2;i++)
			{
				
				//System.out.println(i+" Register Value:"+((Integer.parseInt(String.valueOf(data.get(i)),16))-2732));
				temperature=(((Integer.parseInt(String.valueOf(data.get(i)),16))-2732)/10.0);
				TestInterface.tFStatic[6].setText(temperature.toString());
			}
		}
	}
	
	
	static class SerialPortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
        	if (event.isRXCHAR() && event.getEventValue() > 0) {//If data is available
                //int bytesCount = event.getEventValue();
                try
                {
                	try {
						Thread.sleep(25);
					} catch (InterruptedException e) {

					}
					byte[] b=serialPort.readBytes();
	            	//String bI=port.readHexString();
	            	String bI=new BigInteger(b).toString(16);
	            	//System.out.println(bI);
	            	if(functionCode.equals("05")||functionCode.equals("06")||functionCode.equals("0F"))
	            		{
	            		
	            		}
	            	else
	            	{
	            		readResponse(bI);
	            		 try{
	                     	Thread.sleep(300);
	                     	 serialPort.writeBytes(readRequest());
	                     }
	                     catch(Exception ex)
	                     {
	                     	ex.printStackTrace();
	                     }
	            	}
					 
						
                }catch (SerialPortException e)
                {
                	e.printStackTrace();
                }
                
               
                }
        }
}
}