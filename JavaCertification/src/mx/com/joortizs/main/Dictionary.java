package mx.com.joortizs.main;

import java.util.*;

public class Dictionary {
	   public static void main(String []argh)
	   {
	      Scanner in = new Scanner(System.in);
	      int n=in.nextInt();
	      in.nextLine();
	      HashMap<String,Integer> diccionary = new HashMap<String,Integer>();
	      for(int i=0;i<n;i++)
	      {
	         String name=in.nextLine();
	         int phone=Integer.parseInt(in.nextLine());
	         diccionary.put(name,new Integer(phone));
	      }
	      while(in.hasNext())
	      {
	         String s=in.nextLine();
	         if(diccionary.containsKey(s)){
	             System.out.println(s + "=" + diccionary.get(s));
	         }else{
	             System.out.println("Not found");
	         }
	      }
	      in.close();
	   }
}
