import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Student {
    private String studentName;
    private int userName;
    private int numRegisteredCourses;
    private ArrayList<Course> registeredCourses;
    private int numUnits;
    private double totalAverage;
    private double semesterAverage;
    private Map<String, Double> courseGrades;


    //Constructor
    public Student(String studentName, int userName){
        this.studentName = studentName;
        this.userName = userName;
        this.registeredCourses = new ArrayList<>();
        this.totalAverage = 0.0;
        this.courseGrades = new HashMap<>();
        this.semesterAverage = 0.0;
        this.numRegisteredCourses = 0;
    }


    //Getters
    public String getStudentName() {
        return studentName;
    }
    public int getUserName(){
        return userName;
    }
    public int getNumRegisteredCourses(){
        return numRegisteredCourses;
    }
    public int getNumUnits(){
        return numUnits;
    }
    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }
    public double getSemesterAverage() {
        return semesterAverage;
    }
    public double getTotalAverage() {
        return totalAverage;
    }
    public Map<String, Double> getCourseGrades() {
        return courseGrades;
    }
    public Double getCourseGradestest(Course course) {
        return courseGrades.get(course.getCourseName());
    }


    //Setters
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public void setRegisteredCourses(ArrayList<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }
    public void setCourseGrades(String courseName, double grade){
        courseGrades.put(courseName, grade);
    }
    public void setSemesterAverage(double semesterAverage) {
        this.semesterAverage = semesterAverage;
    }


    //Methods
    public void addCourse(Course course) throws IOException {
        if (!getRegisteredCourses().contains(course)){
            registeredCourses.add(course);
            numUnits += course.getCourseUnits();
            numRegisteredCourses++;
            if (!course.getStudents().contains(this)){
                course.addStudent(this);
            }
        }
    }
    public void removeCourse(Course course){
        if (getRegisteredCourses().contains(course)) {
            registeredCourses.remove(course);
            numUnits -= course.getCourseUnits();
            numRegisteredCourses--;
            if (course.getStudents().contains(this)) {
                course.removeStudent(this);
            }
        }
    }
    public void prtRegisteredCourses(){
        ArrayList<Course> courses = getRegisteredCourses();
        if (courses != null) {
            for (int i = 0; i < courses.size(); i++) {
                System.out.println(courses.get(i).getCourseName());
            }
        }
        else
            System.out.println("You are not registered in a course yet!");
    }
    public void prtTotalAverage(Student student){
        System.out.println(student.getTotalAverage());
    }
    public double calculateSemesterAverage(){
        double total = 0;
        int units = 0;
        for (Course course : registeredCourses){
            double grade = courseGrades.getOrDefault(course.getCourseName(), 0.0);
            int courseUnits = course.getCourseUnits();
            total += grade * courseUnits;
            units += courseUnits;
        }
        if (units > 0){
            semesterAverage = total / units;
            totalAverage = (totalAverage + (total / units)) / 2;
            return semesterAverage;
        }
        return -1;
    }
    public void prtNumUnits(){
        System.out.println("Number of registered units: " + getNumUnits());
    }
}
