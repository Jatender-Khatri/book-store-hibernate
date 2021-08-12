package com.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public static Connection getConnection() {
		Connection con = null;
		if (con == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root",
						"111111");
				System.out.println("Successfully Connnected...");

			} catch (Exception exe) {
				exe.printStackTrace();
			}
		}
		return con;
	}
}
