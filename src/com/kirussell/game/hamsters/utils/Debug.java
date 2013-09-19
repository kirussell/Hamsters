package com.kirussell.game.hamsters.utils;

import android.util.Log;

/**
 * Class for debug logs
 */
public class Debug {
    private static final String TAG = "Hamsters";

    public static void error(Exception ex) {
        Log.e(TAG, ex.toString());
    }

    public static void error(String msg) {
        Log.e(TAG, msg);
    }
}
