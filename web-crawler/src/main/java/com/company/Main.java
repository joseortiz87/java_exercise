package com.company;

import com.company.lib.HttpRequest;

/**
 * Created by buck on 7/11/16.
 */
public class Main {
	public static void main(String[] args) {
		System.out.println("Hello");
		System.out.println(HttpRequest.get("http://www.google.com").code());
	}
}
