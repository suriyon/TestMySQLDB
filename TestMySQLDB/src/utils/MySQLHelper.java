package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLHelper {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/testdb?characterEncoding=UTF-8";
	private static String user = "root";
	private static String password = "root";
	private static Connection con = null;
	
	public static Connection openDB(){
		try {
			Class.forName(driver);//โหลด driver
			con = DriverManager.getConnection(url, user, password);//เปิดการเชื่อมต่อกับ database
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeDB(){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
