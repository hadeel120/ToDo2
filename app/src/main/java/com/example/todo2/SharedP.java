package com.example.todo2;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SharedP {
    private static final String TASK_PREFS = "task_prefs";
    private static final String TASK_LIST_KEY = "task_list";

    public static void saveTasks(Context context, List<Task> taskList) {
        SharedPreferences prefs = context.getSharedPreferences(TASK_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(taskList);
        editor.putString(TASK_LIST_KEY, json);
        editor.apply();
    }

    public static List<Task> getTasks(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(TASK_PREFS, Context.MODE_PRIVATE);
        String json = prefs.getString(TASK_LIST_KEY, "");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Task>>(){}.getType();
        return gson.fromJson(json, type);
    }
}
