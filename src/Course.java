import java.io.IOException;
import java.util.ArrayList;

public class Course {
    private String courseName;
    private Teacher courseTeacher;
    private int courseUnits;
    private boolean isCourseActive;
    private int numPractices;
    private String examDate;
    private int numStudents;
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
        this.numActiveProjects = 0;
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
    public int getNumActiveProjects() {
        return numActiveProjects;
    }


    //Setters
    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }
    public void setNumActiveProjects(int numActiveProjects) {
        this.numActiveProjects = numActiveProjects;
    }
}


