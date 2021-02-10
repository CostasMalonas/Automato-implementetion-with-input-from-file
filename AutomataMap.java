package dfa;

import java.util.ArrayList;

public class AutomataMap {
	
	private ArrayList<Rule> rules;;
	private String[] actions;
	private int numOfStates;
	private int startingState;
	private String[] finalStates;

	public AutomataMap(ArrayList<Rule> rules, int numOfStates, String[] actions, int startingState, String[] finalStates) {
		this.rules = rules;
		this.actions = actions;
		this.numOfStates = numOfStates;
		this.startingState = startingState;
		this.finalStates = finalStates;
	}
	
	/*
	 * Check if we are at a final state
	 */
	public boolean isFinalState(int currentState) {
		for(int i = 0; i < finalStates.length; i++)
		{
			if (currentState == (finalStates[i].charAt(0) - 48))
			{
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Rule> getRules() {
		return rules;
	}

	public void setRules(ArrayList<Rule> rules) {
		this.rules = rules;
	}

	public String[] getActions() {
		return actions;
	}

	public void setActions(String[] actions) {
		this.actions = actions;
	}

	public int getNumOfStates() {
		return numOfStates;
	}

	public void setNumOfStates(int numOfStates) {
		this.numOfStates = numOfStates;
	}

	public int getStartingState() {
		return startingState;
	}

	public void setStartingState(int startingState) {
		this.startingState = startingState;
	}

	/*
	 * Method that gets the current state and returns the actions we are allowed to do in a string
	 *  for the application file to print
	 */
	public String getPossibleActions(int currentState, ArrayList<Rule> rules, int numOfStates, String[] actions) {
		String actionsForCurrentState = "";
		for (int i = 0; i < numOfStates; i++) {
			//Check in what state we are in
			if (currentState == i) {
		        for(Rule rule : rules) {
		        	//Check if there is a rule for the currentState we are
		        	if (rule.getCurrentState() -48 == currentState) {
		        		actionsForCurrentState += rule.getTransition() + " "; //Add symbols we are allowed to give for the state we are in to actionsForCurrentState string
		        	}
		        }
			}
		}
		return actionsForCurrentState;
	}

	public int updateCurrentState(String actionSelected, String currentActions, int currentState) {
		String action = actionSelected; //Store symbol that automato read in String action
		for (int i = 0; i < actions.length; i++) {
			//If action == with the actions we are allowed to perform continue
			if (action.equals(actions[i])) {

				for (Rule rule : rules) {
					//Find the rule for our current state
					if ((rule.getCurrentState() - 48 == currentState)) {
						//Find the rule with the transition (middle column) that is equal with the action
						if (rule.getTransition().equals(action)) {
							currentState = rule.getFinalState() - 48; //Update current state to the final state of the rule
							break;
						}
					}
				}
			}
		}
		return currentState;
	}

}
