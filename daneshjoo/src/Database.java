import java.io.*;


public class Database {

    //Files
    String studentFileName = "C:\\Users\\mnoro\\Desktop\\main ap\\daneshjooyar\\daneshjoo\\src\\data\\students.txt";
    File studentFile = new File(studentFileName);
    String teacherFileName = "src/data/teachers.txt";
    File teacherFile = new File(teacherFileName);
    String courseFileName = "src/data/courses.txt";
    File courseFile = new File(courseFileName);


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


    //Methods for Database
    //Sign Up: To save the information of students in file.
    public void signUp(String username, String password) throws IOException {
        boolean isExist = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))) {
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (username.equals(info[0])) {
                    System.out.println("You can't use this username!");
                    isExist = true;
                    break;
                }
                else {

                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


    //Log In: To check correction of username and password
    public int logIn(String username, String password) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))) {
            String line;
            String[] info;
            int result = 0;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (username.equals(info[1])) {
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
            return result; // 0; username incorrect
        }
    }


    //To add a course to
    public void addCourseToStudent(String student, String course) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))) {
            String tempFileName = "src/data/temp.txt";
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[0].equals(student)) {
                    line += course + ":;";
                }
                FileWriter fileWriter = new FileWriter(tempFileName, true);
                fileWriter.write(line + "\n");
                fileWriter.close();
            }
            PrintWriter writer = new PrintWriter(studentFileName);
            writer.println("");
            writer.close();
            BufferedReader reader1 = new BufferedReader(new FileReader(tempFileName));
            while ((line = reader1.readLine()) != null) {
                FileWriter fileWriter = new FileWriter(studentFileName, true);
                fileWriter.write(line + "\n");
                fileWriter.close();
            }
            PrintWriter writer1 = new PrintWriter(tempFileName);
            writer1.println("");
            writer1.close();
        }
    }

    public void removeCourseFromStudent(String cName, String sName) throws IOException {
        String tempFileName = "src/data/temp.txt";
        boolean removed = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName));
             FileWriter fileWriter = new FileWriter(tempFileName)) {
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[0].equals(sName)) {
                    String[] courses = info[4].split(";");
                    StringBuilder updatedLine = new StringBuilder(info[0] + "," + info[1] + "," + info[2] + ",");
                    for (String str : courses) {
                        String[] part = str.split(":");
                        if (!part[0].equals(cName)) {
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
    }

    public void studentScore(String student, String courseName, String score) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))) {
            String tempFileName = "src/data/temp.txt";
            String line;
            String[] info;
            FileWriter fileWriter = new FileWriter(tempFileName);
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[0].equals(student)) {
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
    }

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

    public void removeCourse(Course course) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName))) {
            String tempFileName = "src/data/temp.txt";
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

    public void updateCourse(Course oldCourse, Course newCourse, String teacher) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName))) {
            String tempFileName = "src/data/temp.txt";
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

    public void updateAssignment(String courseName, Assignment assignment) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName))) {
            String tempFileName = "src/data/temp.txt";
            String line;
            String[] info;
            FileWriter fileWriter = new FileWriter(tempFileName);
            boolean found = false;
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
                        System.out.println("Assignment not found!");
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
    }

    public void addAssignment(Assignment assignment, String courseName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName))) {
            String tempFileName = "src/data/temp.txt";
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
        }
    }

    public void removeAssignment(Assignment assignment, String course) throws IOException {
        String tempFileName = "src/data/temp.txt";
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
                        System.out.println("Assignment not found in course.");
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
    }
    public String maxScore(String student) {
        double max = Double.MIN_VALUE;

        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))){
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[0].equals(student)){
                    if (info.length > 3) {
                        String[] course = info[3].split(";");
                        for (String c : course) {
                            String[] score = c.split(":");
                            if (score.length > 1) {
                                if (Double.parseDouble(score[1]) > max)
                                    max = Double.parseDouble(score[1]);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        if (max != Double.MIN_VALUE)
            return String.valueOf(max);
        else
            return "nothing to show";
    }

    public String minScore(String student) {
        double min = Double.MAX_VALUE;
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))){
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (info[0].equals(student)){
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
        } catch (IOException e) {
            throw new RuntimeException();
        }
        if (min != Double.MAX_VALUE)
            return String.valueOf(min);
        else
            return "nothing to show";
    }

    public String Average(String student) {
        double total = 0;
        int units = 0;
        try (BufferedReader reader1 = new BufferedReader(new FileReader(studentFileName))) {
            String line;
            String[] info;
            while ((line = reader1.readLine()) != null){
                info = line.split(",");
                if (info[0].equals(student) && info.length > 3){
                    String[] course = info[3].split(";");
                    for (String c : course){
                        String[] part = c.split(":");
                        if (part.length > 1){
                            units += getNumUnits(part[0]);
                            total += Double.parseDouble(part[1]) * getNumUnits(part[0]);
                        }
                    }
                }
            }
        } catch (IOException e){
            throw new RuntimeException();
        }
        if (units != 0){
            double average = total / units;
            return String.valueOf(average);
        }
        else {
            return "nothing to show";
        }
    }
}