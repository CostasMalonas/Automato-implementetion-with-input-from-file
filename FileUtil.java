package dfa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Class FileUtil to read information from file
 * numOfStates int: The number of states
 * actions String[]: The number of actions
 * startingState int: The starting state 
 * finalStates int[]: The final states
 */
public class FileUtil {
	
	private int numOfStates = 0;
	private String[] actions;
	private int startingState = -1;
	private String[] finalStates;
	
	/*Take the ArrayList with string array automato will read*/
	public ArrayList<String[]> getMoves() {
		File file = new File("input.txt");
		ArrayList<String[]> runs = new ArrayList<String[]>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st; 
			while ((st = br.readLine()) != null) {
				runs.add(st.split(" ")); //Store in runs symbols automato will read separately
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return runs;
	}

	public ArrayList<Rule> createAllRules() {
		File file = new File("dfa.txt");
	
		ArrayList<Rule> rules = new ArrayList<Rule>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			int cnt = 0;
			while ((st = br.readLine()) != null) {
				if (0 == cnt) {
					numOfStates = st.charAt(0) - 48; //Get int type of number of states
				}
				else if (1 == cnt) {
					actions = st.split(" "); //Alphabet
				}
				else if (2 == cnt) {
					startingState = st.charAt(0) - 48; //Get int type of starting state
				} else if (3 == cnt) {
					finalStates = st.split(" "); //Get final states
				} else {
					String[] str = st.split(" ");
					if (str.length == 3) {
						rules.add(new Rule(str)); //Fill rules ArrayList
					}
				}
				cnt++;
		  	}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return rules;
	}

	public int getNumOfStates() {
		return numOfStates;
	}

	public void setNumOfStates(int numOfStates) {
		this.numOfStates = numOfStates;
	}

	public String[] getActions() {
		return actions;
	}

	public void setActions(String[] actions) {
		this.actions = actions;
	}

	public int getStartingState() {
		return startingState;
	}

	public void setStartingState(int startingState) {
		this.startingState = startingState;
	}

	public String[] getFinalStates() {
		return finalStates;
	}
}
