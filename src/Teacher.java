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
}
