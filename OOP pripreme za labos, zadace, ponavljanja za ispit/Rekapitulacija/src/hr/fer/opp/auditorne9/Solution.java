package hr.fer.opp.auditorne9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	public static List<List<String>> groupAnagrams(String[] s){
		List<List<String>> list = new ArrayList();
		List<String> temp  = new ArrayList();
		int t=0;
		for(int i=0;i<s.length;i++) {
			temp.clear();
			t=0;
			for(int z=0;z<list.size();z++) {
				List<String> x  = list.get(z);	
				if(x.contains(s[i])) {
					t = 1;
					break;
				}
			}			
			if(t!=1) {
			char[] temp1= s[i].toCharArray();
			Arrays.sort(temp1);
			temp.add(s[i]);
			for(int j=0;j<s.length-1;j++) {
				if(i==j) continue;
				char[] temp2 = s[j].toCharArray();
				Arrays.sort(temp2);
				if(Arrays.equals(temp1, temp2)) {
					temp.add(s[j]);
				}				
			}
			list.add(List.copyOf(temp));
			}
		}
		
		return list;
	}	
}
