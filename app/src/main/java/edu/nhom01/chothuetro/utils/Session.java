package edu.nhom01.chothuetro.utils;

import java.util.HashMap;

public class Session {
    private static HashMap<String, Object> data = new HashMap<>();

    public static void put(String key, Object value) {
        data.put(key, value);
    }
    public static Object get(String key) {
        return data.get(key);
    }
    public static void clear() {
        data.clear();
    }
    public static void remove(String key) {
        data.remove(key);
    }
}
