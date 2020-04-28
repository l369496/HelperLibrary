package com.flycloud.andsetvier;

import android.content.Context;
import android.os.Environment;
import android.os.FileUtils;

import com.yanzhenjie.andserver.util.IOUtils;

import java.io.File;

public class PathManager {

    private static String rootDir = null;
    private static String main = "/main";
    private static String index = "/index";
    private static String suffix = ".html";

    public static void setRootDir(Context context){
        rootDir = context.getExternalFilesDir(null).toString();
    }

    public static String getRootDir() {
        return rootDir;
    }

    public static String getWebDir() {
        File file = new File(rootDir, "web");
        if(!file.exists())
            file.mkdirs();
        return file.getAbsolutePath();
    }

    public static String mkWebChildDirs(String childPath){
        File file = new File(getWebDir(), childPath);
        if(!file.exists())
            file.mkdirs();
        return file.getAbsolutePath();
    }

    public static String getWebMainIndex() { return mkWebChildDirs(main) + index + suffix; }

    public static String getRelativeWebMainIndex(){
        return main + index;
    }

    public static String getTest(){ return mkWebChildDirs("test") + "/test.html"; }

    public static String getRelativeTest() { return "/test/test"; }
}
