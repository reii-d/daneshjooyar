import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class tester{
    @Test
    public void TestStudent() {

        Course course1 = new Course("course 1" , 3,"111",new Teacher("teacher1", "teacher1.1"));
        Course course2 = new Course("course 2" , 4,"222",new Teacher("teacher2", "teacher2.2"));
        Course course3 = new Course("course 3" , 5,"333",new Teacher("teacher3", "teacher3.3"));

        Student student1 = new Student("st1",402243108);
        Student student2 = new Student("st2",402243072);
        Student student3 = new Student("st3",402243050);

        // id test
        assertEquals(402243108,student1.getId());
        assertEquals(402243072,student2.getId());
        assertEquals(402243050,student3.getId());

        //course test
        student1.addCourse(course1);
        student1.addCourse(course2);

        ArrayList<Course> courses1 = new ArrayList<>();
        courses1.add(course1);
        courses1.add(course2);


        assertEquals(courses1,student1.getRegisteredCourses());
        assertFalse(student1.getRegisteredCourses().contains(course3));

        assertEquals(7,student1.getNumUnits());
        assertEquals(0,student2.getNumUnits());
        assertEquals(0,student3.getNumUnits());

        //grade test
        student1.addCourse(course3);

        //removam test benevis
        //ino behtare dorost konim //////////////////////////////////////
        student1.setCourseGrades("course 1" , 10.0);
        student1.setCourseGrades("course 2" , 30.0);
        student1.setCourseGrades("course 3" , 15.0);

        assertEquals(18.75,student1.calculateSemesterAverage(),0.01);
    }
    @Test
    public void TestTecher() {
        Teacher teacher1 = new Teacher("teacher1" , "teacher1.1");
        Teacher teacher2 = new Teacher("teacher2" , "teacher2.2");
        Teacher teacher3 = new Teacher("teacher3" , "teacher3.3");
        Teacher teacher4 = new Teacher("teacher4" , "teacher4.4");

        Course course1 = new Course("course 1" , 3,"111",teacher1);
        Course course12 = new Course("course 12" , 3,"111",teacher1);
        Course course13 = new Course("course 13" , 3,"111",teacher1);
        Course course2 = new Course("course 2" , 4,"222",teacher2);
        Course course3 = new Course("course 3" , 5,"333",teacher3);

        Student student1 = new Student("st1",402243108);
        Student student2 = new Student("st2",402243072);
        Student student3 = new Student("st3",402243050);
        Student student4 = new Student("st4",402243052);

        Assignment assignment1 = new Assignment("assignment1",111);
        Assignment assignment2 = new Assignment("assignment2",222);

        //add / remove
        teacher1.addCourse(course1);
        teacher1.addCourse(course12);
        teacher1.addCourse(course13);
        teacher1.removeCourse(course12);
        assertFalse(teacher1.getCoursesTaught().contains(course12));

        //course add
        teacher1.manageStudentInCourse(student1, course1,true);
        teacher1.manageStudentInCourse(student2, course1,true);
        teacher1.manageStudentInCourse(student3, course1,true);
        teacher1.manageStudentInCourse(student3, course2,true);//bayad 1.check bede
        teacher1.manageStudentInCourse(student1, course1,false);
        assertFalse(course1.getStudents().contains(student1));

        //defineProject / removeProject
        teacher2.addCourse(course2);
        teacher2.manageProjects(assignment1,course2,true);
        teacher2.manageProjects(assignment2,course2,true);
        assertTrue(course2.getActiveProjects().contains(assignment1));
        teacher2.manageProjects(assignment2,course2,false);
        assertFalse(course2.getActiveProjects().contains(assignment2));
        teacher2.manageProjects(assignment2,course1,false);

        //units
        teacher3.addCourse(course3);
        assertEquals(5,teacher3.getTotalUnitsTaught());
        teacher3.addCourse(course2);
        assertEquals(9,teacher3.getTotalUnitsTaught());
        teacher3.removeCourse(course2);
        assertEquals(5,teacher3.getTotalUnitsTaught());

        //teacher set grade
        teacher4.addCourse(course3);
        student4.addCourse(course3);
        teacher4.TeacherSetCourseGrades(course3,10.0,student4);
        assertEquals(10.0,student4.getCourseGradestest(course3),0.01);
        teacher4.TeacherSetCourseGrades(course3,40.0,student4);
        assertEquals(40.0,student4.getCourseGradestest(course3),0.01);

    }
    //    @Test
    public void TestCourse(){
        Teacher teacher1 = new Teacher("teacher1" , "teacher1.1");
        Teacher teacher2 = new Teacher("teacher2" , "teacher2.2");
        Teacher teacher3 = new Teacher("teacher3" , "teacher3.3");
        Teacher teacher4 = new Teacher("teacher4" , "teacher4.4");

        Course course1 = new Course("course 1" , 3,"111",teacher1);
        Course course2 = new Course("course 2" , 4,"222",teacher2);
        Course course3 = new Course("course 3" , 5,"333",teacher3);

        Student student1 = new Student("st1",402243108);
        Student student2 = new Student("st2",402243072);
        Student student3 = new Student("st3",402243050);
        Student student4 = new Student("st4",402243052);

        student1.addCourse(course1);
        student2.addCourse(course1);
        student3.addCourse(course1);
        student4.addCourse(course1);

        //top score test

        student1.setCourseGrades("course 1" , 10.0);
        student2.setCourseGrades("course 1" , 30.0);
        student3.setCourseGrades("course 1" , 15.0);
        assertEquals(30.0,course1.findHighestGrade(),0.01);

        student4.setCourseGrades("course 1" , 55.0);
        assertEquals(55.0,course1.findHighestGrade(),0.01);

        course1.giveGrade(student1,70.0);
        assertEquals(70.0,course1.findHighestGrade(),0.01);

    }
    @Test
    public void TestAssingment(){
        //felan chizi nadare
    }
    @Test
    public void TestOverall(){
        Teacher teacher1 = new Teacher("teacher1" , "teacher1.1");
        Teacher teacher2 = new Teacher("teacher2" , "teacher2.2");
        Teacher teacher3 = new Teacher("teacher3" , "teacher3.3");
        Teacher teacher4 = new Teacher("teacher4" , "teacher4.4");

        Course course1 = new Course("course 1" , 3,"111",teacher1);
        Course course2 = new Course("course 2" , 4,"222",teacher2);
        Course course3 = new Course("course 3" , 5,"333",teacher3);
        Course course4 = new Course("course 4" , 6,"444",teacher4);

        Student student1 = new Student("st1",402243108);
        Student student2 = new Student("st2",402243072);
        Student student3 = new Student("st3",402243050);
        Student student4 = new Student("st4",402243052);

        student1.addCourse(course1);
        student1.addCourse(course2);

        course1.addStudent(student3);

        student1.prtRegisteredCourses();
        course1.printCourseStudents();

        student1.removeCourse(course1);
        course1.removeStudent(student1);

        student1.prtRegisteredCourses();
        course1.printCourseStudents();

    }
}