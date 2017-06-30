package filter;

import java.sql.DriverManager;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
/**
 * Compilation of methods to connect to the server
 * @author Alexander
 *
 */
//192.168.1.90
public class Conn {
	//public static String url = "jdbc:mysql://localhost";
	public static String url = "jdbc:mysql://192.168.1.90:3306/";
	//public static String user = "root";
	//public static String user = "root";
	//public static String password = "operation_whiteout";
	//public static String password = "!LEftGrIpS2#7AyyLMaoDuvy2I3F?";
	public static Connection con;
	public static Statement stt;

	/**
	 * Returns true if the database is connected,false if not connected.
	 * 
	 * @return true if connected, else false
	 */
	public static boolean isConnected() {
		try {
			if (!con.isClosed())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Connects to the server.
	 * @param user the user of the database 
	 * @param password the password of the user
	 * 
	 */
	public static void connect(String user, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = (Connection) DriverManager.getConnection(url, user, password);
			stt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * closes connections to the server.
	 */
	public static void closeConnection() {
		try {
			stt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
public static void main(String[] args) {
	connect("Kara", "!LEftGrIpS2#7AyyLMaoDuvy2I3F?");
	System.out.println(isConnected());
	closeConnection();
}
}
