package mx.com.joortizs.main;

import java.util.*;
/*

Given an string, write a function which replace all the emoji sequences with the corresponding unicode character.

Emoji examples:
    :) => U+1F603
    (: => U+1F603
    XD => U+1F606
    :'( => U+1F622
    :(:) => U+1F437

":(:" => carita triste + ":"

Input: "Hello :)"
-----------------
|H|e|l|l|o| |:|)|
-----------------

Output: "Hello U+1F603"
---------------------
|H|e|l|l|o| |U+1F603|
---------------------

**You can assume that your programming language support Unicode when using primitive char data type.

What to consider?
- Solve the problem
- Cover all cases
- Code performance

*/


public class Emoji{

    private static HashMap<String,Character> EMOJI_STORE = new HashMap<String,Character>();
    private static Integer emojiMaxLength = 4;
    private static Integer emojiMinLength = 2;
    
    static{
        EMOJI_STORE.put(":)",new Character((char)0x1F603));
        EMOJI_STORE.put("(:",new Character((char)0x1F603));
        EMOJI_STORE.put(":-)",new Character((char)0x1F603));
        EMOJI_STORE.put("XD",new Character((char)0x1F606));
        EMOJI_STORE.put(":'(",new Character((char)0x1F622));
        EMOJI_STORE.put(":(:)",new Character((char)0x1F437));
    }
    
    public String replaceEmoji(String str){
        if(str == null || str.length() == 0){
            return null;
        }
        
        if(str.length() < emojiMinLength){
            return str;
        }
        
        //PARTO DEL SUBSTRING MAS GRANDE Y VOY DECREMENTANDO AL MAS PEQUEÑO
        for(int length=emojiMaxLength;length>=emojiMinLength;length--){
            for(int pivot=0;pivot<=str.length()-length;pivot++){
                String tmpSubStr = str.substring(pivot,pivot+length);
                if(EMOJI_STORE.containsKey(tmpSubStr)){
                	String unicode = EMOJI_STORE.get(tmpSubStr).toString();
                    str = str.replace(tmpSubStr,unicode);
                }
            }
        }
        return str;
    }
    
    /*
    TEST CASES:
    
    (1) replaceEmoji(null) > null
    (2) replaceEmoji("") > null
    (3) replaceEmoji("null null :)") > "null null U+1F603"
    (4) replaceEmoji("null null .. .") > "null null .. ."
    (5) replaceEmoji(":)") > U+1F603
    (6) replaceEmoji(":-)") > U+1F603
    (7) replaceEmoji(":):-)") > U+1F603U+1F603
    (8) replaceEmoji(n) n > 2000 
    (9) replaceEmoji("l") > "l"
    
    */
    
    public static void main(String args[]){
    	Emoji emoji = new Emoji();
    	System.out.println(new String(emoji.replaceEmoji("Hello :(:)").getBytes()));
    }
}
