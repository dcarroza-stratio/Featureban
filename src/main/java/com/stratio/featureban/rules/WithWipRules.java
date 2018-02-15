package com.stratio.featureban.rules;

import com.stratio.featureban.Board;
import com.stratio.featureban.DevMember;

public class WithWipRules extends Rules {
    public WithWipRules(Board board, int wip) {
        super(board);
        board.setWip(wip);
    }

    @Override
    public boolean moveTaskForward(DevMember member) {
        //TODO

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
