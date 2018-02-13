package com.stratio.featureban;

@lombok.ToString
public class Task {
    private TaskState taskState;
    private DevMember devMemberAssigned;
    private boolean blocked;

    public Task(DevMember devMember) {
        this.devMemberAssigned = devMember;
        taskState = TaskState.IN_PROGRESS;
    }

    public boolean isMine(DevMember devMember){
        return devMemberAssigned == devMember;
    }

    public void block() {
        blocked = true;
    }

    public boolean isBlocked(){
        return blocked == true;
    }

    public void unblock() {
        blocked = false;
    }

    public TaskState getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }
}
