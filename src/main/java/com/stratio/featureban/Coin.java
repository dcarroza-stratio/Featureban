package com.stratio.featureban;

import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@ToString
public enum Coin {
    HEADS, TAILS;

    private static final List<Coin> VALUES =
            Collections.unmodifiableList(Arrays.asList(Coin.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Coin throwIt()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
