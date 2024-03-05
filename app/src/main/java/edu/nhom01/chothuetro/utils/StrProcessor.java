package edu.nhom01.chothuetro.utils;

import android.util.Log;

import java.util.HashMap;

public class StrProcessor {
    public static HashMap<String, String> trimFullName(String fullName) {
        HashMap<String, String> splitedName = new HashMap<>();
        try {
            String tmp = fullName.trim();
            String[] finalTrim = tmp.split(" ");
            splitedName.put("first_name", String.
                    format("%s %s", finalTrim[0], finalTrim[1]));
            splitedName.put("last_name", finalTrim[2]);
        }
        catch(NullPointerException ex) {
            Log.d("INT_ERR", ex.getMessage());
        }

        return splitedName;
    }
}
