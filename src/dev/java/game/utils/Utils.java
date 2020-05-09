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

    public static int vecDir(float f){
        if(f <= 0)
            return -1;
        else if(f > 0)
            return 1;
        else
            return 0;
    }

    public static class Py{

        public static float getC(float a, float b){
            return (float) Math.sqrt(a*a+b*b);
        }

        public static float getB(float a, float c){
            return (float) Math.sqrt(c*c-a*a);
        }
    }

    public static class LinearFunction{

        private final float m, b;

        public LinearFunction(float m, float b){
            this.m = m;
            this.b = b;
        }

        public LinearFunction(float x1, float y1, float x2, float y2){
            m = (y1 - y2)/(x1 - x2);
            b = y1 - m*x1;
        }

        public LinearFunction(float x, float y, float m){
            this.m = m;
            b = y - m*x;
        }

        /** Gets the intersection point with another linear function
         *
         * @param otherFunction the other liner function
         * @return an array with size of 2, arr[0] is x and arr[1] is y
         */
        public float[] getIntersection(LinearFunction otherFunction){
            return new float[]{(otherFunction.getB()-b)/(m-otherFunction.getM()),
                    m*(otherFunction.getB()-b)/(m-otherFunction.getM())+b};
        }

        public float findDistance(float x, float y){
            LinearFunction l = new LinearFunction(x, y, -1/m);
            return Py.getC(getIntersection(l)[0] - x, getIntersection(l)[1] - y);
        }

        public float getM() {
            return m;
        }

        public float getB() {
            return b;
        }
    }

}
