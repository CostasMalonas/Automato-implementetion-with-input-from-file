package dfa;

import java.io.BufferedReader; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



/*
 * Author: Konstantinos Malonas
 * Version: 5
 * Application file.
 * 1. Read contents from file
 * 2. Get states
 * 3. Get actions
 * 4. Get starting state
 * 5. Get final state
 * 6. Create state machine
 * 7. Traverse through the state machine until a final state has been reached 
 */
public class RunAutomato {
	  
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
		FileUtil input = new FileUtil();  //Create and instantiate a FileUtil object to read from file
		ArrayList<Rule> rules = input.createAllRules(); //Get rules
		ArrayList<String[]> runs = input.getMoves(); //Get all content of input.txt and save each line as a separate element inside ArrayList runs. Each line is split at  " " and the substrings are saved in runs 

		int numOfStates = input.getNumOfStates(); //Number of states
		String[] actions = input.getActions(); //Actions we are allowed to perform
		String[] finalStates = input.getFinalStates(); //Final states
		int currentState = input.getStartingState(); //Current state we are
		
		AutomataMap aum; //AutomataMap object declaration
		int mode = -1;
		do {
			System.out.print("Manual mode || File mode [0/1]: "); //Choose if you want to give symbols manually or from file
			mode = sc.nextInt();
		} while((mode != 1) && (mode != 0));
		if (0 == mode) {
			aum= new AutomataMap(rules, numOfStates, actions, currentState, finalStates); //Instantiation of aum 
			while (true) {
				String currentActions = aum.getPossibleActions(currentState, rules, numOfStates, actions); //Take the actions that we can do from our currentState 
	        	System.out.print("State:   " + currentState + "\t|Action:  " + currentActions + "\n:: ");
	        	currentState = aum.updateCurrentState(sc.next(), currentActions, currentState); //Get action from keyboard and update currentState to the new state
	        	//Check if we are on a final state
	        	if (aum.isFinalState(currentState)) {
	        		int decision = -1;
	        		do {
	        			System.out.print("Reached final state " + currentState + ". Continue: [Yes == 0 / No == 1] "); //Ask user if he wants to continue giving symbols
	        			decision = sc.nextInt();
	        		} while((decision != 1) && (decision != 0));
	        		if (1 == decision) {
	        			System.out.println("Exit program");
	        			break;
	        		}
	        	}
			}
			//Read input string from input.txt
		} else if (1 == mode) {
			ArrayList<AutomataMap> aList;  //Declare an ArrayList for AutomataMap type objects   
			aList = new ArrayList<AutomataMap>(); //Instantiate the ArrayList aList
			/*Test the line from runs*/
			for (String[] e : runs) {
				int tempCurrentState = input.getStartingState(); //Get starting state
				ArrayList<Rule> tempRules = input.createAllRules(); //Get rules
				int tempNumOfStates = input.getNumOfStates(); //Get number of states
				String[] tempActions = input.getActions(); //Get allowed actions
				aList.add(new AutomataMap(tempRules, tempNumOfStates, tempActions, tempCurrentState, finalStates)); //Create a new AutomataMap object and add it to aList
				/*Get each element(action) of line*/
				for (String st : e) {
					//System.out.print(st + " ");
					String currentActions = aList.get(0).getPossibleActions(tempCurrentState, tempRules, tempNumOfStates, tempActions); //Get all the possible actions for our currentState
		        	System.out.print("State:   " + tempCurrentState + "\t|Possible actions:  " + currentActions + 
		        			"\t|Action read from file: " + st + "\n");
		        	tempCurrentState = aList.get(0).updateCurrentState(st, currentActions, tempCurrentState); //Update our currentState
				}
				if (aList.get(0).isFinalState(tempCurrentState)) {
					System.out.println("Input finished. Last state is " + tempCurrentState + " and it is  a final state (Success).");
				} else {
					System.out.println("Input finished. Last state is " + tempCurrentState + " and it is not a final state (Failure).");
				}
				aList.remove(0); //Remove the element from ArrayList at position 0
				System.out.println();
			}
		}	
    }
}
