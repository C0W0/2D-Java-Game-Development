package dev.java.game.utils;

import dev.java.game.entities.Entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public static float getDistance(Entity eA, Entity eB){
        return (float)Math.sqrt(Math.pow(eA.getX() - eB.getX(), 2) + Math.pow(eA.getY() - eB.getY(), 2));
    }

    public static float getDistance(Entity e, float x2, float y2){
        return (float)Math.sqrt(Math.pow(e.getX() - x2, 2) + Math.pow(e.getY() - y2, 2));
    }

    public static float pickNumber(float...nums){
        return nums[(int)(Math.random()*nums.length)];
    }

    public static class Py{

        public static float getC(float a, float b){
            return (float) Math.sqrt(a*a+b*b);
        }

        public static float getB(float a, float c){
            return (float) Math.sqrt(c*c-a*a);
        }
    }

}
