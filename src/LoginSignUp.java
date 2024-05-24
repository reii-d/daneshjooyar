import org.junit.platform.engine.support.discovery.SelectorResolver;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;

public class LoginSignUp {
    public static void main (String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName = "students.txt";
        File file = new File(fileName);
        System.out.println("Choose an option: \n1. log in\n2. sign up");
        int choice = scanner.nextInt();
        if (choice == 1) {
            logIn(scanner, fileName);
        } else if (choice == 2) {
            signUp(scanner, fileName, file);
        }
    }
    private static void signUp(Scanner scanner, String fileName, File file) {
        System.out.println("Enter your username/student id: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        System.out.println("\033[H\033[2J");
        System.out.flush();
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
            Pattern pattern = Pattern.compile(username);
            Matcher matcher = pattern.matcher(password);
            if (matcher.find()) {
                System.out.println("Invalid password, Password cannot contains the username.");
            } else {
                try (FileWriter fileWriter = new FileWriter(fileName, true)) {
                    fileWriter.write(username + "," + password + "\n");
                    System.out.println("you signed up!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void logIn(Scanner scanner, String fileName){
        System.out.println("Enter your username/student id: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        System.out.println("\033[H\033[2J");
        System.out.flush();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (username.equals(info[0])){
                    if (password.equals(info[1])){
                        System.out.println("Welcome to Daneshjooyar!");
                        break;
                    }
                    else {
                        System.out.println("Incorrect password. Try again...");
                        do{
                            scanner.nextLine();
                        } while (password.equals(info[1]));
                        System.out.println("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Welcome to Daneshjooyar!");
                    }
                    break;
                }
                else {
                    System.out.println("You're not registered yet!");
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

