import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class camplifeclasses {

    public static void main(String[] args) {

        // Create Courses
        Course oop = new Course("OOP", "Dr. Lee", 6);
        Course discrete = new Course("Discrete Math", "Dr. Smith", 5);
        Course english = new Course("English", "Prof. Brown", 3);

        List<Course> courses = new ArrayList<>();
        courses.add(oop);
        courses.add(discrete);
        courses.add(english);

        // Create Assignments
        List<AssignmentTask> assignments = new ArrayList<>();

        assignments.add(new AssignmentTask("Lab 2", oop, 3, 1));
        assignments.add(new AssignmentTask("Project Proposal", oop, 5, 5));
        assignments.add(new AssignmentTask("Homework 4", discrete, 4, 0));
        assignments.add(new AssignmentTask("Essay Draft", english, 2, 3));
        assignments.add(new AssignmentTask("Final Review", discrete, 6, 2));

        // Create Study Sessions
        List<StudySession> sessions = new ArrayList<>();

        sessions.add(new StudySession(oop, 90));
        sessions.add(new StudySession(discrete, 120));
        sessions.add(new StudySession(oop, 60));
        sessions.add(new StudySession(english, 45));
        sessions.add(new StudySession(discrete, 30));

        // Print Courses
        System.out.println("=== COURSES ===");
        for (Course c : courses) {
            System.out.println(c);
        }

        // Print Assignments + Urgent
        System.out.println("\n=== ASSIGNMENTS ===");
        int totalRemainingHours = 0;

        for (AssignmentTask a : assignments) {
            System.out.println(a);

            if (a.isUrgent()) {
                System.out.println("  ⚠ URGENT!");
            }

            if (!a.isCompleted()) {
                totalRemainingHours += a.getEstimatedHours();
            }
        }

        System.out.println("\nTotal Estimated Hours Remaining: " + totalRemainingHours);

        // Total Study Time per Course
        System.out.println("\n=== STUDY TIME PER COURSE ===");

        Map<Course, Double> studyHours = new HashMap<>();

        for (StudySession s : sessions) {
            studyHours.put(
                    s.getCourse(),
                    studyHours.getOrDefault(s.getCourse(), 0.0) + s.hours()
            );
        }

        for (Course c : studyHours.keySet()) {
            System.out.println(c.getName() + ": " + studyHours.get(c) + " hours");
        }

        // Mark One Assignment Completed
        System.out.println("\n=== MARKING ONE ASSIGNMENT COMPLETED ===");

        AssignmentTask taskToUpdate = assignments.get(0);
        taskToUpdate.markCompleted();

        System.out.println("Updated Assignment:");
        System.out.println(taskToUpdate);

        // Recalculate remaining hours
        totalRemainingHours = 0;
        for (AssignmentTask a : assignments) {
            if (!a.isCompleted()) {
                totalRemainingHours += a.getEstimatedHours();
            }
        }

        System.out.println("New Total Remaining Hours: " + totalRemainingHours);
    }
}


// 1. Course
class Course {

    private String name;
    private String instructor;
    private int credits;

    public Course(String name, String instructor, int credits) {
        this.name = name;
        this.instructor = instructor;
        this.credits = credits;
    }

    public String getName() { return name; }
    public String getInstructor() { return instructor; }
    public int getCredits() { return credits; }

    @Override
    public String toString() {
        return "Course{name='" + name +
                "', instructor='" + instructor +
                "', credits=" + credits + "}";
    }
}


// 2. AssignmentTask
class AssignmentTask {

    private String title;
    private Course course;
    private int estimatedHours;
    private int daysUntilDue;
    private boolean completed;

    public AssignmentTask(String title, Course course,
                          int estimatedHours, int daysUntilDue) {
        this.title = title;
        this.course = course;
        this.estimatedHours = estimatedHours;
        this.daysUntilDue = daysUntilDue;
        this.completed = false;
    }

    public String getTitle() { return title; }
    public Course getCourse() { return course; }
    public int getEstimatedHours() { return estimatedHours; }
    public int getDaysUntilDue() { return daysUntilDue; }
    public boolean isCompleted() { return completed; }

    public void markCompleted() {
        completed = true;
    }

    public boolean isUrgent() {
        return daysUntilDue <= 2 && !completed;
    }

    @Override
    public String toString() {
        return "AssignmentTask{title='" + title +
                "', course='" + course.getName() +
                "', estHours=" + estimatedHours +
                ", dueIn=" + daysUntilDue +
                ", completed=" + completed + "}";
    }
}


// 3) StudySession
class StudySession {

    private Course course;
    private int minutes;

    public StudySession(Course course, int minutes) {
        this.course = course;
        this.minutes = minutes;
    }

    public Course getCourse() { return course; }
    public int getMinutes() { return minutes; }

    public double hours() {
        return minutes / 60.0;
    }

    @Override
    public String toString() {
        return "StudySession{course='" +
                course.getName() +
                "', minutes=" + minutes + "}";
    }
}