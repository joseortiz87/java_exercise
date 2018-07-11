package com.company.already_passing;

import com.company.TestCase;
import com.company.crawler.Crawler;
import com.company.crawler.HtmlHelper;
import com.company.loggers.VerboseCrawlerLogger;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

public class AlreadyPassingTests extends TestCase {
    static String basePath = "/base/path/";

    public static void testCleanUpHref() throws Exception {
        assertEquals(HtmlHelper.cleanUpHref("http://www.example.com/space url"), "http://www.example.com/space%20url");
    }

    public static void testAbsolutizePathDoesNothingToAbsolutePaths() throws Exception {
        assertEquals("/hello", HtmlHelper.absolutizePath("/hello", basePath));
    }

    public static void testAbsolutizePathAffectsImplicitlyRelativePaths() throws Exception {
        assertEquals(basePath + "hello/what", HtmlHelper.absolutizePath("hello/what", basePath));
    }

    public static void testAbsolutizePathAffectsExplicitlyRelativePaths() throws Exception {
        assertEquals(basePath + "hello/what", HtmlHelper.absolutizePath("./hello/what", basePath));
    }

    public static void testAbsolutizePathHandlesDotDot() throws Exception {
        assertEquals("/base/hello/what", HtmlHelper.absolutizePath("../hello/what", basePath));
    }

    public static void testAbsolutizePathHandlesDotDotDotDot() throws Exception {
        assertEquals("/hello/what", HtmlHelper.absolutizePath("../../hello/what", basePath));
    }

    static String testCaseHtml = "<!DOCTYPE html>\n" + "<html>\n" + "  <body>\n" + "    <h1>Test Case 1</h1>\n" + "\n"
            + "    <p>I am a paragraph! <a href=\"javascript:doThing\">blah</a></p>\n" + "\n"
            + "    <p>Sometimes I am <a href=\"./cynical.html\">overly cynical</a>, but sometimes I am\n"
            + "      <a href=\"./page2.html\">overly na&#xEFve.</a></p>\n" + "  </body>\n" + "</html>";

    public static void testGetUrlStringsFromDoc() throws Exception {
        ArrayList<String> result = HtmlHelper.getUrlStringsFromDoc(testCaseHtml);
        assertEquals(result, Arrays.asList("javascript:doThing", "./cynical.html", "./page2.html"));
    }

    public static void main(String[] args) throws Exception {
        testCleanUpHref();
        testAbsolutizePathDoesNothingToAbsolutePaths();
        testAbsolutizePathAffectsImplicitlyRelativePaths();
        testAbsolutizePathAffectsExplicitlyRelativePaths();
        testAbsolutizePathHandlesDotDot();
        testAbsolutizePathHandlesDotDotDotDot();
        testGetUrlStringsFromDoc();
        testCrawl();
        System.out.println("Everything worked!");
    }

	public static void testCrawl() throws Exception {
		Crawler crawler = new Crawler(10, new VerboseCrawlerLogger());
		crawler.crawl("https://www.triplebyte.com", false);
		assertNotEquals(crawler.graph.parents(new URI("http://www.triplebyte.com/careers")).size(), 0);
	}
}
