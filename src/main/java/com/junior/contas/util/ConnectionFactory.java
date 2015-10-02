package com.junior.contas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	
	public Connection getConnection() throws SQLException {
		System.out.println("conectando ...");

		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		
		//return DriverManager.getConnection("jdbc:mysql://localhost:3306/contas", "root", "");
		return DriverManager.getConnection("jdbc:hsqldb:file:C:/Users/Junior/workspace/contas/contas.db","sa", "");
	}

}