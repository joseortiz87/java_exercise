package com.company.crawler;

import com.company.lib.HttpRequest;
import com.company.loggers.CrawlerLogger;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import com.company.lib.urlbuilder.UrlBuilder;

public class Crawler {
	private int numberOfRunningThreads = 0;
	private int maxThreads;
	private ConcurrentLinkedQueue<URI> queue;
	private Set<URI> currentlyBeingExplored = new HashSet<URI>();
	public WebsiteGraph graph = new WebsiteGraph();
	private ArrayList<String> errors;
	private URI domain;
	private CrawlerLogger logger;

	public Crawler(int maxThreads, CrawlerLogger logger) {
		logger.setCrawler(this);
		this.logger = logger;
		this.maxThreads = maxThreads;

		// I have to do this globally for some reason.
		HttpURLConnection.setFollowRedirects(true);
		queue = new ConcurrentLinkedQueue<URI>();
		errors = new ArrayList<String>();
	}

	// todo: add output file
	public WebsiteGraph crawl(String initialUrlString, boolean displayResults) {
		if (!initialUrlString.startsWith("http")) {
			initialUrlString = "http://" + initialUrlString;
		}

		URI initialUri;
		try {
			initialUri = new URI(initialUrlString);
		} catch (URISyntaxException e) {
			System.out.println("Invalid URI");
			return new WebsiteGraph();
		}

		domain = UrlBuilder.fromUri(initialUri).withPath("").withQuery("").withFragment("").toUri();

		// this.mainThread = Thread.currentThread;

		enqueue(initialUri);

		while (!queue.isEmpty() || !currentlyBeingExplored.isEmpty()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// continue while loop
			}
		}

		if (displayResults) {
			System.out.println("\n\ndone");

			if (errors.isEmpty()) {
				System.out.println("No errors found!");
			} else {
				System.out.println("Here are all the complaints found:");
				for (String error : errors) {
					System.out.println(error);
				}
			}
		}

		return graph;
	}

	private synchronized void enqueue(URI uri) {
		if (graph.nodes.containsKey(uri)) {
			return;
		}

		graph.addNode(uri);
		graph.get(uri).setPageNodeStatus(WebsiteGraph.PageNodeStatus.ENQUEUED);
		logger.enqueue(uri);

		if (numberOfRunningThreads < maxThreads) {
			numberOfRunningThreads += 1;
			spawnCrawlingThread(uri);
		} else {
			queue.add(uri);
		}
	}

	private synchronized void finalizeCrawl(URI uri) {
		logger.finalizeCrawl(uri);
		currentlyBeingExplored.remove(uri);

		if (queue.isEmpty()) {
			numberOfRunningThreads -= 1;
		} else {
			spawnCrawlingThread(queue.poll());
		}
	}

	private synchronized void spawnCrawlingThread(URI uri) {
		logger.spawnCrawlingThread(uri);
		currentlyBeingExplored.add(uri);

		(new CrawlerThread(uri, this)).start();
	}

	private class CrawlerThread extends Thread {
		URI uri;
		Crawler crawler;

		CrawlerThread(URI uri, Crawler crawler) {
			this.uri = uri;
			this.crawler = crawler;
		}

		public void run() {
			if (crawler.uriShouldBeCrawledAsNode(uri)) {
				crawler.crawlWithGetRequest(uri);
			} else {
				crawler.crawlWithHeadRequest(uri);
			}
		}
	}

	private void crawlWithGetRequest(URI uri) {
		logger.crawlWithGetRequest(uri);
		WebsiteGraph.PageNode node = graph.nodes.get(uri);

		node.setRequestType(RequestType.GET);
		HttpRequest request;

		try {
			int hash= uri.toString().indexOf('#');
			if(hash > -1){
				new URI (uri.toString().substring(0, hash));
			}
			request = HttpRequest.get(uri.toURL()).connectTimeout(10000);
		} catch (Exception e) {
			node.setPageNodeStatus(WebsiteGraph.PageNodeStatus.FAILURE);
			node.setError(e.toString());
			finalizeCrawl(uri);
			return;
		}

		node.setResponseCode(request.code());
		node.setPageNodeStatus(WebsiteGraph.PageNodeStatus.SUCCESS);

		if (node.responseCode >= 400) {
			noteError("When crawling " + uri + " got a " + node.responseCode + " (linked from "
					+ graph.parents(node.url) + ")");
		} else {
			if (request.header("Content-Type").startsWith("text")) {
				for (URI neighborUri : HtmlHelper.getNeighbors(request.body(), uri, errors)) {
					graph.addNeighbor(uri, neighborUri);
					System.out.println("adding " + neighborUri + " from " + uri);
					enqueue(neighborUri);
				}
			}
		}

		finalizeCrawl(uri);

	}

	private void crawlWithHeadRequest(URI uri) {
		logger.crawlWithHeadRequest(uri);
		WebsiteGraph.PageNode node = graph.nodes.get(uri);

		node.setRequestType(RequestType.HEAD);

		try {
			HttpRequest request = HttpRequest.head(uri.toURL()).connectTimeout(10000);

			node.setResponseCode(request.code());
		} catch (Exception e) {
			node.setPageNodeStatus(WebsiteGraph.PageNodeStatus.FAILURE);
			node.setError(e.toString());
            System.out.println("Error! While crawling " + uri.toString() + ", got " + e.toString());
			return;
		}

		node.setPageNodeStatus(WebsiteGraph.PageNodeStatus.SUCCESS);

		if (node.responseCode >= 400) {
			noteError("When crawling " + uri + " got a " + node.responseCode + " (linked from "
					+ graph.parents(node.url) + ")");
		}

		finalizeCrawl(uri);
	}

	private boolean uriShouldBeCrawledAsNode(URI uri) {
		if (!domain.getHost().equals(uri.getHost())) {
			return false;
		}

		// prevent you from starting to crawl FTP if you're looking at HTTP
		if (!domain.getScheme().equals(uri.getScheme())) {
			return false;
		}

		List<String> filetypeList = Arrays.asList("pdf", "jpg", "gif", "js", "css", "png","svg");

		int i = uri.getPath().lastIndexOf('.');
		if (i > 0) {
			String extension = uri.getPath().substring(i + 1);

			if (filetypeList.contains(extension)) {
				return false;
			}
		}

		return true;
	}

	private synchronized void noteError(String error) {
		errors.add(error);
	}
}
