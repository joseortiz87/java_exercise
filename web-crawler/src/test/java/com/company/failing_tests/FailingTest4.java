package com.company.failing_tests;

import com.company.crawler.Crawler;
import com.company.crawler.WebsiteGraph;
import com.company.loggers.VerboseCrawlerLogger;
import com.company.TestCase;

public class FailingTest4 extends TestCase {
	public static void main(String args[]) throws Exception {
		Crawler crawler = new Crawler(10, new VerboseCrawlerLogger());
		crawler.crawl("http://triplebyte.github.io/web-crawler-test-site/test4/", true);

		assertEquals(WebsiteGraph.PageNodeStatus.SUCCESS,
				crawler.graph.get("http://triplebyte.github.io/web-crawler-test-site/test4/page3").pageNodeStatus);

        System.out.println("Everything passed! Exceptional!");
	}
}
