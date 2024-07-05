import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Student {
    private String studentName;
    private int userName;
    private int numRegisteredCourses;
    private int numUnits;
    private double totalAverage;
    private double semesterAverage;


    //Constructor
    public Student(String studentName, int userName){
        this.studentName = studentName;
        this.userName = userName;
        this.totalAverage = 0.0;
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
    public double getSemesterAverage() {
        return semesterAverage;
    }
    public double getTotalAverage() {
        return totalAverage;
    }


    //Setters
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public void setSemesterAverage(double semesterAverage) {
        this.semesterAverage = semesterAverage;
    }
}
