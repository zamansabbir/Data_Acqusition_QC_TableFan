package com.sabbir.tableFan;

	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
	import java.awt.GridLayout;
	import java.awt.Toolkit;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.ItemEvent;
	import java.awt.event.ItemListener;
	import java.awt.event.KeyEvent;
	import java.awt.event.KeyListener;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Random;
	import java.util.Vector;
	import javax.swing.BorderFactory;
	import javax.swing.JButton;
	import javax.swing.JCheckBox;
	import javax.swing.JComboBox;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JPasswordField;
	import javax.swing.JTextField;

	public class LogInInterface implements ActionListener{

		private JLabel lblUserID, lblPassword;
		private JPasswordField pF;
		private JButton btnProceed,btnCreateAccount;
		private JComboBox<String> cboxUserID;
		private GridBagConstraints gbc;
		private Random random;
		private boolean checkBoxCreateUserState=false,checkBoxDeleteUserState=false,checkBoxChangePasswordState=false;
		private Vector<String> userVector,passwordVector;
		private static JCheckBox checkBoxCreateUser,checkBoxDeleteUser,checkBoxChangePassword;
		private int userIndex;
		private JFrame frame;
		private char[] cArray=null;
		private UpdateorQuery uOrQ;
		public static String operatorName;
		public LogInInterface(){
			gbc=new GridBagConstraints();
			random=new Random();
			uOrQ=new UpdateorQuery();
		}
		
		
		
		public void displayGui()
		{
			frame = new JFrame("Sign In");
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        JPanel contentPane = getPanel();
	        contentPane.setLayout(new BorderLayout(5, 5));
	        
	        //North Border Populating
	        JLabel topLabel = new JLabel("Welcome to Table Fan Project Reporting", JLabel.CENTER);
	        contentPane.add(topLabel, BorderLayout.NORTH);
	        //North Border Populating done
	        
	        JPanel basePanel = getPanel();
	        basePanel.setLayout(new GridLayout(2, 1));
	        
	        //Center Border Populating
	        
	        JPanel topBasePanel=getPanel();
	        topBasePanel.setLayout(new GridBagLayout());
	        topBasePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        
	        JLabel[] lbl={lblUserID, lblPassword};
	        //JTextField[] tF={tFMaterialID,tFMaterialName};
	        String[] lblName={"User ID","Password"};
	        
	       ArrayList<Object> q=uOrQ.Query("SELECT * FROM USER;",1);
	    	
			userVector=new Vector<String>();
			passwordVector=new Vector<String>();
			userVector.add("");
			//userVector.add("MasterAdmin");
			passwordVector.add("");
			for(int i=0;i<q.size()-1;i+=2)
			{
				userVector.add((String)q.get(i));
				passwordVector.add((String)q.get(i+1));
			}
	       
	        
	        	lbl[0] = new JLabel(lblName[0], JLabel.LEADING);
	        	topBasePanel.add(lbl[0], getConstraints( 0, 0, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
	        	
	        		cboxUserID=new JComboBox<String>(userVector);
	        		cboxUserID.addActionListener(this);
	        		topBasePanel.add(cboxUserID, getConstraints( 1, 0, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
	        		lbl[1] = new JLabel(lblName[1], JLabel.LEADING);
		        	topBasePanel.add(lbl[1], getConstraints( 0, 1, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
	        		pF = new JPasswordField(20);       		
	        		topBasePanel.add(pF, getConstraints( 1, 1, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
	        
	        
	        pF.addKeyListener( new KeyListener(){

                public void keyPressed(KeyEvent e){

                    if(e.getKeyCode() == KeyEvent.VK_ENTER){

                    	performAction();
                    }       
                }

				
				public void keyReleased(KeyEvent e) {
					
					;
				}

			
				public void keyTyped(KeyEvent e) {
					
					;
				}
            }
        );
	        
	       // System.out.println(userVector+":"+passwordVector);
	        
	        basePanel.add(topBasePanel);
	      //Center Border Populating done
	        
	        JPanel bottomBasePanel=getPanel();
	        bottomBasePanel.setLayout(new GridBagLayout());
	        bottomBasePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	        /**btnLogIn=new JButton("Proceed");
	        btnLogIn.addActionListener(this);
	        bottomBasePanel.add(btnLogIn, getConstraints(0, 1, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
	        */
	        
	        checkBoxCreateUser=new JCheckBox("Create User");
	        checkBoxCreateUser.addItemListener(new ItemListener() {
	            public void itemStateChanged(ItemEvent e) {         
	               checkBoxCreateUserState=true;
	                             
	            }           
	         });
	        bottomBasePanel.add(checkBoxCreateUser, getConstraints(0, 1, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
	        
	       checkBoxDeleteUser=new JCheckBox("Delete User");
	        checkBoxDeleteUser.addItemListener(new ItemListener() {
	            public void itemStateChanged(ItemEvent e) {         
	               checkBoxDeleteUserState=true;
	                             
	            }           
	         });
	        
	        
	        bottomBasePanel.add(checkBoxDeleteUser, getConstraints(0, 2, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
	        
	        checkBoxChangePassword=new JCheckBox("Change Password");
	        checkBoxChangePassword.addItemListener(new ItemListener() {
	            public void itemStateChanged(ItemEvent e) {         
	               checkBoxChangePasswordState=true;
	                             
	            }           
	         });
	        
	        
	        bottomBasePanel.add(checkBoxChangePassword, getConstraints(0, 3, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
	        
	        
	        btnProceed=new JButton("Proceed");
	        btnProceed.setBackground(Color.CYAN);
	        btnProceed.addActionListener(this);
	        bottomBasePanel.add( btnProceed, getConstraints(0, 4, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
	        
	        
	      
	        
	      
	        
	        basePanel.add(bottomBasePanel);
	        
	        contentPane.add(basePanel,BorderLayout.CENTER);
	        
	        JLabel bottomLabel = new JLabel("Sign in to use", JLabel.CENTER);
	        contentPane.add(bottomLabel, BorderLayout.PAGE_END);

	        frame.setContentPane(contentPane);
	        frame.pack();
	        frame.setLocationByPlatform(true);
	        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		    frame.setLocation(d.width/2-250,d.height/2-220);
	        frame.setResizable(false);
	        frame.setVisible(true);
	        
	        //Center Border Populating Done
			
		}
		
		 private JPanel getPanel()
		    {
		        JPanel panel = new JPanel();
		        panel.setOpaque(true);
		        panel.setBackground(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat() , random.nextFloat()));
		        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		        return panel;
		    }

		    private GridBagConstraints getConstraints(int x, int y, int w, int h, int fillValue, float weightx, float weighty)
		    {
		       
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
		
		    
		    public void actionPerformed(ActionEvent e){
		    		
		    	Object sourceObject=e.getSource();
		    	if(sourceObject instanceof JButton)
		    	{
		    		JButton button=(JButton)sourceObject;
		    		if(button==btnProceed)
		    		{
		    			performAction();
		    		}
		    		else if(button==btnCreateAccount)
		    		{
		    			
		    		}
		    	}
		    	else if(sourceObject instanceof JComboBox)
		    	{
		    		@SuppressWarnings("rawtypes")
					JComboBox cb=(JComboBox)sourceObject;
		    		if(cb==cboxUserID){
		    			userIndex=cb.getSelectedIndex();
		    			operatorName=(String)cb.getSelectedItem();
		    			//System.out.println(operatorName);
		    		}
		    	}
		    	else if(sourceObject instanceof JCheckBox)
		    	{
		    		JCheckBox check=(JCheckBox)sourceObject;
		    		if(check==checkBoxCreateUser)
		    		{
		    			checkBoxCreateUserState=true;
		    		}
		    		else if(check==checkBoxDeleteUser)
		    		{
		    			checkBoxDeleteUserState=true;
		    		}
		    		else if(check==checkBoxChangePassword)
		    		{
		    			checkBoxChangePasswordState=false;
		    		}
		    	}
		    }
		    private void performAction()
		    {
		    	//Create User
    			if(checkBoxCreateUserState==true&&checkBoxDeleteUserState==false&&checkBoxChangePasswordState==false)
    			{
    				if(((String)userVector.get(userIndex)).equals("admin")&&checkPassword())
    				{
    					String userName=null,passWord=null;
    					JLabel userLabel=new JLabel("User Name");
    					JLabel passwordLabel=new JLabel("Password");
    					JTextField enterUser=new JTextField(20);
    					JPasswordField enterPassword=new JPasswordField(20);
    					JPanel createAccountPanel=getPanel();
    					createAccountPanel.setLayout(new GridBagLayout());
    					createAccountPanel.add(userLabel, getConstraints(0, 0, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
    					createAccountPanel.add(enterUser, getConstraints(1, 0, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
    					createAccountPanel.add(passwordLabel, getConstraints(0, 1, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
    					createAccountPanel.add(enterPassword, getConstraints(1, 1, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
    					int result=JOptionPane.showConfirmDialog(null, createAccountPanel, "Please Enter User Name and Passwod", JOptionPane.OK_CANCEL_OPTION);
    					if(result==JOptionPane.OK_OPTION)
    					{
    						userName=enterUser.getText();
    						passWord=String.valueOf(enterPassword.getPassword());
    					}
    					if(userName==null ||passWord==null)
    						;
    					else{
    						String sql="INSERT INTO USER(USER,PASSWORD)VALUES('"+userName+"',"+"'"+passWord+"');";
    						new UpdateorQuery().updateDatabase(sql);
    					}
    				}
    				else
    				{
    					JOptionPane.showMessageDialog(null, "Only Administrator Can create User,Check if adminastrator Password is correct","?",JOptionPane.INFORMATION_MESSAGE);
    				}
    			}
    			//Delete User
    			else if (checkBoxCreateUserState==false&&checkBoxDeleteUserState==true&&checkBoxChangePasswordState==false)
    			{
    				if(((String)userVector.get(userIndex)).equals("admin")&&checkPassword())
    				{
    					String userName=null;
    					JLabel userLabel=new JLabel("User Name");
    					Vector<String> a=new Vector<String>();
    					for(Object x:userVector)
    					{
    						if(((String)x).equals("admin"))
							;
    						else
							a.add((String)x);
    					}
    					JComboBox<String> cboxDeleteUser=new JComboBox<String>(a);
    					JPanel createAccountPanel=getPanel();
    					createAccountPanel.setLayout(new GridBagLayout());
    					createAccountPanel.add(userLabel, getConstraints(0, 0, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
    					createAccountPanel.add(cboxDeleteUser, getConstraints(1, 0, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
					
    					int result=JOptionPane.showConfirmDialog(null, createAccountPanel, "Please Enter User Name and Passwod", JOptionPane.OK_CANCEL_OPTION);
    					if(result==JOptionPane.OK_OPTION)
    					{
    						userName=(String)cboxDeleteUser.getSelectedItem();
						
    					}
    					
    					
    						String sql="DELETE FROM USER WHERE USER='"+userName+"';";
    						new UpdateorQuery().updateDatabase(sql);
    					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Only Administrator Can Delete User,Check if adminastrator Password is correct","?",JOptionPane.INFORMATION_MESSAGE);
				}
    				
    			}
    			//Sign In
    			else if(checkBoxCreateUserState==false&&checkBoxDeleteUserState==false&&checkBoxChangePasswordState==false)
    			{
    				
    				if(((String)userVector.get(userIndex)).equals("MasterAdmin")&&checkPassword())
    				{
    					frame.dispose();
    					new TableFanDatabase();
    					new HomeInterface().displayGUI();
    				}
    				
    			else if(checkPassword())
    				{
    					
    					frame.dispose();
    					new HomeInterface().displayGUI();
    					
    				}
    				else
    					{
    					
    					JOptionPane.showMessageDialog(null, "Password Does not match","Help",JOptionPane.ERROR_MESSAGE);
    					}
    			}
    			//Change Password
    			else if(checkBoxCreateUserState==false&&checkBoxDeleteUserState==false&&checkBoxChangePasswordState==true)
    			{
    				if(checkPassword())
    				{
    					String userName=null;
    					JLabel userLabel=new JLabel("User Name");
    					JLabel newPasswordLabel=new JLabel("New Password");
    					JLabel confirmNewPasswordLabel=new JLabel("Confirm Password");
    					
    					JTextField tFUser=new JTextField(20);
    					tFUser.setEditable(false);
    					userName=(String)userVector.get(userIndex);
    					tFUser.setText(userName);
    					JPasswordField newPasswordField=new JPasswordField();
    					JPasswordField confirmNewPasswordField=new JPasswordField();
    					JPanel createAccountPanel=getPanel();
    					createAccountPanel.setLayout(new GridBagLayout());
    					createAccountPanel.add(userLabel, getConstraints(0, 0, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
    					createAccountPanel.add(tFUser, getConstraints(1, 0, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
    					createAccountPanel.add(newPasswordLabel, getConstraints(0, 1, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
    					createAccountPanel.add(newPasswordField, getConstraints(1, 1, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
    					createAccountPanel.add(confirmNewPasswordLabel, getConstraints(0, 2, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
    					createAccountPanel.add(confirmNewPasswordField, getConstraints(1, 2, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
    					
    					int result=JOptionPane.showConfirmDialog(null, createAccountPanel, "Please Enter Password", JOptionPane.OK_CANCEL_OPTION);
    					if(result==JOptionPane.OK_OPTION)
    					{
    						if(Arrays.equals(newPasswordField.getPassword(),confirmNewPasswordField.getPassword()))
    						{
    							String sql="UPDATE USER SET PASSWORD='"+String.valueOf(newPasswordField.getPassword()) +"'WHERE USER='"+userName+"';";
    							new UpdateorQuery().updateDatabase(sql);
    						}
    						else
    						{
    							JOptionPane.showMessageDialog(null, "Passwords do not match,Try again","Error",JOptionPane.ERROR_MESSAGE);
    						}
    						
						
    					}
    					
    					
    						
    				}
    			}
    			else
    			{
    				int rslt=JOptionPane.showConfirmDialog(null, "Multiple Option Selected","Help",JOptionPane.OK_CANCEL_OPTION);
    				if(rslt==JOptionPane.OK_OPTION)
    				{
    					frame.setVisible(false);	
    					frame.dispose();
    					//frame.revalidate();
    					new LogInInterface().displayGui();
    					
    				}
    			}
		    }
		    
		    private boolean checkPassword()
		    {
		    	cArray=((String)passwordVector.get(userIndex)).toCharArray();
		    	return Arrays.equals(pF.getPassword(),cArray);
		    	
		    }
		    
		    
		/**    public static void main(String[] args)
		    {
		        
		                    new LogInInterface().displayGui();
		                
		    }*/
	}
		
		



