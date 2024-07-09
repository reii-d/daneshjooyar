import javax.swing.*;
import java.awt.*;
import java.io.*;

public class graphic {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createMainFrame());
    }

    //Page for Main menu
    private static void createMainFrame() {
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //Exit
        frame.setSize(800, 600);        //length and width
        frame.setLayout(new GridLayout(4, 1));

        //Buttons
        JLabel label = new JLabel("Main Menu:", SwingConstants.CENTER);
        JButton adminButton = new JButton("Admin");
        JButton teacherButton = new JButton("Teacher");
        JButton exitButton = new JButton("Exit");

        frame.add(label);
        frame.add(adminButton);
        frame.add(teacherButton);
        frame.add(exitButton);

        adminButton.addActionListener(e -> createAdminFrame());
        teacherButton.addActionListener(e -> teacherLogin(frame));
        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }


    //ADMIN
    //Page for Admin Access
    private static void createAdminFrame() {
        JFrame adminFrame = new JFrame("Admin Access");
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setSize(800, 600);
        adminFrame.setLayout(new GridLayout(6, 1));

        JLabel label = new JLabel("Admin Access:", SwingConstants.CENTER);
        JButton teachersButton = new JButton("Teachers");
        JButton coursesButton = new JButton("Courses");
        JButton studentsButton = new JButton("Students");
        JButton newsButton = new JButton("News");
        JButton backButton = new JButton("Back");

        adminFrame.add(label);
        adminFrame.add(teachersButton);
        adminFrame.add(coursesButton);
        adminFrame.add(studentsButton);
        adminFrame.add(newsButton);
        adminFrame.add(backButton);

        teachersButton.addActionListener(e -> adminTeacherFrame());
        coursesButton.addActionListener(e -> adminCourseFrame());
        studentsButton.addActionListener(e -> adminStudentFrame());
        newsButton.addActionListener(e -> adminNewsFrame());
        backButton.addActionListener(e -> adminFrame.dispose());

        adminFrame.setVisible(true);
    }

    //Admin: Access to teachers
    private static void adminTeacherFrame(){
        JFrame adminTeacherFrame = new JFrame("Access to Teachers");
        adminTeacherFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminTeacherFrame.setSize(800, 600);
        adminTeacherFrame.setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Access to TEACHERS:", SwingConstants.CENTER);
        JButton addingButton = new JButton("Add Teacher");
        JButton removingButton = new JButton("Remove Teacher");
        JButton backButton = new JButton("Back");

        adminTeacherFrame.add(label);
        adminTeacherFrame.add(addingButton);
        adminTeacherFrame.add(removingButton);
        adminTeacherFrame.add(backButton);

        addingButton.addActionListener(e -> addTeacherFrame());
        removingButton.addActionListener(e -> removeTeacherFrame());
        backButton.addActionListener(e -> adminTeacherFrame.dispose());

        adminTeacherFrame.setVisible(true);
    }

    //Admin: Access to courses
    private static void adminCourseFrame(){
        JFrame adminCourseFrame = new JFrame("Access to Courses");
        adminCourseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminCourseFrame.setSize(800, 600);
        adminCourseFrame.setLayout(new GridLayout(5, 1));

        JLabel label = new JLabel("Access to COURSES:", SwingConstants.CENTER);
        JButton addingButton = new JButton("Add Course");
        JButton removingButton = new JButton("Remove Course");
        JButton updatingButton = new JButton("Update Course");
        JButton backButton = new JButton("Back");

        adminCourseFrame.add(label);
        adminCourseFrame.add(addingButton);
        adminCourseFrame.add(removingButton);
        adminCourseFrame.add(updatingButton);
        adminCourseFrame.add(backButton);

        addingButton.addActionListener(e -> adminAddCourseFrame());
        removingButton.addActionListener(e -> adminRemoveCourseFrame());
        backButton.addActionListener(e -> adminCourseFrame.dispose());

        adminCourseFrame.setVisible(true);
    }

    //Admin: Access to students
    private static void adminStudentFrame(){
        JFrame adminStudentFrame = new JFrame("Access to Students");
        adminStudentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminStudentFrame.setSize(800, 600);
        adminStudentFrame.setLayout(new GridLayout(5, 1));

        JLabel label = new JLabel("Access to STUDENTS:", SwingConstants.CENTER);
        JButton addingButton = new JButton("Add a Student to a Course");
        JButton removingButton = new JButton("Remove a Student from a Course");
        JButton scoresButton = new JButton("Update scores of a Student");
        JButton backButton = new JButton("Back");

        adminStudentFrame.add(label);
        adminStudentFrame.add(addingButton);
        adminStudentFrame.add(removingButton);
        adminStudentFrame.add(scoresButton);
        adminStudentFrame.add(backButton);

        backButton.addActionListener(e -> adminStudentFrame.dispose());

        adminStudentFrame.setVisible(true);
    }

    //Admin: Access to news
    private static void adminNewsFrame(){
        JFrame adminNewsFrame = new JFrame("Access to News");
        adminNewsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminNewsFrame.setSize(800, 600);
        adminNewsFrame.setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Access to NEWS:", SwingConstants.CENTER);
        JButton addingButton = new JButton("Add News");
        JButton removingButton = new JButton("Remove News");
        JButton backButton = new JButton("Back");

        adminNewsFrame.add(label);
        adminNewsFrame.add(addingButton);
        adminNewsFrame.add(removingButton);
        adminNewsFrame.add(backButton);

        backButton.addActionListener(e -> adminNewsFrame.dispose());

        adminNewsFrame.setVisible(true);
    }

    //Admin: Access to teacher: Adding
    private static void addTeacherFrame() {
        JFrame addTeacherFrame = new JFrame("Add Teacher");
        addTeacherFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addTeacherFrame.setSize(800, 600);
        addTeacherFrame.setLayout(new GridLayout(5, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();
        JLabel teacherIdLabel = new JLabel("Teacher ID:");
        JTextField teacherIdField = new JTextField();
        JButton OKButton = new JButton("OK");
        JButton backButton = new JButton("Back");

        addTeacherFrame.add(firstNameLabel);
        addTeacherFrame.add(firstNameField);
        addTeacherFrame.add(lastNameLabel);
        addTeacherFrame.add(lastNameField);
        addTeacherFrame.add(teacherIdLabel);
        addTeacherFrame.add(teacherIdField);
        addTeacherFrame.add(OKButton);
        addTeacherFrame.add(backButton);

        OKButton.addActionListener(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String teacherId = teacherIdField.getText();
            String newTeacher = firstName + " " + lastName + "," + teacherId + "\n";
            try {
                FileWriter fileWriter = new FileWriter(Database.getInstance().teacherFileName, true);
                fileWriter.write(newTeacher);
                fileWriter.close();
                JOptionPane.showMessageDialog(addTeacherFrame, "Teacher: " + firstName + " " + lastName + ", added successfully!");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        backButton.addActionListener(e -> addTeacherFrame.dispose());
        addTeacherFrame.setVisible(true);
    }

    //Admin: Access to teacher: Removing
    private static void removeTeacherFrame() {
        JFrame removeTeacherFrame = new JFrame("Remove Teacher");
        removeTeacherFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeTeacherFrame.setSize(800, 600);
        removeTeacherFrame.setLayout(new GridLayout(5, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();
        JLabel teacherIdLabel = new JLabel("Teacher ID:");
        JTextField teacherIdField = new JTextField();
        JButton OKButton = new JButton("OK");
        JButton backButton = new JButton("Back");

        removeTeacherFrame.add(firstNameLabel);
        removeTeacherFrame.add(firstNameField);
        removeTeacherFrame.add(lastNameLabel);
        removeTeacherFrame.add(lastNameField);
        removeTeacherFrame.add(teacherIdLabel);
        removeTeacherFrame.add(teacherIdField);
        removeTeacherFrame.add(OKButton);
        removeTeacherFrame.add(backButton);

        OKButton.addActionListener(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String teacherId = teacherIdField.getText();
            String teacherName = firstName + " " + lastName;
            String tempFileName = Database.getInstance().tempFileName;

            try (BufferedReader reader = new BufferedReader(new FileReader(Database.getInstance().teacherFileName))) {
                boolean removed = false;
                String line;
                while ((line = reader.readLine()) != null) {
                    try (FileWriter fileWriter = new FileWriter(tempFileName, true)) {
                        String[] info = line.split(",");
                        if (!info[0].equals(teacherName) && !info[1].equals(teacherId)) {
                            fileWriter.write(line + "\n");
                        } else {
                            removed = true;
                        }
                    }
                }
                PrintWriter writer = new PrintWriter(Database.getInstance().teacherFileName);
                writer.println("");
                writer.close();
                try (BufferedReader reader1 = new BufferedReader(new FileReader(tempFileName))) {
                    while ((line = reader1.readLine()) != null) {
                        try (FileWriter fileWriter = new FileWriter(Database.getInstance().teacherFileName, true)) {
                            fileWriter.write(line + "\n");
                        }
                    }
                }
                if (removed) {
                    JOptionPane.showMessageDialog(removeTeacherFrame, "Teacher " + firstName + " " + lastName + " removed successfully!");
                } else {
                    JOptionPane.showMessageDialog(removeTeacherFrame, "Teacher not found!");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        backButton.addActionListener(e -> removeTeacherFrame.dispose());

        removeTeacherFrame.setVisible(true);
    }

    //Admin: Access to course: Adding
    private static void adminAddCourseFrame() {
        JFrame addCourse = new JFrame("Add Course");
        addCourse.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addCourse.setSize(800, 600);
        addCourse.setLayout(new GridLayout(7, 2));

        JLabel courseNameLabel = new JLabel("Course Name:");
        JTextField courseNameField = new JTextField();
        JLabel courseIDLabel = new JLabel("Course ID:");
        JTextField courseIDField = new JTextField();
        JLabel firstNameLabel = new JLabel("First Name of Teacher:");
        JTextField firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name of Teacher:");
        JTextField lastNameField = new JTextField();
        JLabel numUnitsLabel = new JLabel("Number of Units:");
        JTextField numUnitsField = new JTextField();
        JLabel examLabel = new JLabel("Date of Exam:");
        JTextField examField = new JTextField();
        JButton OKButton = new JButton("OK");
        JButton backButton = new JButton("Back");

        addCourse.add(courseNameLabel);
        addCourse.add(courseNameField);
        addCourse.add(courseIDLabel);
        addCourse.add(courseIDField);
        addCourse.add(firstNameLabel);
        addCourse.add(firstNameField);
        addCourse.add(lastNameLabel);
        addCourse.add(lastNameField);
        addCourse.add(numUnitsLabel);
        addCourse.add(numUnitsField);
        addCourse.add(examLabel);
        addCourse.add(examField);
        addCourse.add(OKButton);
        addCourse.add(backButton);

        OKButton.addActionListener(e -> {
            String courseName = courseNameField.getText();
            String courseID = courseIDField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String numUnits = numUnitsField.getText();
            String examDate = examField.getText();

            Course newCourse = new Course(courseName, courseID, Integer.parseInt(numUnits), examDate, new Teacher(firstName, lastName));
            try {
                Database.getInstance().addCourse(newCourse);
                JOptionPane.showMessageDialog(addCourse, Database.getInstance().addCourse(newCourse));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> addCourse.dispose());
        addCourse.setVisible(true);
    }

    //Admin: Access to course: Adding
    private static void adminRemoveCourseFrame() {
        JFrame removeCourse = new JFrame("Remove Course");
        removeCourse.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeCourse.setSize(800, 600);
        removeCourse.setLayout(new GridLayout(7, 2));

        JLabel courseNameLabel = new JLabel("Course Name:");
        JTextField courseNameField = new JTextField();
        JLabel courseIDLabel = new JLabel("Course ID:");
        JTextField courseIDField = new JTextField();
        JLabel firstNameLabel = new JLabel("First Name of Teacher:");
        JTextField firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name of Teacher:");
        JTextField lastNameField = new JTextField();
        JLabel numUnitsLabel = new JLabel("Number of Units:");
        JTextField numUnitsField = new JTextField();
        JLabel examLabel = new JLabel("Date of Exam:");
        JTextField examField = new JTextField();
        JButton OKButton = new JButton("OK");
        JButton backButton = new JButton("Back");

        removeCourse.add(courseNameLabel);
        removeCourse.add(courseNameField);
        removeCourse.add(courseIDLabel);
        removeCourse.add(courseIDField);
        removeCourse.add(firstNameLabel);
        removeCourse.add(firstNameField);
        removeCourse.add(lastNameLabel);
        removeCourse.add(lastNameField);
        removeCourse.add(numUnitsLabel);
        removeCourse.add(numUnitsField);
        removeCourse.add(examLabel);
        removeCourse.add(examField);
        removeCourse.add(OKButton);
        removeCourse.add(backButton);

        OKButton.addActionListener(e -> {
            String courseName = courseNameField.getText();
            String courseID = courseIDField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String numUnits = numUnitsField.getText();
            String examDate = examField.getText();

            Course course = new Course(courseName, courseID, Integer.parseInt(numUnits), examDate, new Teacher(firstName, lastName));
            try {
                Database.getInstance().removeCourse(course);
                JOptionPane.showMessageDialog(removeCourse, Database.getInstance().removeCourse(course));

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> removeCourse.dispose());
        removeCourse.setVisible(true);
    }







    //TEACHER
    //Teacher's login (with teacher id)
    private static void teacherLogin(JFrame parentFrame) {
        JDialog loginDialog = new JDialog(parentFrame, "Teacher Login", true);
        loginDialog.setLayout(new GridLayout(3, 2));
        loginDialog.setSize(500, 250);

        JLabel idLabel = new JLabel("Enter your teacher ID: ");
        JTextField idField = new JTextField();
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");

        loginDialog.add(idLabel);
        loginDialog.add(idField);
        loginDialog.add(loginButton);
        loginDialog.add(cancelButton);

        loginButton.addActionListener(e -> {
            String teacherID = idField.getText();
            if (isTeacher(teacherID)) {
                try {
                    JOptionPane.showMessageDialog(loginDialog, "Welcome, " + Database.getInstance().teacherName(teacherID) + "!");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                loginDialog.dispose();
                createTeacherFrame();
            } else {
                JOptionPane.showMessageDialog(loginDialog, "Incorrect teacher ID. Please try again!");
            }
        });

        cancelButton.addActionListener(e -> loginDialog.dispose()); // Close the login dialog

        loginDialog.setVisible(true);
    }

    // Method to validate the teacher ID
    private static boolean isTeacher(String teacherID) {
        boolean login = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(Database.getInstance().teacherFileName))) {
            String line;
            String[] info;
            while ((line = reader.readLine()) != null) {
                info = line.split(",");
                if (teacherID.equals(info[1])) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return login;
    }

    //Page for Teacher access
    private static void createTeacherFrame() {
        JFrame teacherFrame = new JFrame("Teacher Access");
        teacherFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        teacherFrame.setSize(800, 600);
        teacherFrame.setLayout(new GridLayout(5, 1));

        JLabel label = new JLabel("Teacher Access:", SwingConstants.CENTER);
        JButton coursesButton = new JButton("Courses");
        JButton studentsButton = new JButton("Students");
        JButton assignmentsButton = new JButton("Assignments");
        JButton backButton = new JButton("Back");

        teacherFrame.add(label);
        teacherFrame.add(coursesButton);
        teacherFrame.add(studentsButton);
        teacherFrame.add(assignmentsButton);
        teacherFrame.add(backButton);

        coursesButton.addActionListener(e -> teacherCourseFrame());
        studentsButton.addActionListener(e -> teacherStudentFrame());
        assignmentsButton.addActionListener(e -> teacherAssignmentFrame());
        backButton.addActionListener(e -> teacherFrame.dispose());

        teacherFrame.setVisible(true);
    }

    //Teacher: Access to courses
    private static void teacherCourseFrame(){
        JFrame teacherCourseFrame = new JFrame("Access to Courses");
        teacherCourseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        teacherCourseFrame.setSize(800, 600);
        teacherCourseFrame.setLayout(new GridLayout(5, 1));

        JLabel label = new JLabel("Access to COURSES:", SwingConstants.CENTER);
        JButton addingButton = new JButton("Add Course");
        JButton removingButton = new JButton("Remove Course");
        JButton updatingButton = new JButton("Update Course");
        JButton backButton = new JButton("Back");

        teacherCourseFrame.add(label);
        teacherCourseFrame.add(addingButton);
        teacherCourseFrame.add(removingButton);
        teacherCourseFrame.add(updatingButton);
        teacherCourseFrame.add(backButton);

        backButton.addActionListener(e -> teacherCourseFrame.dispose());

        teacherCourseFrame.setVisible(true);
    }

    //Teacher: Access to students
    private static void teacherAssignmentFrame(){
        JFrame teacherStudentFrame = new JFrame("Access to Assignments");
        teacherStudentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        teacherStudentFrame.setSize(800, 600);
        teacherStudentFrame.setLayout(new GridLayout(5, 1));

        JLabel label = new JLabel("Access to ASSIGNMENTS:", SwingConstants.CENTER);
        JButton addingButton = new JButton("Add an Assignment to a course");
        JButton removingButton = new JButton("Remove an Assignment from a Course");
        JButton updatingButton = new JButton("Update Assignments of a Course");
        JButton backButton = new JButton("Back");

        teacherStudentFrame.add(label);
        teacherStudentFrame.add(addingButton);
        teacherStudentFrame.add(removingButton);
        teacherStudentFrame.add(updatingButton);
        teacherStudentFrame.add(backButton);

        backButton.addActionListener(e -> teacherStudentFrame.dispose());

        teacherStudentFrame.setVisible(true);
    }

    //Teacher: Access to assignments
    private static void teacherStudentFrame(){
        JFrame teacherStudentFrame = new JFrame("Access to Students");
        teacherStudentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        teacherStudentFrame.setSize(800, 600);
        teacherStudentFrame.setLayout(new GridLayout(5, 1));

        JLabel label = new JLabel("Access to STUDENTS:", SwingConstants.CENTER);
        JButton addingButton = new JButton("Add a Student to a Course");
        JButton removingButton = new JButton("Remove a Student from a Course");
        JButton scoresButton = new JButton("Update scores of a Student");
        JButton backButton = new JButton("Back");

        teacherStudentFrame.add(label);
        teacherStudentFrame.add(addingButton);
        teacherStudentFrame.add(removingButton);
        teacherStudentFrame.add(scoresButton);
        teacherStudentFrame.add(backButton);

        backButton.addActionListener(e -> teacherStudentFrame.dispose());

        teacherStudentFrame.setVisible(true);
    }
}