import java.util.ArrayList;
import java.util.List;

public class campuslife {

    // MAIN METHOD
    public static void main(String[] args) {

        List<PlanItem> planner = new ArrayList<>();

        planner.add(new Assignment("Math Homework", 2, 3));
        planner.add(new ExamPreparation("Biology Midterm", 5, 10));
        planner.add(new ClubEvent("Robotics Club Meeting", 1, 2));
        planner.add(new Assignment("English Essay", 4, 5));

        // Отмечаем одно задание выполненным
        planner.get(0).markCompleted();

        int totalRemainingHours = 0;
        PlanItem mostUrgent = null;
        int highestUrgency = -1;

        System.out.println("    CampusLife Planner    ");

        for (PlanItem item : planner) {

            System.out.println(item.getDetails());

            if (!item.isCompleted()) {
                totalRemainingHours += item.getEstimatedHours();

                int urgency = item.calculateUrgency();
                if (urgency > highestUrgency) {
                    highestUrgency = urgency;
                    mostUrgent = item;
                }
            }
        }

        System.out.println("\n--- Summary ---");
        System.out.println("Total Remaining Study Time: " + totalRemainingHours + " hrs");

        if (mostUrgent != null) {
            System.out.println("Most Urgent Activity: " + mostUrgent.title);
        }
    }
}


// Superclass

abstract class PlanItem {

    protected String title;
    protected int daysUntil;
    protected int estimatedHours;
    protected boolean completed;

    public PlanItem(String title, int daysUntil, int estimatedHours) {
        this.title = title;
        this.daysUntil = daysUntil;
        this.estimatedHours = estimatedHours;
        this.completed = false;
    }

    public void markCompleted() {
        completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public String getSummary() {
        return title + " | Due in: " + daysUntil + " days | Estimated: "
                + estimatedHours + " hrs | Completed: " + completed;
    }

    public abstract int calculateUrgency();

    public abstract String getDetails();
}


// Assignment
class Assignment extends PlanItem {

    public Assignment(String title, int daysUntil, int estimatedHours) {
        super(title, daysUntil, estimatedHours);
    }

    @Override
    public int calculateUrgency() {
        if (completed) return 0;
        return 10 - daysUntil;
    }

    @Override
    public String getDetails() {
        return "[Assignment] " + getSummary()
                + " | Urgency: " + calculateUrgency();
    }
}


// ExamPreparation
class ExamPreparation extends PlanItem {

    public ExamPreparation(String title, int daysUntil, int estimatedHours) {
        super(title, daysUntil, estimatedHours);
    }

    @Override
    public int calculateUrgency() {
        if (completed) return 0;
        return (20 - daysUntil) + estimatedHours;
    }

    @Override
    public String getDetails() {
        return "[Exam Prep] " + getSummary()
                + " | Study Priority: " + calculateUrgency();
    }
}


// ClubEvent
class ClubEvent extends PlanItem {

    public ClubEvent(String title, int daysUntil, int estimatedHours) {
        super(title, daysUntil, estimatedHours);
    }

    @Override
    public int calculateUrgency() {
        if (completed) return 0;
        return 5 - daysUntil;
    }

    @Override
    public String getDetails() {
        return "[Club Event] " + getSummary()
                + " | Social Priority: " + calculateUrgency();
    }
}