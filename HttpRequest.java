package filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringJoiner;

import org.json.JSONException;
import org.omg.CORBA.StringHolder;

import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.jdbc.StringUtils;

public class HttpRequest {

	public static void main(String[] args) throws IOException, JSONException {
        try {
            URL url = new URL("http://192.168.1.90:80/insertStudent.php");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

            writer.write("name=AlexDuvall&password=925dz&email=leftyrighty&grade=10&age=157&family=dude");
            writer.flush();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            writer.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
