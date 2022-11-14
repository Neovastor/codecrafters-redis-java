import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args) {
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    int port = 6379;
    try {
      serverSocket = new ServerSocket(port);
      serverSocket.setReuseAddress(true);
      // Wait for connection from client.
      clientSocket = serverSocket.accept();

      // takes input from the client socket
      DataInputStream din = new DataInputStream(clientSocket.getInputStream());

      // send output to the client socket
      DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());

      String line = "";

      int readByte;
      // reads message from client until "Over" is sent
      //      while (!line.equals("Over")) {
      //        try {
      //          readByte = din.read();
      //        line = din.readUTF();
      //          System.out.println(line);
      //          dout.writeUTF("+PONG\r\n");
      //          dout.flush();
      //        } catch (IOException i) {
      //          System.out.println(i);
      //        }
      //      }
      dout.writeUTF("+PONG\r\n");
      dout.flush();
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    } finally {
      try {
        if (clientSocket != null) {
          clientSocket.close();
        }
      } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
      }
    }
  }
}

