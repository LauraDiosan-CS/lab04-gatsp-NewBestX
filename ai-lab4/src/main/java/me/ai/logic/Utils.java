package me.ai.logic;

import java.util.Random;

public class Utils {
    private static Random rand = new Random();

    public static Random getRand() {
        return rand;
    }

    public static class PrintBuffer {
        private StringBuilder sb = new StringBuilder();
        private int count = 0;

        public void addMessage(String s) {
            sb.append(s).append("\n");
            count++;
            if(count > 10)
                flush();
        }

        public void flush() {
            System.out.println(sb.toString());
            count = 0;
        }
    }
}
