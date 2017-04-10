package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main

{

    public static void main(String[] args) throws IOException, InvalidSymbolExeption {
        Map<String, Integer> counter = new ConcurrentHashMap<>();
        List<Thread> threads = new ArrayList<>();
        KillThreadStatus kill= new KillThreadStatus();
        DaemonThread daemonThread= new DaemonThread(counter,kill,threads);
// DaemonThread lol = new DaemonThread(threads, counter,KillThreadStatus);
        //daemon daemon= new daemon(counter, KillThreadStatus, threads); //
        for (int i = 0; i < args.length; i++) {
            File file = new File(args[i]);
            Thread thread = new Thread(new FilterCalc(file, counter, kill));
            threads.add(thread);
            thread.start();
        }
       // Thread daemonthread = new Thread();
//        DaemonThread daemonthread = new DaemonThread(counter,threads,KillThreadStatus);
        daemonThread.start();
    }
}
