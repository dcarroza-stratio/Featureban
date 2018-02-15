package com.stratio.featureban.rules;

import com.stratio.featureban.Board;
import com.stratio.featureban.DevMember;

public class WithWipRules extends Rules {
    public WithWipRules(Board board) {
        super(board);
    }

    @Override
    public boolean moveTaskForward(DevMember member) {
        return false;
    }

    @Override
    public boolean unblockTask(DevMember member) {
        return false;
    }

    @Override
    public void blockTask(DevMember member) {

    }

    @Override
    public boolean startNewTask(DevMember devMember) {
        return false;
    }
}
