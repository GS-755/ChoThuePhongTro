package edu.nhom01.chothuetro.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StrProcessor {
    public static HashMap<String, String> splitFullName(String fullName) {
        HashMap<String, String> splitedName = new HashMap<>();
        try {
            String firstName = "";
            String[] trimmedNameArray = fullName.trim().split(" ");
            int arrLength = trimmedNameArray.length;
            for(int i = 0; i < arrLength - 1; i++) {
                firstName += String.format("%s ", trimmedNameArray[i]);
            }
            splitedName.put("first-name", firstName.trim());
            splitedName.put("last-name", trimmedNameArray[arrLength - 1]);
        }
        catch(NullPointerException ex) {
            Log.d("INT_ERR", ex.getMessage());
        }

        return splitedName;
    }
    public static String formatVnCurrency(double number) {
        String pattern = "###,###.###";
        DecimalFormat df = new DecimalFormat(pattern);

        return df.format(number) + " đ";
    }
    public static String formatArea(double area) {
        return String.format("%.1f", area);
    }
    public static String getStrChineseDate(int year, int month, int day) {
        return String.format("%d-%d-%d", year, month, day);
    }
    public static Map<String, String> splitQueryParams(URL url)
            throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int index = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, index),
                    "UTF-8"), URLDecoder.decode(pair.substring(index + 1), "UTF-8"));
        }

        return query_pairs;
    }
    public static String splitUrlHostAndParams(String url) {
        String[] splitedUrl = url.split("\'?\'");

        return splitedUrl[1];
    }
}
