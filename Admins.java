package filter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

//sfkfj33ntkt#3js3#3jrkj34W&$#!!&()@!@$%^!*sdfw2
/**
 * A compilation of methods for the admins
 * 
 * @author Alexander
 *
 */
public class Admins extends Query {
	// check
	/**
	 * This returns the Admin Family, or the Group, the students are in. Insert
	 * the Current Admin's Email to function.
	 * 
	 * @deprecated
	 * @param email
	 *            admin's email
	 * @return returns string.
	 */
	public static String getAdminFamily(String email) {
		return Query.getInformationPiece("ADMIN",
				"AES_DECRYPT(FAMILY,'sfkfj33ntkt#3js3#3jrkj34W&$#!!&()@!@$%^!*sdfw2')",
				"AES_DECRYPT(EMAIL,'sfkfj33ntkt#3js3#3jrkj34W&$#!!&()@!@$%^!*sdfw2')", email);
	}

	// check
	/**
	 * Returns the Family,or group, members of the Current admin's family.
	 * Insert the Current admin's familyName to correctly return the students
	 * ordered by Name.
	 * 
	 * @deprecated
	 * @param family
	 *            the admin's family
	 * @return returns an arraylist of students by name
	 */
	public static ArrayList<String> getStudentsByName(String family) {
		return Query.getInformationArray("Students", "AES_DECRPYT(NAME,'GoDSpe_d8*^42v8y|29jhL0lfReach29^32!@')",
				"hours", "grade", "AES_DECRYPT(FAMILY,'GoDSpe_d8*^42v8y|29jhL0lfReach29^32!@')", family, "NAME");
	}

	// check
	/**
	 * Returns the Family,or group, members of the Current admin's family.
	 * Insert the Current admin's familyName to correctly return the students
	 * ordered by the student's hours.
	 * 
	 * @deprecated
	 * @param family
	 *            the admin's family
	 * @return returns an arraylist of students by hours
	 */
	public static ArrayList<String> getStudentsByHours(String family) {
		return Query.getInformationArray("Students", "AES_DECRPYT(NAME,'GoDSpe_d8*^42v8y|29jhL0lfReach29^32!@')",
				"hours", "grade", "AES_DECRYPT(FAMILY,'GoDSpe_d8*^42v8y|29jhL0lfReach29^32!@')", family, "HOURS");
	}

	// check
	/**
	 * This method adds a new admin to the database. Insert each one
	 * accordingly.
	 * 
	 * @deprecated
	 * @param firstName
	 *            firstname
	 * @param lastName
	 *            lastname
	 * @param Email
	 *            admin email
	 * @param Organization
	 *            organization of admin
	 * @param FamilyPassword
	 *            family password
	 * @param ProfilePassword
	 *            profile password
	 */
	public static void insertAdminJDBC(String firstName, String lastName, String Email, String Organization,
			String FamilyPassword, String ProfilePassword) {

		try {
			Conn.stt.execute("USE NHS");
			Conn.stt.execute(String.format(
					"INSERT INTO ADMINS (FIRST_NAME,LAST_NAME,EMAIL,Organization,FAMILY,PASSWORD)"
							+ "VALUES(%1$s,%2$s,%3$s,'%4$s',%5$s,%6$s)",
					Query.AES_ENCRYPT(firstName, "sfkfj33ntkt#3js3#3jrkj34W&$#!!&()@!@$%^!*sdfw2"),
					Query.AES_ENCRYPT(lastName, "sfkfj33ntkt#3js3#3jrkj34W&$#!!&()@!@$%^!*sdfw2"),
					Query.AES_ENCRYPT(Email, "sfkfj33ntkt#3js3#3jrkj34W&$#!!&()@!@$%^!*sdfw2"), Organization,
					Query.AES_ENCRYPT(FamilyPassword, "sfkfj33ntkt#3js3#3jrkj34W&$#!!&()@!@$%^!*sdfw2"),
					Query.HASH(ProfilePassword)));
			String famPassword = getInformationPiece("Admins", "Family",
					"AES_DECRYPT(EMAIL,'sfkfj33ntkt#3js3#3jrkj34W&$#!!&()@!@$%^!*sdfw2')", Email);
			String Name = getInformation("Admins",
					"AES_DECRYPT(FIRST_NAME,'sfkfj33ntkt#3js3#3jrkj34W&$#!!&()@!@$%^!*sdfw2')",
					"AES_DECRYPT(EMAIL,'sfkfj33ntkt#3js3#3jrkj34W&$#!!&()@!@$%^!*sdfw2')", Email)
					+ " "
					+ getInformation("Admins",
							"AES_DECRYPT(LAST_NAME,'sfkfj33ntkt#3js3#3jrkj34W&$#!!&()@!@$%^!*sdfw2')",
							"AES_DECRYPT(EMAIL,'sfkfj33ntkt#3js3#3jrkj34W&$#!!&()@!@$%^!*sdfw2')", Email);
			int ID = Integer.parseInt(getInformationPiece("Admins", "ID",
					"AES_DECRYPT(EMAIL,'sfkfj33ntkt#3js3#3jrkj34W&$#!!&()@!@$%^!*sdfw2')", Email));
			insertFamily(famPassword, Name, ID);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// check
	/**
	 * 
	 * Returns the Family,or group, members of the Current admin's family.
	 * Insert the Current admin's familyName to correctly return the students
	 * ordered by Grade.
	 * 
	 * @deprecated
	 * @param family
	 *            admin's family
	 * @return returns an array of students by name
	 */
	public static ArrayList<String> getStudentsByGrade(String family) {
		return Query.getInformationArray("Students", "AES_DECRPYT(NAME,'GoDSpe_d8*^42v8y|29jhL0lfReach29^32!@')",
				"hours", "grade", "AES_DECRYPT(FAMILY,'GoDSpe_d8*^42v8y|29jhL0lfReach29^32!@')", family, "grade");

	}

	// check
	/**
	 * Returns the Family members of the current admin's students.
	 * 
	 * @deprecated
	 * @param family
	 *            family of admin
	 * @return returns an arraylist of information formated(NAME,HOURS,GRADE)
	 */
	public static ArrayList<String> getFamilyMembers(String family) {
		ArrayList<String> al = new ArrayList<String>();

		try {
			Conn.stt.execute("USE NHS");
			ResultSet rs = Conn.stt.executeQuery(String.format(
					"SELECT AES_DECRPYT(NAME,'GoDSpe_d8*^42v8y|29jhL0lfReach29^32!@'),HOURS,GRADE FROM STUDENTS WHERE AES_DECRYPT(FAMILY,'GoDSpe_d8*^42v8y|29jhL0lfReach29^32!@') = '%1$s';",
					family));
			while (rs.next()) {
				al.add("(" + rs.getString("NAME") + ", " + rs.getString("HOURS") + ", " + rs.getString("GRADE") + ")");
			}
			return al;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * PHP and Java operated method used to retrieve student information.
	 * 
	 * @param family
	 *            family
	 * @param order
	 *            order of information
	 * @return returns a 2d arraylist that is a string.
	 */
	public static ArrayList<ArrayList<String>> fetchStudentInformation(String family, String order) {
		try {
			ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
			URL url = new URL(String.format(
					"http://192.168.1.90:80/fetchStudentInformationA.php?family=%1$s&order=%2$s", family, order));
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

	public static void insertAdmin(String firstName, String lastName, String organization, String Email, String family,
			String password) {
		try {
			URL url = new URL("http://192.168.1.90:80/InsertAdmin.php");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(
					String.format("firstName=%1$s&lastName=%2$s&organization=%3$s&email=%4$s&family=%5$s&password=%6$s",
							firstName, lastName, organization.replaceAll(" ", "+"), Email, family, password));
			writer.flush();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	}
}