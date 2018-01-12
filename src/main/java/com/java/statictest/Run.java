package com.java.statictest;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.jboss.netty.util.internal.StringUtil;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dufeng on 2017/2/15.
 */
public class Run {
    public static void main(String...args){
        String content = "公婆HK哦监控<video src=\"http://video.wenwen.sogou.com/1370bd87cee9938d6573323a2e1cb0f7.mp4\" poster=\"http://p.qpic.cn/wenwenpic/0/20170331105110-1882108417\"></video>";
        System.out.println(content);
        System.out.println(url2Href(content));
    }

    public static void genSql(){
        regex();
    }

    public static void regex(){
        Pattern p = Pattern.compile("(?<!(src=\"|appid=\\d{0,10}&url=|originSrc=\"|href=\"))((https?)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|])");
        String text1 = "href=\"http://test.ld.sogou.com/cate?cid=40280&ch=lds.sy.recd.entry.pc\" target=\"_blank\">略懂网址";
        System.out.println(text1);
        Matcher matcher = p.matcher(text1);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()) {
            String url = matcher.group(2);
            System.out.println(url);
            matcher.appendReplacement(sb, "<a href=\"" + url + "\">" + url + "</a>");
        }
        matcher.appendTail(sb);
        System.out.println(sb.toString());
    }

    /**
     * url 可点击逻辑
     * @param content
     * @return
     */
    public static final String URL_TO_HREF_REGEX =  "(?<!(src=\"|appid=\\d{0,10}&url=|originSrc=\"|href=\"))((https?)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|])";

    public static String url2Href(String content){
        Pattern p = Pattern.compile(URL_TO_HREF_REGEX);
        Matcher matcher = p.matcher(content);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()) {
            String url = matcher.group(2);
            String hrefUrl = String.format("<a class=\"hyper-link\"  href=\"%s\" target=\"_blank\">网页链接</a>", url);
            matcher.appendReplacement(sb, hrefUrl);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
