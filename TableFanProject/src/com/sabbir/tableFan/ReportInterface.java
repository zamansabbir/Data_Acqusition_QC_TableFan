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
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ReportInterface implements ActionListener {
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
	public JTextField 
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
	
	public JTextField[]tFStatic=
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
		private JRadioButton rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8,rb9;
		private JRadioButton[] radioBtn={rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8,rb9};
		//private Double position0=0.0,position1=0.0,position2=0.0,position3=0.0,position4=0.0,position_1=0.0,position_2=0.0,position_3=0.0,position_4=0.0;
	
	private JButton btnAddBrand,btnAddModel,btnPrint;
	private JComboBox<Integer> cmBoxTestId;
	//private JComboBox<String> cmBoxModel;
	//private JCalendarCombo calendar;
    private GridBagConstraints gbc;
  //  private Calendar c;
    private Random random;
   // private String manufacturingDate,modelNumber,testDate;
    private UpdateorQuery uOrQ;
    //private int brandId;
    private JPanel leftBasePanel;
    ArrayList<Object> reportArray,modelArray,arrayAirFlow;
    public ReportInterface()
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
        basePanel.setBorder(getBorderWithTitle("Last Test ID: "+((Integer)rows.get(0)),TitledBorder.CENTER));
        //2a.Creating leftBasePanel for the left portion of base panel(2)
        
        		leftBasePanel=getPanel();
        		leftBasePanel.setLayout(new GridLayout(1,1));
        		leftBasePanel.setBorder(getBorderWithTitle("Description",TitledBorder.LEFT));
        		
        			JPanel topLeftBasePanel=getPanel();
        			topLeftBasePanel.setLayout(new GridBagLayout());
        			
        			lbl[0]=new JLabel("Test ID"); 
        			topLeftBasePanel.add(lbl[0],getConstraints(0, 0, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
        			//tFNonStatic[0]=new JTextField(20);        			
        			//topLeftBasePanel.add(tFNonStatic[0],getConstraints(1, 0, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
        			
        			ArrayList<Integer> testArray=uOrQ.shellSorting(uOrQ.Query("SELECT RID FROM REPORT;", 7));
        			Vector<Integer> z=new Vector<Integer>();
        			for(int x:testArray)
        			{
        				z.add(x);
        			}
        			cmBoxTestId=new JComboBox<Integer>(z);
        			cmBoxTestId.setMaximumRowCount(4);
        			cmBoxTestId.addActionListener(this);
        			//cmBoxTestId.setSelectedItem(z.get(0));
        			topLeftBasePanel.add(cmBoxTestId,getConstraints(1, 0, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
        			
        			for(int i=0;i<10;i++)
        			{
        				
        				lbl[i]=new JLabel(lblName[i]);
        				topLeftBasePanel.add(lbl[i], getConstraints(0, i+1, 1, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
        			
            			
            				tFNonStatic[i]=new JTextField(20);
            				tFNonStatic[i].setEditable(false);
            				topLeftBasePanel.add(tFNonStatic[i], getConstraints(1, i+1, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
            			
        				
        				       				
        			}
        			
        	
        			// btnAddBrand=getButton("Add Brand");
        			// btnAddBrand.addActionListener(this);
        			// btnAddBrand.setText("Add Brand");
        			// btnAddModel=getButton("Add Model");
        			// btnAddModel.addActionListener(this);
        			// btnAddModel.setText("Add Model");
        			
        			// topLeftBasePanel.add(btnAddBrand,getConstraints(0, 10, 1, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
        			 //topLeftBasePanel.add(btnAddModel,getConstraints(1, 10, 1, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
            	
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
        				tFNonStatic[10].setFont(new Font("DEFAULT",Font.BOLD,12));
        				topRightBasePanel.add(lbl[10],getConstraints(0, 0, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
        				topRightBasePanel.add(tFNonStatic[10],getConstraints(1, 0, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
        				
        				
        				for(int i=0;i<11;i++)
            			{
            				
            				lbl[i+11]=new JLabel(lblName[i+11]);
            				topRightBasePanel.add(lbl[i+11], getConstraints(0, i+1, 1, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
            					
                				tFStatic[i]=new JTextField();                				
                				tFStatic[i].setEditable(false);
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
        				btnPrint=getButton("Print");
        				btnPrint.addActionListener(this);
        				//btnUpdate.setText("Update");
        				topRightBasePanel.add(btnPrint,getConstraints(0, 13, 3, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
        				
        			
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
   // public static void main(String[] args){
    	//new ReportInterface();   
    	
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
			else if(button==btnPrint)
			{
				//Call PrintTableInterface.printData(String [] dataA, String[] dataB) for Printing purpose.
				callAirFlowData();
				int testId=(Integer)cmBoxTestId.getSelectedItem();
				String[]description={	String.valueOf(reportArray.get(0)),//0Mfg.date
										String.valueOf(modelArray.get(0)),//1brand
										(String)reportArray.get(3),//2type
										String.valueOf(modelArray.get(1)),//3modelNumber
										String.valueOf(modelArray.get(2)),//4blade
										String.valueOf(modelArray.get(3)),//5size
										String.valueOf(modelArray.get(4))+" Kg",//6weight
										String.valueOf(modelArray.get(5)),//7switch
										(String)reportArray.get(11),//8color
										String.valueOf(modelArray.get(6))};//9bodymaterial
				String[]result={(String)reportArray.get(1),//0testDate
						String.valueOf(reportArray.get(4))+"V",//1rmsVoltage
						String.valueOf(reportArray.get(5))+"A",//2rmsCurrent
						String.valueOf(reportArray.get(6))+"W",//3rmsPower
						String.valueOf(reportArray.get(7)),//4powerFactor
						String.valueOf(reportArray.get(12))+"V",//5dcVoltage
						String.valueOf(reportArray.get(13))+"A",//6dcCurrent
						String.valueOf(reportArray.get(14))+"W",//7dcPower
						String.valueOf(reportArray.get(9))+"RPM",//8speed
						String.valueOf(reportArray.get(10))//9baseTemperature
						
				};
				Double[] airFlow={(Double)(arrayAirFlow.get(0)),//position_4
						(Double)(arrayAirFlow.get(1)),//position_3
						(Double)(arrayAirFlow.get(2)),//position_2
						(Double)(arrayAirFlow.get(3)),//position_1
						(Double)(arrayAirFlow.get(4)),////position0
						(Double)(arrayAirFlow.get(5)),//position1
						(Double)(arrayAirFlow.get(6)),////position2
						(Double)(arrayAirFlow.get(7)),////position3
						(Double)(arrayAirFlow.get(8))//position4
						};
				new PrintTableInterface(description,result,airFlow,testId).printData();
			}
		}
	else if(sourceObject instanceof JComboBox)
		{
			JComboBox<Object> comboBox=(JComboBox<Object>)sourceObject;
			if(comboBox==(Object)cmBoxTestId)
			{
				leftBasePanel.repaint();
				String k="SELECT * FROM REPORT WHERE RID="+(Integer)cmBoxTestId.getSelectedItem()+";";
				 reportArray=uOrQ.Query(k, 9);
				k="SELECT * FROM MODEL WHERE MODELNUMBER='"+(String)reportArray.get(2)+"';";
				modelArray=uOrQ.Query(k, 6);
				//System.out.println(String.valueOf(modelArray.get(2)));
				
				for(int i=0;i<11;i++)
				{
						if(i==2)
						{
							tFNonStatic[i].setText((String)reportArray.get(3));//bodytype text
						}
						else if(i==8)
						{
							tFNonStatic[i].setText((String)reportArray.get(11));//colour text
						}
						else if(i==10)
						{
							tFNonStatic[i].setText((String)reportArray.get(1));//testDate text
						}
						else 
						{
							switch(i)
							{
							case 0:
								tFNonStatic[i].setText(String.valueOf(reportArray.get(0)));//manufacturingdate
								break;
							case 1:
								tFNonStatic[i].setText(String.valueOf(modelArray.get(0)));//brand
								break;
							case 3:
								tFNonStatic[i].setText(String.valueOf(modelArray.get(1)));//modelnumber
								break;							
							case 4:
								tFNonStatic[i].setText(String.valueOf(modelArray.get(2)));//blade
								break;
							case 5:
								tFNonStatic[i].setText(String.valueOf(modelArray.get(3)));//size
								break;
							case 6:
								tFNonStatic[i].setText(String.valueOf(modelArray.get(4)));//weight
								break;
							case 7:
								tFNonStatic[i].setText(String.valueOf(modelArray.get(5)));//switch
								break;
							case 9:
								tFNonStatic[i].setText(String.valueOf(modelArray.get(6)));//body material
								break;
								
							}
							
						}
				}
	
				for(int i=0;i<10;i++)//Right Baste Panel Manipulation using report Array
				{
					switch(i)
					{
					case 0:
						tFStatic[i].setText(String.valueOf(reportArray.get(4)));//voltage
						break;
					case 1:
						tFStatic[i].setText(String.valueOf(reportArray.get(5)));//current
						break;
					case 2:
						tFStatic[i].setText(String.valueOf(reportArray.get(6)));//power
						break;							
					case 3:
						tFStatic[i].setText(String.valueOf(reportArray.get(7)));//powerFactor
						break;
					case 4:
						tFStatic[i].setText(String.valueOf(reportArray.get(8)));//sound
						break;
					case 5:
						tFStatic[i].setText(String.valueOf(reportArray.get(9)));//speed
						break;
					case 6:
						tFStatic[i].setText(String.valueOf(reportArray.get(10)));//base temperature
						break;
					case 7:
						tFStatic[i].setText(String.valueOf(reportArray.get(12)));//dc voltage
						break;
					case 8:
						tFStatic[i].setText(String.valueOf(reportArray.get(13)));//dc current
						break;
					case 9:
						tFStatic[i].setText(String.valueOf(reportArray.get(14)));//dc power
						break;
				}
			}
				
			
		}
	}
	else if(sourceObject instanceof JRadioButton)
	{
			JRadioButton rBtn=(JRadioButton) sourceObject;
			callAirFlowData();
			//System.out.println("asdasd");
			if(arrayAirFlow.size()>0)
			{	
				if(rBtn==radioBtn[0])
				{
					//System.out.println("-4");
					//position_4=AirFlowInterface.airFlow;
					tFStatic[10].setText(String.valueOf(arrayAirFlow.get(0)));
					
				}
				else if(rBtn==radioBtn[1])
				{
					//System.out.println("-3");
					//position_3=AirFlowInterface.airFlow;
					tFStatic[10].setText(String.valueOf(arrayAirFlow.get(1)));
				}
				else if(rBtn==radioBtn[2])
				{
					//System.out.println("-2");
					//position_2=AirFlowInterface.airFlow;
					tFStatic[10].setText(String.valueOf(arrayAirFlow.get(2)));
				}
				else if(rBtn==radioBtn[3])
				{
					//System.out.println("-1");
					//position_1=AirFlowInterface.airFlow;
					tFStatic[10].setText(String.valueOf(arrayAirFlow.get(3)));
				}
				else if(rBtn==radioBtn[4])
				{
					//System.out.println("0");
					//position0=AirFlowInterface.airFlow;
					tFStatic[10].setText(String.valueOf(arrayAirFlow.get(4)));
				}
				else if(rBtn==radioBtn[5])
				{
					//System.out.println("1");
					//position1=AirFlowInterface.airFlow;
					tFStatic[10].setText(String.valueOf(arrayAirFlow.get(5)));
				}
				else if(rBtn==radioBtn[6])
				{
					//System.out.println("2");
					//position2=AirFlowInterface.airFlow;
					tFStatic[10].setText(String.valueOf(arrayAirFlow.get(6)));
				}
				else if(rBtn==radioBtn[7])
				{
					//System.out.println("3");
					//position3=AirFlowInterface.airFlow;
					tFStatic[10].setText(String.valueOf(arrayAirFlow.get(7)));
				}
				else if(rBtn==radioBtn[8])
				{
					//System.out.println("4");
					//position4=AirFlowInterface.airFlow;
					tFStatic[10].setText(String.valueOf(arrayAirFlow.get(8)));
				}
			}	
		}


	
	}
	public void callAirFlowData()
	{
		String k="SELECT * FROM AIRFLOW WHERE RID="+cmBoxTestId.getSelectedItem()+";";
		arrayAirFlow=uOrQ.Query(k, 10);
	}
}