package com.megaflashgames.moneynotebook.ui.service;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanyamihova on 05/05/2015.
 */
public class FontsService {
    private static final String TAG = FontsService.class.getSimpleName();

    private static android.support.v4.util.LruCache<String, Typeface> sTypeFaceCache = new android.support.v4.util.LruCache<String, Typeface>(4);
    private static List<String> sAssets = new ArrayList<String>();

    private FontsService() {
    }

    public static Typeface getFontByName(Context context, String fontName) {
        Typeface typeface = sTypeFaceCache.get(fontName);

        if (sAssets.size() == 0) {
            sAssets.addAll(getAssets(context));
        }

        if (typeface == null) {

            String filePath = String.format("fonts/%s.otf", fontName);
            if (hasInAssets(String.format("%s.otf", fontName))) {
                typeface = Typeface.createFromAsset(context.getAssets(), filePath);
            }

            if (typeface == null) {
                filePath = String.format("fonts/%s.ttf", fontName);
                if (hasInAssets(String.format("%s.ttf", fontName))) {
                    typeface = Typeface.createFromAsset(context.getAssets(), filePath);
                }
            }

            if (typeface == null) {
                Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
            }
            // Cache the Typeface object
            sTypeFaceCache.put(fontName, typeface);
        }

        return typeface;
    }

    private static List<String> getAssets(Context context) {
        List<String> assets = new ArrayList<String>();

        try {
            String[] filesList;

            filesList = context.getAssets().list("fonts");
            if (filesList != null) {
                for (int i = 0; i < filesList.length; i++) {
                    assets.add(filesList[i]);
                }
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return assets;
    }

    private static boolean hasInAssets(String name) {
        return sAssets.contains(name);
    }
}

