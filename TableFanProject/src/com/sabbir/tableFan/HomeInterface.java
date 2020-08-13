package com.sabbir.tableFan;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;




	
	public class HomeInterface implements ActionListener
	{
	    public static JTextField tField /**,tFieldWeight*/;
	    private JButton btnReport,btnTest /**,btnAddModel*/;
	    private ImageIcon logo;
	   // private JComboBox cbPorts;
	    private JCheckBox checkClose;
	   // public static boolean closeStatus=true;
	   

	    private GridBagConstraints abc;

	    private Random random;
	    private UpdateorQuery uOrQ;
	    public HomeInterface()
	    {
	        abc = new GridBagConstraints();
	        random = new Random();
	        //new Thread(new WeightIndicatorInterface2()).start();
	        uOrQ=new UpdateorQuery();
	       // displayGUI();
	        
	      
	    }


	    public void displayGUI()
	    {
	        //ContentPane
	    	JFrame frame = new JFrame("Home: Table Fan Test");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        JPanel contentPane = getPanel();
	        contentPane.setLayout(new BorderLayout(5, 5));
	        JLabel topLabel = new JLabel("Dept. of R&D Automation, WHIL", JLabel.CENTER);
	        contentPane.add(topLabel, BorderLayout.NORTH);

	        //Center Panel
	        JPanel basePanel = getPanel();
	        basePanel.setLayout(new GridLayout(1, 2));

	        JPanel leftPanel = getPanel();
	        leftPanel.setLayout(new GridBagLayout());
	        leftPanel.setBorder( BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        JLabel fieldLabel = new JLabel("Last Model ID", JLabel.LEADING);
	        tField = new JTextField(20);
	        tField.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,14));
	        tField.setEditable(false);
	        
	        //Updating Last Model from Database
	        ArrayList<Object> rows=uOrQ.Query("SELECT COUNT(*) AS ROWS FROM REPORT;", 2);
			int weightID=(Integer)rows.get(0);
			tField.setText(String.valueOf(weightID));
	        //Updating Done
			
			//JLabel lblFirstWeight=new JLabel("First Weight",JLabel.LEADING);
	        //JTextField tfirstWeight=new JTextField(20);

	        JPanel topPanel = getPanel();
	        topPanel.setLayout(new GridBagLayout());
	        topPanel.add(fieldLabel, getConstraints( 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
	        topPanel.add(tField, getConstraints( 1, 0, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
	        
	        //topPanel.add(lblFirstWeight, getConstraints( 0, 1, 1, 1, GridBagConstraints.HORIZONTAL, 0.3f, .1f));
	        //topPanel.add(tfirstWeight, getConstraints(1, 1, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
	        
	        
	        leftPanel.add(topPanel, getConstraints(0, 0, 1, 1, GridBagConstraints.BOTH, 1.0f, 0.40f));

	        JPanel middlePanel = getPanel();
	        middlePanel.setLayout(new BorderLayout(5, 5));
	        
	       
	        
	         //logo=ImageIO.read(new File("D:/sabbir/Sabbir RnD/Eclipse/WorkSpace/TruckScaleFinal/walton_logo.png"));
	         //logo=new ImageIcon(HomeInterface.class.getResource("walton_logo.png"));
	        logo=new ImageIcon(HomeInterface.class.getResource("tablefan.gif"));
	       
	        
	        JLabel pictureLabel=new JLabel(logo);
	        middlePanel.add(pictureLabel, BorderLayout.CENTER);
	        leftPanel.add(middlePanel, getConstraints( 0, 1, 1, 2, GridBagConstraints.BOTH, 1.0f, 0.60f));
	        basePanel.add(leftPanel);

	        JPanel rightPanel = getPanel();
	        rightPanel.setLayout(new GridLayout(0, 1, 10, 10));
	        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        
	        	       	        
	        //tFieldWeight = new JTextField(30);
	        JPanel portSelectPanel=getPanel();
	        portSelectPanel.setLayout(new GridLayout(0,4,10,10));
	        //logo=new ImageIcon(HomeInterface.class.getResource("walton_logo.png"));
	        //JLabel portLabelConnect=new JLabel(logo);
	       
	       //CheckBox
	       checkClose=new JCheckBox("Connect",true);
	       checkClose.addActionListener(this);
	       //Done
	       
	        portSelectPanel.add(checkClose);
	       // portSelectPanel.add(portLabelConnect);
	        //portSelectPanel.add(labelClose);
	        btnReport = new JButton("Report");
	        btnReport.addActionListener(this);
	        btnTest = new JButton("Test Page");
	        btnTest.addActionListener(this);
	       /** btnAddModel = new JButton("Net Weight");
	        btnAddModel.addActionListener(this);*/
	        //rightPanel.add(tFieldWeight);
	        rightPanel.add(portSelectPanel);
	      
	        rightPanel.add(btnReport);

	        rightPanel.add(btnTest);
	        /**rightPanel.add(btnAddModel);*/
	        basePanel.add(rightPanel);
	        contentPane.add(basePanel, BorderLayout.CENTER);

	        JLabel bottomLabel = new JLabel("Developed by Engr. Sabbir", JLabel.CENTER);
	        contentPane.add(bottomLabel, BorderLayout.PAGE_END);

	        frame.setContentPane(contentPane);
	        frame.pack();
	        frame.setLocationByPlatform(true);
	        frame.setResizable(false);
	        frame.setVisible(true);
	    }

	    private JPanel getPanel()
	    {
	        JPanel panel = new JPanel();
	        panel.setOpaque(true);
	        panel.setBackground(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat()));
	        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

	        return panel;
	    }

	    private GridBagConstraints getConstraints( int x, int y, int w, int h, int fillValue, float weightx, float weighty)
	    {
	        //GridBagConstraints gbc = new GridBagConstraints();
	        abc.anchor = GridBagConstraints.FIRST_LINE_START;
	        abc.fill = fillValue;
	        abc.gridx = x;
	        abc.gridy = y;
	        abc.gridwidth = w;
	        abc.gridheight = h;
	        abc.weightx = weightx;
	        abc.weighty = weighty;

	        return abc;
	    }

	
	    
	    public void actionPerformed(ActionEvent e)
	    {
	    	Thread power,sound,speed,airFlow,baseTemperature;
			//k=new Thread(new WeightIndicatorInterface2());
	    	power=new Thread(new PowerAnalyzerInterface());
	    	sound=new Thread(new SoundInterface());
	    	speed=new Thread(new SpeedInterface());
	    	airFlow=new Thread(new AirFlowInterface());
	    	baseTemperature=new Thread(new BaseTemperatureInterface());
	    	
	    	
	    	
	    	Object sourceObject=e.getSource();
	    	if(sourceObject instanceof JButton)
	    	{
	    		JButton button=(JButton)sourceObject;
	    		if(button==btnTest)
	    		{
	    			new TestInterface();
	    			//power.start();
	    	    	//sound.start();
	    	    	//speed.start();
	    	    	//airFlow.start();
	    	    	//baseTemperature.start();
	    		}
	    		else if(button==btnReport)
	    		{
	    			new ReportInterface();
	    		}
	    	
	    	}
	    
	    	else if (sourceObject instanceof JCheckBox)
	    	{
	    		JCheckBox cb=(JCheckBox)e.getSource();
	    		if(cb==checkClose)
	    		{
	    			
	    			//if(closeStatus)
	    			//{
	    				//WeightIndicatorInterface2.p=String.valueOf(cbPorts.getSelectedItem());
	    				//InstrumentInterface1.p=String.valueOf(cbPorts.getSelectedItem());
	    				//k.start();
	    			//}
	    			
	    			power.start();
	    			sound.start();
	    			speed.start();
	    			airFlow.start();
	    			baseTemperature.start();
	    			
	    		}
	    	}
	    }
	}

