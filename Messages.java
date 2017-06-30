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
 * A compilation of methods for Messages
 * 
 * @author Alexander
 *
 */
public class Messages extends Query {
	// check
	/**
	 * Returns an array list of messages created by an Admin.
	 * 
	 * @deprecated
	 * @param family
	 *            which family to select
	 * @return Arraylist of strings
	 */
	public static ArrayList<String> getMessages(String family) {
		return getInformationArray("Messages", "message", "Date", "Family", "Family", family, "Date");
	}

	// check
	/**
	 * Inserts a message created by an admin.
	 * 
	 * 
	 * @param Message
	 *            message to display
	 * @param Email
	 *            admins email
	 */
	public static void insertMessage(String Message, String Email) {
try{
	URL url = new URL("http://192.168.1.90:80/InsertMessage.php?");
	URLConnection conn = url.openConnection();
	conn.setDoOutput(true);
	OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
	writer.write(
			String.format("message=%1$s&email=%2$s",Message,Email));
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

} catch (Exception e){
	e.printStackTrace();
}
	}

	/**
	 * 
	 * @param Family
	 *            Students family
	 * @return returns an array of strings
	 */
	public static ArrayList<String> fetchMessages(String Family) {
		try {
			URL url = new URL(String.format("http://192.168.1.90:80/getMessage.php?family=%1$s", Family));
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			String line;
			String build = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				build += line + ",";
			}
			reader.close();
			ArrayList<String> ff = new ArrayList<String>();
			ff.addAll(Arrays.asList(build.split(",")));
			for (int i = 0; i < ff.size(); i++) {
				if(ff.get(i).equals("null")){
					ff.remove(i);
				}
			}
			return ff;
		} catch (Exception e) {
			// TODO Auto-generated catch block-
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
//insertMessage("We have a new oppurtunity for those who need hours. Come by for more information.","Eeves@gmail.com");
		System.out.println(fetchMessages("mupples"));
	}
}
