package com.sbt.javaschool.rnd;

import org.junit.jupiter.api.RepeatedTest;

class ExecutionManagerTest {

    @RepeatedTest(10)
    void execute() {
        ExecutionManager manager = new ExecutionManagerImpl();
        Runnable[] r = new Runnable[11];
        for (int i = 0; i < 10; i++)
            r[i] = new FactorialCalculate();

        r[10] = new Runnable() {
            Integer[] integers = new Integer[1];
            @Override
            public void run() {
                System.out.println(integers[2]);
            }
        };
        Runnable callback = () -> System.out.println("this function calling once");

        Context context = manager.execute(callback,
                r);

        while (!context.isFinished()) {
//            try {
//                Thread.sleep(1);
                System.out.println("Completed task: " + context.getCompletedTaskCount());
                System.out.println("Failed task: " + context.getFailedTaskCount());
                System.out.println("Interrupted task: " + context.getInterruptedTaskCount());

                context.interrupt();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        System.out.println("Completed task: " + context.getCompletedTaskCount());
        System.out.println("Failed task: " + context.getFailedTaskCount());
        System.out.println("Interrupted task: " + context.getInterruptedTaskCount());
    }
}