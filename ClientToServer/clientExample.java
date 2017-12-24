package ClientToServer;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class clientExample {
  public static void main(String[] args) throws Exception {
	  
		String csvFilePath ="C:\\Users\\Admin\\Documents\\newCsvFile7.csv"; // csv file path
		String dbPath = "C:\\Users\\Admin\\Desktop\\sikuliImages\\EmailIDs.sqlite";  //database path
		String query = "select * from EmailIDdata"; //SQL query
		clientExample.sqlTOcsv(csvFilePath,dbPath,query);// this method converts the sql databse to csv. takes in CSVfile path, database file path and the query as argument.
	        clientExample.run(csvFilePath); //takes the csv file path as argument and sends it to server.
  }
  private static void run(String csvFilePath) throws Exception{
	    Socket sock = new Socket("localhost", 1672);
	    File myFile = new File(csvFilePath);
	    byte[] mybytearray = new byte[ (int) myFile.length()];
	    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
	    bis.read(mybytearray, 0, mybytearray.length);
	    OutputStream os = sock.getOutputStream();
	    os.write(mybytearray, 0, mybytearray.length);
	    os.flush();
	    sock.close();
  }

// function to convert sqlite db into CSV file
  public static void sqlTOcsv(String csvFilePath, String sqlitePath,String query) {
		try(Connection conn = DriverManager.getConnection("jdbc:sqlite:"+sqlitePath);
				FileWriter fw = new FileWriter(csvFilePath);)
		{  
			Class.forName("org.sqlite.JDBC");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				fw.append(rs.getString(1));
				fw.append(',');
				fw.append(rs.getString(2));
				fw.append('\n');}
			fw.flush();
			System.out.println("CSV File is created successfully.");
		}catch (Exception e) {
			e.printStackTrace();
		}}

}