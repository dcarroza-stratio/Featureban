package com.stratio.featureban;

import com.stratio.featureban.rules.Rules;
import com.stratio.featureban.rules.WithWipRules;
import com.stratio.featureban.rules.WithoutWipRules;
import com.stratio.featureban.strategy.TeamStrategy;
import com.stratio.featureban.strategy.MoveFirstStrategy;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Featureban {

    private static final Logger LOGGER = Logger.getLogger(Featureban.class.getName());

    private int teamSize;
    private int numDays;
    private TeamStrategy teamStrategy = new MoveFirstStrategy();
    private Board board = new Board();

    public Featureban(int teamSize, int numDays) {
        this.teamSize = teamSize;
        this.numDays = numDays;
    }

    public static void main(String[] args) {
//        int teamSize = Integer.parseInt(args[0]);
//        int numDays = Integer.parseInt(args[1]);

        int teamSize = 4;
        int numDays = 10;

        Featureban featureban = new Featureban(teamSize, numDays);

        featureban.run();
    }

    private void run() {
        List<DevMember> team = new ArrayList<>();
        for (int devMember = 1; devMember <= teamSize; devMember++) {
            team.add(new DevMember(devMember));
        }

        for (int day = 1; day <= numDays; day++) {
            Map<DevMember, Coin> throwPerDevMember = new HashMap<DevMember, Coin>();
            for (int devMember = 0; devMember < teamSize; devMember++) {
                Coin coin = Coin.throwIt();
                LOGGER.info(coin.name());
                throwPerDevMember.put(team.get(devMember), coin);
            }

            LOGGER.info("Day " + day);
            //TODO parametrizable
            Rules rules = new WithoutWipRules(board);
            teamStrategy.runDay(throwPerDevMember, rules);
//            LOGGER.info(board.toString());
        }
        board.printBoardSummary();
    }

}
