package com.amazon.aws.jordan;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Snigdha {

	public static void main(String arg[]){
		Result result = JUnitCore.runClasses(SnigdhaTestJunit.class);
		for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	    }
	    System.out.println("Time:" + result.getRunTime() +  
	    		" - successful:" + result.wasSuccessful());
	}
	
	public class PlaneVector{
		private int magnification;
		private char direction;
		public PlaneVector(int magnification, char direction) {
			super();
			this.magnification = magnification;
			this.direction = direction;
		}
		public int getMagnification() {
			return magnification;
		}
		public char getDirection() {
			return direction;
		}
	}
	
	public String movePlane(String command) {
		    final String invalid = "(999, 999)"; //invalid command
		    if(command == null || command.length() == 0)
		    	return invalid;
		    long posX =0;
		    long posY =0;
	        int endCommandPointer = 0;
	        java.util.LinkedList<PlaneVector> moves = new java.util.LinkedList<PlaneVector>();
	        StringBuilder tmpCommand = new StringBuilder();
	        //Decoding the command string into single commands O(n)
	        while(endCommandPointer<command.length()){
	            char tmpChar = command.charAt(endCommandPointer);
	            if(tmpChar >= 48 && tmpChar <= 57){
	                tmpCommand.append(tmpChar);
	            }else if(tmpChar == 'U' || tmpChar == 'D' || tmpChar == 'L' || tmpChar == 'R'){
	            	int magnification = 1;
	            	//We have a magnifier!
	            	if(tmpCommand.length() > 0){
	            		try{
	            			magnification = Integer.parseInt(tmpCommand.toString());
	            		}catch(Exception e){
	            			
	            		}
	            	}
	            	//Decode maginifier into simple plane moves
	            	switch(tmpChar){
	            		case 'U' : posY += magnification;
	            				   break;
	            		case 'D' : posY += -1*magnification;
     				   				break;
	            		case 'L' : posX += -1*magnification;
     				   				break;
	            		case 'R' : posX += magnification;
     				   				break;
	            		default : break;
	            	}
	            	moves.add(new PlaneVector(magnification,tmpChar));
	                tmpCommand = new StringBuilder();
	            }else if(tmpChar == 'X'){
	            	//Delete last instruction if any
	                if(!moves.isEmpty()){
	                	PlaneVector tmpPlaneVector = moves.removeLast();
	                	switch(tmpPlaneVector.getDirection()){
		            		case 'U' : posY += -1*tmpPlaneVector.getMagnification();
		            				   break;
		            		case 'D' : posY += tmpPlaneVector.getMagnification();
	     				   				break;
		            		case 'L' : posX += tmpPlaneVector.getMagnification();
	     				   				break;
		            		case 'R' : posX += -1*tmpPlaneVector.getMagnification();
	     				   				break;
	     				   	default : break;
	                	}
	                }
	            }else{
	                return invalid; //invalid command
	            }
	            endCommandPointer++;
	        }
	        
	        //IF ONLY NUMBERS OR END IN NUMBER IS INCOMPLETE
	        if(tmpCommand.length() > 0)
	        	return invalid;
	        
	        return "(" + posX + ", " + posY + ")";
	    }
}
