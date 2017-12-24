package ClientToServer;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class serverExample {
  public static void main(String[] args) throws IOException {
    ServerSocket servsock = new ServerSocket(1672);
   
    while (true) {
      Socket sock = servsock.accept();
     byte[] mybytearray = new byte[1024];
      InputStream is = sock.getInputStream();
      FileOutputStream fos = new FileOutputStream("s7.csv");
      BufferedOutputStream bos = new BufferedOutputStream(fos);
      int bytesRead = is.read(mybytearray, 0, mybytearray.length);
      bos.write(mybytearray, 0, bytesRead);
      bos.close();
      sock.close();
    }}}