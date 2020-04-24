package com.example.mlayouthelper;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ViewHelper {

    public static void selectViewsByName(@NonNull Activity activity, @NonNull java.util.Collection<String> names,
                                         @NonNull Delegate.Action2<View, String> action){
        String packageName = activity.getPackageName();
        String defType = "id";
        for(String name:names) {
            int id = activity.getResources().getIdentifier(name, defType, packageName);
            if (id == 0) continue;
            View view = activity.findViewById(id);
            action.invoke(view, name);
        }
    }

    public static void selectViewsByField(@NonNull Activity activity, @NonNull java.util.Collection<Field> fields,
                                          @NonNull Delegate.Action2<View, Field> action){
        String packageName = activity.getPackageName();
        String defType = "id";
        for(Field field:fields) {
            int id = activity.getResources().getIdentifier(field.getName(), defType, packageName);
            if (id == 0) continue;
            View view = activity.findViewById(id);
            action.invoke(view, field);
        }
    }
    /**
     * 从活动视图中获取与类字段同名的对象,类需要拥有无参数构造函数
     * @param activity 活动
     * @param type 类类型
     * @return 类对象
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static Object getClassByActivityValues(@NonNull Activity activity, @NonNull Class<?> type)
            throws InstantiationException, IllegalAccessException {
        final Object obj = type.newInstance();
        Set<Field> fields = getFields(type);
        selectViewsByField(activity, fields, new Delegate.Action2<View, Field>() {
            @Override
            public void invoke(View view, Field field) {
                try {
                    Object value = getViewValue(view,field.getType());
                    if(value!=null){
                        boolean accessFlag = field.isAccessible();
                        if(!accessFlag)
                            field.setAccessible(true);
                        field.set(obj, value);
                        field.setAccessible(accessFlag);
                    }
                }catch (ClassCastException | IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        });
        return obj;
    }
    /**
     * 获取类中所有字段及父类公共字段
     * @param type 类类型
     * @return 字段集
     */
    private static Set<Field> getFields(@NonNull Class<?> type) {
        Set<Field> fields = new LinkedHashSet<>();
        Collections.addAll(fields, type.getDeclaredFields());
        Collections.addAll(fields,type.getFields());
        return fields;
    }

    /**
     * 尝试从视图中获取数据
     * @param view 视图
     * @param declaringClass 数据类型
     * @return 数据对象
     */
    public static Object getViewValue(View view, @NonNull Class<?> declaringClass) {
        Object value = null;
        if(view instanceof CheckedTextView)
            value = ((CheckedTextView)view).isChecked();
        else if(view instanceof CheckBox)
            value = ((CheckBox)view).isChecked();
        else if(view instanceof TextView)
            value = ((TextView)view).getText().toString();
        else if(view instanceof ProgressBar){
            value = ((ProgressBar)view).getProgress();
        }
        if (value != null) {
            return Convert.changeType(value, declaringClass);
        }
        return null;
    }

    /**
     * 将对象赋值给与对象字段同名的视图中
     * @param activity 活动
     * @param obj 数据对象
     * @throws IllegalAccessException
     */
    public static void setActivityValuesByClass(@NonNull Activity activity, @NonNull final Object obj) throws IllegalAccessException {
        Set<Field> fields = getFields(obj.getClass());
        selectViewsByField(activity, fields, new Delegate.Action2<View, Field>() {
            @Override
            public void invoke(View view, Field field) {
                boolean accessFlag = field.isAccessible();
                if(!accessFlag)
                    field.setAccessible(true);
                Object value = null;
                try {
                    value = field.get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                field.setAccessible(accessFlag);
                setViewValue(view, value);
            }
        });
    }
    public static <TValue> void setViewValuesByMap(@NonNull Activity activity, @NonNull final Map<String,TValue> nameValue){
        selectViewsByName(activity, nameValue.keySet(), new Delegate.Action2<View, String>() {
            @Override
            public void invoke(View view, String name) {
                setViewValue(view, nameValue.get(name));
            }
        });
    }

    /**
     * 将对象赋值给视图
     * @param view 视图
     * @param value 对象
     * @return 成功与否
     */
    public static Boolean setViewValue(View view,@NonNull Object value) {
        if(view instanceof CheckedTextView)
            ((CheckedTextView)view).setChecked(Convert.booleanValue(value));
        else if(view instanceof CheckBox)
            ((CheckBox)view).setChecked(Convert.booleanValue(value));
        else if(view instanceof TextView)
            ((TextView)view).setText(value.toString());
        else if(view instanceof ProgressBar)
            ((ProgressBar)view).setProgress(Convert.intValue(value));
        else
            return false;
        return true;
    }
}
