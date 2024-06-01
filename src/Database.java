import java.io.*;
import javax.imageio.IIOException;

public class Database {

    //Files
    String studentFileName = "src/data/students.txt";
    File studentFile = new File(studentFileName);
    String teacherFileName = "src/data/teachers.txt";
    File teacherFile = new File(teacherFileName);
    String courseFileName = "src/data/courses.txt";
    File courseFile = new File(courseFileName);
    String tempFileName = "src/data/temp.txt";
    File tempFile = new File(tempFileName);


    //Constructor
    private static Database instance;
    private Database(){}
    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    public String getTeacherName (String teacherID) throws IOException {
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


    //Methods
    public void addSCourseToStudent(String student, String course) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))){
            String line;
            String[] info;
            while ((line = reader.readLine()) != null){
                info = line.split(",");
                if (info[0].equals(student)) {
                    line += course + ": ;";
                }
                FileWriter fileWriter = new FileWriter(tempFileName, true);
                fileWriter.write(line + "\n");
                fileWriter.close();
            }
            PrintWriter writer = new PrintWriter(studentFileName);
            writer.println("");
            writer.close();
            BufferedReader reader1 = new BufferedReader(new FileReader(tempFileName));
            while ((line = reader1.readLine()) != null){
                FileWriter fileWriter = new FileWriter(studentFileName, true);
                fileWriter.write(line + "\n");
                fileWriter.close();
            }
            PrintWriter writer1 = new PrintWriter (tempFileName);
            writer1.println("");
            writer1.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void removeCourseFromStudent() throws IOException {

    }
    public void addScoreToStudent(String student, String courseName, String score) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))){
            String line;
            String[] info;
            FileWriter fileWriter = new FileWriter(tempFileName);
            boolean found = false;
            while ((line = reader.readLine()) != null){
                info = line.split(",");
                if (info[0].equals(student)){
                    String[] courses = info[3].split(";");
                    StringBuilder updateLine = new StringBuilder(info[0] + "," + info[1] + "," + info[2] + ",");
                    for (String course : courses) {
                        String[] courseInfo = course.split(":");
                        if (courseInfo[0].equals(courseName)){
                            updateLine.append(courseInfo[0]).append(":").append(score).append(";");
                            found = true;
                        }
                        else {
                            updateLine.append(course).append(";");
                        }
                    }
                    if (!found){
                        System.out.println("Course not found!");
                    }
                    else {
                        fileWriter.write(updateLine.substring(0, updateLine.length() - 1) + "\n");
                    }
                }
                else {
                    fileWriter.write(line + "\n");
                }
            }
            fileWriter.close();
            PrintWriter writer = new PrintWriter (studentFileName);
            writer.println("");
            writer.close();
            BufferedReader reader1 = new BufferedReader (new FileReader(tempFileName));
            while ((line = reader1.readLine()) != null){
                FileWriter fileWriter1 = new FileWriter(studentFileName, true);
                fileWriter1.write(line + "\n");
                fileWriter1.close();
            }
            PrintWriter writer1 = new PrintWriter (tempFileName);
            writer1.println("");
            writer1.close();
        }
    }
    public void removeScoreFromStudent() throws IOException {

    }
    public void addCourseToTeacher() throws IOException {

    }
    public void removeCourseFromTeacher() throws IOException {

    }
    public void updateStudent() throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))){
            String line;
            String[] info;
            while ((line = reader.readLine()) != null){
                info = line.split(":");
                if (info[0] == "1"){
                    line = line.substring(0, line.length() - 3);
                }
                FileWriter fileWriter = new FileWriter(tempFileName, true);
                fileWriter.write(line + "\n");
                fileWriter.close();
            }
            PrintWriter writer = new PrintWriter(studentFileName);
            writer.println("");
            writer.close();
            BufferedReader reader1 = new BufferedReader(new FileReader(tempFileName));
            while ((line = reader1.readLine()) != null){
                FileWriter fileWriter = new FileWriter(studentFileName, true);
                fileWriter.write(line + "\n");
                fileWriter.close();
            }
        }
        catch (IIOException e){
            e.printStackTrace();
        }
    }

    public void removeStudent() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))){
            String line;
            String[] info;
            while ((line = reader.readLine()) != null){
                info = line.split(":");
                if (info[0] == "1") {
                    line = line.substring(0, line.length() - 3);
                    FileWriter fileWriter = new FileWriter(tempFileName, true);
                    fileWriter.write(line + "\n");
                    fileWriter.close();
                }
            }
            PrintWriter writer = new PrintWriter(studentFileName);
            writer.println("");
            writer.close();
            BufferedReader reader1 = new BufferedReader(new FileReader(tempFileName));
            while ((line = reader1.readLine()) != null){
                FileWriter fileWriter = new FileWriter(studentFileName, true);
                fileWriter.write(line + "\n");
                fileWriter.close();
            }
        }
        catch (IIOException e){
            e.printStackTrace();
        }
    }
}