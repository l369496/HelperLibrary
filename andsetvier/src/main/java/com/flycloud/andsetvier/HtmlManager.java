package com.flycloud.andsetvier;

import java.io.FileWriter;
import java.io.IOException;

public class HtmlManager {

    public void init() throws IOException {
        createMainIndex(PathManager.getWebMainIndex());
        createTest(PathManager.getTest());
    }

    private void createTest(String path) throws IOException {
        writeText(path,"<form action=\"\">\n" +
                "    用户名：<input type=\"text\" name=\"username\" id=\"user\">\n" +
                "    <!-- 限定密码格式只能是数字 -->\n" +
                "    密 码： <input type=\"text\" name=\"pwd\" id=\"pwd\" pattern=\"\\d+\">\n" +
                "    <input type=\"submit\">\n" +
                "</form>\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "    var user = document.getElementById('user');\n" +
                "    var pwd = document.getElementById('pwd');\n" +
                "    \n" +
                "    // 用户输入的时候触发\n" +
                "    user.oninput = function(){\n" +
                "        this.setCustomValidity(\"已输入\"+user);\n" +
                "    }\n" +
                "    // 验证无法通过的时候触发\n" +
                "    pwd.oninvalid = function(){\n" +
                "        this.setCustomValidity(\"密码格式错误\");\n" +
                "    }\n" +
                "</script>");
    }

    protected void createMainIndex(String path) throws IOException {
        writeText(path,"<!DOCTYPE html>\n" +
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
                "</html>");
    }

    protected void writeText(String path, String html) throws IOException {
        FileWriter writer = new FileWriter(path,false);
        writer.write(html);
        writer.flush();
        writer.close();
    }
}
