package com.remotetask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class UrlValidator {

    public static final String INVALID = "INVALID";
    public static final String VALID = "VALID";

    public static final Predicate<String> LOWER_CASE_CHECK = str -> !str.chars().anyMatch(chars -> Character.isUpperCase(chars));

    public static final Predicate<String> SIZE_CHECK = str -> str.length() >= 8;

    public static final Predicate<String> LETTER_NUMBER_CHECK = str -> str.chars().allMatch(chars -> Character.isLetterOrDigit(chars));

    public static final Predicate<String> CSRF_CHECK = str -> SIZE_CHECK.test(str) && LOWER_CASE_CHECK.test(str) && LETTER_NUMBER_CHECK.test(str);


    public static void main(String arg []) {
        List<String> validaAccessTokens = Arrays.asList("abscghd2","h23jbsslakjh3");
        List<List<String>> urls = new ArrayList<>();
        urls.add(Arrays.asList("GET","http://google.com?token=hj&name=jose&lastname=other"));
        urls.add(Arrays.asList("POST","http://google.com?token=abscghd2&name=jose&lastname=other"));
        urls.add(Arrays.asList("POST","http://google.com?token=abscghd2&name=jose&lastname=other&csrf=123+ab"));

        urls.add(Arrays.asList("GET","http://google.com?token=abscghd2&name=jose&lastname=other"));
        urls.add(Arrays.asList("POST","http://google.com?token=h23jbsslakjh3&name=jose&lastname=other&csrf=abcdfghijk"));
        System.out.println(LETTER_NUMBER_CHECK.test("123abcd56fghijk"));
        System.out.println(LOWER_CASE_CHECK.test("123abcd56fghijk"));
        System.out.println(validateURL(validaAccessTokens,urls));
    }

    public static List<String> validateURL(List<String> validaAccessTokens, List<List<String>> urls){
        List<String> requestResponse = new ArrayList<>();
        if(urls == null || urls.isEmpty()) return requestResponse;
        Set<String> tokenSet = new HashSet<>(validaAccessTokens);
        for(List<String> urlData : urls){
            String method = urlData.get(0);
            String url = urlData.get(1);
            Map<String,String> params = gerUrlParams(url);
            if(!params.containsKey("token")){
                requestResponse.add(INVALID);
                continue;
            }
            if(!tokenSet.contains(params.get("token"))){
                requestResponse.add(INVALID);
                continue;
            }
            if("POST".equals(method)){
                if(!params.containsKey("csrf")) {
                    requestResponse.add(INVALID);
                    continue;
                }
                if(!CSRF_CHECK.test(params.get("csrf"))){
                    requestResponse.add(INVALID);
                    continue;
                }
            }
            if(params.containsKey("other")){
                requestResponse.add(String.format("%s,%s",VALID,params.get("other")));
            }else{
                requestResponse.add(VALID);
            }
        }
        return requestResponse;
    }

    public static Map<String,String> gerUrlParams(String url){
        if(url == null || url.isEmpty()) return Collections.emptyMap();
        int paramIndex = url.indexOf("?");
        if(paramIndex == -1 || paramIndex+1 >= url.length()) return Collections.emptyMap();
        String urlParamsPart = url.substring(paramIndex+1);
        String[] params = urlParamsPart.split("&");
        if(params == null || params.length == 0) return Collections.emptyMap();
        Map<String,String> paramsMap = new HashMap<>();
        Arrays.stream(params).forEach(param -> {
            String[] paramData = param.split("=");
            if(paramData != null && paramData.length > 1){
                String name = paramData[0];
                String value = paramData[1];
                if("token".equals(name) || "csrf".equals(name)){
                    paramsMap.put(name,value);
                }else{
                    String currentVal = paramsMap.getOrDefault("other",new String());
                    StringBuilder newVal = new StringBuilder();
                    if(currentVal.length() > 0) newVal.append(String.format("%s,",currentVal));
                    newVal.append(String.format("%s,%s",name,value));
                    paramsMap.put("other",newVal.toString());
                }
            }
        });
        return paramsMap;
    }
}
