package com.tdd.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.tdd.HackThread;
import com.tdd.RotationRequest;

@Service
public class TddService {
	
	public String leftRotation(RotationRequest request){
		if(request.getLength() == 0 || request.getArray() == null 
				|| request.getArray().length == 0 || request.getRotation() < 0){
			return "empty";
		}
		if(request.getRotation() == 0 || request.getRotation()%request.getLength() == 0){
			return arrayToString(request.getArray());
		}
		int [] newArray = new int[request.getArray().length];
		for(int i=0;i<request.getLength();i++){
			int newIndex = (i + (request.getLength() - request.getRotation())) % request.getLength();
			newArray[newIndex] = request.getArray()[i];
		}
		return arrayToString(newArray);
	}
	
	private String arrayToString(int [] array){
		StringBuilder str = new StringBuilder();
		int i = 0;
		for(int item : array){
			if(i == 0){
				str.append(item);
			}else{
				str.append(" ");
				str.append(item);
			}
			i++;
		}
		return str.toString();
	}
	
	public void executeHack(int pool,int loop){
		ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < loop; i++) {
          Runnable worker = new HackThread(i + "");
          executor.execute(worker);
        }
	}
	
}
