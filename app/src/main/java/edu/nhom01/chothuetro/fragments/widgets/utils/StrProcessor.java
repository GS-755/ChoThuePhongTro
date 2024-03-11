package edu.nhom01.chothuetro.fragments.widgets.utils;

import android.util.Log;
import java.text.DecimalFormat;
import java.util.HashMap;

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
    public static String getStrChineseDate(int year, int month, int day) {
        return String.format("%d-%d-%d", year, month, day);
    }
}
