package edu.nhom01.chothuetro.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.net.URL;

import edu.nhom01.chothuetro.api.routes.IApiRoutes;

public class DisplayImage {
    public static String getMotelImageUrl(int motelId) {
        String imageUrl = String.format("%s%d", IApiRoutes.IMG_URL, motelId);

        return imageUrl;
    }
    public static Bitmap getMotelImageBitmap(String imageUrl) {
        try {

            URL url = new URL(imageUrl);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            return bitmap;
        } catch (Exception ex) {
            Log.e("API_ERR", ex.getMessage());
        }

        return null;
    }
}