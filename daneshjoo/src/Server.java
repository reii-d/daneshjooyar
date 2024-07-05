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
            case "GET: logInChecker":
                //0 -> username no
                //1 -> username yes, password no
                //2 -> username yes, password yes
                boolean signedIn = false;
                int responseDatabase = 100;
                try {
                    responseDatabase = Database.getInstance().logIn(splitter[1], splitter[2]);
                } catch (IOException e){
                    throw new RuntimeException();
                }
                if (responseDatabase == 2){
                    signedIn = true;
                    System.out.println("code 200");
                    System.out.println("logged in successfully!");
                    try {
                        writer("200");
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if (responseDatabase == 1){
                    signedIn = false;
                    System.out.println("code 100");
                    System.out.println("password is not correct");
                    try{
                        writer("100");
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if (responseDatabase == 0){
                    signedIn = false;
                    System.out.println("code 000");
                    System.out.println("user not found");
                    try {
                        writer("000");
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
                break;
            //signup and other commands
        }
    }

    public void writer(String write) throws IOException {
        dos.writeBytes(write);
        dos.flush();
        dos.close();
        dis.close();
        socket.close();
        System.out.println(write);
        System.out.println("finished");
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