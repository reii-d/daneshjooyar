import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Teacher {
    private String teacherName;
    private String teacherLastName;
    private int numCoursesTaught;


    //Constructor
    public Teacher(String firstName, String lastName){
        this.teacherName = firstName;
        this.teacherLastName = lastName;
        this.numCoursesTaught = 0;
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
}