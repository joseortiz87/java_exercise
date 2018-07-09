package com.jortiz.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RepetedWords {

	public static void main(String arg[]){
		System.out.println("Repeated words...");
		try {
			Stream<String> rows = Files.lines(Paths.get("src/data2.txt"));
			Map<String,Integer> map = new HashMap<String,Integer>();
			List<String> listWords = new ArrayList<String>();
			rows
				.map(x -> x.split(" "))
				.filter(x -> x.length > 0)
				.forEach(x -> listWords.addAll(Arrays.asList(x)) );
			
			map = listWords
					.stream()
					.collect(Collectors.toMap(
							x -> x,
							x -> 1,
							(oldValue, newValue) -> oldValue + newValue
						));
			rows.close();
			for(String key : map.keySet()){
				System.out.println(key + " " + map.get(key));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
