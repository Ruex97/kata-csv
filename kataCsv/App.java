package kataCsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

	public static void main(String[] args) {
		
		List<String> records = readCsv();
		List<String> users = new ArrayList<>();
		List<String> hashtags = new ArrayList<>();
		List<String> cities = new ArrayList<>();
		

		classifyData(records, users, hashtags, cities);
		
		
		// 3
		long usersCount = users.stream().count();
		long hashtagsCount = hashtags.stream().count();		
		long citiesCount = cities.stream().count();		
		
		System.out.println("These are the cities:" + users.toString() + 
							"\nTotal: " + usersCount);
		
		System.out.println("These are the hashtags:" + hashtags.toString() + 
							"\nTotal: " + hashtagsCount);
		
		System.out.println("These are the cities:" + cities.toString() + 
							"\nTotal: " + citiesCount);
		
		
		// 4
		String hashtag = Keyboard.getString("Enter a hashtag. Usage: #hashtag");
		
		if (hashtag.startsWith("#")) {
			List<String> matches = 
			hashtags.stream().filter(h -> (h.equals(hashtag))).collect(Collectors.toList());
			System.out.println("These are the matches:" + matches);
		} else {
			System.out.println("Usage: #hashtag");
		}
		
		
		//5
		// He de ordenar la llista:
		
		Map<String, Integer> counts = new HashMap<>();
		for (String s : hashtags) {
		    counts.put(s, counts.getOrDefault(s, 0) + 1);
		}

		counts.entrySet().stream()
		    .sorted(Map.Entry.comparingByValue())
		    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
		
		// Imprimirla
		
		
		//6
		String userHashtag = Keyboard.getString("Enter a hashtag. Usage: #hashtag");
		int occurrences = Collections.frequency(hashtags, userHashtag);
		
		System.out.println("Hashtag " + userHashtag + " is " + occurrences + " times");

	}
	
	
	private static List<String> readCsv() {
		
		List<String> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\formacio\\Desktop\\xavi Rue\\palabras.csv"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(";");
		        records.addAll(Arrays.asList(values));
		        //  records.add(Arrays.asList(values));
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return records;
	
	}

	private static void classifyData(List<String> records, List<String> users, List<String> hashtags, List<String> cities) {
		
		records.stream().forEach(r -> {
			if (r.startsWith("@")) {
				users.add(r);
			} else if (r.startsWith("#")) {
				hashtags.add(r);
			} else {
				cities.add(r);
			}
		});
		
	}
}
