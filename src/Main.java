
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.annotation.processing.Filer;

public class Main {
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String studentFileName = "students.txt";
        String teacherFileName = "teachers.txt";
        File studentFile = new File(studentFileName);
        String mode;
        menu();
        mode = scanner.nextLine();
        clear();
        switch (mode) {
            case "1":
                clear();
                AdminAccess();
                String adminChoice = scanner.nextLine();
                switch (adminChoice) {
                    case "1":
                        //get access to teachers
                        break;
                        case "2":
                            //get access to students
                            break;
                        case "3":
                            //get access to courses
                            break;
                        default:
                            System.out.println("Invalid");
                            break;
                    }
                    break;
                case "2":
                    clear();
                    System.out.println("Enter your teacher ID: ");
                    String teacherID = scanner.nextLine();
                    boolean login = false;
                    try (BufferedReader reader = new BufferedReader(new FileReader(teacherFileName))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (teacherID.equals(line)) {
                                System.out.println("Welcome!");
                                login = true;
                                break;
                            }
                        }
                        int attempts = 1;
                        while (!login && attempts < 5) {
                            System.err.println("Incorrect teacher ID. Please try again: ");
                            teacherID = scanner.nextLine();
                            try (BufferedReader readerRetry = new BufferedReader(new FileReader(teacherFileName))) {
                                while ((line = readerRetry.readLine()) != null) {
                                    if (teacherID.equals(line)) {
                                        System.out.println("Welcome!");
                                        login = true;
                                        break;
                                    }
                                }
                            }
                            attempts++;
                        }
                        if (!login){
                            System.err.println("You cannot enter as teacher!");
                        }
                    } catch (IOException e){
                        throw new IOException();
                    }
                    if (login) {
                        TeacherAccess();
                        String teacherChoice = scanner.nextLine();
                        switch (teacherChoice) {
                            case "1":
                                //get access to courses
                                break;
                            case "2":
                                //get access to students
                                break;
                            default:
                                System.out.println("Invalid");
                                break;
                        }
                    }
                    break;
                case "3":
                    clear();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Choose an option: \n1. log in\n2. sign up");
                    int choice = scanner.nextInt();
                    if (choice == 1) {
                        LoginSignUp.logIn(studentFileName);
                    } else if (choice == 2) {
                        LoginSignUp.signUp(studentFileName, studentFile);
                    }
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    break;
                default:
                    break;
            }
    }
    public static void menu(){
        System.out.println("\nMenu: ");
        System.out.println("1. admin");
        System.out.println("2. teacher");
        System.out.println("3. student");
        System.out.println("4. exit");
        System.out.println("What is your choice?: ");
    }
    public static void AdminAccess(){
        System.out.println("""
                access to?\s
                1. Teachers
                2. Students
                3. Courses
                """);
    }
    public static void TeacherAccess(){
        System.out.println("""
                access to?\s
                1. Courses
                2. Students
                """);
    }
}