package com.company.loggers;

import com.company.crawler.Crawler;
import java.net.URI;

/**
 * Created by buck on 7/7/16.
 */
public class VerboseCrawlerLogger extends CrawlerLogger {
	public void enqueue(URI url) {
		System.out.println("url enqueued: " + url);
	}

	public void finalizeCrawl(URI url) {
		System.out.println("finalize crawl: " + url);
	}

	public void spawnCrawlingThread(URI url) {
		System.out.println("spawning a crawler to look at: " + url);
	}

	public void crawlWithHeadRequest(URI url) {
		System.out.println("crawling with HEAD request: " + url);
	}

	public void crawlWithGetRequest(URI url) {
		System.out.println("crawling with GET request: " + url);
	}

	public void noteError(String error) {
		System.out.println("error! " + error);
	}

	public void setCrawler(Crawler crawler) {
		this.crawler = crawler;
	}
}
