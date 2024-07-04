import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main (String[] args) throws IOException {
        System.out.println("Welcome to the Server!");
        ServerSocket serverSocket = new ServerSocket(4884);
        while (true) {
            System.out.println("Waiting for the Client...");
            new handleClient(serverSocket.accept()).start();
        }
    }
}

class handleClient extends Thread {
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    public handleClient(Socket socket) throws IOException {
        this.socket = socket;
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        System.out.println("Connected!");
    }

    @Override
    public void run() {
        super.run();
        String command;
        try {
            command = receiver();
            System.out.println("command: " + command);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        String[] splitter = command.split(",");
        switch (splitter[0]){
            //case
            //TODO
        }
    }
    public String receiver() throws IOException {
        System.out.println("Receiving...");
        StringBuilder builder = new StringBuilder();
        int num = dis.read();
        while (num != 0) {
            builder.append((char) num);
            num = dis.read();
        }
        System.out.println("Command read successfully!");
        return builder.toString();
    }
}
