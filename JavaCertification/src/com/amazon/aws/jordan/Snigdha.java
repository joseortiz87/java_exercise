package com.amazon.aws.jordan;


public class Snigdha {

	public static void main(String arg[]){
		System.out.println(movePlane("UDLL"));
		System.out.println(movePlane("8D2L"));
		System.out.println(movePlane("4D2RX"));
		System.out.println(movePlane("8D2L1RX1R"));
		System.out.println(movePlane("XXX"));
		System.out.println(movePlane("UK"));
		System.out.println(movePlane("883434"));
		System.out.println(movePlane("3450L3450R"));
		System.out.println(movePlane("9999D9999U"));
		System.out.println(movePlane(null));
		System.out.println(movePlane(""));
		System.out.println(movePlane("8D2L1RXXX"));
	}
	
	   static String movePlane(String command) {
		    final String invalid = "(999, 999)"; //invalid command
		    if(command == null || command.length() == 0)
		    	return invalid;
		    long posX =0;
		    long posY =0;
		    int lastMagnification = 0;
		    char lastDirection = 'N';
	        int endCommandPointer = 0;
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
	            	lastDirection = tmpChar;
	            	lastMagnification = magnification;
	                tmpCommand = new StringBuilder();
	            }else if(tmpChar == 'X'){
	            	//Delete last instruction if any
	                if(lastMagnification > 0 && lastDirection != 'N' ){
	                	switch(lastDirection){
		            		case 'U' : posY += -1*lastMagnification;
		            				   break;
		            		case 'D' : posY += lastMagnification;
	     				   				break;
		            		case 'L' : posX += lastMagnification;
	     				   				break;
		            		case 'R' : posX += -1*lastMagnification;
	     				   				break;
	     				   	default : break;
	                	}
	                	//RESET DELETE
	                	lastMagnification = 0;
	                	lastDirection = 'N';
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
