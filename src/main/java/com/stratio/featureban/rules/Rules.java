package com.stratio.featureban.rules;

import com.stratio.featureban.Board;
import com.stratio.featureban.DevMember;
import com.stratio.featureban.Task;

public abstract class Rules {

    protected Board board;

    public Rules(Board board) {
        this.board = board;
    }

    public abstract boolean moveTaskForward(DevMember member);

    public abstract boolean unblockTask(DevMember member);

    public abstract void blockTask(DevMember member);

    public abstract boolean startNewTask(DevMember devMember);
}
