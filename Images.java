package filter;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * a compilation of images to replace, write, or get images.
 * @author Alexander
 *
 */
public class Images {
	/**
	 * Returns true if there is a user ID equivalent to the email.
	 * @deprecated
	 * @param Email email of the user.
	 * @return returns true if the user already has an image
	 */
	public static boolean isDuplicateIdentifier(String Email) {
		try {
			PreparedStatement use = Conn.con.prepareStatement("USE NHS");
			PreparedStatement prep = Conn.con.prepareStatement("SELECT USERID FROM IMAGES");
			use.execute();
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == Students.getStudentID(Email))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * This Method inserts an image into the database. Provide the current users
	 * email address. It uses that to find the users ID to use. Get the
	 * fileDirectory of the Image I.E. 'C/Alex/Stuff/More Stuff'
	 * @param Email email of student
	 * @param fileDirectory the director to insert, or replace image
	 */
	public static void insertImage(String Email, File fileDirectory) {
		
		try {
			FileInputStream fs = new FileInputStream(fileDirectory);
			URL url = new URL("http://192.168.1.90:80/InsertImage.php");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			ImageIO.write((RenderedImage) fs, "PNG", conn.getOutputStream());
			writer.write(
					String.format("image=%1$s&email=%3$s&directory=%2$s",fs,fileDirectory,Email ));
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

	/**
	 * Updates the image of the user. Insert the user email and the
	 * Filedirectory of the picture to update the current image with.
	 * @deprecated
	 * @param Email email of student 
	 * @param fileDirectory filedirectory to update or insert image
	 */
	public static void updateImage(String Email, File fileDirectory) {
		
		try {
			FileInputStream fs = new FileInputStream(fileDirectory);
			PreparedStatement use = Conn.con.prepareStatement("USE NHS");
			PreparedStatement prep = Conn.con.prepareStatement("UPDATE IMAGES SET Image = ? WHERE USERID = ?");
			use.execute();
			prep.setBinaryStream(1, fs, fs.available());
			prep.setInt(2, Students.getStudentID(Email));
			prep.executeUpdate();
			System.out.println("success?");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		System.out.println("success?");
	}

	/**
	 * As compared to getImage(String Email), this takes the Email to search for
	 * the image; it then overwrites the image.
	 * @deprecated
	 * @param Email email of student 
	 * @param FileDirectory file directory to insert write new image
	 */

	public static void getImage(String Email, String FileDirectory) {
		
		try {
			InputStream g = null;
			PreparedStatement prep = Conn.con.prepareStatement("USE NHS");
			prep.execute();
			PreparedStatement ImageQuery = Conn.con.prepareStatement("SELECT Image From IMAGES WHERE USERID = ?");
			ImageQuery.setInt(1, Students.getStudentID(Email));
			ResultSet rs = ImageQuery.executeQuery();
			System.out.println("asdfasdf");
			while (rs.next()) {
				System.out.println("sdfsdf");
				g = rs.getBinaryStream(1);
				OutputStream f = new FileOutputStream(new File(FileDirectory));
				int c = 0;
				while ((c = g.read()) > -1) {
					f.write(c);
				}
				f.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			System.out.println("success?");
		}
	}

	/**
	 * As compared to getImage(String Email,String FileDirectory),
	 * getImage(String Email) returns an inputStream variable with the Image
	 * data, which can be viewed and manipulated.
	 * @param Email email of student
	 * @return returns an input stream, null if exception
	 */
	public static InputStream getImage(String Email) {
		try {
			InputStream g = null;
			PreparedStatement prep = Conn.con.prepareStatement("USE NHS");
			prep.execute();
			PreparedStatement ImageQuery = Conn.con.prepareStatement("SELECT Image From IMAGES WHERE Emails = ?");
			ImageQuery.setString(1,Email);
			ResultSet rs = ImageQuery.executeQuery();
			while (rs.next()) {
				g = new BufferedInputStream(rs.getBinaryStream(1));
			}
			
			return g;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		
		try {
			Conn.connect("Kara", "!LEftGrIpS2#7AyyLMaoDuvy2I3F?");
			Image image =null;
			InputStream asdf = new BufferedInputStream(getImage("IMG_20161014_153929.jpg"));
			image = ImageIO.read(asdf);
			JFrame frame  = new JFrame();
			JLabel label = new JLabel(new ImageIcon(image));
			frame.getContentPane().add(label, BorderLayout.CENTER);
			frame.pack();
			frame.setVisible(true);
			Conn.closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
