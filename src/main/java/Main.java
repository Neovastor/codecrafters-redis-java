import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args) {
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;
    int port = 6379;
    try {
      serverSocket = new ServerSocket(port);
      serverSocket.setReuseAddress(true);
      // Wait for connection from client.
      clientSocket = serverSocket.accept();
      InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
      OutputStreamWriter outputStreamWriter =
          new OutputStreamWriter(clientSocket.getOutputStream());
      bufferedReader = new BufferedReader(inputStreamReader);
      bufferedWriter = new BufferedWriter(outputStreamWriter);
      String fromClient = bufferedReader.readLine();
      System.out.println("fromClient = " + fromClient);
      bufferedWriter.write("+PONG\r\n");
      bufferedWriter.flush();
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    } finally {
      try {
        if (bufferedReader != null)
          bufferedReader.close();
        if (bufferedWriter != null)
          bufferedReader.close();
        if (clientSocket != null) {
          clientSocket.close();
        }
      } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
      }
    }
  }
}

