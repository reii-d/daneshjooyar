import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Teacher {
    private String teacherFirstName;
    private String teacherLastName;
    private int numCoursesTaught;
    private ArrayList<Course> coursesTaught;


    //Constructor
    public Teacher(String firstName, String lastName){
        this.teacherFirstName = firstName;
        this.teacherLastName = lastName;
        this.numCoursesTaught = 0;
        this.coursesTaught = new ArrayList<>();
    }


    //Getters
    public String getTeacherLastName() {
        return teacherLastName;
    }
    public String getTeacherFirstName() {
        return teacherFirstName;
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
    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
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
    public void manageStudentInCourse(Student student, Course course, boolean add){
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
        if (coursesTaught.contains(course) && student.getRegisteredCourses().contains(course)){
            student.setCourseGrades(course.getCourseName(), grade);
        }
        else {
            System.out.println("3.check");
        }
    }
}
