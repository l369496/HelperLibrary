package com.flycloud.andsetvier;

import com.yanzhenjie.andserver.annotation.Controller;
import com.yanzhenjie.andserver.annotation.GetMapping;
import com.yanzhenjie.andserver.annotation.ResponseBody;

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
