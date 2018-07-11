package com.company.crawler;

import com.company.lib.urlbuilder.UrlBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.net.*;
import java.io.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlHelper {
	public static String cleanUpHref(String href) {
		return href.replace(" ", "%20");
	}

    public static String absolutizePath(String path, String basePath) {
		if (path.length() == 0) {
			return path;
		}
		// If the path starts with "/", it's absolute. Unless it starts with
		// "/.", in which case it's relative.
		else if ((path.startsWith("/") && !path.startsWith("/."))) {
			return path;
		} else {
			ArrayList<String> sections = new ArrayList<String>(
					Arrays.asList(basePath.replaceAll("/([^/]*)$", "").split("/")));
			sections.addAll(Arrays.asList(path.split("/")));

			ArrayList<String> out = new ArrayList<String>();

			for (String section : sections) {
				if (section.equals(".") || section.equals("")) {
					// do nothing
				} else if (section.equals("..")) {
					out.remove(out.size() - 1);
				} else {
					out.add(section);
				}
			}

			return "/" + HtmlHelper.join(out, "/");
		}
	}

	static ArrayList<URI> getNeighbors(String bodyStr, URI uri, List<String> errors) {
		ArrayList<String> pathsToFollow = getUrlStringsFromDoc(bodyStr);
		return getUrlsFromPage(pathsToFollow, uri, errors);
	}

    static ArrayList<URI> getUrlsFromPage(ArrayList<String> pathsToFollow, URI parentUri, List<String> errors) {

		ArrayList<URI> out = new ArrayList<URI>();

		for (String href : pathsToFollow) {
			href = cleanUpHref(href);
			URI hrefUri;
			try {
				hrefUri = new URI(href);
			} catch (java.net.URISyntaxException e) {
				errors.add("There was an invalid url in " + parentUri.toString() + ": " + href);
				continue;
			}

			if (href.startsWith("mailto")) {
				continue;
			}

			// default scheme is http
			String scheme = hrefUri.getScheme() == null ? "http" : hrefUri.getScheme();

			String path;
			String host;
			Integer port;

			if (hrefUri.getHost() == null) {
				host = parentUri.getHost();
				port = parentUri.getPort() <= 0 ? null : parentUri.getPort();
				path = absolutizePath(hrefUri.getPath(), parentUri.getPath());
			} else {
				host = hrefUri.getHost();
				port = hrefUri.getPort() <= 0 ? null : hrefUri.getPort();
				path = hrefUri.getPath();
			}

			out.add(UrlBuilder.empty().withScheme(scheme).withHost(host).withPort(port).withPath(path)
					.withQuery(hrefUri.getQuery()).toUri());
		}

		return out;
	}

	public static ArrayList<String> getUrlStringsFromDoc(String bodyStr) {
		ArrayList<String> out = new ArrayList<String>();

		Matcher hrefFromATagMatcher = Pattern.compile("<a [^>]*href=\"([^\"]*)").matcher(bodyStr);
		while (hrefFromATagMatcher.find()) {
			out.add(hrefFromATagMatcher.group(1));
		}

		Matcher hrefFromLinkTagMatcher = Pattern.compile("<link [^>]*href=\"([^\"]*)\"").matcher(bodyStr);
		while (hrefFromLinkTagMatcher.find()) {
			out.add(hrefFromLinkTagMatcher.group(1));
		}

		Matcher srcFromScriptTagMatcher = Pattern.compile("<script [^>]*src=\"([^\"]*)\"").matcher(bodyStr);
		while (srcFromScriptTagMatcher.find()) {
			out.add(srcFromScriptTagMatcher.group(1));
		}

		return out;
	}

    static private String join(List<String> list, String conjunction)
    {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String item : list)
        {
            if (first)
                first = false;
            else
                sb.append(conjunction);
            sb.append(item);
        }
        return sb.toString();
    }
}
