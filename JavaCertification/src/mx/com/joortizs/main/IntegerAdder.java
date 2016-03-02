package mx.com.joortizs.main;

import java.util.*;

/*
Write a method that implements an integer adder. Given two arrays of integers where each position in the array contains an integer <= 9, return an array with the result of the addition of the two.

-> add([1,0], [5]) => [1,5]
-> add([1], [9,9,9]) => [1,0,0,0]
-> add([9],[9]) => [1,8]
*/

public class IntegerAdder{
	
	public Integer[] multiAdder(Integer[]...arrays){
		LinkedList<Integer> sumList = new LinkedList<Integer>();
		int pointer = 0;
		int carrier = 0;
		while(true){
			int sum = 0;
			int negativePointers = 0;
			
			//SUM POSITIONS FOR ALL ARRAYS
			for(Integer[] array : arrays){
				int currentPointer = array.length-1-pointer;
				if(currentPointer >= 0){
					sum += array[currentPointer];
				}else{
					sum += 0;
					negativePointers++;
				}
			}
			
			// CARRIER
            // case: num1=9, num2=9, carrier=0  => 9+9 = 18  carrier=1; sum = 8
            if(sum+carrier > 9){
            	sumList.addFirst((sum+carrier)%10);
                carrier = (sum+carrier)/10;
            }else{
            	if(!(carrier == 0 && negativePointers == arrays.length)){
            		sumList.addFirst(sum+carrier);
                    carrier = 0;
            	}
            }
            
            //BREAK IF ALL ARRAYS ARE OUT OF INDEX AND NO CARRIER TO SUM IN NEXT POSITION
            if(carrier == 0 && negativePointers == arrays.length){
            	break;
            }
			
			pointer++;
		}
		return sumList.toArray(new Integer[sumList.size()]);
	}

    public Integer[] adder(Integer[] numArray1,Integer[] numArray2){
    	return multiAdder(numArray1,numArray2);
    }

    public void printArray(Integer[] number){
    	System.out.println("Numero : ");
    	for(Integer num : number){
    		System.out.print(num);
    	}
    }
    
    public static void main(String args[]){
    	IntegerAdder integerAdder = new IntegerAdder();
    	Integer[] num1 = new Integer[]{1,0};
    	Integer[] num2 = new Integer[]{0};
    	Integer[] num3 = new Integer[]{5};
    	integerAdder.printArray(integerAdder.multiAdder(num1,num2,num3));
    }
    
}
