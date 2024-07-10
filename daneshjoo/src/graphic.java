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
        adminCourseFrame.setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Access to COURSES:", SwingConstants.CENTER);
        JButton addingButton = new JButton("Add Course");
        JButton removingButton = new JButton("Remove Course");
        JButton backButton = new JButton("Back");

        adminCourseFrame.add(label);
        adminCourseFrame.add(addingButton);
        adminCourseFrame.add(removingButton);
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

        addingButton.addActionListener(e -> adminAddStudentToCourseFrame());
        removingButton.addActionListener(e -> adminRemoveStudentFrCourseFrame());
        scoresButton.addActionListener(e -> adminScoresFrame());
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

        addingButton.addActionListener(e -> addNewsFrame());
        removingButton.addActionListener(e -> removeNewsFrame());
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
                JOptionPane.showMessageDialog(addCourse, "in progress...");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> addCourse.dispose());
        addCourse.setVisible(true);
    }

    //Admin: Access to course: Removing
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
                JOptionPane.showMessageDialog(removeCourse, "in progress...");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> removeCourse.dispose());
        removeCourse.setVisible(true);
    }

    //Admin: Access to news: Adding
    private static void addNewsFrame() {
        JFrame addNews = new JFrame("Add News");
        addNews.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addNews.setSize(800, 600);
        addNews.setLayout(new GridLayout(3, 2));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();
        JLabel textLabel = new JLabel("Text of news:");
        JTextField textField = new JTextField();
        JButton OKButton = new JButton("OK");
        JButton backButton = new JButton("Back");

        addNews.add(titleLabel);
        addNews.add(titleField);
        addNews.add(textLabel);
        addNews.add(textField);
        addNews.add(OKButton);
        addNews.add(backButton);

        OKButton.addActionListener(e -> {
            String title = titleField.getText();
            String text = textField.getText();
            try {
                Database.getInstance().addNews(title, text);
                JOptionPane.showMessageDialog(addNews, Database.getInstance().addNews(title, text));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> addNews.dispose());
        addNews.setVisible(true);
    }

    //Admin: Access to news: Removing
    private static void removeNewsFrame() {
        JFrame removeNews = new JFrame("Remove News");
        removeNews.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeNews.setSize(800, 600);
        removeNews.setLayout(new GridLayout(2, 2));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();
        JButton OKButton = new JButton("OK");
        JButton backButton = new JButton("Back");

        removeNews.add(titleLabel);
        removeNews.add(titleField);
        removeNews.add(OKButton);
        removeNews.add(backButton);

        OKButton.addActionListener(e -> {
            String title = titleField.getText();
            try {
                Database.getInstance().removeNews(title);
                JOptionPane.showMessageDialog(removeNews, Database.getInstance().removeNews(title));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> removeNews.dispose());
        removeNews.setVisible(true);
    }

    //Admin: Access to students: Adding
    private static void adminAddStudentToCourseFrame() {
        JFrame addStudent = new JFrame("Add a Student to a Course");
        addStudent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addStudent.setSize(800, 600);
        addStudent.setLayout(new GridLayout(3, 2));

        JLabel courseLabel = new JLabel("Name of Course:");
        JTextField courseField = new JTextField();
        JLabel studentLabel = new JLabel("Name of Student:");
        JTextField studentField = new JTextField();
        JButton OKButton = new JButton("OK");
        JButton backButton = new JButton("Back");

        addStudent.add(courseLabel);
        addStudent.add(courseField);
        addStudent.add(studentLabel);
        addStudent.add(studentField);
        addStudent.add(OKButton);
        addStudent.add(backButton);

        OKButton.addActionListener(e -> {
            String course = courseField.getText();
            String student = studentField.getText();
            try {
                Database.getInstance().addCourseToStudent(student, course);
                JOptionPane.showMessageDialog(addStudent, "OK!");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> addStudent.dispose());
        addStudent.setVisible(true);
    }

    //Admin: Access to students: Removing
    private static void adminRemoveStudentFrCourseFrame() {
        JFrame removeStudent = new JFrame("Remove a Student from a Course");
        removeStudent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeStudent.setSize(800, 600);
        removeStudent.setLayout(new GridLayout(3, 2));

        JLabel courseLabel = new JLabel("Name of Course:");
        JTextField courseField = new JTextField();
        JLabel studentLabel = new JLabel("Name of Student:");
        JTextField studentField = new JTextField();
        JButton OKButton = new JButton("OK");
        JButton backButton = new JButton("Back");

        removeStudent.add(courseLabel);
        removeStudent.add(courseField);
        removeStudent.add(studentLabel);
        removeStudent.add(studentField);
        removeStudent.add(OKButton);
        removeStudent.add(backButton);

        OKButton.addActionListener(e -> {
            String course = courseField.getText();
            String student = studentField.getText();
            try {
                Database.getInstance().removeCourseFromStudent(course, student);
                JOptionPane.showMessageDialog(removeStudent, "in progress...");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> removeStudent.dispose());
        removeStudent.setVisible(true);
    }

    //Admin: Access to students: Scores
    private static void adminScoresFrame() {
        JFrame updateScores = new JFrame("Updating Scores");
        updateScores.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateScores.setSize(800, 600);
        updateScores.setLayout(new GridLayout(4, 2));

        JLabel courseLabel = new JLabel("Name of Course:");
        JTextField courseField = new JTextField();
        JLabel studentLabel = new JLabel("Name of Student:");
        JTextField studentField = new JTextField();
        JLabel scoreLabel = new JLabel("Enter the Score: ");
        JTextField scoreField = new JTextField();
        JButton OKButton = new JButton("OK");
        JButton backButton = new JButton("Back");

        updateScores.add(courseLabel);
        updateScores.add(courseField);
        updateScores.add(studentLabel);
        updateScores.add(studentField);
        updateScores.add(scoreLabel);
        updateScores.add(scoreField);
        updateScores.add(OKButton);
        updateScores.add(backButton);

        OKButton.addActionListener(e -> {
            String course = courseField.getText();
            String student = studentField.getText();
            String score = scoreField.getText();
            try {
                Database.getInstance().studentScore(student, course, score);
                JOptionPane.showMessageDialog(updateScores, "in progrss...");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> updateScores.dispose());
        updateScores.setVisible(true);
    }



    //TEACHER
    //Teacher's login (with teacher id)
    private static void teacherLogin(JFrame parentFrame) {
        JDialog loginDialog = new JDialog(parentFrame, "Teacher Login", true);
        loginDialog.setLayout(new GridLayout(2, 2));
        loginDialog.setSize(600, 300);

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
        teacherCourseFrame.setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Access to COURSES:", SwingConstants.CENTER);
        JButton addingButton = new JButton("Add Course");
        JButton removingButton = new JButton("Remove Course");
        JButton backButton = new JButton("Back");

        teacherCourseFrame.add(label);
        teacherCourseFrame.add(addingButton);
        teacherCourseFrame.add(removingButton);
        teacherCourseFrame.add(backButton);

        addingButton.addActionListener(e -> teacherAddCourseFrame());
        removingButton.addActionListener(e -> teacherRemoveCourseFrame());
        backButton.addActionListener(e -> teacherCourseFrame.dispose());

        teacherCourseFrame.setVisible(true);
    }

    //Teacher: Access to students
    private static void teacherAssignmentFrame(){
        JFrame teacherStudentFrame = new JFrame("Access to Assignments");
        teacherStudentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        teacherStudentFrame.setSize(800, 600);
        teacherStudentFrame.setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Access to ASSIGNMENTS:", SwingConstants.CENTER);
        JButton addingButton = new JButton("Add an Assignment to a course");
        JButton removingButton = new JButton("Remove an Assignment from a Course");
        JButton backButton = new JButton("Back");

        teacherStudentFrame.add(label);
        teacherStudentFrame.add(addingButton);
        teacherStudentFrame.add(removingButton);
        teacherStudentFrame.add(backButton);

        addingButton.addActionListener(e -> addAssignmentFrame());
        removingButton.addActionListener(e -> removeAssignmentFrame());
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

        addingButton.addActionListener(e -> teacherAddStudentFrame());
        removingButton.addActionListener(e -> teacherRemoveStudentFrame());
        scoresButton.addActionListener(e -> teacherScoresFrame());
        backButton.addActionListener(e -> teacherStudentFrame.dispose());

        teacherStudentFrame.setVisible(true);
    }

    //Teacher: Access to courses: Adding
    private static void teacherAddCourseFrame() {
        JFrame addCourse = new JFrame("Add Course");
        addCourse.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addCourse.setSize(800, 600);
        addCourse.setLayout(new GridLayout(6, 2));

        JLabel courseNameLabel = new JLabel("Course Name:");
        JTextField courseNameField = new JTextField();
        JLabel courseIDLabel = new JLabel("Course ID:");
        JTextField courseIDField = new JTextField();
        JLabel teacherIDLabel = new JLabel("Enter your ID:");
        JTextField teacherIDField = new JTextField();
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
        addCourse.add(teacherIDLabel);
        addCourse.add(teacherIDField);
        addCourse.add(numUnitsLabel);
        addCourse.add(numUnitsField);
        addCourse.add(examLabel);
        addCourse.add(examField);
        addCourse.add(OKButton);
        addCourse.add(backButton);

        OKButton.addActionListener(e -> {
            String courseName = courseNameField.getText();
            String courseID = courseIDField.getText();
            String teacherID = teacherIDField.getText();
            String numUnits = numUnitsField.getText();
            String examDate = examField.getText();

            try {
                String[] teacher = Database.getInstance().teacherName(teacherID).split(" ");
                Course newCourse = new Course(courseName, courseID, Integer.parseInt(numUnits), examDate, new Teacher(teacher[0], teacher[1]));
                Database.getInstance().addCourse(newCourse);
                JOptionPane.showMessageDialog(addCourse, "in progress...");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> addCourse.dispose());
        addCourse.setVisible(true);
    }

    //Teacher: Access to courses: Removing
    private static void teacherRemoveCourseFrame() {
        JFrame removeCourse = new JFrame("Remove Course");
        removeCourse.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeCourse.setSize(800, 600);
        removeCourse.setLayout(new GridLayout(6, 2));

        JLabel courseNameLabel = new JLabel("Course Name:");
        JTextField courseNameField = new JTextField();
        JLabel courseIDLabel = new JLabel("Enter Course ID:");
        JTextField courseIDField = new JTextField();
        JLabel teacherIDLabel = new JLabel("Enter your ID:");
        JTextField teacherIDField = new JTextField();
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
        removeCourse.add(teacherIDLabel);
        removeCourse.add(teacherIDField);
        removeCourse.add(numUnitsLabel);
        removeCourse.add(numUnitsField);
        removeCourse.add(examLabel);
        removeCourse.add(examField);
        removeCourse.add(OKButton);
        removeCourse.add(backButton);

        OKButton.addActionListener(e -> {
            String courseName = courseNameField.getText();
            String courseID = courseIDField.getText();
            String teacherID = teacherIDField.getText();
            String numUnits = numUnitsField.getText();
            String examDate = examField.getText();

            try {
                String[] teacher = Database.getInstance().teacherName(teacherID).split(" ");
                Course course = new Course(courseName, courseID, Integer.parseInt(numUnits), examDate, new Teacher(teacher[0], teacher[1]));
                if (Database.getInstance().isTeacher(teacherID, courseName)) {
                    Database.getInstance().removeCourse(course);
                    JOptionPane.showMessageDialog(removeCourse, "in progress...");
                } else {
                    JOptionPane.showMessageDialog(removeCourse, "Sorry, You have not access to this course!");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> removeCourse.dispose());
        removeCourse.setVisible(true);
    }

    //Teacher: Access to students: Adding
    private static void teacherAddStudentFrame() {
        JFrame addStudent = new JFrame("Add a Student to your Course");
        addStudent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addStudent.setSize(800, 600);
        addStudent.setLayout(new GridLayout(4, 2));

        JLabel courseLabel = new JLabel("Name of Course:");
        JTextField courseField = new JTextField();
        JLabel studentLabel = new JLabel("Name of Student:");
        JTextField studentField = new JTextField();
        JLabel teacherIDLabel = new JLabel("Enter your ID:");
        JTextField teacherIDField = new JTextField();
        JButton OKButton = new JButton("OK");
        JButton backButton = new JButton("Back");

        addStudent.add(courseLabel);
        addStudent.add(courseField);
        addStudent.add(studentLabel);
        addStudent.add(studentField);
        addStudent.add(teacherIDLabel);
        addStudent.add(teacherIDField);
        addStudent.add(OKButton);
        addStudent.add(backButton);

        OKButton.addActionListener(e -> {
            String course = courseField.getText();
            String student = studentField.getText();
            String teacher = teacherIDField.getText();
            try {
                if (Database.getInstance().isTeacher(teacher, course)) {
                    Database.getInstance().addCourseToStudent(student, course);
                    JOptionPane.showMessageDialog(addStudent, "OK!");
                }
                else
                    JOptionPane.showMessageDialog(addStudent, "Sorry, You have not access to this course!");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> addStudent.dispose());
        addStudent.setVisible(true);
    }

    //Teacher: Access to students: Removing
    private static void teacherRemoveStudentFrame() {
        JFrame removeStudent = new JFrame("Remove a Student from your Course");
        removeStudent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeStudent.setSize(800, 600);
        removeStudent.setLayout(new GridLayout(4, 2));

        JLabel courseLabel = new JLabel("Name of Course:");
        JTextField courseField = new JTextField();
        JLabel studentLabel = new JLabel("Name of Student:");
        JTextField studentField = new JTextField();
        JLabel teacherIDLabel = new JLabel("Enter your ID:");
        JTextField teacherIDField = new JTextField();
        JButton OKButton = new JButton("OK");
        JButton backButton = new JButton("Back");

        removeStudent.add(courseLabel);
        removeStudent.add(courseField);
        removeStudent.add(studentLabel);
        removeStudent.add(studentField);
        removeStudent.add(teacherIDLabel);
        removeStudent.add(teacherIDField);
        removeStudent.add(OKButton);
        removeStudent.add(backButton);

        OKButton.addActionListener(e -> {
            String course = courseField.getText();
            String student = studentField.getText();
            String teacher = teacherIDField.getText();
            try {
                if (Database.getInstance().isTeacher(teacher, course)) {
                    Database.getInstance().removeCourseFromStudent(course, student);
                    JOptionPane.showMessageDialog(removeStudent, "in progress...");
                }
                else
                    JOptionPane.showMessageDialog(removeStudent, "Sorry, You have not access to this course!");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> removeStudent.dispose());
        removeStudent.setVisible(true);
    }

    //Teacher: Access to students: Scores
    private static void teacherScoresFrame() {
        JFrame updateScores = new JFrame("Updating Scores");
        updateScores.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateScores.setSize(800, 600);
        updateScores.setLayout(new GridLayout(5, 2));

        JLabel courseLabel = new JLabel("Name of Course:");
        JTextField courseField = new JTextField();
        JLabel studentLabel = new JLabel("Name of Student:");
        JTextField studentField = new JTextField();
        JLabel teacherIDLabel = new JLabel("Enter your ID:");
        JTextField teacherIDField = new JTextField();
        JLabel scoreLabel = new JLabel("Enter the Score: ");
        JTextField scoreField = new JTextField();
        JButton OKButton = new JButton("OK");
        JButton backButton = new JButton("Back");

        updateScores.add(courseLabel);
        updateScores.add(courseField);
        updateScores.add(studentLabel);
        updateScores.add(studentField);
        updateScores.add(teacherIDLabel);
        updateScores.add(teacherIDField);
        updateScores.add(scoreLabel);
        updateScores.add(scoreField);
        updateScores.add(OKButton);
        updateScores.add(backButton);

        OKButton.addActionListener(e -> {
            String course = courseField.getText();
            String student = studentField.getText();
            String teacher = teacherIDField.getText();
            String score = scoreField.getText();
            try {
                if (Database.getInstance().isTeacher(teacher, course)) {
                    Database.getInstance().studentScore(student, course, score);
                    JOptionPane.showMessageDialog(updateScores, "in progress...");
                }
                else {
                    JOptionPane.showMessageDialog(updateScores, "Sorry, You have not access to this course!");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> updateScores.dispose());
        updateScores.setVisible(true);
    }

    //Teacher: Access to assignments: Adding
    private static void addAssignmentFrame() {
        JFrame addAssignment = new JFrame("Add an Assignment to your Course");
        addAssignment.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addAssignment.setSize(800, 600);
        addAssignment.setLayout(new GridLayout(5, 2));

        JLabel courseLabel = new JLabel("Name of Course:");
        JTextField courseField = new JTextField();
        JLabel assignmentLabel = new JLabel("Name of Assignment:");
        JTextField assignmentField = new JTextField();
        JLabel deadlineLabel = new JLabel("Deadline (in days):");
        JTextField deadlineField = new JTextField();
        JLabel teacherIDLabel = new JLabel("Enter your ID:");
        JTextField teacherIDField = new JTextField();
        JButton OKButton = new JButton("OK");
        JButton backButton = new JButton("Back");

        addAssignment.add(courseLabel);
        addAssignment.add(courseField);
        addAssignment.add(assignmentLabel);
        addAssignment.add(assignmentField);
        addAssignment.add(deadlineLabel);
        addAssignment.add(deadlineField);
        addAssignment.add(teacherIDLabel);
        addAssignment.add(teacherIDField);
        addAssignment.add(OKButton);
        addAssignment.add(backButton);

        OKButton.addActionListener(e -> {
            String course = courseField.getText();
            String assignment = assignmentField.getText();
            String deadline = deadlineField.getText();
            String teacher = teacherIDField.getText();
            try {
                if (Database.getInstance().isTeacher(teacher, course)) {
                    Database.getInstance().addAssignment(new Assignment(assignment, Integer.parseInt(deadline)), course);
                    JOptionPane.showMessageDialog(addAssignment, "in progress...");
                }
                else
                    JOptionPane.showMessageDialog(addAssignment, "Sorry, You have not access to this course!");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> addAssignment.dispose());
        addAssignment.setVisible(true);
    }

    //Teacher: Access to assignments: Removing
    private static void removeAssignmentFrame() {
        JFrame removingAssignment = new JFrame("Remove an Assignment from your Course");
        removingAssignment.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removingAssignment.setSize(800, 600);
        removingAssignment.setLayout(new GridLayout(4, 2));

        JLabel courseLabel = new JLabel("Name of Course:");
        JTextField courseField = new JTextField();
        JLabel assignmentLabel = new JLabel("Name of Assignment:");
        JTextField assignmentField = new JTextField();
        JLabel teacherIDLabel = new JLabel("Enter your ID:");
        JTextField teacherIDField = new JTextField();
        JButton OKButton = new JButton("OK");
        JButton backButton = new JButton("Back");

        removingAssignment.add(courseLabel);
        removingAssignment.add(courseField);
        removingAssignment.add(assignmentLabel);
        removingAssignment.add(assignmentField);
        removingAssignment.add(teacherIDLabel);
        removingAssignment.add(teacherIDField);
        removingAssignment.add(OKButton);
        removingAssignment.add(backButton);

        OKButton.addActionListener(e -> {
            String course = courseField.getText();
            String assignment = assignmentField.getText();
            String teacher = teacherIDField.getText();
            try {
                if (Database.getInstance().isTeacher(teacher, course)) {
                    Database.getInstance().removeAssignment(assignment, course);
                    JOptionPane.showMessageDialog(removingAssignment, "in progress...");
                }
                else
                    JOptionPane.showMessageDialog(removingAssignment, "Sorry, You have not access to this course!");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> removingAssignment.dispose());
        removingAssignment.setVisible(true);
    }
}