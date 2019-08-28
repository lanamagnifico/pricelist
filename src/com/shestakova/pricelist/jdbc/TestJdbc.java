package com.shestakova.pricelist.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		 String jdbcUrl = "jdbc:mysql://localhost:3306/hb_price_list?useSSl=false&serverTimezone=UTC";
	        String user = "hbpricelist";
	        String pass = "hbpricelist";
	        
	        try {
	        	System.out.println("Connecting to databese "+jdbcUrl);
	        	Connection connection = DriverManager.getConnection(jdbcUrl, user, pass);
	        	System.out.println("Connection succesful!!!");
	        } 
	        catch  (Exception e){
	        	e.printStackTrace();
	        }
	}

}
