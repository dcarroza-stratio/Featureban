package com.stratio.featureban;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@lombok.ToString
public class Board {

    private static final Logger LOGGER = Logger.getLogger(Board.class.getName());

    private Integer wip = null;

    List<Task> tasksInBoard = new ArrayList<>();

    public boolean moveTaskForward(DevMember devMember){
        boolean moved = false;
        for (Task task : getTasksFromDev(devMember)) {
            if (!task.isBlocked() && task.getTaskState() != TaskState.DONE){
                switch (task.getTaskState()) {
                    case TO_DO:
                        task.setTaskState(TaskState.IN_PROGRESS);
                        moved = true;
                        break;
                    case IN_PROGRESS:
                        moved = true;
                        task.setTaskState(TaskState.IN_REVIEW);
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

    public void startNewTask(DevMember devMember){
        //TODO WIP
        tasksInBoard.add(new Task(devMember));
    }

    public List<Task> getTasksFromDev(DevMember devMember){
        List<Task> myTasks = new ArrayList<>();
        for (Task task : this.tasksInBoard) {
            if (task.isMine(devMember)) {
                myTasks.add(task);
            }
        }
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
