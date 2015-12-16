package com.rdt.coin.coin.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.EditText;

public class InputUtils {

    public static String getPoints(Activity activity, int editTextResId, int defaultValue) {
        String points = getEditTextContent((EditText) activity.findViewById(editTextResId));
        return !TextUtils.isEmpty(points) && TextUtils.isDigitsOnly(points) ? points : String.valueOf(defaultValue);
    }

    public static String getEditTextContent(EditText editText) {
        return editText.getText().toString().trim();
    }
}
