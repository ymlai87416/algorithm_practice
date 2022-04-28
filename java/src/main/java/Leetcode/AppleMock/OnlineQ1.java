package Leetcode.AppleMock;

import java.util.Arrays;
import java.util.Comparator;

public class OnlineQ1 {

    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] log1 = getLogInfo(o1);
                String[] log2 = getLogInfo(o2);

                int logType1 = logType(log1[1]);
                int logType2 = logType(log2[1]);

                if(logType1 == 2 && logType2 == 2) return 0; //no need to reorder
                if(logType1 == 1 && logType2 == 2) return -1;
                if(logType1 == 2 && logType2 == 1) return 1;

                //both are logType1
                if(log1[1].compareTo(log2[1]) == 0)
                    return log1[0].compareTo(log2[0]);
                else
                    return log1[1].compareTo(log2[1]);

            }
        });

        return logs;
    }

    private int logType(String a){
        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            if(c >= 'a' && c <= 'z') return 1;
            if('0' <= c && c <='9') return 2;
        }
        return -1;
    }

    private String[] getLogInfo(String a){
        for (int i = 0; i < a.length(); i++) {
            if(a.charAt(i) == ' ') {
                String[] result = new String[2];
                result[0] = a.substring(0, i);
                result[1] = a.substring(i+1);
                return result;
            }
        }
        return null;
    }

    public static void main(String[] args){
        OnlineQ1 q = new OnlineQ1();
        String[] s = new String[]{"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        System.out.println(q.reorderLogFiles(s));
    }
}
