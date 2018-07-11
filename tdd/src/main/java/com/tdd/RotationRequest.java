package com.tdd;

public class RotationRequest {

	private int length;
	private int rotation;
	private int[] array;
	public RotationRequest(){}
	public RotationRequest(int length, int rotation, int[] array) {
		super();
		this.length = length;
		this.rotation = rotation;
		this.array = array;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getRotation() {
		return rotation;
	}
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	public int[] getArray() {
		return array;
	}
	public void setArray(int[] array) {
		this.array = array;
	}
}
