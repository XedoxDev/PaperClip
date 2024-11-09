package com.xedox.paperclip.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XDoc {

    public static String pageExample = "<page1>Hi, i am page1</page1>";

    public static Map<String, String> parse(String source) {
        Map<String, String> pages = new HashMap<>();
        Pattern pattern = Pattern.compile("<([^>]+)>(.*?)</\\1>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            pages.put(matcher.group(1), matcher.group(2).trim()); 
        }
        return pages;
    }

    public static String connect(Map<String, String> pages) {
        StringBuilder buffer = new StringBuilder();
        for (String name : pages.keySet()) {
            buffer.append(String.format("<%s>%s</%s>\n", name, pages.get(name), name));
        }
        return buffer.toString();
    }
}
