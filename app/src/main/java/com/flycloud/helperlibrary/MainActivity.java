package com.flycloud.helperlibrary;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.flycloud.andsetvier.ServerManager;
import com.flycloud.utils.SystemInfo;
import com.flycloud.mlayouthelper.BindUtils;
import com.flycloud.mlayouthelper.BindView;
import com.flycloud.utils.Delegate;
import com.flycloud.mlayouthelper.ViewHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @BindView(id = R.id.helloEdit)
    private View hello;
    private ServerManager andServer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String logTag = "输出";
        TextView helloText = findViewById(R.id.helloText);
        int view_id = getResources().getIdentifier("helloEdit", "id", getPackageName());
        EditText helloEdit = findViewById(view_id);
        helloEdit.setText("233");
        BindUtils.initBindView(this);
        Log.d(logTag, String.valueOf(hello instanceof EditText));
        Log.d(logTag, ViewHelper.getViewValue(hello,int.class).toString());
        helloEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("before",s.toString()+" start:"+start+" count:"+count+" after:"+after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("changed",s.toString()+" start:"+start+" before:"+before+" count:"+count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("after",s.toString());
            }
        });
        Log.d(logTag, helloText.getText().toString());
        TestValue value = new TestValue();
        value.setHelloEdit(SystemInfo.getFirstLocalIp4Address().getHostAddress());
        value.setHelloText("How are you?");
        value.editText = "I'm fine!";
        try {
            ViewHelper.setActivityValuesByClass(this, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        TestValue result;
        try {
            result = (TestValue) ViewHelper.getClassByActivityValues(this,TestValue.class);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        List<String> names = new ArrayList<String>(){
            {
                add("helloText");
                add("helloEdit");
                add("editText");
                add("editText2");
                add("aaa");
            }
        };
        ViewHelper.selectViewsByName(this, names, new Delegate.Action2<View, String>() {
            @Override
            public void invoke(View view, String name) {
                try {
                    Object value = ViewHelper.getViewValue(view, Object.class);
                    if (value != null) {
                        Log.d(name, value.toString());
                    }else {
                        Log.d(name, "value is null");
                    }
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }
            }
        });
        Map<String, Object> map = new LinkedHashMap();
        map.put("editText2","me too");
        ViewHelper.setViewValuesByMap(this, map);
        andServer = new ServerManager(getApplicationContext(), 8080);
        andServer.startServer();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected  void onPause(){
        super.onPause();
        Log.d("onPause","isPause");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        andServer.stopServer();
        Log.d("onDestroy","is Destroy");
    }
}
