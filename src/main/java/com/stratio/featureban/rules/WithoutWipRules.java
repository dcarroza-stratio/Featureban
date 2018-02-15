package com.stratio.featureban.rules;

import com.stratio.featureban.Board;
import com.stratio.featureban.DevMember;
import com.stratio.featureban.Task;

public class WithoutWipRules extends Rules {
    public WithoutWipRules(Board board) {
        super(board);
    }

    @Override
    public boolean moveTaskForward(DevMember member) {
        return board.moveTaskForward(member);
    }

    @Override
    public boolean unblockTask(DevMember member) {
        board.unblockTask(member);
        return false;
    }

    @Override
    public void blockTask(DevMember member) {
        board.blockTask(member);
    }

    @Override
    public boolean startNewTask(DevMember devMember) {
        board.startNewTask(devMember);
        return true;
    }


}
