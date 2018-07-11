package com.company.loggers;

import com.company.crawler.Crawler;
import java.net.URI;

/**
 * Created by buck on 7/7/16.
 */
public abstract class CrawlerLogger {
	Crawler crawler;

	abstract public void enqueue(URI url);

	abstract public void finalizeCrawl(URI url);

	abstract public void spawnCrawlingThread(URI url);

	abstract public void crawlWithHeadRequest(URI url);

	abstract public void crawlWithGetRequest(URI url);

	abstract public void noteError(String error);

	abstract public void setCrawler(Crawler crawler);
}
