package mx.com.joortizs.main;

import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int str_length = in.nextInt();
        in.nextLine();
        String word = in.nextLine();
        int rotation = in.nextInt();
        StringBuilder encrypt = new StringBuilder();
        for(int i=0;i<str_length;i++){
            char letter = word.charAt(i);
            encrypt.append(encryptChar(letter,rotation));
        }
        System.out.println(encrypt.toString());
        in.close();
    }
    
    public static char encryptChar(char letter,int key){
        if((letter >= 65 && letter <= 90) || (letter >= 97 && letter <= 122)) {
            //lettras
            char min_val = 97; //minusculas
            char max_val = 122;
            if(letter >= 65 && letter <= 90){
                //mayusculas
                min_val = 65;
                max_val = 90;   
            }
            int standar_key = key%(max_val-min_val);
            if(max_val-(letter+standar_key) < 0){
            	return ((char)(min_val+letter+standar_key-max_val-1));
            }else{
                return (char)(letter+standar_key);
            }
        }else{
            return letter;
        }
    }
}
