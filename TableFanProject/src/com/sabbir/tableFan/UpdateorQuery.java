package com.sabbir.tableFan;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UpdateorQuery {
	public void updateDatabase(String sql){
		
		Connection c=null;
		Statement stmt=null;
		try{
		 	Class.forName("org.sqlite.JDBC");		 
			c=DriverManager.getConnection("jdbc:sqlite:TableFan.db");
			c.setAutoCommit(false);
			stmt=c.createStatement();
			stmt.executeUpdate(sql);
			//System.out.println("updated");
			
			
		}catch(Exception x)
		{
			JFrame f1=new JFrame();
			String errorMessage=x.getClass().getName()+": "+x.getMessage();
			JOptionPane.showMessageDialog(f1, errorMessage,"Exception",JOptionPane.ERROR_MESSAGE);
			f1.dispose();
		}finally
		{
			try {
				stmt.close();
				c.commit();
				c.close();
			} catch (SQLException e1) {
				System.out.println("Problem Lies here");
				e1.printStackTrace();
			}
			
			
		}
	}
	
	
	public ArrayList<Object> Query(String sql,int i)
	{
		ResultSet rs=null;
		Connection c=null;
		Statement stmt=null;
		ArrayList<Object> r=new ArrayList<Object>();
		try{
			Class.forName("org.sqlite.JDBC");
			//String year=String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			c=DriverManager.getConnection("jdbc:sqlite:TableFan.db");
			c.setAutoCommit(false);
			//System.out.println("Database connection Successful");
			stmt=c.createStatement();
			rs= stmt.executeQuery(sql);
			
			//LogIn Interface data retrieving from USER Table
			 if(i==1)
			{
				while(rs.next())
				{
					r.add(rs.getString("USER"));
					r.add(rs.getString("PASSWORD"));
				}
				
			}
			 //Test Interface: retrieving amount of test done from Report Table
			 else if(i==2)
			 {
				 r.add(rs.getInt("ROWS"));
			 }
			 else if(i==3)
			 {
				 while(rs.next())
				 {
					 r.add(rs.getInt("BRANDID"));
				 }
			 }
			 else if(i==4)
			 {
				 
					 r.add(rs.getString("BRANDNAME"));
				 
			 }
			 else if(i==5)
			 {
				 while(rs.next())
				 {
					 r.add(rs.getInt("MODELID"));
				 }
			 }
			 else if(i==6)
			 {
				
					 r.add(rs.getInt("BRANDID"));//0
					 r.add(rs.getString("MODELNUMBER"));//1
					 r.add(rs.getInt("BLADE"));//2
					 r.add(rs.getDouble("SIZE"));//3
					 r.add(rs.getDouble("WEIGHT"));//4
					 r.add(rs.getString("SWITCH"));//5
					 r.add(rs.getString("BODYTYPE"));//6
				
			 }
			 else if(i==7)//Looking for RID from Report Table
			 {
				 while(rs.next())
				 {
					 r.add(rs.getInt("RID"));
				 }
				 
			 }
			 else if(i==8)//looking for modelnumber from MODEL table
			 {
				 r.add(rs.getString("MODELNUMBER"));
			 }
			 else if(i==9)//looking for specific report according RID=X
			 {
				 r.add(rs.getString("MANUFACTURINGDATE"));//0
				 r.add(rs.getString("TESTDATE"));//1
				 r.add(rs.getString("MODELNUMBER"));//2
				 r.add(rs.getString("TYPE"));//3
				 r.add(rs.getDouble("RMSVOLTAGE"));//4
				 r.add(rs.getDouble("RMSCURRENT"));//5
				 r.add(rs.getDouble("POWER"));//6
				 r.add(rs.getDouble("POWERFACTOR"));//7
				 r.add(rs.getDouble("SOUND"));//8
				 r.add(rs.getDouble("SPEED"));//9
				// r.add(rs.getDouble("AIRFLOW"));
				 r.add(rs.getDouble("TEMPERATURE"));//10
				 r.add(rs.getString("COLOUR"));//11
				 r.add(rs.getDouble("DCVOLTAGE"));//12
				 r.add(rs.getDouble("DCCURRENT"));//13
				 r.add(rs.getDouble("DCPOWER"));//14
				 
				 
			 }
			 else if(i==10)
			 {
				 r.add(rs.getDouble("POSITION_4")); //0
				 r.add(rs.getDouble("POSITION_3"));//1
				 r.add(rs.getDouble("POSITION_2")); //2
				 r.add(rs.getDouble("POSITION_1"));//3
				 r.add(rs.getDouble("POSITION0"));//4
				 r.add(rs.getDouble("POSITION1"));//5
				 r.add(rs.getDouble("POSITION2"));//6
				 r.add(rs.getDouble("POSITION3"));//7
				 r.add(rs.getDouble("POSITION4")); //8
			 }
			 
			
			
		}catch(Exception e)
		{
			//System.err.println(e.getClass().getName()+":"+e.getMessage());
			
			try{
				if(i==1){
					
					Class.forName("org.sqlite.JDBC");
					//String year=String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
					c=DriverManager.getConnection("jdbc:sqlite:TableFan.db");
					c.setAutoCommit(false);
					stmt=c.createStatement();
					String userTable="CREATE TABLE USER "+
					"(USER TEXT PRIMARY KEY NOT NULL,"+
					"PASSWORD TEXT NOT NULL)";
					stmt.executeUpdate(userTable);
					stmt.executeUpdate("INSERT INTO USER(USER,PASSWORD)VALUES('admin','automation');");
					stmt.executeUpdate("INSERT INTO USER(USER,PASSWORD)VALUES('MasterAdmin','maintenance');");
					new LogInInterface().displayGui();
					stmt.close();
					c.commit();
					c.close();
				}
			
				
			}catch(Exception ex)
			{
				//System.err.println(e.getClass().getName()+":"+e.getMessage());
			}
			//System.exit(0);
			
		}finally
		{
			 try{
					rs.close();
					stmt.close();
					c.close();
					//System.out.println("Connection Closed");
					}catch(SQLException e2)
					{
						System.out.println("Not Closed");
					}
		}
		
		return r;
		}
	
	//Sorting the arrayList
	
	public ArrayList<Integer> shellSorting(ArrayList<Object> r)
	{
		int inner,outer,h=1,temp,nElements=r.size();
		while(h<=nElements/3)
		{
			h=h*3+1;
		}
		
		
		while(h>0)
		{
			for(outer=h;outer<nElements;outer++)
			{
					temp=(Integer)r.get(outer);
					inner=outer;
				 while(inner>h-1&&(Integer)r.get(inner-h)>=temp)
				 {
					 
					 r.set(inner, r.get(inner-h));
					 inner-=h;
				 }
				 
				 r.set(inner, temp);
			}
			 h=(h-1)/3;
			
		}
		ArrayList<Integer> x=new ArrayList<Integer>();
		for(Object i:r)
			x.add((Integer)i);
		return x;
	}
	
	public int findMissing(ArrayList<Integer> x)//return 0 if nothing is missing, else returned value is missing 
	{
		int missing=0;
		/*ArrayList<Integer> y=new ArrayList<Integer>();
		for(Object i:x)
		{
			y.add((Integer)i);
		}*/
		for(int i=1;i<=Collections.max(x);i++)
		{
			if((i^x.get(i-1))!=0)
				
				{
					missing=i;
					break;
				}
			
		}
		return missing;
	}


}
