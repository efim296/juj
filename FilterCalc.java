package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 07.04.2017.
 */
public class FilterCalc implements Runnable {
    private File filePath;
    private Map<String, Integer> counter;
    private KillThreadStatus kill;

    public FilterCalc(File filePath, Map<String, Integer> counter, KillThreadStatus kill)
    {
        this.filePath = filePath;
        this.counter = counter;
        this.kill = kill;


    }


    @Override
    public void run() {
        String[] s = null;
        try (FileReader reader = new FileReader(filePath)) {
            int c;
            StringBuilder str = new StringBuilder();
            while (((c = reader.read()) != -1)) {
                str.append(String.valueOf((char) c));
            }
            if (!isCyrillic(str.toString())) {
             kill.setStatus(true);////////////////
                throw new InvalidSymbolExeption("NON CYRILLIC!!!");
            }
            //  System.out.println(str);
            s = str.toString().split("[\\p{Punct}\\s\\d]+");                                        ////\p{P}?[ \t\n\r]+
            for (String i : s) {
                if (!i.isEmpty()) {
                    Integer count = counter.get(i);
                    if (count == null) {
                        count = 0;
                    }
                    counter.put(i, ++count);
                }
            }

            for (String p : counter.keySet()) {
                System.out.println(p + ": " + counter.get(p));
            }
            System.out.println("файл закончился");
        } catch (InvalidSymbolExeption invalidSymbolExeption) {
            invalidSymbolExeption.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < s.length; i++) {
            //  System.out.println(s[i]);
        }
    }


    public static boolean isCyrillic(String str) {
        Pattern p = Pattern.compile("[а-яА-ЯёЁ\\d\\s\\p{Punct}]*");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
