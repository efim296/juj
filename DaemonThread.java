package com.company;

import java.util.ArrayList;
import java.util.HashMap;
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

    public DaemonThread(Map<String,Integer> counter, KillThreadStatus kill,List<Thread> threads)
    {
        this.counter = counter;
        this.kill = kill;
        this.threads = threads;
        setDaemon(false);
    }

    @Override
    public void run()
    {
        while (true){
            if (kill.isStatus())
            {
                for (Thread thread : threads ) {
                    thread.interrupt();
                }
            }
        }
//       // if (Thread.currentThread().isDaemon())
//        {
//            System.out.println(" daemon work------------------------------------");
//        }
//        else
//        {
//            System.out.println(" thred work+++++++++++++++++++++++++++++++++++++");
//        }
    }
}
