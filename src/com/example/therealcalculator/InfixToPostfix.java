package com.example.therealcalculator;

import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;


/**
 * InfixToPostFix has the capability of turning Operations in the infix notation to postfix
 * @author Tulio Troncoso
 *
 */
public class InfixToPostfix {
	private Stack<String> stack;
	private ArrayList<String> result;
	private TreeMap<String, Integer> operatorMap;
	private ArrayList<String> infix;
	
	public InfixToPostfix(){
		stack 	= new Stack<String>();
		result 	= new ArrayList<String>();
		infix 	= new ArrayList<String>();
		operatorMap = new TreeMap<String, Integer>();
		
		operatorMap.put(")", 0);
		operatorMap.put("(", 1);
		operatorMap.put("x", 2);
		operatorMap.put("/", 2);
		operatorMap.put("+", 3);
		operatorMap.put("-", 3);
				
	}
	
	public void pushMany(String arg[]){
		for(String e: arg){
			push(e);
		}
	}
	
	/**
	 * Pushes an item, 'arg', to the stack.
	 * @param arg
	 */
	public void push(String arg){
		int precedenceThis = getPrecedence(arg);
		infix.add(arg);
		if(precedenceThis == -1){ //its a number
			result.add(arg);
		}
		else{ //it is an operator
			
			while(!stack.isEmpty()){
				if(arg.equals("(")){
					stack.push(arg);
					System.out.println(arg + " pushed");
					return;
				}
				
				if(arg.equals(")")){
					while(!stack.peek().equals("(") || stack.isEmpty()){
						if(stack.isEmpty())
							break;
						pop();
					};
					pop();
					
					return;
				}
				
				if(getPrecedence( stack.peek() ) > getPrecedence(arg))
					break;
				
				if(getPrecedence( stack.peek() ) <= getPrecedence(arg) ){
					pop();
					break;
				}
				
			}
			stack.push(arg);
			System.out.println(arg + " pushed");

		}
	}
	
	private void pop(){
		String popped = stack.pop();
		if(!popped.equals(")") && !popped.equals("("))
			result.add(popped);
		System.err.println("\t" + popped + " popped");
	}
	
	/**
	 * @return An ArrayList holding the Operation in postfix
	 */
	public ArrayList<String> getPostfix(){
		finalizePostfix();
		return result;
	}
	
	public void finalizePostfix(){
		while(!stack.isEmpty()){
			System.out.println("Finalize is popping!!");
			pop();
		}
	}
	
	public String getPostfixString(){
		//finalizePostfix();
		String str ="";
		for(String e: result){
			str += " " + e + " ";
		}
		
		return str;
	}
	
	public String getInfixString(){
		String str ="";
		for(String e:infix){
			str += " " + e;
		}
		return str;
	}
	/**
	 * @param str
	 * @return precedence of operator, -1 if str is not an argument in the list
	 */
	private int getPrecedence(String str){
		if(operatorMap.containsKey(str))
			return (int)operatorMap.get(str);
		else
			return -1;
	}

}
