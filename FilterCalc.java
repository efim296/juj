package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 07.04.2017.
 */
public class FilterCalc implements Runnable {
    private File file;
    private Map<String, Integer> counter;
    private KillThreadStatus kill;

    public FilterCalc(File file, Map<String, Integer> counter, KillThreadStatus kill)
    {
        this.file = file;
        this.counter = counter;
        this.kill = kill;
    }

    @Override
    public void run()
    {
        String[] s = null;
        try (FileReader reader = new FileReader(file)) {
//            synchronized (kill) {
                int c;
                StringBuilder str = new StringBuilder();
                while (((c = reader.read()) != -1)) {
                    str.append(String.valueOf((char) c));
                }
                if (!isCyrillic(str.toString())) {
                    kill.setStatus(true);
                    throw new InvalidSymbolExeption("NON CYRILLIC!!!");
                }
//                Thread.sleep(5000);
                //  System.out.println(str);
                s = str.toString().split("[\\p{Punct}\\s\\d]+");                                        ////\p{P}?[ \t\n\r]+
                for (String i : s) {
                    if(kill.isStatus())
                        break;

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
                System.out.println("файл закончился----------------------------");
//            }
        } catch (InvalidSymbolExeption invalidSymbolExeption) {
            invalidSymbolExeption.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
        }


//        for (int i = 0; i < s.length; i++) {
            //  System.out.println(s[i]);
       // }
    }


    public static boolean isCyrillic(String str) {
        Pattern p = Pattern.compile("[а-яА-ЯёЁ\\d\\s\\p{Punct}]*");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
