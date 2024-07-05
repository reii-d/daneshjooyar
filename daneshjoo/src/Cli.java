import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.IIOException;

public class Cli {
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
                            FileWriter fileWriter = new FileWriter(database.teacherFileName, true);
                            fileWriter.write(newTeacher);
                            fileWriter.close();
                        }
                        else if (teacherChoice == 2){
                            String tempFileName = "src/data/temp.txt";
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
                                    FileWriter fileWriter = new FileWriter(tempFileName, true);
                                    info = line.split(",");
                                    if (!info[0].equals(teacherName) && !info[1].equals(teacherId)){
                                        fileWriter.write(line + "\n");
                                    }
                                    fileWriter.close();
                                }
                                PrintWriter writer = new PrintWriter(database.teacherFileName);
                                writer.println("");
                                writer.close();
                                BufferedReader reader1 = new BufferedReader(new FileReader(tempFileName));
                                while ((line = reader1.readLine()) != null){
                                    FileWriter fileWriter = new FileWriter(database.teacherFileName, true);
                                    fileWriter.write(line + "\n");
                                    fileWriter.close();
                                }
                            }
                        }
                        break;
                    case "2":
                        clear();
                        System.out.println("1. New Course\n2. Remove Course\n3. Update Course");
                        int courseChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (courseChoice == 1){
                            System.out.println("Creating a new course!\nEnter the name of course: ");
                            String courseName = scanner.nextLine();
                            System.out.println("First name of Teacher: ");
                            String firstName = scanner.nextLine();
                            System.out.println("Last name of teacher: ");
                            String lastName = scanner.nextLine();
                            System.out.println("How many units does the course have?");
                            String numUnits = scanner.nextLine();
                            System.out.println("When is the exam date?");
                            String examDate = scanner.nextLine();
                            Course newCourse = new Course(courseName, Integer.parseInt(numUnits), examDate, new Teacher(firstName, lastName));
                            database.addCourse(newCourse);
                        }
                        else if (courseChoice == 2){
                            System.out.println("Removing an old course!\nEnter the name of course: ");
                            String courseName = scanner.nextLine();
                            System.out.println("First name of Teacher: ");
                            String firstName = scanner.nextLine();
                            System.out.println("Last name of teacher: ");
                            String lastName = scanner.nextLine();
                            System.out.println("How many units does the course have?");
                            String numUnits = scanner.nextLine();
                            System.out.println("When is the exam date?");
                            String examDate = scanner.nextLine();
                            Course oldCourse = new Course(courseName, Integer.parseInt(numUnits), examDate, new Teacher(firstName, lastName));
                            database.removeCourse(oldCourse);
                        } else if (courseChoice == 3) {
                            System.out.println("Enter the name of course: ");
                            String courseName = scanner.nextLine();
                            System.out.println("First name of Teacher: ");
                            String firstName = scanner.nextLine();
                            System.out.println("Last name of teacher: ");
                            String lastName = scanner.nextLine();
                            //old
                            System.out.println("Write the old information.\nHow many units does it have?");
                            String numUnits = scanner.nextLine();
                            System.out.println("When is the exam date?");
                            String examDate = scanner.nextLine();
                            Course oldCourse = new Course(courseName, Integer.parseInt(numUnits), examDate, new Teacher(firstName, lastName));
                            //new
                            System.out.println("Write the new information.\nHow many units does it have?");
                            String newNumUnits = scanner.nextLine();
                            System.out.println("When is the exam date?");
                            String newExamDate = scanner.nextLine();
                            Course newCourse = new Course(courseName, Integer.parseInt(newNumUnits), newExamDate, new Teacher(firstName, lastName));

                            database.updateCourse(oldCourse, newCourse, firstName + " " + lastName);
                        }
                        break;
                    case "3":
                        clear();
                        System.out.println("1. Add Students to a course\n2. Remove Students from a course\n3. Update scores");
                        int studentChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (studentChoice == 1){
                            System.out.println("Add a student!\nWrite the name of course: ");
                            String courseName = scanner.nextLine();
                            System.out.println("Write the name of student: ");
                            String studentName = scanner.nextLine();
                            database.addCourseToStudent(courseName, studentName);
                            System.out.println(studentName + "added to the course successfully!");
                        }
                        else if (studentChoice == 2){
                            System.out.println("Remove a student!\nWrite the name of course: ");
                            String courseName = scanner.nextLine();
                            System.out.println("Write the name of student: ");
                            String studentName = scanner.nextLine();
                            database.removeCourseFromStudent(courseName, studentName);
                            System.out.println(studentName + "removed from the course successfully!");
                        }
                        else if (studentChoice == 3) {
                            System.out.println("Updating scores!\nfor which course? ");
                            String courseName = scanner.nextLine();
                            System.out.println("for who? Write the name of Student: ");
                            String studentName = scanner.nextLine();
                            System.out.println("Write the score: ");
                            String score = scanner.nextLine();
                            database.studentScore(studentName, courseName, score);
                            System.out.println("Score added successfully!");
                        }
                        break;
                    default:
                        System.out.println("Invalid");
                        break;
                }
                break;

            //TEACHER
            case "2":
                //Teacher Login
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
                            scanner.nextLine();
                            if (courseChoice == 1){
                                System.out.println("Enter the name of course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("How many units does it have?");
                                String numUnits = scanner.nextLine();
                                System.out.println("When is the exam date?");
                                String examDate = scanner.nextLine();
                                String[] teacher = database.teacherName(teacherID).split(" ");
                                Course newCourse = new Course(courseName, Integer.parseInt(numUnits), examDate, new Teacher(teacher[0], teacher[1]));
                                database.addCourse(newCourse);
                            }
                            else if (courseChoice == 2){
                                System.out.println("Remove your course!\nEnter the name of course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("How many units does it have?");
                                String numUnits = scanner.nextLine();
                                System.out.println("When is the exam date?");
                                String examDate = scanner.nextLine();
                                String[] teacher = database.teacherName(teacherID).split(" ");
                                Course oldCourse = new Course(courseName, Integer.parseInt(numUnits), examDate, new Teacher(teacher[0], teacher[1]));
                                if (database.isTeacher(teacherID, courseName)) {
                                    database.removeCourse(oldCourse);
                                    System.out.println("Course removed successfully!");
                                }
                                else {
                                    System.out.println("You have not access to this course!");
                                }
                            } else if (courseChoice == 3) {
                                String[] teacher = database.teacherName(teacherID).split(" ");
                                System.out.println("Enter the name of course: ");
                                String courseName = scanner.nextLine();
                                //old
                                System.out.println("Write the old information.\nHow many units does it have?");
                                String numUnits = scanner.nextLine();
                                System.out.println("When is the exam date?");
                                String examDate = scanner.nextLine();
                                Course oldCourse = new Course(courseName, Integer.parseInt(numUnits), examDate, new Teacher(teacher[0], teacher[1]));
                                //new
                                System.out.println("Write the new information.\nHow many units does it have?");
                                String newNumUnits = scanner.nextLine();
                                System.out.println("When is the exam date?");
                                String newExamDate = scanner.nextLine();
                                Course newCourse = new Course(courseName, Integer.parseInt(newNumUnits), newExamDate, new Teacher(teacher[0], teacher[1]));

                                if (database.isTeacher(teacherID, courseName)) {
                                    database.updateCourse(oldCourse, newCourse, database.teacherName(teacherID));
                                    System.out.println("Course updated successfully!");
                                }
                                else {
                                    System.out.println("You have not access to this course!");
                                }
                            }
                            break;
                        case "2":
                            clear();
                            System.out.println("1. Add Students to your course\n2. Remove Students from your course\n3. Update scores");
                            int studentChoice = scanner.nextInt();
                            scanner.nextLine();
                            if (studentChoice == 1){
                                System.out.println("Add a student!\nWrite the name of course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("Write the name of student: ");
                                String studentName = scanner.nextLine();
                                if (database.isTeacher(teacherID, courseName)){
                                    database.addCourseToStudent(courseName, studentName);
                                    System.out.println( studentName + "added to the course successfully!");
                                }
                                else {
                                    System.out.println("You have not access to this course!");
                                }
                            }
                            else if (studentChoice == 2){
                                System.out.println("Remove a student!\nWrite the name of course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("Write the name of student: ");
                                String studentName = scanner.nextLine();
                                if (database.isTeacher(teacherID, courseName)){
                                    database.removeCourseFromStudent(courseName, studentName);
                                    System.out.println(studentName + "removed from the course successfully!");
                                }
                                else {
                                    System.out.println("You have not access to this course!");
                                }
                            }
                            else if (studentChoice == 3) {
                                System.out.println("Updating scores!\nfor which course? ");
                                String courseName = scanner.nextLine();
                                System.out.println("for who? Write the name of Student: ");
                                String studentName = scanner.nextLine();
                                System.out.println("Write the score: ");
                                String score = scanner.nextLine();
                                if (!database.isTeacher(teacherID, courseName)) {
                                    System.out.println("You have not this course!");
                                }
                                else if (database.isTeacher(teacherID, courseName)) {
                                    database.studentScore(studentName, courseName, score);
                                    System.out.println("Score added successfully!");
                                }
                            }
                            break;
                        case "3":
                            clear();
                            System.out.println("1. Add an assignment\n2. Remove an assignment\n3. Update an assignment");
                            String assignmentChoice = scanner.nextLine();
                            if (assignmentChoice.equals("1")){
                                System.out.println("Add a new assignments!");
                                System.out.println("Write the name of course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("Write the name of assignment: ");
                                String assignmentName = scanner.nextLine();
                                System.out.println("Deadline: ");
                                String deadline = scanner.nextLine();
                                if (database.isTeacher(teacherID, courseName)) {
                                    database.addAssignment(new Assignment(assignmentName, Integer.parseInt(deadline)), courseName);
                                    System.out.println("Assignment added to the course!");
                                }
                                else {
                                    System.out.println("You have not access to this course");
                                }
                            }
                            else if (assignmentChoice.equals("2")){
                                System.out.println("Remove an assignments!");
                                System.out.println("Write the name of course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("Write the name of assignment: ");
                                String assignmentName = scanner.nextLine();
                                System.out.println("Deadline: ");
                                String deadline = scanner.nextLine();
                                if (database.isTeacher(teacherID, courseName)) {
                                    database.removeAssignment(new Assignment(assignmentName, Integer.parseInt(deadline)), courseName);
                                    System.out.println("Assignment removed!");
                                }
                                else {
                                    System.out.println("You have not access to this course");
                                }
                            }
                            else if (assignmentChoice.equals("3")) {
                                System.out.println("Update an assignments!");
                                System.out.println("Write the name of course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("Write the name of assignment: ");
                                String assignmentName = scanner.nextLine();
                                System.out.println("Deadline: ");
                                String deadline = scanner.nextLine();
                                if (database.isTeacher(teacherID, courseName)) {
                                    database.updateAssignment(courseName, new Assignment(assignmentName, Integer.parseInt(deadline)));
                                    System.out.println("Assignment updated");
                                }
                                else {
                                    System.out.println("You have not access to this course");
                                }
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
                /*System.out.println("Choose an option: \n1. log in\n2. sign up");
                int choice = scanner.nextInt();
                scanner.nextLine();
                boolean enter = false;
                String UserName = "";
                if (choice == 1) {
                    System.out.println("Enter your username/student id: ");
                    String username = scanner.nextLine();
                    System.out.println("Enter your password: ");
                    String password = scanner.nextLine();
                    clear();
                    try (BufferedReader reader = new BufferedReader(new FileReader(database.studentFileName))) {
                        String line;
                        String[] info;
                        boolean isExist = false;
                        while ((line = reader.readLine()) != null) {
                            info = line.split(",");
                            if (username.equals(info[1])) {
                                if (password.equals(info[2])) {
                                    System.out.println("Welcome to Daneshjooyar!");
                                    enter = true;
                                    UserName = username;
                                    break;
                                } else {
                                    System.out.println("Incorrect password. Try again...");
                                    do {
                                        scanner.nextLine();
                                    } while (password.equals(info[1]));
                                    clear();
                                    System.out.println("Welcome to Daneshjooyar!");
                                    enter = true;
                                    UserName = username;
                                }
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            System.out.println("You have not registered yet.");
                        }
                    }
                } else if (choice == 2) {
                    System.out.println("Enter your Name: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter your last name: ");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter your username/student id: ");
                    String username = scanner.nextLine();
                    System.out.println("Enter your password: ");
                    String password = scanner.nextLine();
                    clear();
                    boolean isExist = false;
                    try (BufferedReader reader = new BufferedReader(new FileReader(database.studentFileName))) {
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
                    }
                    if (!isExist) {
                        while (password.length() < 8
                                || password.contains(username) ||
                                !password.matches(".*[a-z].*") ||
                                !password.matches(".*[A-Z].*")) {
                            System.err.println("Invalid password!");
                            password = scanner.nextLine();
                        }
                        try (FileWriter fileWriter = new FileWriter(database.studentFileName, true)) {
                            fileWriter.write(firstName + " " + lastName + "," + username + "," + password + ",\n");
                            System.out.println("you signed up successfully!");
                            enter = true;
                            UserName = username;
                        }
                    }
                }
                if (enter){
                    System.out.println("Access to Courses:\n1. Add a course\n2. Remove a course");
                    String sChoice = scanner.nextLine();
                    if (sChoice.equals("1")){
                        System.out.println("Enter the name of course: ");
                        String courseName = scanner.nextLine();
                        database.addCourseToStudent(database.studentName(UserName), courseName);
                    }
                    else if (sChoice.equals("2")){
                        System.out.println("Removing an old course!\nEnter the name of course: ");
                        String courseName = scanner.nextLine();
                        database.removeCourseFromStudent(courseName, database.studentName(UserName));
                    }
                }*/
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
                2. Courses
                3. Students
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