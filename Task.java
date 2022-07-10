public class Task {

   private int taskID;
   private int Date;
   private String name;
   private String description;
   private String priority;
   private String status;

    public Task(int taskID, int date, String name, String description, String priority, String status) {
        this.taskID = taskID;
        Date = date;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getDate() {
        return Date;
    }

    public void setDate(int date) {
        Date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
