/**package filter;

public class Creation {
	public static void create_student() {
		try {
			Conn.connect("root", "password");
			Conn.stt.execute("CREATE DATABASE IF NOT EXISTS NHS");
			Conn.stt.execute("USE NHS");
			Conn.stt.execute("DROP TABLE IF EXISTS Students");
			Conn.stt.execute("CREATE TABLE Students(" + "ID INT NOT NULL AUTO_INCREMENT,"
					+ "Name varchar(100) NOT NULL," + "Password varchar(100) NOT NULL,"
					+ "Email varchar(130) NOT NULL UNIQUE," + "Grade Integer NOT NULL," + "Age Integer NOT NULL,"
					+ "Family varchar(100)," + "Hours INT," + "PRIMARY KEY(ID)" + ");");
			Conn.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void create_admin() {

		try {
			Conn.connect("root", "password");
			Conn.stt.execute("CREATE DATABASE IF NOT EXISTS NHS");
			Conn.stt.execute("USE NHS");
			Conn.stt.execute("DROP TABLE IF EXISTS Admins");
			Conn.stt.execute(
					"CREATE TABLE Admins (" + "ID INT NOT NULL AUTO_INCREMENT," + "FIRST_NAME varchar(100) NOT NULL,"
							+ "LAST_NAME varchar(100) NOT NULL," + "EMAIL varchar(200) NOT NULL UNIQUE,"
							// + "SCHOOL varchar(250) NOT NULL,"
							// + "CITY varchar(250) NOT NULL,"
							// + "DISTRICT varchar(250) NOT NULL,"
							+ "Organization varchar(250) NOT NULL," + "FAMILY varchar(250)," + "PASSWORD varchar(250),"
							+ "PRIMARY KEY(ID));");
			Conn.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void create_messages() {

		try {
			Conn.connect("root", "password");
			Conn.stt.execute("CREATE DATABASE IF NOT EXISTS NHS");
			Conn.stt.execute("USE NHS");
			Conn.stt.execute("DROP TABLE IF EXISTS messages");
			Conn.stt.execute("CREATE TABLE messages(" + "Message varchar(300)," + "Dates varchar(255),"
					+ "Family varchar(100));");
			Conn.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void create_hours() {

		try {
			System.out.println("CREATE TABLE IF NOT EXISTS hours("
					+ "HOUR_ID int NOT NULL auto_increment," 
					+ "EMAIL varchar(255) NOT NULL,"
					+ "NAME varchar(255) NOT NULL," 
					+ "GRP varchar(255) NOT NULL,"
					+ "family varchar(255) NOT NULL,"
					+ "Explanation TEXT NOT NULL,"
					+ "DATE_WORKED varchar(255) NOT NULL,"
					+ "Hours_WORKED INT NOT NULL,"
					+ "DATE_SUBMITTED varchar(255),"
					+ "PUNCTIALITY_AND_ATTENDANCE varchar(255),"
					+ "EFFORT_AND_COMMITMENT varchar(255),"
					+ "FURTHER_COMMENTS varchar(255) ,"
					+ "Completion varchar(255) ,"
					+ "Activity_Leader_Name varchar(255) ,"
					+ "Activity_Leader_Email varchar(255),"
					+ "RESULT varchar(20),"
					+ "PRIMARY KEY(HOUR_ID));");
			Conn.connect("root", "password");
			Conn.stt.execute("CREATE DATABASE IF NOT EXISTS NHS");
			Conn.stt.execute("USE NHS");
			Conn.stt.execute("DROP TABLE IF EXISTS hours");
			Conn.stt.execute("CREATE TABLE IF NOT EXISTS hours("
					+ "HOUR_ID int NOT NULL auto_increment," 
					+ "EMAIL varchar(255) NOT NULL,"
					+ "NAME varchar(255) NOT NULL," 
					+ "GRP varchar(255) NOT NULL,"
					+ "family varchar(255) NOT NULL,"
					+ "Explanation TEXT NOT NULL,"
					+ "DATE_WORKED varchar(255) NOT NULL,"
					+ "Hours_WORKED INT NOT NULL,"
					+ "DATE_SUBMITTED varchar(255),"
					+ "PUNCTIALITY_AND_ATTENDANCE varchar(255),"
					+ "EFFORT_AND_COMMITMENT varchar(255),"
					+ "FURTHER_COMMENTS varchar(255) ,"
					+ "Completion varchar(255) ,"
					+ "Activity_Leader_Name varchar(255) ,"
					+ "Activity_Leader_Email varchar(255),"
					+ "RESULT varchar(20),"
					+ "PRIMARY KEY(HOUR_ID));");
			Conn.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void create_family() {
		try {
			Conn.connect("root", "password");
			Conn.stt.execute("CREATE DATABASE IF NOT EXISTS NHS");
			Conn.stt.execute("USE NHS");
			Conn.stt.execute("DROP TABLE IF EXISTS FAMILY");
			Conn.stt.execute("CREATE TABLE IF NOT EXISTS FAMILY(" + "FamilyPassword varchar(255) NOT NULL, "
					+ "FamilyLeaderName varchar(255) NOT NULL, " + "FamilyLeaderID INT NOT NULL,"
					+ "PRIMARY KEY(FamilyLeaderID));");
			Conn.closeConnection();
		} catch (Exception E) {
			E.printStackTrace();
		}
	}

	public static void create_Images(){
		try {
			Conn.connect("root", "password");
			Conn.stt.execute("CREATE DATABASE IF NOT EXISTS NHS");
			Conn.stt.execute("USE NHS");
			Conn.stt.execute("DROP TABLE IF EXISTS Images");
			Conn.stt.execute("CREATE TABLE Images( USERID INT NOT NULL, IMAGES BLOB NOT NULL);");
			Conn.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}public static void main(String[] args) {
create_admin();
create_family();
create_hours();
create_Images();
create_messages();
create_student();}

}*/
