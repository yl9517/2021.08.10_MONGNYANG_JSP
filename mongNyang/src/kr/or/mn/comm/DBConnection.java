package kr.or.mn.comm;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DBConnection {
	private static DBConnection dbInstance = new DBConnection();
	public static DBConnection getDBInstance() {
		return dbInstance;
	}
	
	private DBConnection() {};
	
	public Connection getConnection() throws SQLException,NamingException{
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/kosta");
		
		Connection conn = ds.getConnection();
		return conn;
	}
	
}
