package com.flycloud.andsetvier;

import com.yanzhenjie.andserver.annotation.GetMapping;
import com.yanzhenjie.andserver.annotation.PathVariable;
import com.yanzhenjie.andserver.annotation.PostMapping;
import com.yanzhenjie.andserver.annotation.QueryParam;
import com.yanzhenjie.andserver.annotation.RequestMapping;
import com.yanzhenjie.andserver.annotation.RequestParam;
import com.yanzhenjie.andserver.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @PostMapping("/login")
    String login(@RequestParam("account") String account,
        @RequestParam("password") String password){
        if("123".equals(account) && "123".equals(password)) {
            return "Login successful.";
        } else {
            return "Login failed.";
        }
    }
    @GetMapping("/login")
    String login(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<br>\n" +
                "First name:<br>\n" +
                "<input type=\"text\" name=\"firstname\" value=\"Mickey\">\n" +
                "<br>\n" +
                "Last name:<br>\n" +
                "<input type=\"text\" name=\"lastname\" value=\"Mouse\">\n" +
                "<br><br>\n" +
                "<input type=\"submit\" value=\"Submit\">\n" +
                "</br> \n" +
                "\n" +
                "<p>如果您点击提交，表单数据会被发送到名为 demo_form.asp 的页面。</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
    @GetMapping("/get")
    String login(@RequestParam(value = "id", required = false, defaultValue = "123") String id) {
        return String.valueOf(id)+" AndServer";
    }
    @GetMapping("/get/{id}")
    String idInfo(@PathVariable("id") String id){
        return String.valueOf(id)+" AndServer";
    }
    @GetMapping("/info")
    String info(@QueryParam(name = "id") long id) {
        return String.valueOf(id)+" AndServer";
    }
}
