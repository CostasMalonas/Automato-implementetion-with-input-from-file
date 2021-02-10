package dfa;

public class Rule {
	private char currentState;
	private String transition;
	private char finalState;
	
	
	public Rule() {
		System.out.println("default constructor");
	}
	
	public Rule(String[] currentRule) {
		if(currentRule.length == 3 ) {
			this.currentState = currentRule[0].charAt(0); //State that we are
			this.setTransition(currentRule[1]); //Symbol that we are reading
			this.finalState = currentRule[2].charAt(0); //State that we will be
		}
	}


	public char getCurrentState() {
		return currentState;
	}


	public void setCurrentState(char currentState) {
		this.currentState = currentState;
	}


	public String getTransition() {
		return transition;
	}


	public void setTransition(String transition) {
		this.transition = transition;
	}


	public char getFinalState() {
		return finalState;
	}


	public void setFinalState(char finalState) {
		this.finalState = finalState;
	}
	
	
	public String toString() {
		return currentState +  " " + transition + " " + finalState;
	}
}
