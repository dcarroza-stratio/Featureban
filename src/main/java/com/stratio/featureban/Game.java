package com.stratio.featureban;

public class Game {

    private int teamSize;
    private int numDays;

    public Game(int teamSize, int numDays) {
        this.teamSize = teamSize;
        this.numDays = numDays;
    }

    public static void main(String[] args) {
        int teamSize = Integer.parseInt(args[0]);
        int numDays = Integer.parseInt(args[1]);

        Game game = new Game(teamSize, numDays);
        game.run();
    }

    private void run() {
        for (int day = 1; day <= numDays; day++) {
            for (int devMember = 1; devMember <= teamSize; devMember++) {
                simulateDay(day, devMember);
            }
        }
    }

    private void simulateDay(int day, int devMember) {
        Coin coin = Coin.throwIt();
        switch (coin) {
            case HEADS:

                break;
            case TAILS:
                break;
        }
    }

}
