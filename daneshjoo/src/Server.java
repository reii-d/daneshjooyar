import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.xml.crypto.Data;

////////////////////////////////////////////////ineeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
public class Server {
    public static void main (String[] args) throws IOException {
        System.out.println("Welcome to the Server!");
        ServerSocket serverSocket = new ServerSocket(8080);
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
            String[] splitter = command.split(",");
            switch (splitter[0]) {

                case "GET: logInChecker": //LOGIN
                    //0 -> username No
                    //1 -> username Yes, password No
                    //2 -> username Yes, password Yes
                    boolean signedIn = false;
                    int responseDatabase = 100;
                    try {
                        responseDatabase = Database.getInstance().logIn(splitter[1], splitter[2]);
                    } catch (IOException e) {
                        System.err.println("Error accessing database: " + e.getMessage());
                        return;  // Exit the method gracefully
                    }
                    if (responseDatabase == 2) {
                        signedIn = true;
                        System.out.println("code 200");
                        System.out.println("logged in successfully!");
                        try {
                            writer("200");
                        } catch (IOException e) {
                            System.err.println("Error writing response: " + e.getMessage());
                        }
                    } else if (responseDatabase == 1) {
                        System.out.println("code 100");
                        System.err.println("password is not correct");
                        try {
                            writer("100");
                        } catch (IOException e) {
                            System.err.println("Error writing response: " + e.getMessage());
                        }
                    } else if (responseDatabase == 0) {
                        System.out.println("code 000");
                        System.err.println("user not found");
                        try {
                            writer("000");
                        } catch (IOException e) {
                            System.err.println("Error writing response: " + e.getMessage());
                        }
                    }
                    break;

                case "GET: SignUpCheck":
                    //0 -> StudentID is in use
                    //2 -> Signed up successfully
                    boolean signedUp = false;
                    int response = 100;
                    try {
                        response = Database.getInstance().signUp(splitter[1], splitter[2], splitter[3]);
                    }catch (IOException e){
                        System.err.println("Error accessing database: " + e.getMessage());
                        return;  // Exit the method gracefully
                    }
                    if (response == 0) {
                        System.out.println("code 000");
                        System.out.println("StudentId is in use");
                        try {
                            writer("000");
                        } catch (IOException e) {
                            System.err.println("Error writing response: " + e.getMessage());
                        }
                    }
                    else if (response == 2){
                        signedUp = true;
                        System.out.println("code 200");
                        System.out.println("Signed up successfully");
                        try {
                            writer("200");
                        } catch (IOException e) {
                            System.err.println("Error writing response: " + e.getMessage());
                        }
                    }
                    break;

                case "GET: DeleteAccount":
                    try {
                        Database.getInstance().deleteAccount(splitter[2]);
                    } catch (IOException e){
                        System.err.println("Error accessing database: " + e.getMessage());
                        return;  // Exit the method gracefully
                    }
                    try {
                        writer("200");
                    } catch (IOException e){
                        System.err.println("Error accessing database: " + e.getMessage());
                        return;  // Exit the method gracefully
                    }
                    break;

                case "GET: SaraInfo":
                    try {
                        String response1 = Database.getInstance().saraInfo(splitter[1]);
                        System.out.println(response1);
                        writer(response1 + "\u0000");
                    } catch (IOException e) {
                        System.err.println("Error accessing database: " + e.getMessage());
                    }
                    break;

                case "GET: ProfileInfo":
                    try {
                        Database.getInstance().GPA(splitter[1]);
                        writer(Database.getInstance().GPA(splitter[1]));
                    } catch (IOException e) {
                        System.err.println("Error accessing database: " + e.getMessage());
                    }
                    break;

                case "GET: Classes":
                    try {
                        Database.getInstance().classaInfo(splitter[1]);
                        writer(Database.getInstance().classaInfo(splitter[1]));
                    } catch (IOException e) {
                        System.out.println("Error accessing database: " + e.getMessage());
                    }
                    break;

                case "GET: AddClassa":
                    try {
                        Database.getInstance().addClassaInfo(splitter[1], splitter[2]);
                    } catch (IOException e){
                        System.err.println("Error accessing database: " + e.getMessage());
                    }

                case "GET: TamrinaInfo":
                    try {

                        System.out.println(Database.getInstance().tamrinaInfo(splitter[1]));
                        Database.getInstance().tamrinaInfo(splitter[1]);
                        writer(Database.getInstance().tamrinaInfo(splitter[1]));
                    } catch (IOException e){
                        System.err.println("Error accessing database: " + e.getMessage());
                    }
                default:
                    System.err.println("Unknown command: " + splitter[0]);
            }
        } catch (IOException e) {
            System.err.println("Error receiving command: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Runtime exception: " + e.getMessage());
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