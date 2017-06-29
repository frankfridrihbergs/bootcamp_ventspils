package com.bootcamp;

import java.sql.*;

import org.springframework.web.bind.annotation.RequestParam;

public class Mys {
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://sql8.freemysqlhosting.net:3306/id2079363_library";

	   static final String USER = "sql8182588";
	   static final String PASS = "gBVUqBdkqC";
   Connection conn;
   Statement stmt;


   public User getSomeInfo(@RequestParam("getWhat") String getWhat,String info, String info2, int info3){
   try{
	  User user = new User();
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      
      stmt = conn.createStatement();
      String sql = null;
      ResultSet rs = null;
      
      
      
      
      
      
      switch (getWhat){
      case "infoByName":
    	  sql = "SELECT name, username, surname, password, role FROM users Where name = \"" + info+"\"";
    	  break;
      case "infoBySurname":
    	  sql = "SELECT name, username, surname, password, role FROM users Where surname = \"" + info+"\"";
    	  break;
      case "infoByUsername":
    	  sql = "SELECT name, username, surname, password, role FROM users Where username = \"" + info+"\"";
    	  break;
      default:
    	 return null;
      }
      
      
      
      
      
      
      if(sql != null){
      rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
    	  user.setName(rs.getString("name"));
    	  user.setSurname(rs.getString("surname"));
    	  user.setPass(rs.getString("password"));
      }
      }
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
      return user;
   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
   return null;
   }
}