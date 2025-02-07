import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run(){
        try {
            int port = 8010;
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            while(true){
                System.out.println("Server is listening on" + port);
                Socket accepted = serverSocket.accept();
                System.out.println("connection accepted from client" + accepted.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(accepted.getOutputStream());
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(accepted.getInputStream()));
                toClient.println("Hello from the server");
                fromClient.close();
                toClient.close();
                serverSocket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
