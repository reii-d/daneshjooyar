import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Teacher {
    private String teacherName;
    private String teacherLastName;
    private int numCoursesTaught;
    private ArrayList<Course> coursesTaught;
    Database database = Database.getInstance();


    //Constructor
    public Teacher(String firstName, String lastName){
        this.teacherName = firstName;
        this.teacherLastName = lastName;
        this.numCoursesTaught = 0;
        this.coursesTaught = new ArrayList<>();
    }


    //Getters
    public String getTeacherLastName() {
        return teacherLastName;
    }
    public String getTeacherName() {
        return teacherName;
    }
    public int getNumCoursesTaught() {
        return numCoursesTaught;
    }
    public ArrayList<Course> getCoursesTaught() {
        return coursesTaught;
    }


    //Setters
    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public void setNumCoursesTaught(int numCoursesTaught) {
        this.numCoursesTaught = numCoursesTaught;
    }
    public void setCoursesTaught(ArrayList<Course> coursesTaught) {
        this.coursesTaught = coursesTaught;
    }


    //Methods
    public void addCourse(Course course){
        coursesTaught.add(course);
        numCoursesTaught++;
    }
    public void removeCourse(Course course){
        coursesTaught.remove(course);
        numCoursesTaught--;
    }
    public void manageStudentInCourse(Student student, Course course, boolean add) throws IOException {
        if (coursesTaught.contains(course)){
            if (add) {
                course.addStudent(student);
                course.setNumStudents(course.getNumStudents() + 1);
            }
            else {
                course.removeStudent(student);
                course.setNumStudents(course.getNumStudents() - 1);
            }
        }
        else {
            System.out.println("1.check");
        }
    }
    public void manageProjects (Assignment project, Course course, boolean add){
        if (coursesTaught.contains(course)) {
            if (add){
                course.getActiveProjects().add(project);
                course.setNumActiveProjects(course.getNumActiveProjects() + 1);
            }
            else {
                for (Assignment assignment : course.getActiveProjects()){
                    if (assignment.getAssignmentName().equals(project.getAssignmentName())){
                        course.getActiveProjects().remove(assignment);
                        course.setNumActiveProjects(course.getNumActiveProjects() - 1);
                        break;
                    }
                }
            }
        }
        else {
            System.out.println("2.check");
        }
    }
    public int getTotalUnitsTaught(){
        int totalUnits = 0;
        for (Course course : coursesTaught){
            totalUnits += course.getCourseUnits();
        }
        return totalUnits;
    }
    public void TeacherSetCourseGrades(Course course, double grade, Student student){
        String courseName = course.getCourseName();
        String courseUnit = Integer.toString(course.getCourseUnits());
        String stu = student.getStudentName();
        String courseExam = course.getExamDate();
        String teacher = this.getTeacherName() + " " + this.getTeacherLastName();
        try (BufferedReader reader = new BufferedReader(new FileReader(database.courseFileName))){
            String line;
            String[] info;
            boolean ok = false;
            while ((line = reader.readLine()) != null){
                info = line.split(",");
                if (info[0].equals(courseName) && info[3].equals(teacher) && info[1].equals(courseUnit) && info[2].equals(courseExam)){
                    database.addScoreToStudent(stu, courseName, Double.toString(grade));
                    ok = true;
                    break;
                }
            }
            if (!ok){
                System.out.println("You have not this course!");
            }
            if (ok){
                System.out.println("Score added successfully!");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void definePractice(String practiceName, int deadline, Course course){
        Assignment practice = new Assignment(practiceName, deadline);
        practice.setAssignmentName(practiceName);
        course.getPractices().add(practice);
        course.setNumPractices(course.getNumPractices() + 1);
    }
    public void removePractice(String practiceName, Course course){
        for (Assignment practice : course.getPractices()){
            if (practice.getAssignmentName().equals(practiceName)){
                course.getPractices().remove(practice);
                course.setNumPractices(course.getNumPractices() - 1);
                break;
            }
        }
    }
}
