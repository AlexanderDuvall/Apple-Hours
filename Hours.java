package filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Compilation of methods to insert hours
 * 
 * @author Alexander
 *
 */
public class Hours {
	// check
	// 292323$@$%25235dbsdvzxcjf6u$^2342%!&*(!@$
	/**
	 * @deprecated Inserts the first portion of hours.
	 * @param Email
	 *            student email
	 * @param group
	 *            group student worked for
	 * @param explanation
	 *            explanation of work done
	 * @param Date_Worked
	 *            date worked
	 * @param hours_worked
	 *            hours worked
	 * @param date_submitted
	 *            date information submitted
	 * @param leader_email
	 *            the leaders email to confirma hours performed
	 */
	public static void insertFirstPartHours(String Email, String group, String explanation, String Date_Worked,
			int hours_worked, String date_submitted, String leader_email) {

		if (Query.isDuplicateEmail(Email)) {
			try {
				String name = Students.getStudentName(Email);
				String family = Students.getStudentFamily(Email);
				Conn.stt.execute("USE NHS");
				Conn.stt.execute(String.format(
						"INSERT INTO HOURS(Email,Name,Grp,family,explanation,date_worked,hours_worked,date_submitted,ACTIVITY_LEADER_EMAIL)"
								+ "Values(%1$s,'%2$s',%3$s,%4$s,'%5$s','%6$s',%7$s,'%8$s',%9$s);",
						Query.AES_ENCRYPT(Email, "292323$@$%25235dbsdvzxcjf6u$^2342%!&*(!@$"), name,
						Query.AES_ENCRYPT(group, "292323$@$%25235dbsdvzxcjf6u$^2342%!&*(!@$"),
						Query.AES_ENCRYPT(family, "292323$@$%25235dbsdvzxcjf6u$^2342%!&*(!@$"), explanation,
						Date_Worked, hours_worked, date_submitted,
						Query.AES_ENCRYPT(leader_email, "292323$@$%25235dbsdvzxcjf6u$^2342%!&*(!@$")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Email Found");
		}

	}

	// check
	/**
	 * updates the secons portion of hours.
	 * 
	 * @deprecated do not use in java
	 * @param punctiality_and_Attendance
	 *            null
	 * @param effort_and_Commitment
	 *            null
	 * @param Further_comments
	 *            null
	 * @param Completion
	 *            null
	 * @param Activity_Leader_Name
	 *            null
	 * @param Result
	 *            null
	 * @param Student_Email
	 *            null
	 */
	public static void insertSecondPartHours(String punctiality_and_Attendance, String effort_and_Commitment,
			String Further_comments, String Completion, String Activity_Leader_Name, String Result,
			String Student_Email) {

		try {
			Conn.stt.execute("USE NHS");
			Conn.stt.execute(String.format(
					"UPDATE Hours SET PUNCTIALITY_AND_ATTENDANCE = '%1$s', EFFORT_AND_COMMITMENT = '%2$s', Further_Comments = '%3$s',"
							+ " Completion = '%4$s', Activity_Leader_Name = '%5$s',RESULT = %7$s WHERE STUDENT_ID = %6$s;",
					punctiality_and_Attendance, effort_and_Commitment, Further_comments, Completion,
					Query.AES_ENCRYPT(Activity_Leader_Name, "292323$@$%25235dbsdvzxcjf6u$^2342%!&*(!@$"),
					Students.getStudentID(Student_Email), Result));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void insertHours(String Email, String group, String explanation, String Date_Worked, int hours_worked,
			String leader_email) {

		try {
			URL url = new URL("http://192.168.1.90:80/InsertHours.php");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(String.format(
					"email=%1$s&group=%2$s&explanation=%3$s&dateworked=%4$s&hoursworked=%5$s&activityleaderemail=%6$s",
					Email, group.replaceAll(" ", "+"), explanation.replaceAll(" ", "+"), Date_Worked, hours_worked,
					leader_email));
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
	}

	public static String getHours(String email) {
		try {
			URL url = new URL("http://192.168.1.90:80/hours_page.php?");
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

	public static void main(String[] args) {
		getHours("ultimateweapon9@gmail.com");
	}
}
