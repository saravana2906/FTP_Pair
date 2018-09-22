import java.net.*;
import java.io.*;

public class Client {
    public static void sendfile(String server,int port,String file_path) throws IOException {
        Socket socket = null;
        String host = server;

        socket = new Socket(host,port);

        File file = new File(file_path);
        // Get the size of the file
        long length = file.length();
        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = socket.getOutputStream();

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
    }
}