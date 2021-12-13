package com.company;


public class Philosopher implements Runnable {
    private int eaten = 0;
    private boolean successful = true;

    @Override
    public void run() {
        while (successful) {
            if (getRandomBoolean()) {
                successful = eat();
                ++eaten;
                System.out.println(Thread.currentThread().getName() + " съел в сумме: " + eaten);
            } else {
                successful = reflect();
            }
        }
        System.out.println(Thread.currentThread().getName() + " ушёл...");
    }

    public static synchronized boolean eat() {
        System.out.println(Thread.currentThread().getName() + " ест, теперь тарелка с рисом занята");
        boolean successful = sleep();
        System.out.println(Thread.currentThread().getName() + " освободил тарелку с рисом");
        return successful;
    }

    public static boolean reflect() {
        System.out.println(Thread.currentThread().getName() + " размышляет");
        boolean successful = sleep();
        System.out.println(Thread.currentThread().getName() + " перестал размышлять");
        return successful;
    }

    /**
     * Возвращает успешность сна:
     * - если поспал без прерываний -> true;
     * - иначе -> false.
     */
    public static boolean sleep() {
        try {
            Thread.sleep(getRandomSleepTime());
            return true;
        } catch (InterruptedException ignored) {}
        return false;
    }

    public static boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }

    public static int getRandomSleepTime() {
        return (int)(Math.random() * 3000) + 500;
    }
}
