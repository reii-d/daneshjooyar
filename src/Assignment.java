
public class Assignment {
    private String assignmentName;
    private int dayCounter;
    private boolean isProjectActive;


    //Constructor
    public Assignment(String assignmentName, int dayCounter){
        this.assignmentName = assignmentName;
        this.dayCounter = dayCounter;
        this.isProjectActive = true;
    }


    //Getter
    public String getAssignmentName() {
        return assignmentName;
    }
    public boolean getIsProjectActive() {
        return isProjectActive;
    }
    public int getDayCounter() {
        return dayCounter;
    }


    //Setter
    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }
    public void setIsProjectActive(boolean projectActive) {
        isProjectActive = projectActive;
    }
    public void setDayCounter(int dayCounter) {
        this.dayCounter = dayCounter;
    }


    //Methods
    public void changeDeadline(Assignment assignment, int newDeadline){
        assignment.setDayCounter(newDeadline);
    }
    public void archiveAssignment(Assignment assignment){
        assignment.setIsProjectActive(false);
    }
    public void unArchiveAssignment(Assignment assignment){
        assignment.setIsProjectActive(true);
    }
}
