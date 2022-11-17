package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseConnectivity {
	
	String driverClassName = "com.mysql.cj.jdbc.Driver";
	String connectionUrl = "jdbc:mysql://localhost:3306/projekat?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
			+ "&rewriteBatchedStatements=true";
	String dbUser = "root";
	String dbPwd = "1234";

	private static DataBaseConnectivity connectionFactory = null;

	private DataBaseConnectivity() {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
		return conn;
	}

	public static DataBaseConnectivity getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new DataBaseConnectivity();
		}
		return connectionFactory;
	}

	public static void closeAll(ResultSet rs, PreparedStatement ps, Connection con) {
		// TODO Auto-generated method stub
		if (rs != null)
	        try {
	            rs.close();
	        } catch (SQLException e) {
	        }
	   /* if (st != null)
	        try {
	            st.close();
	        } catch (SQLException e) {
	        }*/
	    if (ps != null)
	        try {
	            ps.close();
	        } catch (SQLException e) {
	        }
	    if (con != null)
	        try {
	            con.close();
	        } catch (SQLException e) {
	        }
					
	}

}