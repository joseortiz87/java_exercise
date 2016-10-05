package com.amazon.aws.jordan;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class SnigdhaTestJunit {
	
	@Rule
	public TestRule watcher = new TestWatcher() {
		@Override
		public void starting(Description description) {
	    	System.out.println("Starting test: " + description.getMethodName());
	    }
	 };

	@Test
	public void testMovePlane(){
		Snigdha snigdha = new Snigdha();
		assertEquals("(-2, 0)",snigdha.movePlane("UDLL"));
		assertEquals("(-2, -8)",snigdha.movePlane("8D2L"));
		assertEquals("(0, -4)",snigdha.movePlane("4D2RX"));
		assertEquals("(-1, -8)",snigdha.movePlane("8D2L1RX1R"));
		assertEquals("(0, 0)",snigdha.movePlane("XXX"));
		assertEquals("(999, 999)",snigdha.movePlane("UK"));
		assertEquals("(999, 999)",snigdha.movePlane("883434"));
		assertEquals("(0, 0)",snigdha.movePlane("3450L3450R"));
		assertEquals("(0, 0)",snigdha.movePlane("9999D9999U"));
		assertEquals("(999, 999)",snigdha.movePlane(null));
		assertEquals("(999, 999)",snigdha.movePlane(""));
		assertEquals("(0, 0)",snigdha.movePlane("8D2L1RXXXX"));
	}
}
