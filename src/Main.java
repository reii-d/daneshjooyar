
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String mode;
        do {
            menu();
            mode = scanner.nextLine();
            System.out.println("\033[H\033[2J");
            System.out.flush();
            switch (mode) {
                case "1":
                    AdminAccess();
                    String adminChoice = scanner.nextLine();
                    switch (adminChoice){
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
                    TeacherAccess();
                    String teacherChoice = scanner.nextLine();
                    switch (teacherChoice){
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
                    StudentAccess();
                    String studentChoice = scanner.nextLine();
                    /*if (studentChoice.equals("1")){
                        Student student = new Student("r");
                        student.prtRegisteredCourses();
                    }*/
                case "4":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid. Please try again.");
                    break;
            }
        } while (!mode.equals("exit"));
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
    public static void StudentAccess() {
        System.out.println("""
                access to?\s
                1. Courses
                """);
    }
}
