package com.flycloud.andsetvier;

import android.util.Log;

import com.yanzhenjie.andserver.annotation.Controller;
import com.yanzhenjie.andserver.annotation.GetMapping;
import com.yanzhenjie.andserver.annotation.PostMapping;
import com.yanzhenjie.andserver.annotation.RequestParam;
import com.yanzhenjie.andserver.annotation.ResponseBody;
import com.yanzhenjie.andserver.http.HttpRequest;
import com.yanzhenjie.andserver.http.HttpResponse;
import com.yanzhenjie.andserver.http.RequestBody;

@Controller
public class MainController {

    @GetMapping(value = "/index")
    public String index(){
        return PathManager.getRelativeWebMainIndex();
    }

    @GetMapping("/")
    public String main(){
        return "redirect:/index";
    }

    @GetMapping("/test")
    public String test(){ return PathManager.getRelativeTest(); }

    @PostMapping("/test")
    public String test(@RequestParam("names") String name){
        Log.d("name", name);
        return PathManager.getRelativeTest();
    }

    @ResponseBody
    @GetMapping("/project/info")
    public String newInfo() {
        return "I am new api.";
    }

    @GetMapping("/projectInfo")
    public String oldInfo() {
        return "forward:/project/info";
    }
}
