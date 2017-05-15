package com.company;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 09.04.2017.
 */
public class DaemonThread extends Thread

{
    private Map<String, Integer> counter;
    private KillThreadStatus kill;
    private List<Thread> threads;

    public DaemonThread(Map<String, Integer> counter, KillThreadStatus kill, List<Thread> threads) {
        this.counter = counter;
        this.kill = kill;
        this.threads = threads;
        setDaemon(true);
    }

    @Override
    public void run() {
//        try {
        while (true) {
//                synchronized (kill) {
            if (kill.isStatus()) {
                for (Thread thread : threads) {
                    thread.interrupt();
                    System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
                }

            }
        }

    }
}
