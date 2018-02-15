package com.stratio.featureban;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@lombok.ToString
public class Board {

    private static final Logger LOGGER = Logger.getLogger(Board.class.getName());

    private Integer wip = Integer.MAX_VALUE;

    List<Task> tasksInBoard = new ArrayList<>();

    public boolean moveTaskForward(DevMember devMember){
        boolean moved = false;
        for (Task task : getTasksFromDev(devMember)) {
            if (!task.isBlocked() && task.getTaskState() != TaskState.DONE){
                switch (task.getTaskState()) {
                    case TO_DO:
                        if (!isInProgressColumnLimited()) {
                            task.setTaskState(TaskState.IN_PROGRESS);
                            moved = true;
                        }
                        break;
                    case IN_PROGRESS:
                        if (!isInReviewColumnLimited()) {
                            task.setTaskState(TaskState.IN_REVIEW);
                            moved = true;
                        }
                        break;
                    case IN_REVIEW:
                        moved = true;
                        task.setTaskState(TaskState.DONE);
                        break;
                    case DONE:
                        break;
                }
                break;
            }
        }
        return moved;
    }

    private boolean isInReviewColumnLimited() {
        int count = 0;
        for (Task task : this.tasksInBoard) {
            if (task.getTaskState() == TaskState.IN_REVIEW){
                count++;
            }
        }
        return count >= wip;
    }

    private boolean isInProgressColumnLimited() {
        int count = 0;
        for (Task task : this.tasksInBoard) {
            if (task.getTaskState() == TaskState.IN_PROGRESS){
                count++;
            }
        }
        return count >= wip;
    }

    public void startNewTask(DevMember devMember){
        Task newTask = new Task(devMember);
        tasksInBoard.add(newTask);
        if (!isInProgressColumnLimited()) {
            newTask.setTaskState(TaskState.IN_PROGRESS);
        }
    }

    public List<Task> getTasksFromDev(DevMember devMember){
        List<Task> myTasks = new ArrayList<>();
        for (Task task : this.tasksInBoard) {
            if (task.isMine(devMember)) {
                myTasks.add(task);
            }
        }
        Collections.sort(myTasks);
        return myTasks;
    }

    public void blockTask(DevMember devMember){
        for (Task task : getTasksFromDev(devMember)) {
            if (!task.isBlocked()){
                switch (task.getTaskState()) {
                    case TO_DO:
                        break;
                    case IN_PROGRESS:
                    case IN_REVIEW:
                        task.block();
                        break;
                    case DONE:
                        break;
                }
                break;
            }
        }
    }

    public void unblockTask(DevMember devMember) {
        for (Task task : getTasksFromDev(devMember)) {
            if (task.isBlocked()){
                switch (task.getTaskState()) {
                    case TO_DO:
                        break;
                    case IN_PROGRESS:
                    case IN_REVIEW:
                        task.unblock();
                        break;
                    case DONE:
                        break;
                }
                break;
            }
        }
    }

    public void printBoardSummary() {
        List<Task> toDoColumn = new ArrayList<Task>();
        List<Task> inProgressColumn = new ArrayList<Task>();
        List<Task> inReviewColumn = new ArrayList<Task>();
        List<Task> doneColumn = new ArrayList<Task>();

        for (Task task : tasksInBoard) {
            switch (task.getTaskState()) {
                case TO_DO:
                    toDoColumn.add(task);
                    break;
                case IN_PROGRESS:
                    inProgressColumn.add(task);
                    break;
                case IN_REVIEW:
                    inReviewColumn.add(task);
                    break;
                case DONE:
                    doneColumn.add(task);
                    break;
            }
        }

        LOGGER.info("To Do: " + toDoColumn.size());
        LOGGER.info("In Progress: " + inProgressColumn.size());
        LOGGER.info("In Review: " + inReviewColumn.size());
        LOGGER.info("Done: " + doneColumn.size());
    }

    public void setWip(int wip) {
        this.wip = wip;
    }

//    @Override
//    public String toString() {
//        return "Board{" +
//                "toDoColumn=" + toDoColumn +
//                ", inProgressColumn=" + inProgressColumn +
//                ", inReviewColumn=" + inReviewColumn +
//                ", doneColumn=" + doneColumn +
//                ", tasksInBoard=" + tasksInBoard +
//                '}';
//    }
}
