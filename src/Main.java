
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName = "students.txt";
        File file = new File(fileName);
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
                    break;
                case "3":
                    clear();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                System.out.println("Choose an option: \n1. log in\n2. sign up");
                    int choice = scanner.nextInt();
                    if (choice == 1) {
                        LoginSignUp.logIn(fileName);
                    } else if (choice == 2) {
                        LoginSignUp.signUp(fileName, file);
                    }
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid. Please try again.");
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
