package com.company.crawler;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

public class WebsiteGraph {
	public enum PageNodeStatus {
		NONE, ENQUEUED, SUCCESS, FAILURE
	}

	public class PageNode {
		URI url;
		public RequestType requestType;
		public PageNodeStatus pageNodeStatus = PageNodeStatus.NONE;
		String error;
		Integer responseCode;

		PageNode(URI url) {
			this.url = url;
		}

		@Override
		public String toString() {
			return "PageNode<" + url + ", " + requestType + ", " + requestType + ">";
		}

		void setRequestType(RequestType requestType) {
			this.requestType = requestType;
		}

		void setPageNodeStatus(PageNodeStatus pageNodeStatus) {
			this.pageNodeStatus = pageNodeStatus;
		}

		void setError(String error) {
			this.error = error;
		}

		void setResponseCode(Integer responseCode) {
			this.responseCode = responseCode;
		}
	}

	HashMap<URI, PageNode> nodes = new HashMap<URI, PageNode>();
	private HashMap<URI, ArrayList<PageNode>> outgoingLinks = new HashMap<URI, ArrayList<PageNode>>();
	private HashMap<URI, ArrayList<PageNode>> incomingLinks = new HashMap<URI, ArrayList<PageNode>>();

	void addNode(URI url) {
		if (!nodes.containsKey(url)) {
			nodes.put(url, new PageNode(url));
		}
	}

	public PageNode get(URI url) {
		PageNode node = nodes.get(url);
		if (node == null) {
			addNode(url);
			return nodes.get(url);
		} else {
			return node;
		}
	}

	public PageNode get(String urlString) throws Exception {
		return get(new URI(urlString));
	}

	public void printPages() {
		System.out.println("Pages: ");
		for (URI uri : nodes.keySet()) {
			System.out.println(uri);
		}
	}

	void addNeighbor(URI fromUrl, URI toUrl) {
		ArrayList<PageNode> arr = outgoingLinks.containsKey(fromUrl) ? outgoingLinks.get(fromUrl)
				: new ArrayList<PageNode>();
		arr.add(nodes.get(toUrl));

		outgoingLinks.put(fromUrl, arr);

		ArrayList<PageNode> arr2 = incomingLinks.containsKey(fromUrl) ? incomingLinks.get(fromUrl)
				: new ArrayList<PageNode>();
		arr2.add(nodes.get(fromUrl));
		incomingLinks.put(toUrl, arr2);
	}

	public ArrayList<PageNode> parents(URI url) {
		return incomingLinks.containsKey(url) ? incomingLinks.get(url) : new ArrayList<PageNode>();
	}
}
