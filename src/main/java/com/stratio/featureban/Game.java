package com.stratio.featureban;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());

    private int teamSize;
    private int numDays;
    private Board board = new Board();

    public Game(int teamSize, int numDays) {
        this.teamSize = teamSize;
        this.numDays = numDays;
    }

    public static void main(String[] args) {
//        int teamSize = Integer.parseInt(args[0]);
//        int numDays = Integer.parseInt(args[1]);

        int teamSize = 4;
        int numDays = 10;

        Game game = new Game(teamSize, numDays);
        game.run();
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

            doDaily(day, throwPerDevMember);
        }

    }

    private void doDaily(int day, Map<DevMember, Coin> throwPerDevMember) {
        for (DevMember member : throwPerDevMember.keySet()) {
            Coin coin = throwPerDevMember.get(member);
            switch (coin) {
                case HEADS:
                    if (!board.moveTaskForward(member)){
                        board.unblockTask(member);
                    }
                    break;
                case TAILS:
                    board.blockTask(member);
                    board.startNewTask(member);
                    break;
            }
        }
        LOGGER.info("Day " + day);
        LOGGER.info(board.toString());
        board.printBoardSummary();
    }

}
