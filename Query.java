package filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.StringUtils;

/**
 * A compilation of methods to query information
 */
public class Query {
	/**
	 * @deprecated
	 * @param table
	 *            table to use
	 * @param column
	 *            column to return
	 * @param column2
	 *            column to compare
	 * @param value2
	 *            column to check value
	 * @return returns a string separated by ','
	 */
	public static String getInformationPiece(String table, String column, String column2, String value2) {

		try {
			String ad = "";
			Conn.stt.execute("USE NHS");
			ResultSet rs = null;
			if (value2.contains("MD5") || value2.contains("AES") || StringUtils.isStrictlyNumeric(value2)) {
				System.out.println(
						String.format("SELECT %1$s FROM %2$s WHERE %3$s = %4$s;", column, table, column2, value2));
				rs = Conn.stt.executeQuery(
						String.format("SELECT %1$s FROM %2$s WHERE %3$s = %4$s;", column, table, column2, value2));
				System.out.println(
						String.format("SELECT %1$s FROM %2$s WHERE %3$s = %4$s;", column, table, column2, value2));
			} else {
				rs = Conn.stt.executeQuery(
						String.format("SELECT %1$s FROM %2$s WHERE %3$s = '%4$s';", column, table, column2, value2));
				System.err.println(
						String.format("SELECT %1$s FROM %2$s WHERE %3$s = '%4$s';", column, table, column2, value2));
			}
			while (rs.next()) {
				ad += rs.getString(column) + ",";
			}
			Conn.stt.close();
			Conn.con.close();
			return ad;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// check
	/**
	 * Returns an array list of information.
	 * 
	 * @deprecated
	 * @param Table
	 *            which table to use
	 * @param column
	 *            column to display
	 * @param Column2
	 *            column to display
	 * @param column3
	 *            column to display
	 * @param IFcolumn
	 *            where statement
	 * @param value
	 *            value compared
	 * @param ORDERBY
	 *            column to order by
	 * @return returns an arraylist of strings seperated by
	 *         (string,string,string)
	 */
	public static ArrayList<String> getInformationArray(String Table, String column, String Column2, String column3,
			String IFcolumn, String value, String ORDERBY) {

		ArrayList<String> al = new ArrayList<String>();

		try {
			Conn.stt.execute("USE NHS");
			ResultSet rs = null;
			if (!StringUtils.isStrictlyNumeric(value) && !value.contains("AES"))
				rs = Conn.stt.executeQuery(
						String.format("SELECT %1$s,%2$s,%3$s FROM %4$s WHERE %5$s = '%6$s' ORDER BY %7$s;", column,
								Column2, column3, Table, IFcolumn, value, ORDERBY));
			if (StringUtils.isStrictlyNumeric(value) || value.contains("AES"))
				rs = Conn.stt
						.executeQuery(String.format("SELECT %1$s,%2$s,%3$s FROM %4$s WHERE %5$s = %6$s ORDER BY %7$s;",
								column, Column2, column3, Table, IFcolumn, value, ORDERBY));
			while (rs.next()) {
				al.add("(" + rs.getString(column) + ", " + rs.getString(Column2) + ", " + rs.getString(column3) + ")");
			}
			return al;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// check
	/**
	 * Updates information in the DataBase.
	 * 
	 * @deprecated
	 * @param table
	 *            table to use
	 * @param column
	 *            column to set
	 * @param value
	 *            value to set
	 * @param column2
	 *            column compared
	 * @param value2
	 *            value compared
	 */
	public static void updateInformation(String table, String column, String value, String column2, String value2) {

		try {
			Conn.stt.execute("USE NHS");
			if ((!StringUtils.isStrictlyNumeric(value) && !value.contains("AES"))
					&& (!StringUtils.isStrictlyNumeric(value2) && !value2.contains("AES")))
				Conn.stt.execute(String.format("UPDATE %1$s SET %2$s = '%3$s' WHERE %4$s = '%5$s';", table, column,
						value, column2, value2));
			else if ((!StringUtils.isStrictlyNumeric(value) && !value.contains("AES"))
					&& (value2.contains("AES") || StringUtils.isStrictlyNumeric(value2)))
				Conn.stt.execute(String.format("UPDATE %1$s SET %2$s = '%3$s' WHERE %4$s = %5$s;", table, column, value,
						column2, value2));
			else if ((!StringUtils.isStrictlyNumeric(value2))
					&& (value.contains("AES") || StringUtils.isStrictlyNumeric(value)))
				Conn.stt.execute(String.format("UPDATE %1$s SET %2$s = %3$s WHERE %4$s = '%5$s';", table, column, value,
						column2, value2));
			else
				Conn.stt.execute(String.format("UPDATE %1$s SET %2$s = %3$s WHERE %4$s = %5$s;", table, column, value,
						column2, value2));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// check
	/**
	 * Deletes Information in the Database.
	 * 
	 * @deprecated
	 * @param table
	 *            table to use
	 * @param column
	 *            column to check
	 * @param value
	 *            value to check
	 */
	public static void deleteInformation(String table, String column, String value) {

		try {
			Conn.stt.execute("USE NHS");
			if ((column.contains("AES") || !StringUtils.isStrictlyNumeric(column))
					&& (value.contains("AES") || StringUtils.isStrictlyNumeric(value)))
				Conn.stt.execute(String.format("DELETE FROM %1$s WHERE %2$s = %3$s;", table, column, value));
			else
				Conn.stt.execute(String.format("DELETE FROM %1$s WHERE %2$s = '%3$s';", table, column, value));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// check
	/**
	 * Deletes information in the Database.
	 * 
	 * @deprecated
	 * @param table
	 *            table to use
	 * @param column
	 *            column to check
	 * @param value
	 *            value to check
	 * @param column2
	 *            column to check
	 * @param value2
	 *            value to check
	 */
	public static void deleteInformation(String table, String column, String value, String column2, String value2) {

		try {
			Conn.stt.execute("USE NHS");
			if ((!StringUtils.isStrictlyNumeric(value) && !value.contains("AES"))
					&& (!StringUtils.isStrictlyNumeric(value2) && !value2.contains("AES")))
				Conn.stt.execute(String.format("DELETE FROM %1$s WHERE %2$s = '%3$s' WHERE %4$s = '%5$s';", table,
						column, value, column2, value2));
			else if ((!StringUtils.isStrictlyNumeric(value) && !value.contains("AES"))
					&& (value2.contains("AES") || StringUtils.isStrictlyNumeric(value2)))
				Conn.stt.execute(String.format("DELETE FROM %1$s WHERE %2$s = '%3$s' WHERE %4$s = %5$s;", table, column,
						value, column2, value2));
			else if ((!StringUtils.isStrictlyNumeric(value2))
					&& (value.contains("AES") || StringUtils.isStrictlyNumeric(value)))
				Conn.stt.execute(String.format("DELETE FROM %1$s WHERE %2$s = %3$s WHERE %4$s = '%5$s';", table, column,
						value, column2, value2));
			else
				Conn.stt.execute(String.format("DELETE FROM %1$s WHERE %2$s = %3$s WHERE %4$s = %5$s;", table, column,
						value, column2, value2));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// check
	/**
	 * Inserts a new family into the DataBase.
	 * 
	 * @deprecated
	 * @param FamilyPassword
	 *            family password
	 * @param FamilyLeaderName
	 *            family leader name
	 * @param FamilyLeaderID
	 *            family leader ID
	 */
	public static void insertFamily(String FamilyPassword, String FamilyLeaderName, int FamilyLeaderID) {

		try {
			Conn.stt.execute("USE NHS");
			Conn.stt.execute(
					String.format(
							"INSERT INTO FAMILY(FAMILYPASSWORD,FAMILYLEADERNAME,FAMILYLEADERID)"
									+ "VALUES('%1$s',%2$s,%3$s);",
							FamilyPassword, AES_ENCRYPT(FamilyLeaderName, "sgdhdsg&&#$h$!%thwe@*5434rS%$^761!@"),
							FamilyLeaderID));
			// updateInformation("Family", "FamilyLeaderID",
			// HASH(Integer.toString(FamilyLeaderID)),
			// Integer.toString(FamilyLeaderID),
			// Integer.toString(FamilyLeaderID));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// check
	/**
	 * Returns information in string format.
	 * 
	 * @deprecated
	 * @param table
	 *            table to use
	 * @param column
	 *            column to display
	 * @return returns a string seperated by ','
	 */
	public static String getInformation(String table, String column) {

		try {
			Conn.stt.execute("USE NHS");
			ResultSet rs = Conn.stt.executeQuery(String.format("SELECT %1$s FROM %2$s", column, table));
			String variable = "";
			while (rs.next()) {
				variable += rs.getString(column) + ",";
			}
			return variable;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return "null";
	}

	// check
	/**
	 * Returns information in string format.
	 * 
	 * @deprecated
	 * @param table
	 *            table to use
	 * @param column
	 *            column to display
	 * @param column2
	 *            column to check
	 * @param value2
	 *            value to check
	 * @return returns an arraylist of results or null if exception occurs
	 */
	public static ArrayList<String> getInformation(String table, String column, String column2, String value2) {

		try {
			ArrayList<String> ad = new ArrayList<String>();
			Conn.stt.execute("USE NHS");
			ResultSet rs = null;
			if (value2.contains("MD5") || value2.contains("AES") || StringUtils.isStrictlyNumeric(value2)) {
				System.out.println(
						String.format("SELECT %1$s FROM %2$s WHERE %3$s = %4$s;", column, table, column2, value2));
				rs = Conn.stt.executeQuery(
						String.format("SELECT %1$s FROM %2$s WHERE %3$s = %4$s;", column, table, column2, value2));
				System.out.println(
						String.format("SELECT %1$s FROM %2$s WHERE %3$s = %4$s;", column, table, column2, value2));
			} else {
				rs = Conn.stt.executeQuery(
						String.format("SELECT %1$s FROM %2$s WHERE %3$s = '%4$s';", column, table, column2, value2));
				System.err.println(
						String.format("SELECT %1$s FROM %2$s WHERE %3$s = '%4$s';", column, table, column2, value2));
			}
			while (rs.next()) {
				ad.add(rs.getString(column));
			}
			Conn.stt.close();
			Conn.con.close();
			return ad;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// check
	/**
	 * Check if there is a duplicate email in the database.
	 * 
	 * @param email
	 *            email
	 * @return returns true if another email is present.
	 */
	public static boolean isDuplicateEmail(String email) {
		try {
			URL url = new URL("http://192.168.1.90:80/checkemail.php");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(String.format("email=%1$s", email));
			writer.flush();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
			writer.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean isDuplicateFamily(String family) {
		try {
			URL url = new URL("http://192.168.1.90:80/checkfamily.php");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(String.format("email=%1$s", family));
			writer.flush();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
			writer.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * @deprecated checks to see if there is a match in the email and password
	 *             provided from either admin or students
	 * @param email
	 *            email of user
	 * @param password
	 *            password of user
	 * @return s if student, a if admin, null if nothing found.
	 */
	public static String match(String email, String password) {
		String sql1 = Query.getInformationPiece("Students",
				"AES_DECRYPT(EMAIL,'GoDSpe_d8*^42v8y|29jhL0lfReach29^32!@')", "PASSWORD", "MD5('" + password + "')");
		String sql2 = Query.getInformationPiece("ADMINS",
				"AES_DECRYPT(EMAIL,'sfkfj33ntkt#3js3#3jrkj34W&$#!!&()@!@$%^!*sdfw2')", "PASSWORD",
				"MD5('" + password + "')");
		try {

			if (!sql1.isEmpty() && !sql2.isEmpty())
				throw new Exception("FATAL DATABASE ERROR - MULTIPLE ASSIGNED EMAILS ");
			if (!sql1.isEmpty())
				return "s";
			if (!sql2.isEmpty())
				return "a";
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.getMessage();
		} finally {

		}
		return null;
	}

	/**
	 * @deprecated inputs the given string and imports it into a method for
	 *             hashing
	 * @param mm
	 *            string to hash
	 * @return returns a string for hashing in 'md5' format.
	 */
	public static String HASH(String mm) {
		if (StringUtils.isStrictlyNumeric(mm))
			return mm = "md5(" + mm + ")";
		else
			return mm = "md5('" + mm + "')";
	}

	/**
	 * @deprecated turns the string into an aes encryption
	 * @param mm
	 *            the string to encrypt
	 * @param password
	 *            the key to encrypt the string
	 * @return returns the necessary elements for an encryption format
	 *         'aes_encryt(string,key)
	 */
	public static String AES_ENCRYPT(String mm, String password) {
		String x = String.format("AES_ENCRYPT('%1$s','%2$s')", mm, password);
		return x;
	}

	/**
	 * @deprecated
	 * @param mm
	 *            the string to decrypt
	 * @param password
	 *            the key to decrpyt the string
	 * @return returns the necessary elements for an encryption format
	 *         'aes_decrypt(string,key)
	 */
	public static String AES_DECRYPT(String mm, String password) {
		String x = String.format("AES_DECRYPT('%1$s','%2$s')", mm, password);
		return x;
	}

	public static String login(String email, String password) {
		try {
			URL url = new URL("http://192.168.1.90:80/login.php");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(String.format("email=%1$s&password=%2$s", email, password));
			writer.flush();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				if (line.equals("student") || line.equals("admin") || line.equals("no match")) {
					System.out.println(line);
					return line;
				}
			}
			reader.close();
			writer.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int sendMail(String email) {
		try {
			URL url = new URL("http://192.168.1.90:80/sendMail.php");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(
					String.format("email=%1$s",email));
			writer.flush();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
					System.out.println(line);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void main(String[] args) {
		sendMail("ultimateweapon9@gmail.com");			
	}
}
