import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.IIOException;

public class Main {
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Database database = Database.getInstance();
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
                        scanner.nextLine();
                        if (teacherChoice == 1){
                            System.out.println("A new Teacher!\nFirst Name: ");
                            String firstName = scanner.nextLine();
                            System.out.println("Last Name: ");
                            String lastName = scanner.nextLine();
                            System.out.println("TeacherID: ");
                            String teacherId = scanner.nextLine();
                            String newTeacher = firstName + " " + lastName + "," + teacherId + ",\n";
                            try {
                                FileWriter fileWriter = new FileWriter(database.teacherFileName, true);
                                fileWriter.write(newTeacher);
                                fileWriter.close();
                            }
                            catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                        else if (teacherChoice == 2){
                            System.out.println("Removing a Teacher!\nFirst Name: ");
                            String firstName = scanner.nextLine();
                            System.out.println("Last Name: ");
                            String lastName = scanner.nextLine();
                            System.out.println("TeacherID: ");
                            String teacherId = scanner.nextLine();
                            String teacherName = firstName + " " + lastName;
                            try (BufferedReader reader = new BufferedReader(new FileReader(database.teacherFileName))){
                                String line;
                                String[] info;
                                while ((line = reader.readLine()) != null){
                                    FileWriter fileWriter = new FileWriter(database.tempFileName, true);
                                    info = line.split(",");
                                    if (!info[0].equals(teacherName) && !info[1].equals(teacherId)){
                                        fileWriter.write(line + "\n");
                                    }
                                    fileWriter.close();
                                }
                                PrintWriter writer = new PrintWriter(database.teacherFileName);
                                writer.println("");
                                writer.close();
                                BufferedReader reader1 = new BufferedReader(new FileReader(database.tempFileName));
                                while ((line = reader1.readLine()) != null){
                                    FileWriter fileWriter = new FileWriter(database.teacherFileName, true);
                                    fileWriter.write(line + "\n");
                                    fileWriter.close();
                                }
                            }
                            catch (IIOException e){
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "2":
                        //هر وقت بخش access to courses استاد رو کامل کردی همونو کپی کن اینجا!
                        break;
                    case "3":
                        //هر وقت بخش access to students استاد رو کامل کردی همونو کپی کن اینجا!
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
                try (BufferedReader reader = new BufferedReader(new FileReader(database.teacherFileName))) {
                    String line;
                    String[] info;
                    while ((line = reader.readLine()) != null) {
                        info = line.split(",");
                        if (teacherID.equals(info[1])) {
                            System.out.println("Welcome, " + info[0] + "!");
                            login = true;
                            break;
                        }
                    }
                    int attempts = 1;
                    while (!login && attempts < 5) {
                        System.err.println("Incorrect teacher ID. Please try again: ");
                        teacherID = scanner.nextLine();
                        String[] info1;
                        try (BufferedReader readerRetry = new BufferedReader(new FileReader(database.teacherFileName))) {
                            while ((line = readerRetry.readLine()) != null) {
                                info1 = line.split(",");
                                if (teacherID.equals(info1[1])) {
                                    System.out.println("Welcome, " + info1[0] + "");
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
                            System.out.println("1. Add Students to your course\n2. Remove Students from your course\n3. Update scores");
                            int studentChoice = scanner.nextInt();
                            scanner.nextLine();
                            if (studentChoice == 1){
                                //from courseTaught --> add student
                            }
                            else if (studentChoice == 2){
                                //from courseTaught --> remove student
                            }
                            else if (studentChoice == 3) {
                                scanner.nextLine();
                                System.out.println("for which course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("for who? Write the name of Student: ");
                                String studentName = scanner.nextLine();
                                System.out.println("Write the score: ");
                                double score = scanner.nextDouble();
                                try (BufferedReader reader = new BufferedReader(new FileReader(database.courseFileName))) {
                                    String line;
                                    String[] info;
                                    boolean ok = false;
                                    while ((line = reader.readLine()) != null) {
                                        info = line.split(",");
                                        if (info[0].equals(courseName) && info[3].equals(database.getTeacherName(teacherID))) {
                                            database.addScoreToStudent(studentName, courseName, Double.toString(score));
                                            ok = true;
                                            break;
                                        }
                                    }
                                    if (!ok) {
                                        System.out.println("You have not this course!");
                                    }
                                    if (ok) {
                                        System.out.println("Score added successfully!");
                                    }
                                }
                                catch (IOException e){
                                    e.printStackTrace();
                                }
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
                    LoginSignUp.logIn(database.studentFileName);
                } else if (choice == 2) {
                    LoginSignUp.signUp(database.studentFileName, database.studentFile);
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