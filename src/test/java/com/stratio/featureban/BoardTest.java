package com.stratio.featureban;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void getTasksFromDev() {
        Board board = new Board();
        DevMember devMember = new DevMember(1);
        board.startNewTask(devMember);
        board.startNewTask(devMember);
        board.moveTaskForward(devMember);

        List tasksFromDev = board.getTasksFromDev(devMember);

        Iterator iterator = tasksFromDev.iterator();
        Task task1 = (Task) iterator.next();
        Assert.assertEquals(TaskState.IN_REVIEW, task1.getTaskState());
        Task task2 = (Task) iterator.next();
        Assert.assertEquals(TaskState.IN_PROGRESS, task2.getTaskState());
    }
}