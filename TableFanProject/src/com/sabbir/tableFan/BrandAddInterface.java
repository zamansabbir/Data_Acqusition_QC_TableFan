package com.sabbir.tableFan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class BrandAddInterface implements ActionListener{

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private JLabel lblBrandID, lblBrandName;
	private JTextField tFBrandID,tFBrandName;	
	private JTextField[]tF={tFBrandID,tFBrandName};
	private JButton btnUpdate;
	private JComboBox<Integer> cboxBrandID;
	private GridBagConstraints gbc;
	private Random random;
	private int brandID;
	private UpdateorQuery uOrQ; 
	private JCheckBox checkBox;
	
	public BrandAddInterface(){
		gbc=new GridBagConstraints();
		random=new Random();
		uOrQ=new UpdateorQuery();
		displayGui();
	}
	
	
	
	public void displayGui()
	{
		JFrame frame = new JFrame("Brand Entry");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel contentPane = getPanel();
        contentPane.setLayout(new BorderLayout(5, 5));
        
        //North Border Populating
        JLabel topLabel = new JLabel("Dept. of R&D Automation, WHIL", JLabel.CENTER);
        contentPane.add(topLabel, BorderLayout.NORTH);
        //North Border Populating done
        
        JPanel basePanel = getPanel();
        basePanel.setLayout(new GridLayout(2, 1));
        
        //Center Border Populating
        
        JPanel topBasePanel=getPanel();
        topBasePanel.setLayout(new GridBagLayout());
        topBasePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel[] lbl={lblBrandID, lblBrandName};
        //JTextField[] tF={tFMaterialID,tFMaterialName};
        String[] lblName={"Brand ID","Brand Name"};
        
        ArrayList<Integer> q=uOrQ.shellSorting(uOrQ.Query("SELECT BRANDID FROM BRAND;",3));
       // System.out.println((Integer)q.get(0));
        Vector<Integer> mCode=new Vector<Integer>();
        for(int i=0;i<q.size();i++)
        {
        	mCode.add((Integer)q.get(i));
        }
        lbl[0] = new JLabel(lblName[0], JLabel.LEADING);
        cboxBrandID = new JComboBox<Integer>(mCode);
        cboxBrandID.setMaximumRowCount(3);
        cboxBrandID.addActionListener(this);
        topBasePanel.add(lbl[0], getConstraints( 0, 0, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
        topBasePanel.add(cboxBrandID, getConstraints( 1, 0, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
        
       
        	
        
        	lbl[1] = new JLabel(lblName[1], JLabel.LEADING);
	        tF[1] = new JTextField(20);
	        topBasePanel.add(lbl[1], getConstraints( 0, 1, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
	        topBasePanel.add(tF[1], getConstraints( 1, 1, 2, 1, GridBagConstraints.HORIZONTAL, 0.7f, 1.0f));
       
        
        
        
        basePanel.add(topBasePanel);
      //Center Border Populating done
        
        JPanel bottomBasePanel=getPanel();
        bottomBasePanel.setLayout(new GridBagLayout());
        bottomBasePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        btnUpdate=getButton("Update");
        bottomBasePanel.add(btnUpdate, getConstraints(0, 1, 1, 1 , GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
        btnUpdate.addActionListener(this);
        
       // btnUpdate.addActionListener(this);
        //JCheckBox 
        checkBox=new JCheckBox("Delete Brand");
        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {         
               //checkBoxState=true;
            	if(checkBox.isSelected())
            	{
            		ArrayList<Object> q=uOrQ.Query("SELECT BRANDNAME FROM BRAND WHERE BRANDID="+brandID+";",4);
                    tF[1].setText(String.valueOf(q.get(0)));
            	}
            	else if(!checkBox.isSelected())
            	{
            		tF[1].setText("");
            	}
              
               //System.out.println(materialID);
               
            }           
         });
        
        bottomBasePanel.add(checkBox, getConstraints(0, 0, 1, 1, GridBagConstraints.HORIZONTAL, 0.3f, 0.1f));
        
      
        
        basePanel.add(bottomBasePanel);
        
        contentPane.add(basePanel,BorderLayout.CENTER);
        
        JLabel bottomLabel = new JLabel("R&D Automation, Walton Hi-Tech Industries Ltd.", JLabel.CENTER);
        bottomLabel.setToolTipText("sabbir.zaman@hotmail.com");
        contentPane.add(bottomLabel, BorderLayout.PAGE_END);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
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
	    	//String k="INSERT INTO MATERIAL(MATERIALCODE,MATERIALNAME) "+"VALUES("+Integer.parseInt(tF[0].getText())+",'"+(String)tF[1].getText()+"');";
	    	//new UpdateorQuery().updateDatabase(k);   
	    	Object sourceObject=e.getSource();
			if(sourceObject instanceof JButton)
			{
				JButton button=(JButton)sourceObject;
					if(button==btnUpdate)
					{
						if(!checkBox.isSelected())
						{
							String brandName=tF[1].getText();
							System.out.println(brandName);
							ArrayList<Integer> p=uOrQ.shellSorting(uOrQ.Query("SELECT BRANDID FROM BRAND;", 3));
							
							if(p.size()>0){
							//p=uOrQ.shellSorting(p);
								int missing=uOrQ.findMissing(p);
								
								if(missing==0)//i.e. nothing missing found 
								{
									String k="INSERT INTO BRAND(BRANDID,BRANDNAME) "+"VALUES("+(Collections.max(p)+1)+",'"+brandName+"');";
									uOrQ.updateDatabase(k);
								}
								else//missing found
								{
									String k="INSERT INTO BRAND(BRANDID,BRANDNAME) "+"VALUES("+missing+",'"+brandName+"');";
									uOrQ.updateDatabase(k);
								}
							}
							else {//for the first time entry
								String k="INSERT INTO BRAND(BRANDID,BRANDNAME) "+"VALUES("+1+",'"+brandName+"');";
								uOrQ.updateDatabase(k);
							}
								
							
					
						}
						if(checkBox.isSelected())
						{
							//System.out.println("Selected");
							String k="DELETE FROM BRAND WHERE BRANDID="+brandID+";";
							uOrQ.updateDatabase(k);
						}
					}
					
				}
			else if(sourceObject instanceof JComboBox)
			{
				
				@SuppressWarnings("rawtypes")
				JComboBox cb=(JComboBox)e.getSource();
				if(cb==cboxBrandID)
				{
					brandID=(Integer)cb.getSelectedItem();
				}
			}
	    	}
	    
	 /**   public static void main(String[] args){
	    	
	    	new BrandAddInterface().displayGui();
	    }
	 */
}
	
	
	