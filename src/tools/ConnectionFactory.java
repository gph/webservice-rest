package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String IP = "127.0.0.1";
	private static final String PORT = "3306";
	private static final String DATABASE = "webservice";
	private static final String URL = "jdbc:mysql://" + IP +":" + PORT + "/" + DATABASE;
	private static final String USER = "root";
	private static final String PASS = "";
	
	public Connection createConn() throws ClassNotFoundException{
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL + "?user=" + USER + "&password=" + PASS);
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		
		return connection;
	}
	
	public void closeConn(Connection conn, java.sql.PreparedStatement pstmt, ResultSet rs) {
		try {
			if(conn != null){
				conn.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			if(rs != null){
				rs.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error!\nFile: ConnectionFactory.java\nMethod: closeConn\n" + e);
			e.printStackTrace();
		}			
	}
	
}