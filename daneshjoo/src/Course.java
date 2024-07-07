import java.io.IOException;
import java.util.ArrayList;

public class Course {
    private String courseName;
    private Teacher courseTeacher;
    private int courseUnits;
    private String courseID;
    private boolean isCourseActive;
    private int numPractices;
    private String examDate;
    private int numStudents;


    //Constructor
    public Course(String name, String courseID, int units, String examDate, Teacher teacher){
        this.courseName = name;
        this.courseID = courseID;
        this.courseUnits = units;
        this.examDate = examDate;
        this.isCourseActive = false;
        this.numPractices = 0;
        this.courseTeacher = teacher;
        this.numStudents = 0;
    }


    //Getters
    public int getCourseUnits() {
        return courseUnits;
    }
    public String getExamDate() {
        return examDate;
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
    public String getCourseID() {
        return courseID;
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
    public void setNumPractices(int numPractices) {
        this.numPractices = numPractices;
    }
    public void setCourseActive(boolean courseActive) {
        isCourseActive = courseActive;
    }
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
}


