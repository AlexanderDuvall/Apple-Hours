package filter;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A compilation of methods for the 'student' part of the application.
 * 
 * @author Alexander
 *
 */
public class Students {
	// GoDSpe_d8*^42v8y|29jhL0lfReach29^32!@
	/**
	 * Inserts a Student into the database.
	 * 
	 * @param fullName
	 *            students fullName
	 * @param password
	 *            students password
	 * @param Email
	 *            students email
	 * @param grade
	 *            students grade level
	 * @param age
	 *            students age
	 * @param Family
	 *            students family
	 */
	public static void insertStudent(String fullName, String password, String Email, int grade, String age,
			String Family) {
		try {
			URL url = new URL("http://192.168.1.90:80/InsertStudent.php");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(String.format("name=%1$s&password=%2$s&email=%3$s&grade=%4$s&age=%5$s&family=%6$s",
					fullName.replaceAll(" ", "+"), password, Email, grade, age, Family.replaceAll(" ", "+")));
			writer.flush();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null && line != "") {
				System.out.println(line);
			}
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// check
	/**
	 * @deprecated returns a student ID
	 * @param email
	 *            students email
	 * @return returns the students id as an int.
	 */
	public static int getStudentID(String email) {

		return Integer.parseInt(Query.getInformationPiece("Students", "ID",
				"AES_DECRYPT(EMAIL,'GoDSpe_d8*^42v8y|29jhL0lfReach29^32!@')", email));
	}

	/// check
	/**
	 * returns an array list of the students statistics ordered by place.
	 * 
	 * @deprecated
	 * @param Email
	 *            students mail
	 * @return returns an arraylist of the students statistics.
	 */
	public static ArrayList<String> getStudentStatsByPlace(String Email) {
		return Query.getInformationArray("Hours", "AES_DECRPYT(grp,'292323$@$%25235dbsdvzxcjf6u$^2342%!&*(!@$')",
				"DATE_WORKED", "HOURS_WORKED", "AES_DECRYPT(EMAIL,'292323$@$%25235dbsdvzxcjf6u$^2342%!&*(!@$')", Email,
				"grp");
	}

	// check
	/**
	 * returns an array list of the students statistics ordered by date.
	 * 
	 * @deprecated
	 * @param Email
	 *            Student email
	 * @return returns an arraylist of the students statistics.
	 */
	public static ArrayList<String> getStudentsStatsByDate(String Email) {
		return Query.getInformationArray("Hours", "AES_DECRPYT(grp,'292323$@$%25235dbsdvzxcjf6u$^2342%!&*(!@$')",
				"DATE_WORKED", "HOURS_WORKED", "AES_DECRYPT(EMAIL,'292323$@$%25235dbsdvzxcjf6u$^2342%!&*(!@$')", Email,
				"DATE_WORKED");
	}

	// check
	/**
	 * returns an array list of the students statistics ordered by hours.
	 * 
	 * @deprecated
	 * @param Email
	 *            Student email
	 * @return returns an arraylist of the students statistics.
	 */
	public static ArrayList<String> getStudentsStatsByHours(String Email) {
		return Query.getInformationArray("Hours", "AES_DECRPYT(grp,'292323$@$%25235dbsdvzxcjf6u$^2342%!&*(!@$')",
				"DATE_WORKED", "HOURS_WORKED", "AES_DECRYPT(EMAIL,'292323$@$%25235dbsdvzxcjf6u$^2342%!&*(!@$')", Email,
				"HOURS_WORKED");
	}

	// check
	/**
	 * returns the total hours of a student.
	 * 
	 * @deprecated
	 * @param Email
	 *            Student email
	 * @return returns an integer.
	 */
	public static String getTotalStudentHours(String Email) {
		return Query.getInformationPiece("Students", "HOURS",
				"AES_DECRPYT(EMAIL,'GoDSpe_d8*^42v8y|29jhL0lfReach29^32!@')", Email);
	}

	// check
	/**
	 * Returns the student name.
	 * 
	 * @deprecated
	 * @param Email
	 *            Student's email
	 * @return returns a string
	 */
	public static String getStudentName(String Email) {
		return Query.getInformationPiece("Students", "AES_DECRPYT(NAME,'GoDSpe_d8*^42v8y|29jhL0lfReach29^32!@')",
				"AES_DECRYPT(EMAIL,'GoDSpe_d8*^42v8y|29jhL0lfReach29^32!@')", Email);
	}

	// check
	/**
	 * Returns the students family.
	 * 
	 * @deprecated
	 * @param Email
	 *            Student email
	 * @return returns a string
	 */
	public static String getStudentFamily(String Email) {
		return Query.getInformationPiece("Students", "AES_DECRYPT(FAMILY,'GoDSpe_d8*^42v8y|29jhL0lfReach29^32!@')",
				"AES_DECRYPT(EMAIL,'GoDSpe_d8*^42v8y|29jhL0lfReach29^32!@')", Email);
	}

	/**
	 * PHP and Java operated method used to retrieve student information.
	 * @d
	 * @return returns a 2d arraylist that is a string.
	 */
	public static ArrayList<ArrayList<String>> fetchStudentInformation(String email) {
		try {
			ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
			URL url = new URL(String.format("http://192.168.1.90:80/fetchStudentInformationS.php?email=%1$s", email));
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				if (line.length() != 0) {
					ArrayList<String> arg = new ArrayList<String>();
					arg.addAll(Arrays.asList(line.split(",")));
					info.add(arg);
				}
			}
			reader.close();
			return info;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		insertStudent("Emelia Avan", "Aincrad", "Jeem@gmail.com", 9, "December 4, 1994", "mupples");
	}
}
