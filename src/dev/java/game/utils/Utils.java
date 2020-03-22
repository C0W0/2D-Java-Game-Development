package dev.java.game.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {

    public static String loadFileAsString(String path){

        StringBuilder builder = new StringBuilder();

        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null){
                String newLine = line + "\n";
                if(!newLine.startsWith("//")){
                    builder.append(newLine);
                }
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();;
        }

        return builder.toString();
    }

    public static ArrayList<String> loadFileAsArrayList(String path){
        ArrayList<String> lines = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null){
                if(!line.startsWith("//")){
                    lines.add(line);
                }
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();;
        }

        return lines;
    }

    public static int parseInt(String number){
        try {
            return Integer.parseInt(number);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }

}
