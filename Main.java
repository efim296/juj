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
        KillThreadStatus killThreadStatus = new KillThreadStatus();//
        for (int i = 0; i < args.length; i++) {
            File file = new File(args[i]);
            Thread thread = new Thread(new FilterCalc(file, counter, killThreadStatus));//
            threads.add(thread);
            //  System.out.println("");
            thread.start();
        }
    }
}
