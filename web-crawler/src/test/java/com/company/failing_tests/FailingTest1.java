package com.company.failing_tests;

import com.company.crawler.Crawler;
import com.company.crawler.RequestType;
import com.company.loggers.VerboseCrawlerLogger;
import com.company.TestCase;

public class FailingTest1 extends TestCase {
	public static void main(String args[]) throws Exception {
		Crawler crawler = new Crawler(10, new VerboseCrawlerLogger());
		crawler.crawl("http://triplebyte.github.io/web-crawler-test-site/test1/", false);

		assertEquals(RequestType.HEAD,
				crawler.graph.get("http://triplebyte.github.io/web-crawler-test-site/test1/SVG_logo.svg").requestType);

        System.out.println("Everything passed! Great job!");
	}
}
