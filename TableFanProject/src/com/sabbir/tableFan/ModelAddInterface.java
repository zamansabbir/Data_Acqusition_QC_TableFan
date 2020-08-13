package com.sabbir.tableFan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ModelAddInterface implements ActionListener{
	
	private JComboBox<Integer> cboxModelId,cboxBrandId;
	private JTextField tFModelNumber,tFSize,tFWeight, tFSwitch,tFBodyType,tFSliderBlade;
	private JTextField[] tFNonStatic={	tFModelNumber,//0
										tFSize,//1
										tFWeight, //2
										tFSwitch,//3
										tFBodyType,//4
										tFSliderBlade};//5
	private JLabel lblModelId, lblBrandId, lblModelNumber,lblSize,lblWeight, lblSwitch, lblBodyType,lblBlade;
	private JSlider sliderBlade;
	private GridBagConstraints gbc;
	private Random random;
	private JButton btnUpdate,btnAddBrand;
	private JCheckBox chkBoxDelete;
	//private boolean  checkBoxState;
	private int modelId,brandId;
	private UpdateorQuery uOrQ; 
	private int blade;
	private JPanel contentPane;
	//Constructor
	public ModelAddInterface(){
		 gbc = new GridBagConstraints();
	     random = new Random();
	     uOrQ=new UpdateorQuery();
	     displayGui();
	     //setCheckBoxState(false);
	}
	
	private void displayGui(){
		
		JFrame frame= new JFrame("Model Entry");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //0. Creating contentPane as the MainPanel
        contentPane = getPanel();
        contentPane.setLayout(new BorderLayout(5, 5));
        //1. Creating base panel
        	JPanel basePanel=getPanel();
        	basePanel.setLayout(new GridBagLayout());
        	basePanel.setBorder(getBorderWithTitle("Model Details:",TitledBorder.LEFT));
        	
        	
        String[] lblName={"ModelID", "Brand ID", "Model Number","Size","Weight", "Switch", "Body Type","Blade"};
        JLabel[] lbl={lblModelId, lblBrandId, lblModelNumber,lblSize,lblWeight, lblSwitch, lblBodyType,lblBlade};
        
        //Populating Base Panel
        for(int i=0;i<lbl.length;i++)
        {
        	lbl[i]=new JLabel(lblName[i]);
			basePanel.add(lbl[i], getConstraints(0, i, 1, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
        	if(i==0)
        	{
        		ArrayList<Integer> z=uOrQ.shellSorting(uOrQ.Query("SELECT MODELID FROM MODEL;", 5));
        		Vector<Integer> vectorModel=new Vector<Integer>();
        		for(int x:z)
        		{
        			vectorModel.add(x);
        			//System.out.print(x+" ");
        		}
        		cboxModelId=new JComboBox<Integer>(vectorModel); 
        		cboxModelId.setMaximumRowCount(3);
        		basePanel.add(cboxModelId, getConstraints(1, i, 1, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
        		cboxModelId.addActionListener(this);
        	}
        	else if(i==1)
        	{
        		ArrayList<Integer> z=uOrQ.shellSorting(uOrQ.Query("SELECT BRANDID FROM BRAND;",3));
        		Vector<Integer> vectorBrand=new Vector<Integer>();
        		for(int x:z)
        		{
        			vectorBrand.add(x);
        		}
        		cboxBrandId=new JComboBox<Integer>(vectorBrand); 
        		basePanel.add(cboxBrandId, getConstraints(1, i, 1, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
        		cboxBrandId.addActionListener(this);
        	}
        	else if(i==7)
        	{
        		sliderBlade=getSlider();
        		basePanel.add(sliderBlade, getConstraints(1, i, 1, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
        		sliderBlade.addChangeListener(new ChangeListener(){
        			public void stateChanged(ChangeEvent e){
        				//System.out.println(((JSlider)e.getSource()).getValue());
        				blade=((JSlider)e.getSource()).getValue();
        				contentPane.repaint();
        			}
        			
        		});
        	}
        	else
        	{
        		tFNonStatic[i-2]=new JTextField(20);
        		basePanel.add(tFNonStatic[i-2], getConstraints(1, i, 1, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
        	}
        }
        
        	chkBoxDelete=new JCheckBox("Delete");
        	chkBoxDelete.setBackground(new Color(random.nextFloat() , random.nextFloat(), random.nextFloat(), random.nextFloat()));
        	chkBoxDelete.setForeground(Color.WHITE);
        	basePanel.add(chkBoxDelete, getConstraints(1, 8, 1, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
        	chkBoxDelete.addActionListener(this);
        	btnAddBrand=getButton("Add Brand");
        	btnAddBrand.addActionListener(this);
        	basePanel.add(btnAddBrand, getConstraints(0, 10, 2, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
        	btnUpdate=getButton("Update");
        	basePanel.add(btnUpdate, getConstraints(0, 9, 2, 1 , GridBagConstraints.HORIZONTAL, 0.7f, 0.1f));
        	btnUpdate.addActionListener(this);
        
        //1. Done
        contentPane.add(basePanel,BorderLayout.CENTER);
        JLabel bottomLabel = new JLabel("Developed by R&D Automation, Walton.", JLabel.CENTER);
        bottomLabel.setToolTipText("sabbir.zaman@hotmail.com");
        contentPane.add(bottomLabel, BorderLayout.PAGE_END);
        //0.Done
        
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
        panel.setBackground(new Color(random.nextFloat() , random.nextFloat(), random.nextFloat(), random.nextFloat()));
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
    
    private JSlider getSlider(){
    	JSlider sl=new JSlider(JSlider.HORIZONTAL,0,10,3);
    	sl.setMajorTickSpacing(2);
    	sl.setMinorTickSpacing(1);
    	sl.setPaintTicks(true);
    	sl.setPaintLabels(true);
    	sl.setBackground(new Color(random.nextFloat() , random.nextFloat(), random.nextFloat(), random.nextFloat()));
    	return sl;
    }
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
    
    public void actionPerformed(ActionEvent e){
    	Object sourceObject=e.getSource();
    	
    	if(sourceObject instanceof JButton )
    	{
    		JButton btn=(JButton)sourceObject;
    		if(btn==btnUpdate)
    		{
    			//if(isCheckBoxState()==false)
    			if(!chkBoxDelete.isSelected())
				{
    				//System.out.println(chkBoxDelete.isSelected());
					String modelNumber=tFNonStatic[0].getText(),switchText=tFNonStatic[3].getText(), bodyType=tFNonStatic[4].getText();
					Double size=Double.valueOf(tFNonStatic[1].getText()), weight=Double.valueOf(tFNonStatic[2].getText());
					ArrayList<Integer> z=uOrQ.shellSorting(uOrQ.Query("SELECT MODELID FROM MODEL;", 5));
					if(z.size()>0){
					//p=uOrQ.shellSorting(p);
						int missingModelId=uOrQ.findMissing(z);
						if(missingModelId==0)//i.e. nothing missing found 
						{
							String k="INSERT INTO MODEL(MODELID,BRANDID,MODELNUMBER,SIZE,WEIGHT,SWITCH,BODYTYPE,BLADE) "+"VALUES("+(Collections.max(z)+1)+","+brandId+",'"+modelNumber+"',"+size+","+weight+",'"+switchText+"','"+bodyType+"',"+blade+");";
							uOrQ.updateDatabase(k);
						}
						else//missing found
						{
							String k="INSERT INTO MODEL(MODELID,BRANDID,MODELNUMBER,SIZE,WEIGHT,SWITCH,BODYTYPE,BLADE) "+"VALUES("+missingModelId+","+brandId+",'"+modelNumber+"',"+size+","+weight+",'"+switchText+"','"+bodyType+"',"+blade+");";
							uOrQ.updateDatabase(k);
						}
					}
					else {//for the first time entry
							String k="INSERT INTO MODEL(MODELID,BRANDID,MODELNUMBER,SIZE,WEIGHT,SWITCH,BODYTYPE,BLADE) "+"VALUES("+1+","+brandId+",'"+modelNumber+"',"+size+","+weight+",'"+switchText+"','"+bodyType+"',"+blade+");";
							uOrQ.updateDatabase(k);
						}
    			
    			
    		}
    			else
				{
    				//int x=this.modelId;
        			//System.out.println(modelId);
    				
        			
					String k="DELETE FROM MODEL WHERE MODELID="+modelId+";";
					uOrQ.updateDatabase(k);
				}}
    		else if(btn==btnAddBrand)
    		{
    			new BrandAddInterface();
    		}
    		
    	}
    	
    	else if(sourceObject instanceof JComboBox)
    	{
    		@SuppressWarnings("unchecked")
			JComboBox<Integer> cbox=(JComboBox<Integer>)sourceObject;
    		if(cbox==cboxModelId)
    		{	//if(chkBoxDelete.isSelected())
    			modelId=((Integer)cboxModelId.getSelectedItem());
    		}
    		else if(cbox==cboxBrandId)
    		{
    			
    			brandId=(Integer)cboxBrandId.getSelectedItem();
    		}
    	}
    	else if(sourceObject instanceof JCheckBox)
    	{
    		JCheckBox chkbox=(JCheckBox)sourceObject;
    		if(chkbox==chkBoxDelete)
    		{
    			if(chkBoxDelete.isSelected()){
    				
    				ArrayList<Object> deleteItems=uOrQ.Query("SELECT * FROM MODEL WHERE MODELID="+modelId+";", 6);
    			
    				cboxBrandId.setSelectedItem((Integer)deleteItems.get(0));
    				//tFNonStatic[0].setText((String)deleteItems.get(1));
    				tFNonStatic[0].setText(String.valueOf(deleteItems.get(1)));
    				//System.out.println(getModelId());
    				sliderBlade.setValue((Integer)deleteItems.get(2));
    				tFNonStatic[1].setText(String.valueOf(deleteItems.get(3)));
    				tFNonStatic[2].setText(String.valueOf(deleteItems.get(4)));
    				tFNonStatic[3].setText((String)deleteItems.get(5));
    				tFNonStatic[4].setText((String)deleteItems.get(6));
    			}
    			else
    			{
    				tFNonStatic[0].setText("");
    				tFNonStatic[0].setText("");
    				//System.out.println(getModelId());
    				sliderBlade.setValue(3);
    				tFNonStatic[1].setText("");
    				tFNonStatic[2].setText("");
    				tFNonStatic[3].setText("");
    				tFNonStatic[4].setText("");
    			}
    			
    		}
    	}

    }
    
  /**  public static void main(String[] args){
    	new ModelAddInterface().displayGui();
    }*/

	

	
}
