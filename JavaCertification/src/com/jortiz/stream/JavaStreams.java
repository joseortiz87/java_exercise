package com.jortiz.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.*;

public class JavaStreams {
	public static void main(String args[]){
		
		//1. Skip first 5 elements
		System.out.println("1. Skip first 5 elements");
		IntStream
		.range(1,10)
		.skip(5)
		.forEach(x -> System.out.println(x));
		
		//2. Sum elements
		System.out.println("2. Sum elements");
		System.out.println(
				IntStream.range(1, 5)
						 .sum());
		
		//3. Stream.of , sorted and findfirst
		System.out.println("3. Stream.of , sorted and findfirst");
		Stream.of("Ava","Aneri","Alberto")
			  .sorted()
			  .findFirst()
			  .ifPresent(System.out::println);
		
		//4. Stream from Array, sort, filter and print
		System.out.println("4. Stream from Array, sort, filter and print");
		String [] names = {"Al","Ankit","Kushal","Brent","Sarika","amanda","Hans","Shivika","Samira","Saul","Jose","Hernan"};
		Arrays.stream(names)
			.filter(x -> x.startsWith("S"))
			.sorted()
			.forEach(System.out::println);
		
		//5. avarage of squares of an int array
		System.out.println("5. avarage of squares of an int array");
		int [] intArray = {2,4,6,8,10};
		Arrays.stream(intArray)
			.map(x -> x*x)
			.average()
			.ifPresent(System.out::println);
		
		//6. Stream from list, sort, filter and print
		System.out.println("6. Stream from list, sort, filter and print");
		List<String> listNames = Arrays.asList("Al","Ankit","Kushal","Brent","Sarika","amanda","Hans","Shivika","Samira","Saul","Jose","Hernan");
		listNames
		    .stream()
		    .map(x -> x.toLowerCase())
			.filter(x -> x.startsWith("a"))
			.sorted()
			.forEach(System.out::println);
		
		//7. Stream rows from a text file filter and count
		System.out.println("7. Stream rows from a text file filter and count");
		try {
			//"C://Users//allia//git//java_exercise//JavaCertification//src//data1.txt"
			Stream<String> row1 = Files.lines(Paths.get("src/data1.txt"));
			long count = row1
						.map(x -> x.split(","))
						.filter(x -> x.length == 3)
						.count();
			System.out.println(count + " rows.");
			row1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//8. Stream rows from a text file filter and parse
		System.out.println("8. Stream rows from a text file filter and parse");
		try {
			//"C://Users//allia//git//java_exercise//JavaCertification//src//data1.txt"
			Stream<String> row2 = Files.lines(Paths.get("src/data1.txt"));
						row2
						.map(x -> x.split(","))
						.filter(x -> x.length == 3)
						.filter(x -> Integer.parseInt(x[1]) > 15)
						.forEach(x -> System.out.println(x[0] + " " + x[1] + " " + x[2]));
			row2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//9. Stream rows from a text file, filter and save in a hashmap
		System.out.println("8. Stream rows from a text file filter and parse");
		try {
			//"C://Users//allia//git//java_exercise//JavaCertification//src//data1.txt"
			Stream<String> row3 = Files.lines(Paths.get("src/data1.txt"));
			Map<String,Integer> map = new HashMap<String,Integer>();
			map = row3
				.map(x -> x.split(","))
				.filter(x -> x.length == 3)
				.filter(x -> Integer.parseInt(x[1]) > 15)
				.collect(Collectors.toMap(
							x -> x[0],
							x -> Integer.parseInt(x[1])
						));
			row3.close();
			for(String key : map.keySet()){
				System.out.println(key + " " + map.get(key));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//10. Reduction - sum
		System.out.println("10. Reduction - sum");
		double total = Stream.of(7.3,1.5,4.8)
				.reduce(0.0, (Double a,Double b) -> a + b);
		System.out.println(total);
		
		//11. Summary
		System.out.println("11. Summary");
		IntSummaryStatistics summary = IntStream.of(2,4,6,8,10,12,14)
				.summaryStatistics();
		System.out.println(summary);
	}
}
