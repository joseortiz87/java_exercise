package com.company.loggers;

import com.company.crawler.Crawler;

import java.net.URI;

/**
 * Created by buck on 7/7/16.
 */
public class SilentCrawlerLogger extends CrawlerLogger {
	public void enqueue(URI url) {
	}

	public void finalizeCrawl(URI url) {
	}

	public void spawnCrawlingThread(URI url) {
	}

	public void crawlWithHeadRequest(URI url) {
	}

	public void crawlWithGetRequest(URI url) {
	}

	public void noteError(String error) {
	}

	public void setCrawler(Crawler crawler) {
		this.crawler = crawler;
	}
}
