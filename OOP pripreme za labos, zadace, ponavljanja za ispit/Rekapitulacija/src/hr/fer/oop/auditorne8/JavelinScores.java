package hr.fer.oop.auditorne8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JavelinScores {
	
	List<Score> scores;
	
	public JavelinScores() {
		scores=new LinkedList();
	}
	
	public void add(String name, Double score){
		Score novi = new Score(name, score);
		int i;
		if(scores.size()==0) {
			scores.add(novi);
		}else {
			for(i=0;i<scores.size();i++) {
				if(score>scores.get(i).getScore()) {
					scores.add(i, novi);
					break;
				}
				
			}
			if(i==scores.size()) {
				scores.add(scores.size(),novi);
			}
		}
	}
	

	public void print() {
		Double veci=0.;
		List<String> imena=new ArrayList();
		for(int i=0; i<scores.size();i++) {
			if(imena.contains(scores.get(i).getName())) {
				if(i==scores.size()-1) {
					break;
				}else {
					i++;
				}
			}
			for(int j=1; j<scores.size();j++) {
				if(scores.get(i).getName().equals(scores.get(j).getName())) {
					if((scores.get(i).getScore().compareTo(scores.get(j).getScore())==-1)){
						veci=scores.get(j).getScore();
					}else {
						veci=scores.get(i).getScore();
					}
				}else {
					veci=scores.get(i).getScore();
				}
			}
			imena.add(scores.get(i).getName());
			System.out.println(scores.get(i).getName() + " "+ veci);
		}

		}
}
