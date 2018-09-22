import java.net.*;
import java.io.*;

public class Server {
    public static void startServer(int port,String file) throws IOException {
        ServerSocket serverSocket = null;
		System.out.println("Inside the server class");

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }

        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;

        try {
		System.out.println("Before starting for listening");
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("Can't accept client connection. ");
        }

        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
        }

        try {
            out = new FileOutputStream(file);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. ");
        }

        byte[] bytes = new byte[16*1024];

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }
System.out.println("Closing connection");
        out.close();
        in.close();
        socket.close();
        serverSocket.close();
		System.out.println("Closed connection");
    }
}
