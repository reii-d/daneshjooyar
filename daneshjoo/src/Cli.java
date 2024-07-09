import java.io.*;
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

                    //Access to TEACHERS
                    case "1":
                        System.out.println("1. Add Teacher\n2. Remove Teacher");
                        int teacherChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (teacherChoice == 1){            //Adding a teacher to teacher file
                            System.out.println("Adding a new Teacher!\nFirst Name: ");
                            String firstName = scanner.nextLine();
                            System.out.println("Last Name: ");
                            String lastName = scanner.nextLine();
                            System.out.println("TeacherID: ");
                            String teacherId = scanner.nextLine();
                            String newTeacher = firstName + " " + lastName + "," + teacherId + "\n";
                            FileWriter fileWriter = new FileWriter(database.teacherFileName, true);
                            fileWriter.write(newTeacher);
                            fileWriter.close();
                            System.out.println("Teacher" + firstName + " " + lastName + ", added successfully!");
                        }

                        else if (teacherChoice == 2){       //Removing a teacher from teacher file
                            String tempFileName = database.tempFileName;
                            System.out.println("Removing a Teacher!\nFirst Name: ");
                            String firstName = scanner.nextLine();
                            System.out.println("Last Name: ");
                            String lastName = scanner.nextLine();
                            System.out.println("TeacherID: ");
                            String teacherId = scanner.nextLine();
                            String teacherName = firstName + " " + lastName;

                            //file access
                            try (BufferedReader reader = new BufferedReader(new FileReader(database.teacherFileName))) {
                                boolean removed = false;
                                String line;
                                String[] info;
                                while ((line = reader.readLine()) != null){
                                    FileWriter fileWriter = new FileWriter(tempFileName, true);
                                    info = line.split(",");
                                    if (!info[0].equals(teacherName) && !info[1].equals(teacherId)){
                                        fileWriter.write(line + "\n");
                                    } else
                                        removed = true;
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
                                if (removed)
                                    System.out.println("Teacher" + firstName + " " + lastName + ", removed successfully!");
                            }
                        }

                        else {
                            System.err.println("Unknown command!");
                        }
                        break;


                    //Access to COURSES
                    case "2":
                        clear();
                        System.out.println("1. New Course\n2. Remove Course\n3. Update Course");
                        String courseChoice = scanner.nextLine();

                        if (courseChoice.equals("1")){      //Adding a course to course file
                            System.out.println("Creating a new course!\nEnter the name of course: ");
                            String courseName = scanner.nextLine();
                            System.out.println("Set an ID for the course: ");
                            String courseID = scanner.nextLine();
                            System.out.println("First name of the Teacher: ");
                            String firstName = scanner.nextLine();
                            System.out.println("Last name of the teacher: ");
                            String lastName = scanner.nextLine();
                            System.out.println("How many units does the course have?");
                            String numUnits = scanner.nextLine();
                            System.out.println("When is the exam date?");
                            String examDate = scanner.nextLine();

                            Course newCourse = new Course(courseName, courseID, Integer.parseInt(numUnits), examDate, new Teacher(firstName, lastName));
                            System.out.println(database.addCourse(newCourse));
                        }

                        else if (courseChoice.equals("2")){        //Removing a course from course file
                            System.out.println("Removing an old course!\nEnter the name of course: ");
                            String courseName = scanner.nextLine();
                            System.out.println("Set an ID for the course: ");
                            String courseID = scanner.nextLine();
                            System.out.println("First name of the Teacher: ");
                            String firstName = scanner.nextLine();
                            System.out.println("Last name of the teacher: ");
                            String lastName = scanner.nextLine();
                            System.out.println("How many units does the course have?");
                            String numUnits = scanner.nextLine();
                            System.out.println("When is the exam date?");
                            String examDate = scanner.nextLine();
                            Course oldCourse = new Course(courseName, courseID, Integer.parseInt(numUnits), examDate, new Teacher(firstName, lastName));
                            System.out.println(database.removeCourse(oldCourse));

                        } else if (courseChoice.equals("3")) {         //Changing the information of a course
                            //Fixed information about the course
                            System.out.println("Enter the name of course: ");
                            String courseName = scanner.nextLine();
                            System.out.println("Set an ID for the course: ");
                            String courseID = scanner.nextLine();
                            System.out.println("First name of Teacher: ");
                            String firstName = scanner.nextLine();
                            System.out.println("Last name of teacher: ");
                            String lastName = scanner.nextLine();

                            //the previous information of the course
                            System.out.println("Write the previous information...\nHow many units did it have?");
                            String numUnits = scanner.nextLine();
                            System.out.println("When was the previous exam date?");
                            String examDate = scanner.nextLine();
                            Course oldCourse = new Course(courseName, courseID, Integer.parseInt(numUnits), examDate, new Teacher(firstName, lastName));

                            //the new information of the course
                            System.out.println("Write the new information...\nHow many units does it have?");
                            String newNumUnits = scanner.nextLine();
                            System.out.println("When is the new exam date?");
                            String newExamDate = scanner.nextLine();
                            Course newCourse = new Course(courseName, courseID, Integer.parseInt(newNumUnits), newExamDate, new Teacher(firstName, lastName));

                            database.updateCourse(oldCourse, newCourse, firstName + " " + lastName);

                        }

                        else {
                            System.err.println("Unknown command!");
                        }
                        break;


                    //Access to STUDENTS
                    case "3":
                        clear();
                        System.out.println("1. Add Students to a course\n2. Remove Students from a course\n3. Update scores");
                        String studentChoice = scanner.nextLine();

                        if (studentChoice.equals("1")){         //Adding a student to a course
                            System.out.println("Add a student!\nWrite the name of course: ");
                            String courseName = scanner.nextLine();
                            System.out.println("Write the name of student: ");
                            String student = scanner.nextLine();
                            System.out.println(database.addCourseToStudent(student, courseName));
                        }

                        else if (studentChoice.equals("2")){           //Removing a student from a course
                            System.out.println("Remove a student!\nWrite the name of course: ");
                            String courseName = scanner.nextLine();
                            System.out.println("Write the name of student: ");
                            String studentName = scanner.nextLine();
                            System.out.println(database.removeCourseFromStudent(courseName, studentName));
                        }

                        else if (studentChoice.equals("3")) {          //Changing the score of a student's course
                            System.out.println("Updating scores!\nfor which course? ");
                            String courseName = scanner.nextLine();
                            System.out.println("for who? Write the name of Student: ");
                            String studentName = scanner.nextLine();
                            System.out.println("Write the score: ");
                            String score = scanner.nextLine();
                            System.out.println(database.studentScore(studentName, courseName, score));
                        } else
                            System.err.println("Unknown command!");

                        break;

                    //Access to NEWS
                    case "4":
                        clear();
                        System.out.println("1. Add news \n2. Remove the news");
                        String newsChoice = scanner.nextLine();

                        if (newsChoice.equals("1")){        //Adding news
                            System.out.println("Adding news!\nWrite the title of the news: ");
                            String title = scanner.nextLine();
                            System.out.println("Write the texe of the news: ");
                            String text = scanner.nextLine();
                            System.out.println(database.addNews(title, text));
                        }

                        else if (newsChoice.equals("2")){       //Removing news
                            System.out.println("Removing news!\nWrite the title of the news: ");
                            String title = scanner.nextLine();
                            System.out.println(database.removeNews(title));
                        } else
                            System.err.println("Unknown command!");
                        break;

                    default:
                        System.out.println("Unknown command");
                        break; //for the admin options
                }
                break; //for the admin

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
                                    System.out.println("Welcome, " + info1[0] + "!");
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

                //Access to options
                if (login) {
                    TeacherAccess();
                    String teacherChoice = scanner.nextLine();
                    switch (teacherChoice) {

                        //Access to COURSES
                        case "1":
                            clear();
                            System.out.println("1. New Course\n2. Remove Course\n3. Update Course");
                            String courseChoice = scanner.nextLine();

                            if (courseChoice.equals("1")){              //Adding a new course for the teacher
                                System.out.println("Enter the name of course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("Set an ID for the course: ");
                                String courseID = scanner.nextLine();
                                System.out.println("How many units does it have?");
                                String numUnits = scanner.nextLine();
                                System.out.println("When is the exam date?");
                                String examDate = scanner.nextLine();
                                String[] teacher = database.teacherName(teacherID).split(" ");

                                Course newCourse = new Course(courseName, courseID, Integer.parseInt(numUnits), examDate, new Teacher(teacher[0], teacher[1]));
                                System.out.println(database.addCourse(newCourse));
                            }


                            else if (courseChoice.equals("2")){         //Removing a course
                                System.out.println("Remove your course!\nEnter the name of course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("Set an ID for the course: ");
                                String courseID = scanner.nextLine();
                                System.out.println("How many units does it have?");
                                String numUnits = scanner.nextLine();
                                System.out.println("When is the exam date?");
                                String examDate = scanner.nextLine();
                                String[] teacher = database.teacherName(teacherID).split(" ");

                                Course oldCourse = new Course(courseName, courseID, Integer.parseInt(numUnits), examDate, new Teacher(teacher[0], teacher[1]));
                                if (database.isTeacher(teacherID, courseName))
                                    System.out.println(database.removeCourse(oldCourse));
                                else
                                    System.out.println("Sorry, You have not access to this course!");


                            } else if (courseChoice.equals("3")) {      //Changing the information of a course
                                String[] teacher = database.teacherName(teacherID).split(" ");

                                //information of the teacher
                                System.out.println("Enter the name of course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("Set an ID for the course: ");
                                String courseID = scanner.nextLine();

                                //the previous information of the course
                                System.out.println("Write the previous information...\nHow many units did it have?");
                                String numUnits = scanner.nextLine();
                                System.out.println("When was the previous exam date?");
                                String examDate = scanner.nextLine();
                                Course oldCourse = new Course(courseName, courseID, Integer.parseInt(numUnits), examDate, new Teacher(teacher[0], teacher[1]));

                                //the new information of the course
                                System.out.println("Write the new information...\nHow many units does it have?");
                                String newNumUnits = scanner.nextLine();
                                System.out.println("When is the new exam date?");
                                String newExamDate = scanner.nextLine();
                                Course newCourse = new Course(courseName, courseID, Integer.parseInt(newNumUnits), newExamDate, new Teacher(teacher[0], teacher[1]));

                                if (database.isTeacher(teacherID, courseName)) {
                                    database.updateCourse(oldCourse, newCourse, database.teacherName(teacherID));}
                                else {
                                    System.out.println("You have not access to this course!");}
                            } else
                                System.err.println("Unknown command!");

                            break;

                        //Access to STUDENTS
                        case "2":
                            clear();
                            System.out.println("1. Add Students to your course\n2. Remove Students from your course\n3. Update scores");
                            String studentChoice = scanner.nextLine();

                            if (studentChoice.equals("1")) {         //Adding a student to the teacher's course
                                System.out.println("Add a student!\nWrite the name of course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("Write the name of student: ");
                                String student = scanner.nextLine();
                                if (database.isTeacher(teacherID, courseName)){
                                    System.out.println(database.addCourseToStudent(student, courseName));}
                                else {
                                    System.out.println("You have not access to this course!");}
                            }

                            else if (studentChoice.equals("2")) {        //Removing a student from the teacher's course
                                System.out.println("Remove a student!\nWrite the name of course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("Write the name of student: ");
                                String student = scanner.nextLine();
                                if (database.isTeacher(teacherID, courseName)){
                                    System.out.println(database.removeCourseFromStudent(courseName, student));}
                                else {
                                    System.out.println("You have not access to this course!");}
                            }

                            else if (studentChoice.equals("3")) {       //Updating score of teacher's course
                                System.out.println("Updating scores!\nfor which course? ");
                                String courseName = scanner.nextLine();
                                System.out.println("Write the ID of Student: ");
                                String student = scanner.nextLine();
                                System.out.println("Write the score: ");
                                String score = scanner.nextLine();
                                if (!database.isTeacher(teacherID, courseName)) {
                                    System.out.println("You have not this course!");}
                                else if (database.isTeacher(teacherID, courseName)) {
                                    database.studentScore(student, courseName, score);}
                            } else
                                System.err.println("Unknown command!");
                            break;


                        //Access to ASSIGNMENTS
                        case "3":
                            clear();
                            System.out.println("1. Add an assignment\n2. Remove an assignment\n3. Update an assignment");
                            String assignmentChoice = scanner.nextLine();

                            if (assignmentChoice.equals("1")){              //Adding an assignment
                                System.out.println("Add a new assignment!");
                                System.out.println("Write name of the course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("Write name of the assignment: ");
                                String assignmentName = scanner.nextLine();
                                System.out.println("Write the Deadline (in days): ");
                                String deadline = scanner.nextLine();

                                if (database.isTeacher(teacherID, courseName)) {
                                    database.addAssignment(new Assignment(assignmentName, Integer.parseInt(deadline)), courseName);}
                                else {
                                    System.out.println("You have not access to this course");}
                            }

                            else if (assignmentChoice.equals("2")){         //Removing an assignment
                                System.out.println("Remove an assignment!");
                                System.out.println("Write name of the course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("Write name of the assignment: ");
                                String assignmentName = scanner.nextLine();
                                System.out.println("Write the Deadline (in days): ");
                                String deadline = scanner.nextLine();

                                if (database.isTeacher(teacherID, courseName)) {
                                    database.removeAssignment(new Assignment(assignmentName, Integer.parseInt(deadline)), courseName);}
                                else {
                                    System.out.println("You have not access to this course");}
                            }

                            else if (assignmentChoice.equals("3")) {           //Updating an assignment
                                System.out.println("Update an assignments!");
                                System.out.println("Write name of the course: ");
                                String courseName = scanner.nextLine();
                                System.out.println("Write the new name of the assignment: ");
                                String assignmentName = scanner.nextLine();
                                System.out.println("Write the new Deadline (in days): ");
                                String deadline = scanner.nextLine();

                                if (database.isTeacher(teacherID, courseName)) {
                                    database.updateAssignment(courseName, new Assignment(assignmentName, Integer.parseInt(deadline)));}
                                else {
                                    System.out.println("You have not access to this course");}
                            }else
                                System.err.println("Unknown command!");

                            break;

                        default:
                            System.out.println("Invalid");
                            break; //for the options
                    }
                }
                break; //for the teacher

            case "3":
                System.out.println("Goodbye!");
                break;

            default:
                System.err.println("Unknown command!");
                break;
        }
    }

    public static void mainMenu(){
        System.out.println("\nMenu: ");
        System.out.println("1. admin");
        System.out.println("2. teacher");
        System.out.println("3. exit");
        System.out.println("What is your choice?: ");
    }

    public static void AdminAccess(){
        System.out.println("""
                access to?\s
                1. Teachers
                2. Courses
                3. Students
                4. News
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