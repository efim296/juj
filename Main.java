package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Main

{
    public static void main(String[] args) throws IOException, InvalidSymbolExeption {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> counter = new ConcurrentHashMap<>();
        List<Thread> threads = new ArrayList<>();
        KillThreadStatus kill = new KillThreadStatus();
        DaemonThread daemonThread = new DaemonThread(counter, kill, threads);
        daemonThread.start();
        for (int i = 0; i < args.length; i++) {
            File file = new File(args[i]);
            Thread thread = new Thread(new FilterCalc(file, counter, kill));
            threads.add(thread);
            thread.start();

        }scanner.next();
    }
}
