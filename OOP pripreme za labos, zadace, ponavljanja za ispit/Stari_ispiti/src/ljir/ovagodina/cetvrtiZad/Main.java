package ljir.ovagodina.cetvrtiZad;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
	 public static void main(String[] args) {
	 Map<String, Map<String, Integer>> votesMap = Loader.loadData();
	 System.out.println("Votes per election unit: " + getVotesPerElectionUnit(votesMap)); //Votes per election unit: {eu1=1000, eu2=1000}
	 System.out.println("Average votes per party: " + getAverageVotesPerParty(votesMap)); //Average votes per party: {Red=350.0, Yellow=200.0, Blue=250.0, Green=200.0}
	 }
	 
	 
	 private static Map<String, Long> getVotesPerElectionUnit(Map<String, Map<String, Integer>> votesMap) {
	 return votesMap.entrySet().stream().map(jedinicaMapa -> new SimpleEntry<>(jedinicaMapa.getKey(),
			 jedinicaMapa.getValue().values().stream().mapToLong(s -> s).sum()))
			 .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));

	 }
	 private static Map<String, Double> getAverageVotesPerParty(Map<String, Map<String, Integer>> votesMap) {
	 Map<String, List<Integer>> tempMap = new HashMap<>();
	 //fill tempMap using merge
	 votesMap.values().stream().flatMap(nMap -> nMap.entrySet().stream())
	 .forEach(strankaGlasovi -> tempMap.merge(strankaGlasovi.getKey(),new LinkedList<>(Arrays.asList(strankaGlasovi.getValue())),
			 (staraMapa, novaMapa) -> {
				 staraMapa.addAll(novaMapa);
				 return staraMapa;
			 }));
	 
	 
	 //calculate the result using tempMap
	 return tempMap.
			 entrySet().
			 stream().
			 map(lista -> new SimpleEntry<>(lista.getKey(),
			 lista.getValue().stream().mapToInt(Integer::intValue).average().getAsDouble()))
			 .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
	 }
}
