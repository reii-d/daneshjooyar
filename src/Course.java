import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Course {
    Database database = Database.getInstance();
    private String courseName;
    private Teacher courseTeacher;
    private int courseUnits;
    private ArrayList<Student> students;
    private boolean isCourseActive;
    private ArrayList<Assignment> assignments;
    private int numPractices;
    private String examDate;
    private int numStudents;
    private ArrayList<Assignment> activeProjects;
    private int numActiveProjects;


    //Constructor
    public Course(String name, int units, String examDate, Teacher teacher){
        this.courseName = name;
        this.courseUnits = units;
        this.examDate = examDate;
        this.isCourseActive = false;
        this.numPractices = 0;
        this.courseTeacher = teacher;
        this.numStudents = 0;
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
        this.activeProjects = new ArrayList<>();

        this.numActiveProjects = 0;
    }


    //Getters
    public int getCourseUnits() {
        return courseUnits;
    }
    public boolean isCourseActive() {
        return isCourseActive;
    }
    public String getExamDate() {
        return examDate;
    }
    public int getNumPractices() {
        return numPractices;
    }
    public int getNumStudents() {
        return numStudents;
    }
    public String getCourseName() {
        return courseName;
    }
    public Teacher getCourseTeacher() {
        return courseTeacher;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
    public ArrayList<Assignment> getPractices() {
        return assignments;
    }
    public int getNumActiveProjects() {
        return numActiveProjects;
    }
    public ArrayList<Assignment> getActiveProjects() {
        return activeProjects;
    }


    //Setters
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setCourseTeacher(Teacher courseTeacher) {
        this.courseTeacher = courseTeacher;
    }
    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }
    public void setCourseUnits(int courseUnits) {
        this.courseUnits = courseUnits;
    }
    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }
    public void setActiveProjects(ArrayList<Assignment> activeProjects) {
        this.activeProjects = activeProjects;
    }
    public void setPractices(ArrayList<Assignment> practices) {
        this.assignments = practices;
    }
    public void setNumPractices(int numPractices) {
        this.numPractices = numPractices;
    }
    public void setCourseActive(boolean courseActive) {
        isCourseActive = courseActive;
    }
    public void setNumActiveProjects(int numActiveProjects) {
        this.numActiveProjects = numActiveProjects;
    }


    //Methods
    public void addStudent(Student student) throws IOException {
        String course = this.getCourseName();
        String stu = student.getStudentName();
        database.addSCourseToStudent(stu, course);
    }
    public void removeStudent(Student student){
        if (getStudents().contains(student)) {
            students.remove(student);
            numStudents--;
            if (student.getRegisteredCourses().contains(this)) {
                student.removeCourse(this);
            }
        }
    }
    public void printCourseStudents(){
        ArrayList<Student> students = getStudents();
        if (students != null){
            for (Student student : students) {
                System.out.println("Student Name: " + student.getStudentName());
            }
        }
    }
    public void giveGrade(Student student, double grade){
        student.setCourseGrades(courseName, grade);
    }
    public double findHighestGrade(){
        double highest = 0;
        for (Student student : students){
            if(student.getCourseGrades().containsKey(courseName)){
                double grade = student.getCourseGrades().get(courseName);
                highest = Math.max(highest, grade);
            }
        }
        return highest;
    }
}
