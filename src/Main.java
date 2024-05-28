import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String studentFileName = "students.txt";
        String teacherFileName = "teachers.txt"; //باید علاوه بر ایدی استاد اسم و فامیلش هم اضافه بشه. الان فقط ایدی داره. بعدش توی ورود استاد هم باید با اسپلیت ایدی رو از اسم و فامیل جدا کنیم.
        File studentFile = new File(studentFileName);
        String mode;
        mainMenu();
        mode = scanner.nextLine();
        clear();
        switch (mode) {
            //ADMIN
            case "1":
                clear();
                AdminAccess();
                String adminChoice = scanner.nextLine();
                switch (adminChoice) {
                    case "1":
                        System.out.println("1. Add Teacher\n2. Remove Teacher");
                        int teacherChoice = scanner.nextInt();
                        if (teacherChoice == 1){
                            System.out.println("A new Teacher!\nFirst Name: ");
                            String firstName = scanner.nextLine();
                            System.out.println("Last Name: ");
                            String lastName = scanner.nextLine();
                            System.out.println("TeacherID: ");
                            String teacherId = scanner.nextLine();
                            String newTeacher = firstName + lastName + ": " + teacherId;

                        }
                        else if (teacherChoice == 2){

                        }
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

            //TEACHER
            case "2":
                //Techaer Login
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

                //running
                if (login) {
                    TeacherAccess();
                    String teacherChoice = scanner.nextLine();
                    switch (teacherChoice) {
                        case "1":
                            clear();
                            System.out.println("1. New Course\n2. Remove Course\n3. Update Course");
                            int courseChoice = scanner.nextInt();
                            if (courseChoice == 1){
                                System.out.println("Enter the name of course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("Enter the name of the teacher: \nFirst Name:");
                                String teacherFirstName = scanner.nextLine();
                                System.out.println("Last Name:");
                                String teacherLastName = scanner.nextLine();
                                System.out.println("How many units does it have?");
                                int numUnits = scanner.nextInt();
                                System.out.println("When is the ٍexam date?");
                                String examDate = scanner.nextLine();
                                Course newCourse = new Course(courseName, numUnits, examDate, new Teacher(teacherFirstName, teacherLastName));
                                //adding "newCourse" to the database;
                            }
                            else if (courseChoice == 2){
                                System.out.println("Enter the name of course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("Enter the name of the teacher: \nFirst Name:");
                                String teacherFirstName = scanner.nextLine();
                                System.out.println("Last Name:");
                                String teacherLastName = scanner.nextLine();
                                System.out.println("How many units does it have?");
                                int numUnits = scanner.nextInt();
                                System.out.println("When is the ٍexam date?");
                                String examDate = scanner.nextLine();
                                //removing this course from database

                            } else if (courseChoice == 3) {
                                //old
                                System.out.println("Write the old information.\nEnter the name of course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("Enter the name of the teacher: \nFirst Name:");
                                String teacherFirstName = scanner.nextLine();
                                System.out.println("Last Name:");
                                String teacherLastName = scanner.nextLine();
                                System.out.println("How many units does it have?");
                                int numUnits = scanner.nextInt();
                                System.out.println("When is the ٍexam date?");
                                String examDate = scanner.nextLine();
                                //new
                                System.out.println("Write the new information.\nEnter the name of course: ");
                                String newCourseName = scanner.nextLine();
                                System.out.println("Enter the name of the teacher: \nFirst Name:");
                                String newTeacherFirstName = scanner.nextLine();
                                System.out.println("Last Name:");
                                String newTeacherLastName = scanner.nextLine();
                                System.out.println("How many units does it have?");
                                int newNumUnits = scanner.nextInt();
                                System.out.println("When is the ٍexam date?");
                                String newExamDate = scanner.nextLine();
                                //Update the Course information (درس رو با اطلاعات قدیم توی فایل پیدا میکنیم بعد کل جدیدا رو میذاریم جاش)
                            }
                            break;
                        case "2":
                            clear();
                            System.out.println("1. Add Students to your course\n2. Remove Students from your course");
                            int studentChoice = scanner.nextInt();
                            if (studentChoice == 1){
                                //from courseTaught --> add student
                            }
                            else if (studentChoice == 2){
                                //from courseTaught --> remove student
                            }
                            break;
                        case "3":
                            clear();
                            System.out.println("1. Define an Assignment\n2. Remove an Assignment\n3. Update an Assignment");
                            int assignmentChoice = scanner.nextInt();
                            if (assignmentChoice == 1){
                                //from courseTaught --> add assignment
                            } else if (assignmentChoice == 2) {
                                //from courseTaught --> remove
                            } else if (assignmentChoice == 3) {
                                //from courseTaught --> update (changing deadline/changing explanations)
                            }
                            break;
                        default:
                            System.out.println("Invalid");
                            break;
                    }
                }
                break;

            //STUDENT
            case "3":
                clear();
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
    public static void mainMenu(){
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
                3. Assignments
                """);
    }
}