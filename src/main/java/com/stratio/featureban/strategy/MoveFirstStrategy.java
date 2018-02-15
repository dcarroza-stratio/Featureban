package com.stratio.featureban.strategy;

import com.stratio.featureban.Coin;
import com.stratio.featureban.DevMember;
import com.stratio.featureban.rules.Rules;

import java.util.Map;

public class MoveFirstStrategy implements TeamStrategy {
    @Override
    public void runDay(Map<DevMember, Coin> throwPerDevMember, Rules rules) {
        for (DevMember member : throwPerDevMember.keySet()) {
            Coin coin = throwPerDevMember.get(member);
            switch (coin) {
                case HEADS:
                    if (!rules.moveTaskForward(member)){
                        rules.unblockTask(member);
                    }
                    break;
                case TAILS:
                    rules.blockTask(member);
                    rules.startNewTask(member);
                    break;
            }
        }
    }

}
