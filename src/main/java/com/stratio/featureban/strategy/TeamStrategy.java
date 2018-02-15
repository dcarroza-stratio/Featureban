package com.stratio.featureban.strategy;

import com.stratio.featureban.Coin;
import com.stratio.featureban.DevMember;
import com.stratio.featureban.rules.Rules;

import java.util.Map;

public interface TeamStrategy {
    void runDay(Map<DevMember, Coin> day, Rules throwPerDevMember);
}
