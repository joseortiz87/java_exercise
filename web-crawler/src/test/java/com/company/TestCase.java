package com.company;

public class TestCase {
	protected static <A> void assertEquals(A expected, A result) throws Exception {
		if (!expected.equals(result)) {
			throw new RuntimeException(
					"Assertion error: " + result.toString() + " should equal " + expected.toString());
		}
	}

	protected static <A> void assertNotEquals(A expected, A result) throws Exception {
		if (expected.equals(result)) {
			throw new RuntimeException(
					"Assertion error: " + result.toString() + " should not equal" + expected.toString());
		}
	}
}
