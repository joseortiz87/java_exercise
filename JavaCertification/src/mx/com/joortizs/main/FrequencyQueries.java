package mx.com.joortizs.main;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public class FrequencyQueries {

    static class Query{
        Integer command;
        Integer number;
        Integer frecuency;
        Integer response;

        public Query(Integer command, Integer number, Integer frecuency, Integer response) {
            this.command = command;
            this.number = number;
            this.frecuency = frecuency;
            this.response = response;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "command=" + command +
                    ", number=" + number +
                    ", frecuency=" + frecuency +
                    ", response=" + response +
                    '}';
        }
    }

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<int[]> queries) {
        Map<Integer,Integer> numberFreq = new HashMap<>();
        Map<Integer,Integer> freqCount = new HashMap<>();
        List<Integer> responses = new ArrayList<>();
        for(int[] query : queries){
            Integer command = query[0];
            Integer number = query[1];
            switch(command){
                case 1:
                    //Insert x in your data structure
                    if(numberFreq.containsKey(number)){
                        Integer prevFreq = numberFreq.get(number);
                        decreaseCountByOne(freqCount,prevFreq);
                    }
                    increaseCountByOne(numberFreq,number);
                    increaseCountByOne(freqCount,numberFreq.get(number));
                    //responses.add(new Query(1,number,numberFreq.get(number),repetedFreq.get(freq)));
                    break;
                case 2:
                    //Delete one occurence of y from your data structure, if present
                    if(numberFreq.containsKey(number)){
                        Integer prevFreq = numberFreq.get(number);
                        decreaseCountByOne(freqCount,prevFreq);
                    }
                    decreaseCountByOne(numberFreq,number);
                    if(numberFreq.containsKey(number)){
                        increaseCountByOne(freqCount,numberFreq.get(number));
                    }
                    //responses.add(new Query(2,number,numberFreq.get(number),repetedFreq.get(numberFreq.get(number))));
                    break;
                case 3:
                    //Check if any integer is present whose frequency is exactly . If yes, print 1 else 0.
                    if(freqCount.containsKey(number)){
                        //responses.add(new Query(3,number,0,1));
                        responses.add(1);
                    }else{
                        //responses.add(new Query(3,number,0,0));
                        responses.add(0);
                    }
                    break;
            }
        }
        return responses;
    }

    public static void decreaseCountByOne(Map<Integer,Integer> countMap,Integer freq){
        Integer freqCount;
        if(countMap.containsKey(freq)){
            freqCount = countMap.get(freq);
            if(freqCount <= 1){
                countMap.remove(freq);
            }else{
                freqCount--;
                countMap.put(freq,freqCount);
            }
        }
    }

    public static void increaseCountByOne(Map<Integer,Integer> countMap,Integer freq){
        Integer freqCount = 1;
        if(countMap.containsKey(freq)){
            freqCount = countMap.get(freq);
            freqCount++;
        }
        countMap.put(freq,freqCount);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int q = Integer.parseInt(bufferedReader.readLine().trim());
            List<int[]> queries = new ArrayList<>(q);
            Pattern p  = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");
            for (int i = 0; i < q; i++) {
                int[] query = new int[2];
                Matcher m = p.matcher(bufferedReader.readLine());
                if (m.matches()) {
                    query[0] = Integer.parseInt(m.group(1));
                    query[1] = Integer.parseInt(m.group(2));
                    queries.add(query);
                }
            }
            List<Integer> ans = freqQuery(queries);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("frequency_queries_output2"))) {
                bufferedWriter.write(
                        ans.stream()
                                .map(Object::toString)
                                .collect(joining("\n"))
                                + "\n");
            }
        }
    }
}
