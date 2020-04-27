package com.flycloud.andsetvier;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HtmlManager {

    public void init() throws IOException {
        createMainIndex(PathManager.getWebMainIndex());
    }

    protected void createMainIndex(String path) throws IOException {
        String html="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "\t<title>测试网页</title>\n" +
                "\t<link rel=\"stylesheet\" href=\"\">\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<div id=\"id\">\n" +
                "\t\t这是id为“id”的标签内容\n" +
                "\t</div>\n" +
                "\t<p>这是p标签的内容</p>\n" +
                "\t<a href=\"http://blog.beifengtz.com\" title=\"beifengtz's blog\">beifeng blog</a>\n" +
                "\t<div class=\"father\">\n" +
                "\t\t<div class=\"children\">\n" +
                "\t\t\t这是div的子元素\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "\t<div class=\"father\">\n" +
                "\t\t<div class=\"children\">\n" +
                "\t\t\t<a href=\"http://www.sicau.edu.cn\">四川农业大学</a>\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "</body>\n" +
                "</html>";
        FileWriter writer = new FileWriter(path,false);
        writer.write(html);
        writer.flush();
        writer.close();
    }
}
