import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginSignUp {
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void signUp(String fileName, File file) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username/student id: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        clear();
        boolean isExist = false;

        if (!file.exists()) {
            file.createNewFile();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (username.equals(info[0])) {
                    System.out.println("You can't use this username!");
                    isExist = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!isExist) {
                while (password.length() < 8
                        || password.contains(username) ||
                        !password.matches(".*[a-z].*") ||
                        !password.matches(".*[A-Z].*")){
                    System.err.println("Invalid password!");
                    password = scanner.nextLine();
                }
            try (FileWriter fileWriter = new FileWriter(fileName, true)) {
                fileWriter.write(username + "," + password + ",\n");
                System.out.println("you signed up successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void logIn(String fileName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username/student id: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String[] info;
            boolean isExist = false;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (username.equals(info[1])) {
                    if (password.equals(info[2])) {
                        System.out.println("Welcome to Daneshjooyar!");
                        break;
                    } else {
                        System.out.println("Incorrect password. Try again...");
                        do {
                            scanner.nextLine();
                        } while (password.equals(info[1]));
                        clear();
                        System.out.println("Welcome to Daneshjooyar!");
                    }
                    isExist = true;
                    break;
                }
            }
            if (!isExist){
                System.out.println("You have not registered yet.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}