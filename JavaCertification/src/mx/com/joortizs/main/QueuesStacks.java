package mx.com.joortizs.main;

import java.util.*;

public class QueuesStacks {

	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		
		//create the palindriome class object p
		Palindrome p = new Palindrome();
		char arr[] = s.toCharArray();
		
		for(char c : arr){
			p.pushCharacter(c);
		}
		
		for(char c : arr){
			p.enqueueCharacter(c);
		}
		
		boolean f = true;
		
		for(int i=0;i< s.length();i++){
			if(p.popCharacter() != p.dequeueCharacter()){
				f = false;
				break;
			}
		}
		
		if(f){
			System.out.println("True");
		}else{
			System.out.println("False");
		}
		
		sc.close();
	}
	
}

class Palindrome {
    //Write your code here
    private LinkedList<Character> queue = new LinkedList<Character>();
    private Stack<Character> stack = new Stack<Character>();

    public void pushCharacter(char ch){
        this.stack.push(new Character(ch));
    }
    
    public void enqueueCharacter(char ch){
        this.queue.add(new Character(ch));
    }
    
    public char popCharacter(){
    	char res = this.stack.pop().charValue();
        return res;
    }
    
    public char dequeueCharacter(){
        char res = this.queue.poll().charValue(); 
    	return res;
    }
}