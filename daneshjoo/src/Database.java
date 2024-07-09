import java.io.*;
import java.util.ArrayList;

public class Database {

    //Files PATHs
    String studentFileName = "C:\\Users\\mnoro\\Desktop\\main ap\\daneshjooyar\\daneshjoo\\src\\data\\students.txt";
    String teacherFileName = "C:\\Users\\mnoro\\Desktop\\main ap\\daneshjooyar\\daneshjoo\\src\\data\\teachers.txt";
    String courseFileName = "C:\\Users\\mnoro\\Desktop\\main ap\\daneshjooyar\\daneshjoo\\src\\data\\courses.txt";
    String taskFileName = "C:\\Users\\mnoro\\Desktop\\main ap\\daneshjooyar\\daneshjoo\\src\\data\\tasks.txt";
    String newsFileName = "C:\\Users\\mnoro\\Desktop\\main ap\\daneshjooyar\\daneshjoo\\src\\data\\news.txt";
    String tempFileName = "C:\\Users\\mnoro\\Desktop\\main ap\\daneshjooyar\\daneshjoo\\src\\data\\temp.txt";


    //Constructor
    private static Database instance;
    private Database() {}
    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }


    //for returning the name of students with their username.
    public String studentName(String username) throws IOException {
        String name = "";
        BufferedReader reader = new BufferedReader(new FileReader(studentFileName));
        String line;
        String[] info;
        while ((line = reader.readLine()) != null) {
            info = line.split(",");
            if (username.equals(info[1])) {
                name = info[0];
            }
        }
        return name;
    }


    //for returning the name of teachers with their teacherID.
    public String teacherName(String teacherID) throws IOException {
        String name = "";
        BufferedReader reader = new BufferedReader(new FileReader(teacherFileName));
        String line;
        String[] info;
        while ((line = reader.readLine()) != null) {
            info = line.split(",");
            if (teacherID.equals(info[1])) {
                name = info[0];
            }
        }
        return name;
    }

    //To return the name of course with course ID
    public String courseName (String courseID) throws IOException {
        String name = "";
        BufferedReader reader = new BufferedReader(new FileReader(courseFileName));
        String line;
        String[] info;
        while ((line = reader.readLine()) != null){
            info = line.split(",");
            if (courseID.equals(info[1]))
                name = info[0];
        }
        return name;
    }

    //To check the teacher teaches a course or not
    public boolean isTeacher (String teacherID, String courseName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName))) {
            String line;
            String[] info;
            boolean ok = false;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[0].equals(courseName) && info[3].equals(teacherName(teacherID))) {
                    ok = true;
                    break;
                }
            }
            return ok;
        }
    }

    //to return the number of units of a course
    public int getNumUnits (String courseName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName))) {
            String line;
            String[] info;
            int units = 0;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[0].equals(courseName)) {
                    units = Integer.parseInt(info[1]);
                    break;
                }
            }
            return units;
        }
    }

    //To return assignments of a course
    public ArrayList<String> getAssignments(String courseName) throws IOException {
        ArrayList<String> assignments = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName))) {
            String line;
            String[] info;
            String[] assignment;
            String[] part;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[0].equals(courseName) && info.length > 5) {
                    assignment = info[5].split(";");
                    for (String a :assignment){
                        part = a.split(":");
                        if (part.length == 2)
                            assignments.add("," + part[0] + "," + part[1]);
                    }
                    break; //
                }
            }
            return assignments;
        }
    }

    //To send course's info (name, ID, teacher)
    public String courseInfo(String courseName) throws IOException {
        String courseInfo = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName))) {
            String line;
            String[] info;
            while ((line = reader.readLine()) != null){
                info = line.split(",");
                if (info[0].equals(courseName)){
                    courseInfo = info[0] + "," + info[1] + "," + info[4];
                }
            }
        }
        return courseInfo;
    }


    //Methods for Database
    //Sign Up: To save the information of students in file.
    public int signUp(String realName, String username, String password) throws IOException {
        int result = 2;
        boolean usernameExists = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(",");
                if (username.equals(info[0])) {
                    usernameExists = true;
                    break; // Username is already in use
                }
            }
        }

        if (usernameExists) {
            return 0; // Username is in use
        }
        File file = new File(studentFileName);
        boolean isEmptyFile = file.length() == 0;

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            if (!isEmptyFile) {
                raf.seek(file.length() - 1); // Move to the last character of the file
                char lastChar = (char) raf.readByte();
                if (lastChar != '\n') {
                    raf.write("\n".getBytes());
                }
            }
            raf.seek(file.length());
            raf.write((realName + "," + username + "," + password + ",").getBytes());
        }
        return result; // Signed up successfully
    }

    //Log In: To check correction of studentID and password
    public int logIn(String studentID, String password) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))) {
            String line;
            String[] info;
            int result = 0;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (studentID.equals(info[1])) {
                    result = 1; //password incorrect
                    if (password.equals(info[2])) {
                        result = 2; //logged in
                        reader.close();
                        return result;
                    }
                    reader.close();
                    return result;
                }
            }
            reader.close();
            return result; // 0; studentID incorrect
        }
    }

    //To remove an student from student file (delete account)
    public int deleteAccount(String studentID) throws IOException {
        int result = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName));
             PrintWriter tempWriter = new PrintWriter(new FileWriter(tempFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(",");
                if (!info[1].equals(studentID)) {
                    tempWriter.println(line);
                } else {
                    result = 2; // Account deleted successfully
                }
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(studentFileName))) {
            writer.print("");
        }
        try (BufferedReader tempReader = new BufferedReader(new FileReader(tempFileName));
             PrintWriter mainWriter = new PrintWriter(new FileWriter(studentFileName))) {

            String line;
            while ((line = tempReader.readLine()) != null) {
                mainWriter.println(line);
            }
        }

        // Clear the temp file
        try (PrintWriter writer = new PrintWriter(new FileWriter(tempFileName))) {
            writer.print(""); // This will clear the file
        }

        return result;
    }

    //To add a course to student's courses (student file)
    public void addCourseToStudent(String studentID, String course) throws IOException {
        boolean courseAlreadyAdded = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFileName))) {
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[1].equals(studentID)) {
                    if (info[3].contains(course)) {
                        courseAlreadyAdded = true;
                    } else {
                        line += course + ":;";
                    }
                }
                writer.println(line);
            }
        }

        if (!courseAlreadyAdded) {
            try (BufferedReader reader = new BufferedReader(new FileReader(tempFileName));
                 PrintWriter writer = new PrintWriter(new FileWriter(studentFileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.println(line);
                }
            }
        }

        if (!courseAlreadyAdded)
            System.out.println(studentName(studentID) + "added to the course successfully!");
    }

    //To remove a course from student's courses
    public void removeCourseFromStudent(String courseName, String studentID) throws IOException {
        boolean removed = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName));
             FileWriter fileWriter = new FileWriter(tempFileName)) {
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[1].equals(studentID)) {
                    String[] courses = info[4].split(";");
                    StringBuilder updatedLine = new StringBuilder(info[0] + "," + info[1] + "," + info[2] + ",");
                    for (String str : courses) {
                        String[] part = str.split(":");
                        if (!part[0].equals(courseName)) {
                            updatedLine.append(str).append(";");
                        } else {
                            removed = true;
                        }
                    }
                    if (!removed) {
                        System.out.println("Course not found.");
                    }
                    fileWriter.write(updatedLine.toString() + "\n");
                } else {
                    fileWriter.write(line + "\n");
                }
            }
            fileWriter.close();
            PrintWriter writer = new PrintWriter(studentFileName);
            writer.println("");
            writer.close();
            BufferedReader reader1 = new BufferedReader(new FileReader(tempFileName));
            while ((line = reader1.readLine()) != null) {
                FileWriter fileWriter1 = new FileWriter(studentFileName, true);
                fileWriter1.write(line + "\n");
                fileWriter1.close();
            }
            PrintWriter writer1 = new PrintWriter(tempFileName);
            writer1.println("");
            writer1.close();
        }
        if (removed)
            System.out.println(studentID + "removed from the course successfully!");
    }

    //To change the score of a course (student file)
    public void studentScore(String studentID, String courseName, String score) throws IOException {
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))) {
            String line;
            String[] info;
            FileWriter fileWriter = new FileWriter(tempFileName);
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[1].equals(studentID)) {
                    String[] courses = info[3].split(";");
                    StringBuilder updateLine = new StringBuilder(info[0] + "," + info[1] + "," + info[2] + ",");
                    for (String course : courses) {
                        String[] courseInfo = course.split(":");
                        if (courseInfo[0].equals(courseName)) {
                            updateLine.append(courseInfo[0]).append(":").append(score).append(";");
                            found = true;
                        } else {
                            updateLine.append(course).append(";");
                        }
                    }
                    if (!found) {
                        System.out.println("Course not found!");
                    } else {
                        fileWriter.write(updateLine.substring(0, updateLine.length() - 1) + "\n");
                    }
                } else {
                    fileWriter.write(line + "\n");
                }
            }
            fileWriter.close();
            PrintWriter writer = new PrintWriter(studentFileName);
            writer.println("");
            writer.close();
            BufferedReader reader1 = new BufferedReader(new FileReader(tempFileName));
            while ((line = reader1.readLine()) != null) {
                FileWriter fileWriter1 = new FileWriter(studentFileName, true);
                fileWriter1.write(line + "\n");
                fileWriter1.close();
            }
            PrintWriter writer1 = new PrintWriter(tempFileName);
            writer1.println("");
            writer1.close();
        }
        if (found)
            System.out.println("Score added successfully!");
    }

    //To define a new course to course file(name, units, date of exam, teacher)
    public void addCourse(Course course) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName))) {
            String newCourse = course.getCourseName() + "," + course.getCourseUnits() + "," + course.getExamDate() + "," +
                    course.getCourseTeacher().getTeacherName() + " " + course.getCourseTeacher().getTeacherLastName() + ",";
            String line;
            boolean foundInFile = false;
            while ((line = reader.readLine()) != null) {
                if (line.equals(newCourse))
                    foundInFile = true;
                else
                    foundInFile = false;
            }
            if (foundInFile) {
                System.err.println("This Course is already exists!");
            } else {
                FileWriter fileWriter = new FileWriter(courseFileName, true);
                fileWriter.write(newCourse);
                System.out.println("Course added successfully!");
                fileWriter.close();
            }
        }
    }

    //To remove a course from course file
    public void removeCourse(Course course) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName))) {
            String line;
            boolean removed = false;
            String remove = course.getCourseName() + "," + course.getCourseUnits() + "," +
                    course.getExamDate() + ",";
            while ((line = reader.readLine()) != null) {
                if (!line.contains(remove)) {
                    FileWriter fileWriter = new FileWriter(tempFileName, true);
                    fileWriter.write(line + "\n");
                    fileWriter.close();
                } else
                    removed = true;
            }
            if (removed) {
                PrintWriter writer = new PrintWriter(courseFileName);
                writer.println("");
                writer.close();
                BufferedReader reader1 = new BufferedReader(new FileReader(tempFileName));
                while ((line = reader1.readLine()) != null) {
                    FileWriter fileWriter = new FileWriter(courseFileName, true);
                    fileWriter.write(line + "\n");
                    fileWriter.close();
                }
                System.out.println("Course removed successfully!");
            } else
                System.err.println("Oops! Course not found.");
            PrintWriter writer = new PrintWriter(tempFileName);
            writer.println("");
            writer.close();
        }
    }

    //To change information of a course in course file
    public void updateCourse(Course oldCourse, Course newCourse, String teacher) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName))) {
            String line;
            String[] info;
            boolean updated = false;
            String oldLine = oldCourse.getCourseName() + "," + oldCourse.getCourseUnits() + "," + oldCourse.getExamDate() + "," + teacher + ",";
            String newLine = oldCourse.getCourseName() + "," + newCourse.getCourseUnits() + "," + newCourse.getExamDate() + "," + teacher + ",";
            while ((line = reader.readLine()) != null) {
                FileWriter fileWriter = new FileWriter(tempFileName, true);
                if (line.contains(oldLine)) {
                    info = line.split(",");
                    fileWriter.write(newLine + info[4] + "\n");
                    System.out.println(newLine + info[4] + "\n");
                    updated = true;
                } else {
                    fileWriter.write(line + "\n");
                }
                fileWriter.close();
            }
            if (updated) {
                PrintWriter writer = new PrintWriter(courseFileName);
                writer.println("");
                writer.close();
                BufferedReader reader1 = new BufferedReader(new FileReader(tempFileName));
                while ((line = reader1.readLine()) != null) {
                    FileWriter fileWriter = new FileWriter(courseFileName, true);
                    fileWriter.write(line + "\n");
                    fileWriter.close();
                }
                System.out.println("Course updated successfully!");
            } else
                System.err.println("Oops! Course not found.");
            PrintWriter writer = new PrintWriter(tempFileName);
            writer.println("");
            writer.close();
        }
    }

    //To change assignments of a course
    public void updateAssignment(String courseName, Assignment assignment) throws IOException {
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName))) {
            String line;
            String[] info;
            FileWriter fileWriter = new FileWriter(tempFileName);
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[0].equals(courseName)) {
                    String[] assignments = info[4].split(";");
                    StringBuilder updateLine = new StringBuilder(info[0] + "," + info[1] + "," + info[2] + "," + info[3] + ",");
                    for (String str : assignments) {
                        String[] assignmentInfo = str.split(":");
                        if (assignmentInfo[0].equals(assignment.getAssignmentName())) {
                            updateLine.append(assignmentInfo[0]).append(":").append(assignment.getAssignmentName()).append(":").append(assignment.getDayCounter()).append(";");
                            found = true;
                        } else {
                            updateLine.append(str).append(";");
                        }
                    }
                    if (!found) {
                        System.err.println("Assignment not found!");
                    } else {
                        fileWriter.write(updateLine.substring(0, updateLine.length() - 1) + "\n");
                    }
                } else {
                    fileWriter.write(line + "\n");
                }
            }
            fileWriter.close();
            PrintWriter writer = new PrintWriter(courseFileName);
            writer.println("");
            writer.close();
            BufferedReader reader1 = new BufferedReader(new FileReader(tempFileName));
            while ((line = reader1.readLine()) != null) {
                FileWriter fileWriter1 = new FileWriter(courseFileName, true);
                fileWriter1.write(line + "\n");
                fileWriter1.close();
            }
            PrintWriter writer1 = new PrintWriter(tempFileName);
            writer1.println("");
            writer1.close();
        }
        if (found)
            System.out.println("Assignment updated successfully!");
    }

    //To define an assignment for a course
    public void addAssignment(Assignment assignment, String courseName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName))) {
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[0].equals(courseName)) {
                    line += assignment.getAssignmentName() + ":" + assignment.getDayCounter() +";";
                }
                FileWriter fileWriter = new FileWriter(tempFileName, true);
                fileWriter.write(line + "\n");
                fileWriter.close();
            }
            PrintWriter writer = new PrintWriter(courseFileName);
            writer.println("");
            writer.close();
            BufferedReader reader1 = new BufferedReader(new FileReader(tempFileName));
            while ((line = reader1.readLine()) != null) {
                FileWriter fileWriter = new FileWriter(courseFileName, true);
                fileWriter.write(line + "\n");
                fileWriter.close();
            }
            PrintWriter writer1 = new PrintWriter(tempFileName);
            writer1.println("");
            writer1.close();
            System.out.println("Assignment added to the course!");
        }
    }

    //To remove an assignment from a course
    public void removeAssignment(Assignment assignment, String course) throws IOException {
        boolean removed = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName));
             FileWriter fileWriter = new FileWriter(tempFileName)) {
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[0].equals(course)) {
                    String[] assignments = info[4].split(";");
                    StringBuilder updatedLine = new StringBuilder(info[0] + "," + info[1] + "," + info[2] + "," + info[3] + ",");
                    for (String str : assignments) {
                        String[] part = str.split(":");
                        if (!part[0].equals(assignment.getAssignmentName())) {
                            updatedLine.append(str).append(";");
                        } else {
                            removed = true;
                        }
                    }
                    if (!removed) {
                        System.err.println("Assignment not found in course.");
                    }
                    fileWriter.write(updatedLine.toString() + "\n");
                } else {
                    fileWriter.write(line + "\n");
                }
            }
            fileWriter.close();
            PrintWriter writer = new PrintWriter(courseFileName);
            writer.println("");
            writer.close();
            BufferedReader reader1 = new BufferedReader(new FileReader(tempFileName));
            while ((line = reader1.readLine()) != null) {
                FileWriter fileWriter1 = new FileWriter(courseFileName, true);
                fileWriter1.write(line + "\n");
                fileWriter1.close();
            }
            PrintWriter writer1 = new PrintWriter(tempFileName);
            writer1.println("");
            writer1.close();
        }
        if (removed)
            System.out.println("Assignment removed successfully!");
    }

    //To return the highest score of a student
    public double bestScore(String student) throws IOException {
        double max = Double.MIN_VALUE;
        String name = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))) {
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[1].equals(student)) {
                    if (info.length > 3) {
                        String[] course = info[3].split(";");
                        for (String c : course) {
                            String[] score = c.split(":");
                            if (score.length > 1) {
                                if (Double.parseDouble(score[1]) > max) {
                                    max = Double.parseDouble(score[1]);
                                    name = score[0];
                                }
                            }
                        }
                    }
                }
            }
        }
        if (max != Double.MIN_VALUE)
            return (max);
        else
            return -1;
    }

    //To return the lowest score of a student
    public double worstScore(String student) throws IOException {
        double min = Double.MAX_VALUE;
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))){
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[1].equals(student)){
                    if (info.length > 3) {
                        String[] course = info[3].split(";");
                        for (String c : course) {
                            String[] score = c.split(":");
                            if (score.length > 1) {
                                if (Double.parseDouble(score[1]) < min)
                                    min = Double.parseDouble(score[1]);
                            }
                        }
                    }
                }
            }
        }
        if (min < Double.MAX_VALUE)
            return min;
        else
            return -1;
    }

    //To calculate and return the average of a student (and the real name)
    public String GPA(String studentID) throws IOException {
        double total = 0;
        int units = 0;
        String realName = "";
        try (BufferedReader reader1 = new BufferedReader(new FileReader(studentFileName))) {
            String line;
            String[] info;
            while ((line = reader1.readLine()) != null){
                info = line.split(",");
                if (info[1].equals(studentID)){
                    realName = info[0];
                    if (info.length > 3) {
                        String[] course = info[3].split(";");
                        for (String c : course) {
                            String[] part = c.split(":");
                            if (part.length > 1) {
                                units += getNumUnits(part[0]);
                                total += Double.parseDouble(part[1]) * getNumUnits(part[0]);
                            }
                        }
                    }
                }
            }
        }
        if (units != 0){
             return String.valueOf(total / units) + "," + realName;
        }
        else {
            return realName;
        }
    }

    //To send information for page "SARA"
    public String saraInfo(String studentID) throws IOException {
        ArrayList<Double> sara = new ArrayList<>();
        double num = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))) {
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[1].equals(studentID)) {
                    String[] courses = info[3].split(";");
                    for (String c : courses) {
                        String[] part = c.split(":");
                        num += getAssignments(part[0]).size();
                    }
                }
            }
        }
        sara.add(bestScore(studentID));
        sara.add(worstScore(studentID));
        sara.add(num);

        // Convert the ArrayList<Double> to a String.
        StringBuilder result = new StringBuilder();
        for (Double d : sara) {
            if (d != null) {  // Check for null values
                result.append(d.toString()).append(",");
            }
        }
        return result.toString();
    }

    //To send information for page "CLASSA" (showing courses)
    public String classaInfo(String studentID) throws IOException {
        String courses = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))) {
            String line;
            String[] info;
            while ((line = reader.readLine()) != null){
                info = line.split(",");
                if (info[1].equals(studentID) && info.length > 3){
                    String[] course = info[3].split(";");
                    for (int i = 0; i < course.length; i++){
                        String[] part = course[i].split(":");
                        courses += courseInfo(part[0]);
                        if (i < course.length - 1){
                            courses += ",";
                        }
                    }
                }
            }
        }
        return courses;
    }

    //To send information for page "CLASSA" (adding course)
    public String addClassaInfo(String studentID, String courseID) throws IOException {
        StringBuilder newCourse = new StringBuilder();
        boolean courseAdded = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))) {
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info.length > 3 && info[1].equals(studentID) && !info[3].contains(courseName(courseID))) {
                    addCourseToStudent(studentID, courseName(courseID));
                    if (!courseAdded) {
                        newCourse.append(courseInfo(courseName(courseID)));
                        courseAdded = true;
                    }
                }
            }
        }
        return newCourse.toString();
    }

    //To send information for page "TAMRINA" (name of course, assignment name, deadline)
    public String tamrinaInfo(String studentID) throws IOException {
        StringBuilder tamrina = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))){
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info.length > 1 && info[1].equals(studentID) && info.length > 3){
                    String[] course = info[3].split(";");
                    for (int i = 0; i < course.length; i++){
                        String[] part = course[i].split(":");
                        if (part.length == 2) {
                            tamrina.append(part[0]);
                            ArrayList<String> tamrin = getAssignments(part[0]);
                            for (String s : tamrin) {
                                tamrina.append(s);
                            }
                            if (i < course.length - 1) {
                                tamrina.append(";");
                            }
                        }
                    }
                    break;
                }
            }
        }
        return tamrina.toString();
    }

    //To send tasks for the page "KARA"
    public String sendTasks (String studentID) throws IOException {
        String tasks = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(taskFileName))) {
            String line;
            String[] info;
            while ((line = reader.readLine()) != null){
                info = line.split(";");
                if (info[0].equals(studentID)) {
                    tasks = info[1];
                }
            }
        }
        return tasks;
    }

    //To add task to todolist, for the page "KARA"
    public void addTask(String studentID, String task) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(taskFileName));
                PrintWriter tempWriter = new PrintWriter(new FileWriter(tempFileName, true))) {
            String line;
            boolean taskAdded = false;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(";");
                if (info[0].equals(studentID)) {
                    line += "," + task;
                    taskAdded = true;
                }
                tempWriter.println(line);
            }
            //for the time student ID not found
            if (!taskAdded) {
                tempWriter.println(studentID + ";" + task);
            }
        }

        try (BufferedReader tempReader = new BufferedReader(new FileReader(tempFileName));
                PrintWriter writer = new PrintWriter(new FileWriter(taskFileName))) {
            String line;
            while ((line = tempReader.readLine()) != null) {
                writer.println(line);
            }
        }
        try (PrintWriter tempWriter = new PrintWriter(new FileWriter(tempFileName))) {
            tempWriter.print("");
        }
    }


    //To remove a task from todolist, for the page "KARA"
    public void removeTask(String studentID, String task) throws IOException {
        boolean removed = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(taskFileName));
             FileWriter fileWriter = new FileWriter(tempFileName)) {
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(";");
                if (info[0].equals(studentID)) {
                    String[] tasks = info[1].split(",");
                    StringBuilder updatedLine = new StringBuilder(info[0] + ";");
                    for (String str : tasks) {
                        if (!str.contains(task)) {
                            updatedLine.append(str).append(",");
                        } else {
                            removed = true;
                        }
                    }
                    if (!removed) {
                        System.out.println("Task did not found.");
                    }
                    fileWriter.write(updatedLine.toString().replaceAll(",$", "") + "\n");
                } else {
                    fileWriter.write(line + "\n");
                }
            }
            fileWriter.close();
            PrintWriter writer = new PrintWriter(taskFileName);
            writer.println("");
            writer.close();
            BufferedReader reader1 = new BufferedReader(new FileReader(tempFileName));
            while ((line = reader1.readLine()) != null) {
                FileWriter fileWriter1 = new FileWriter(taskFileName, true);
                fileWriter1.write(line + "\n");
                fileWriter1.close();
            }
            PrintWriter writer1 = new PrintWriter(tempFileName);
            writer1.println("");
            writer1.close();
        }
    }

    //To send news, for the page "KHABARA"
    public String khabaraInfo() throws IOException {
        StringBuilder news = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(newsFileName))){
            String line;
            while ((line = reader.readLine()) != null) {
                news.append(line).append("~");
            }
        }
        news.toString().replaceAll("~$", "");
        return news.toString();
    }

    //To add news (command from Cli)
    public void addNews (String title, String text) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(newsFileName))) {
            String line;
            boolean newsAdded = true;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(";");
                if (info[0].equals(title)) {
                    newsAdded = false;
                }
            }

            if (newsAdded) {
                String completeText = title + ":" + text + "\n";
                FileWriter fileWriter = new FileWriter(newsFileName, true);
                fileWriter.write(completeText);
                fileWriter.close();
                System.out.println("News added successfully!");
            } else if (!newsAdded) {
                System.err.println("This title is in use. Try again with another title.");
            }
        }
    }

    //To Removing news (command from Cli)
    public void removeNews(String title) throws IOException {
        boolean removed = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(newsFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(":");
                if (!info[0].equals(title)) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    removed = true;
                }
            }
        }

        if (removed) {
            try (BufferedReader reader = new BufferedReader(new FileReader(tempFileName));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(newsFileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            System.out.println("News removed successfully!");
        } else {
            System.err.println("Cannot find this news. Try again please...");
        }
    }

}