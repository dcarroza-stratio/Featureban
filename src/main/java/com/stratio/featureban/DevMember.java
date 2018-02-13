package com.stratio.featureban;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DevMember {
    public DevMember(int devMember) {

    }


    public void blockTask(Board board) {
        for (Task task : board.getTasksFromDev(this)) {
            if (!task.isBlocked()){
                task.block();
                break;
            }
        }
    }

    public void startNewTask(Board board){
        board.startNewTask(this);
    }

    public void unblockTask(Board board){
        for (Task task : board.getTasksFromDev(this)) {
            if (task.isBlocked()){
                task.unblock();
                break;
            }
        }
    }

    public void moveTaskForward(Board board){
        board.moveTaskForward(this);
    }
}
