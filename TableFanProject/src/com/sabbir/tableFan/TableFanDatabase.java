package com.sabbir.tableFan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class TableFanDatabase {

	public TableFanDatabase() {
		Connection c=null;
		Statement stmt=null;
		try{
			Class.forName("org.sqlite.JDBC");
			//String year=String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			c=DriverManager.getConnection("jdbc:sqlite:TableFan.db");
			c.setAutoCommit(false);
			stmt=c.createStatement();
			//String userTable="CREATE TABLE USER "+ //userTable created in updateOrQuery
			//"(USER TEXT PRIMARY KEY NOT NULL,"+
			//"PASSWORD TEXT NOT NULL)";
			
			
			String brandTable="CREATE TABLE BRAND "+
			"(SL INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"BRANDID INTEGER NOT NULL UNIQUE,"+
			"BRANDNAME TEXT NOT NULL)";
			
			String modelTable="CREATE TABLE MODEL "+
			"(SL INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"MODELID INTEGER UNIQUE ,"+
			"BRANDID INTEGER NOT NULL,"+
			"MODELNUMBER TEXT NOT NULL,"+
			"BLADE INTEGER NOT NULL,"+
			"SIZE REAL,"+
			"WEIGHT REAL,"+
			"SWITCH TEXT,"+
			"BODYTYPE TEXT)";
			
			String reportTable="CREATE TABLE REPORT "+
			"(SL INTEGER PRIMARY KEY AUTOINCREMENT," +
			"MANUFACTURINGDATE TEXT NOT NULL,"+
			"TESTDATE TEXT NOT NULL,"+			
			"MODELNUMBER TEXT NOT NULL,"+
			"TYPE TEXT,"+
			"RMSVOLTAGE REAL NOT NULL,"+
			"RMSCURRENT REAL NOT NULL,"+
			"POWER REAL NOT NULL,"+
			"POWERFACTOR NOT NULL"+
			"SOUND REAL NOT NULL,"+
			"SPEED REAL NOT NULL,"+
			//"AIRFLOW REAL,"+
			"TEMPERATURE REAL NOT NULL,"+
			"COLOUR TEXT," +
			"DCVOLTAGE REAL," +
			"DCCURRENT REAL," +
			"DCPOWER REAL,"+
			"RID INTEGER NOT NULL UNIQUE)";
			
			
			/**
			String portSpecificationTable="CREATE TABLE PORTSPECIFICATION" +
			"(POWERANALYZER TEXT NOT NULL," +
			"SOUND TEXT NOT NULL," +
			"SPEED TEXT NOT NULL," +
			"AIRFLOW TEXT NOT NULL," +
			"BASETEMPERATURE TEXT NOT NULL)";
			*/
			String airFlowTable="CREATE TABLE AIRFLOW" +
					"(SL INTEGER PRIMARY KEY AUTOINCREMENT," +
					"POSITION_4 REAL," +
					"POSITION_3 REAL," +
					"POSITION_2 REAL," +
					"POSITION_1 REAL," +
					"POSITION0 REAL," +
					"POSITION1 REAL," +
					"POSITION2 REAL," +
					"POSITION3 REAL," +
					"POSITION4 REAL," +
					"RID INTEGER NOT NULL UNIQUE)";
			
			//String portSpecificationTableInfo="INSERT INTO PORTSPECIFICATION(POWERANALYZER,SOUND,SPEED,AIRFLOW,BASETEMPERATURE)VALUES('COM1','COM2','COM3','COM4','COM5')";
			//String
			stmt.executeUpdate(brandTable);
			stmt.executeUpdate(modelTable);
			stmt.executeUpdate(reportTable);
			stmt.executeUpdate(airFlowTable);
			//stmt.execute(userTable);
			//stmt.executeUpdate("INSERT INTO USER(USER,PASSWORD)VALUES('admin','automation');");
			//stmt.executeUpdate("INSERT INTO USER(USER,PASSWORD)VALUES('MasterAdmin','maintenance');");
			//stmt.executeUpdate(portSpecificationTable);
			
			//stmt.executeUpdate(comPortTable);
			stmt.close();
			c.commit();
			c.close();
		}catch(Exception e){
			System.err.println(e.getClass().getName()+":"+e.getMessage());
		}
		
		System.out.println("Success");
	}
	
}
