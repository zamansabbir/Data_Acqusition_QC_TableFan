package com.sabbir.tableFan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;
import org.freixas.jcalendar.JCalendar;
import org.freixas.jcalendar.JCalendarCombo;

public class TestInterface implements ActionListener {
	private JTextField tFDateOfManufacture, 
			tFBrand, 
			tFType, 
			tFModelNumber, 
			tFBlade,
			tFSize,
			tFWeight,
			tFSwitch,
			tFColor,
			tFBodyMaterial,
			tFDateOfTest; 
	private JTextField[] tFNonStatic=
			{tFDateOfManufacture, //0
			tFBrand, //1
			tFType, //2
			tFModelNumber,//3 
			tFBlade,//4
			tFSize,//5
			tFWeight,//6
			tFSwitch,//7
			tFColor,//8
			tFBodyMaterial,//9
			tFDateOfTest};//10
	public static JTextField 
			tFVoltage, //0
			tFCurrent, //1
			tFPower, //2
			tFPowerFactor,//3
			tFSound, //4
			tFSpeed, //5
			tFBaseTemperature, //6
			tFDCVoltage,//7
			tFDCCurrent,//8
			tFDCPower,//9
			tFAirFlow;//10
	
	public static JTextField[]tFStatic=
			{tFVoltage, //0
			tFCurrent, //1
			tFPower, //2
			tFPowerFactor,//3
			tFSound, //4
			tFSpeed, //5
			tFBaseTemperature,//6
			tFDCVoltage,//7
			tFDCCurrent,//8
			tFDCPower,//9
			tFAirFlow};//10
	
	private JLabel 
			lbDateOfManufacture, //0
			lbBrand, //1
			lbType, //2
			lbModelNumber,//3 
			lbBlade,//4
			lbSize,//5
			lbWeight,//6
			lbSwitch,//7
			lbColor,//8
			lbBodyMaterial,//9
			lbDateOfTest,//10
			lbVoltage, //11
			lbCurrent, //12
			lbPower, //13
			lbPowerFactor,//14
			lbSound, //15
			lbSpeed, //16
			lbBaseTemperature,//17
			lbDCVoltage,//18
			lbDCCurrent,//19
			lbDCPower,//20
			lbAirFlow;//21
	
	private JButton btnAddBrand,btnAddModel,btnUpdate;
	private JComboBox<Integer> cmBoxBrand;
	private JComboBox<String> cmBoxModel;
	private JCalendarCombo calendar;
    private GridBagConstraints gbc;
  //  private Calendar c;
    private Random random;
    private String manufacturingDate,modelNumber,testDate;
    private UpdateorQuery uOrQ;
    private int brandId;
    private JPanel leftBasePanel;
    private JRadioButton rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8,rb9;
    private JRadioButton[] radioBtn={rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8,rb9};
    private Double position0=0.0,position1=0.0,position2=0.0,position3=0.0,position4=0.0,position_1=0.0,position_2=0.0,position_3=0.0,position_4=0.0;
   
    public TestInterface()
    {
        gbc = new GridBagConstraints();
        random = new Random();
        uOrQ=new UpdateorQuery();
        displayGUI();
        
    }


    private void displayGUI()
    {
    	JFrame frame= new JFrame("Table Fan");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //0. Creating contentPane as the MainPanel
        JPanel contentPane = getPanel();
        contentPane.setLayout(new BorderLayout(5, 5));
        

        JLabel[] lbl={
        		lbDateOfManufacture, //0
        		lbBrand, //1
        		lbType, //2
        		lbModelNumber,//3 
        		lbBlade,//4
        		lbSize,//5
        		lbWeight,//6
        		lbSwitch,//7
        		lbColor,//8
        		lbBodyMaterial,//9
        		lbDateOfTest,//10
        		lbVoltage, //11
        		lbCurrent, //12
        		lbPower, //13
        		lbPowerFactor,//14
        		lbSound, //15
        		lbSpeed, //16
        		lbBaseTemperature, //17
        		lbDCVoltage,//18
    			lbDCCurrent,//19
    			lbDCPower,//20
    			lbAirFlow//21
        		
        };
        
        String[] lblName={
        		"Date Of Manufacture", //0
        		"Brand", //1
        		"Type", //2
        		"Model Number",//3 
        		"Blade",//4
        		"Size",//5
        		"Weight",//6
        		"Switch",//7
        		"Color",//8
        		"Body Material",//9
        		"Date Of Test",//10
        		"RMS Voltage", //11
        		"RMS Current", //12
        		"Power", //13
        		"Power Factor",//14
        		"Sound", //15
        		"Speed", //16
        		"BaseTemperature", //17
        		"DC Voltage",//18
    			"DC Current",//19
    			"DC Power",//20
    			"AirFlow"//21
        };
        
        //1.Creating single Panel for North Region of contentPane
        
        JPanel topPanel=getPanel();
        topPanel.setLayout(new GridLayout());
        ArrayList<Object> rows=uOrQ.Query("SELECT COUNT(*) AS ROWS FROM REPORT;", 2);
		
       // JLabel lblTestID=new JLabel("Test ID: "+(Integer)rows.get(0)+1,JLabel.CENTER);
        
       // topPanel.add(lblTestID);

        contentPane.add(topPanel, BorderLayout.NORTH);
        //1.Done
        
        //2.Creating basePanel for Center region of contentPane 
        JPanel basePanel = getPanel();
        basePanel.setLayout(new GridLayout(1, 2));
       // basePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),"Test ID: "+(Integer)rows.get(0)+1));
        basePanel.setBorder(getBorderWithTitle("Test ID: "+((Integer)rows.get(0)+1),TitledBorder.CENTER));
        //2a.Creating leftBasePanel for the left portion of base panel(2)
        
        		leftBasePanel=getPanel();
        		leftBasePanel.setLayout(new GridLayout(1,1));
        		leftBasePanel.setBorder(getBorderWithTitle("Description",TitledBorder.LEFT));
        		
        			JPanel topLeftBasePanel=getPanel();
        			topLeftBasePanel.setLayout(new GridBagLayout());
        			
        			lbl[0]=new JLabel(lblName[0]); 
        			topLeftBasePanel.add(lbl[0],getConstraints(0, 0, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
        			//tFNonStatic[0]=new JTextField(20);        			
        			//topLeftBasePanel.add(tFNonStatic[0],getConstraints(1, 0, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
        			calendar=new JCalendarCombo(JCalendar.DISPLAY_DATE,false);
        			topLeftBasePanel.add(calendar,getConstraints(1, 0, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
        			calendar.addDateListener(new DateListener(){
        				
        				public void dateChanged(DateEvent e){

        				
        					Calendar c=e.getSelectedDate();
        					
        					setManufacturingDate(c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR));
        					//System.out.println( getManufacturingDate());
        				}
        			});
        			
        			
        			ArrayList<Integer> z=uOrQ.shellSorting(uOrQ.Query("SELECT BRANDID FROM BRAND;", 3));
        			Vector<Integer> vectorBrand=new Vector<Integer>();
        			for(int x:z)
        			{
        				vectorBrand.add(x);
        			}
        			for(int i=1;i<10;i++)
        			{
        				
        				lbl[i]=new JLabel(lblName[i]);
        				topLeftBasePanel.add(lbl[i], getConstraints(0, i, 1, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
        				if(i==1)
            			{
            				cmBoxBrand=new JComboBox<Integer>(vectorBrand);
            				cmBoxBrand.addActionListener(this);
            				cmBoxBrand.setForeground(Color.BLACK);
            				cmBoxBrand.setMaximumRowCount(3);
            				topLeftBasePanel.add(cmBoxBrand, getConstraints(1, i, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
            				
            			}else if(i==3)
            			{
            				cmBoxModel=new JComboBox<String>();
            				cmBoxModel.addActionListener(this);
            				cmBoxModel.setMaximumRowCount(3);
            				topLeftBasePanel.add(cmBoxModel, getConstraints(1, i, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
            				
            			}
            			else{
            				tFNonStatic[i]=new JTextField(20);
            				if(i==2||i==8)
            				{
            					tFNonStatic[i].setBackground(Color.gray);
            					tFNonStatic[i].setForeground(Color.WHITE);
            					tFNonStatic[i].setText("Enter Data..");
            				}
            				else
            				tFNonStatic[i].setEditable(false);
            				topLeftBasePanel.add(tFNonStatic[i], getConstraints(1, i, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
            			}
        				
        				       				
        			}
        			
        	
        			 btnAddBrand=getButton("Add Brand");
        			 btnAddBrand.addActionListener(this);
        			// btnAddBrand.setText("Add Brand");
        			 btnAddModel=getButton("Add Model");
        			 btnAddModel.addActionListener(this);
        			// btnAddModel.setText("Add Model");
        			
        			 topLeftBasePanel.add(btnAddBrand,getConstraints(0, 10, 1, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
        			 topLeftBasePanel.add(btnAddModel,getConstraints(1, 10, 1, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
            	
        			leftBasePanel.add(topLeftBasePanel);
        		
        
        	//2b.Creating rightBasePanel for the right portion of base panel(2)
        		JPanel rightBasePanel=getPanel();
            	rightBasePanel.setLayout(new GridLayout(1,1));
            	rightBasePanel.setBorder(getBorderWithTitle("Results",TitledBorder.RIGHT));
        			
        				JPanel topRightBasePanel=new JPanel();
        				Float x1=random.nextFloat(),y1=random.nextFloat(),z1=random.nextFloat();
        				topRightBasePanel.setBackground(new Color(x1,y1,z1));
        				topRightBasePanel.setLayout(new GridBagLayout());
        				//topRightBasePanel.setBorder(BorderFactory.createEmptyBorder(2, 2,0, 2));
        				lbl[10]=new JLabel(lblName[10]);
        				tFNonStatic[10]=new JTextField(20);//Test Date
        				tFNonStatic[10].setEditable(false);
        				DateFormat dF=new SimpleDateFormat("EEEEE, MMMMM d, yyyy");
        				
        				Date dt=new Date();
        				
        				tFNonStatic[10].setText(dF.format(dt));
        				
        				
        				tFNonStatic[10].setFont(new Font("DEFAULT",Font.BOLD,12));
        				topRightBasePanel.add(lbl[10],getConstraints(0, 0, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
        				topRightBasePanel.add(tFNonStatic[10],getConstraints(1, 0, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
        				
        				
        				for(int i=0;i<11;i++)
            			{
            				
            				lbl[i+11]=new JLabel(lblName[i+11]);
            				topRightBasePanel.add(lbl[i+11], getConstraints(0, i+1, 1, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
            					
                				tFStatic[i]=new JTextField();
                				if(i>6&&i<10)
            					{
                					tFStatic[i].setBackground(Color.gray);
                					tFStatic[i].setText("Enter Data..");
                					tFStatic[i].setForeground(Color.white);
                					tFStatic[i].setEditable(true);
            					}
                				else
                				{
                					
                					
                					tFStatic[i].setEditable(false);
                				}
                					
                				topRightBasePanel.add(tFStatic[i], getConstraints(1, i+1, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
                		}
        			
        				JPanel radioPanel=new JPanel();
        		        radioPanel.setOpaque(true);
        		        radioPanel.setBackground(new Color(x1,y1,z1));
        		        radioPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));        	
        				radioPanel.setLayout(new FlowLayout());
        				ButtonGroup bg=new ButtonGroup();
        				
        				for(int i=-4;i<5;i++)
        				{
        					radioBtn[i+4]=new JRadioButton(i+"");
        					radioBtn[i+4].setBackground(new Color(x1,y1,z1));
        					radioBtn[i+4].addActionListener(this);
        					bg.add(radioBtn[i+4]);
        					radioPanel.add(radioBtn[i+4]);
        				}
        				
        				topRightBasePanel.add(radioPanel,getConstraints(1, 12, 1, 1 , GridBagConstraints.HORIZONTAL, 0.01f, 0.01f));
        				btnUpdate=getButton("Update");
        				btnUpdate.addActionListener(this);
        				//btnUpdate.setText("Update");
        				topRightBasePanel.add(btnUpdate,getConstraints(0, 13, 3, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
        				
        			
            			rightBasePanel.add(topRightBasePanel);
        	//2b.Done
        	basePanel.add(leftBasePanel);
        	basePanel.add(rightBasePanel);
        //2.Done
        			
        contentPane.add(basePanel,BorderLayout.CENTER);
        JLabel bottomLabel = new JLabel("Developed by R&D Automation, Walton", JLabel.CENTER);
        contentPane.add(bottomLabel, BorderLayout.PAGE_END);
        //0.Done
        
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        
        
        
        
    }

    private JPanel getPanel()
    {
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(new Color(random.nextFloat() , random.nextFloat(), random.nextFloat(), random.nextFloat()));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        return panel;
    }
    //Set Button Property
    private JButton getButton(String text)
    {
    	JButton btn=new JButton(text);
    	btn.setOpaque(true);
    	btn.setBackground(new Color(random.nextFloat(),random.nextFloat(),random.nextFloat()));
    	btn.setForeground(Color.WHITE);
    	btn.setFont(new Font("Calibri",Font.ITALIC,15));
    	btn.setFocusPainted(true);
    	
    	return btn;
    }
    //set Border
    private TitledBorder getBorderWithTitle(String titleString,int titlePosition)
    {
    	TitledBorder title;
    	title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),titleString);
    	title.setTitleJustification(titlePosition);
    	return title;
    }

    private GridBagConstraints getConstraints(int x, int y, int w, int h, int fillValue, float weightx, float weighty)
    {
        //GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = fillValue;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.weightx = weightx;
        gbc.weighty = weighty;

        return gbc;
    }
    
    //Delete after integration and testing
  //  public static void main(String[] args){
    //	new TestInterface();
    	//T.start();
    	//Thread A=new Thread(new AirFlowInterface());
    	//Thread A=new Thread(new SoundInterface());
    	//A.start();
    	//tFStatic[5].setText("Haha");
    	//Thread A=new Thread(new PowerAnalyzerInterface());
    	//A.start();
    	//BaseTemperatureInterface x=new BaseTemperatureInterface();
    	//Thread A=new Thread(new BaseTemperatureInterface());
    	//A.start();
    		//Thread speedThread=new Thread(new SpeedInterface());
    		//speedThread.start();
    	//Thread powerAnalyzerThread=new Thread(new PowerAnalyzerInterface());
    //	powerAnalyzerThread.start();
    	
    	
   // }
    

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object sourceObject=e.getSource();
		if(sourceObject instanceof JButton)
		{
			JButton button=(JButton)sourceObject;
			if(button==btnAddBrand)
			{
				new BrandAddInterface();
			}
			else if(button==btnAddModel)
			{
				new ModelAddInterface();
			}
			else if(button==btnUpdate)
			{
				DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
				testDate=df.format(new Date());
				//System.out.println(testDate);
				if(manufacturingDate==null)
				{
					//remember to edit
					//System.out.println("testDate=manufacturingdate");
					manufacturingDate=testDate;
				}
				
				String colour=tFNonStatic[8].getText(),type=tFNonStatic[2].getText();
				Double dcVoltage=findDouble(tFStatic[7].getText()),dcCurrent=findDouble(tFStatic[8].getText()),dcPower=findDouble(tFStatic[9].getText());
				
				ArrayList<Integer> z=uOrQ.shellSorting(uOrQ.Query("SELECT RID FROM REPORT;", 7));
				if(z.size()>0){
				//p=uOrQ.shellSorting(p);
					int missingModelId=uOrQ.findMissing(z);
					//System.out.println(missingModelId);
					if(missingModelId==0)//i.e. nothing missing found 
					{
						String k="INSERT INTO REPORT(RID,MANUFACTURINGDATE,TESTDATE,MODELNUMBER,TYPE,RMSVOLTAGE,RMSCURRENT,POWER,POWERFACTOR,SOUND,SPEED,AIRFLOW,TEMPERATURE,COLOUR,DCVOLTAGE,DCCURRENT,DCPOWER) "+"VALUES("+(Collections.max(z)+1)+",'"+manufacturingDate+"','"+testDate+"','"+modelNumber+"','"+type+"',"+PowerAnalyzerInterface.voltage+","+PowerAnalyzerInterface.current+","+PowerAnalyzerInterface.power+","+PowerAnalyzerInterface.pf+","+SoundInterface.sound+","+SpeedInterface.speed+","+AirFlowInterface.airFlow+","+BaseTemperatureInterface.temperature+",'"+colour+"',"+dcVoltage+","+dcCurrent+","+dcPower+");";
						uOrQ.updateDatabase(k);
						k="INSERT INTO AIRFLOW(RID,POSITION_4,POSITION_3,POSITION_2,POSITION_1,POSITION0,POSITION1,POSITION2,POSITION3,POSITION4)"+"VALUES("+(Collections.max(z)+1)+","+position_4+","+position_3+","+position_2+","+position_1+","+position0+","+position1+","+position2+","+position3+","+position4+");";
						//System.out.println(k);
						uOrQ.updateDatabase(k);
					}
						else//missing found
					{
							String k="INSERT INTO REPORT(RID,MANUFACTURINGDATE,TESTDATE,MODELNUMBER,RMSVOLTAGE,RMSCURRENT,POWER,POWERFACTOR,SOUND,SPEED,AIRFLOW,TEMPERATURE,COLOUR,DCVOLTAGE,DCCURRENT,DCPOWER) "+"VALUES("+missingModelId+",'"+manufacturingDate+"','"+testDate+"','"+modelNumber+"','"+type+"',"+PowerAnalyzerInterface.voltage+","+PowerAnalyzerInterface.current+","+PowerAnalyzerInterface.power+","+PowerAnalyzerInterface.pf+","+SoundInterface.sound+","+SpeedInterface.speed+","+AirFlowInterface.airFlow+","+BaseTemperatureInterface.temperature+",'"+colour+"',"+dcVoltage+","+dcCurrent+","+dcPower+");";
							uOrQ.updateDatabase(k);
							k="INSERT INTO AIRFLOW(RID,POSITION_4,POSITION_3,POSITION_2,POSITION_1,POSITION0,POSITION1,POSITION2,POSITION3,POSITION4)"+"VALUES("+missingModelId+","+position_4+","+position_3+","+position_2+","+position_1+","+position0+","+position1+","+position2+","+position3+","+position4+");";
							uOrQ.updateDatabase(k);
					}
				}
				else {//for the first time entry
						
						String k="INSERT INTO REPORT(RID,MANUFACTURINGDATE,TESTDATE,MODELNUMBER,RMSVOLTAGE,RMSCURRENT,POWER,POWERFACTOR,SOUND,SPEED,AIRFLOW,TEMPERATURE,COLOUR,DCVOLTAGE,DCCURRENT,DCPOWER) "+"VALUES("+1+",'"+manufacturingDate+"','"+testDate+"','"+modelNumber+"','"+type+"',"+PowerAnalyzerInterface.voltage+","+PowerAnalyzerInterface.current+","+PowerAnalyzerInterface.power+","+PowerAnalyzerInterface.pf+","+SoundInterface.sound+","+SpeedInterface.speed+","+AirFlowInterface.airFlow+","+BaseTemperatureInterface.temperature+",'"+colour+"',"+dcVoltage+","+dcCurrent+","+dcPower+");";
						uOrQ.updateDatabase(k);
					
					//System.out.println(k);
						k="INSERT INTO AIRFLOW(RID,POSITION_4,POSITION_3,POSITION_2,POSITION_1,POSITION0,POSITION1,POSITION2,POSITION3,POSITION4)"+"VALUES("+1+","+position_4+","+position_3+","+position_2+","+position_1+","+position0+","+position1+","+position2+","+position3+","+position4+");";
						uOrQ.updateDatabase(k);
					
					
					}
			}
		}
	else if(sourceObject instanceof JComboBox)
		{
			JComboBox<Object> comboBox=(JComboBox<Object>)sourceObject;
			if(comboBox==(Object)cmBoxBrand)
			{
				//populate Model according the action taken by selected by brand Id
				
				brandId=(Integer)cmBoxBrand.getSelectedItem();
				cmBoxModel.removeAllItems();
				leftBasePanel.repaint();
				//leftBasePanel.revalidate();
				ArrayList<Object> modelNumberArray=uOrQ.Query("SELECT MODELNUMBER FROM MODEL WHERE BRANDID="+brandId+";", 8);
				//Vector<String> modelVector=new Vector<String>();
				cmBoxModel.addItem(" ");
				for(Object x:modelNumberArray)
				{
					//modelVector.add((String)x);
					cmBoxModel.addItem((String)x);
				}
				//cmBoxModel.addItem(modelVector);
				//modelId=cmBoxModel.getItemAt(0)
			}
			else if(comboBox==(Object)cmBoxModel)
			{
				modelNumber=(String)cmBoxModel.getSelectedItem();
				leftBasePanel.repaint();
				
					ArrayList<Object> modelDetailArray=uOrQ.Query("SELECT * FROM MODEL WHERE MODELNUMBER='"+modelNumber+"';",6);
																	//0   //1         //2   //3  //4     //5    //6
					//System.out.println("SELECT * FROM MODEL WHERE MODELNUMBER='"+modelNumber+"';");
					if(modelDetailArray.size()>0)
					{
						tFNonStatic[4].setText(String.valueOf(modelDetailArray.get(2)));
						tFNonStatic[5].setText(String.valueOf(modelDetailArray.get(3)));
						tFNonStatic[6].setText(String.valueOf(modelDetailArray.get(4)));
						tFNonStatic[7].setText(String.valueOf(modelDetailArray.get(5)));
						tFNonStatic[9].setText(String.valueOf(modelDetailArray.get(6)));
					}								
			}
		}
	else if(sourceObject instanceof JRadioButton)
	{
		JRadioButton rBtn=(JRadioButton) sourceObject;
		if(rBtn==radioBtn[0])
		{
			//System.out.println("-4");
			position_4=AirFlowInterface.airFlow;
			
		}
		else if(rBtn==radioBtn[1])
		{
			//System.out.println("-3");
			position_3=AirFlowInterface.airFlow;
		}
		else if(rBtn==radioBtn[2])
		{
			//System.out.println("-2");
			position_2=AirFlowInterface.airFlow;
		}
		else if(rBtn==radioBtn[3])
		{
			//System.out.println("-1");
			position_1=AirFlowInterface.airFlow;
		}
		else if(rBtn==radioBtn[4])
		{
			//System.out.println("0");
			position0=AirFlowInterface.airFlow;
		}
		else if(rBtn==radioBtn[5])
		{
			//System.out.println("1");
			position1=AirFlowInterface.airFlow;
		}
		else if(rBtn==radioBtn[6])
		{
			//System.out.println("2");
			position2=AirFlowInterface.airFlow;
		}
		else if(rBtn==radioBtn[7])
		{
			//System.out.println("3");
			position3=AirFlowInterface.airFlow;
		}
		else if(rBtn==radioBtn[8])
		{
			//System.out.println("4");
			position4=AirFlowInterface.airFlow;
		}
	}
	}

	
	private void setManufacturingDate(String manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
		
	}


	public String getManufacturingDate() {
		return manufacturingDate;
	}
	
	public Double findDouble(String x)
	{
		//System.out.println(x);
		Double d=0.0;
		String[] xx=x.split("[vVaAwW]");
		try{
			d=Double.valueOf(xx[0]);
		}
		catch(NumberFormatException ex)
		{
			JFrame f1=new JFrame();
			String errorMessage=x+" is not a valid: 123.4V or 123.4 A or 123.4w";
			JOptionPane.showMessageDialog(f1, errorMessage,"Exception",JOptionPane.ERROR_MESSAGE);
			f1.dispose();
		}
		return d;
	}
	
	
	
	
  
}