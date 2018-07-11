package com.tdd;

import org.assertj.core.util.Objects;

public class Message {
	private String message;

	public Message(){}
	
	public Message(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == this){
			return true;
		}
		if(obj != null && obj.getClass().getName().equals(this.getClass().getName())){
			if(((Message)obj).getMessage().equals(this.getMessage())){
				return true;
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hashCodeFor(message);
	}
}
