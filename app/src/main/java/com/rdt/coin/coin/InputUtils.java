package com.rdt.coin.coin;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.EditText;

public class InputUtils {

    public static String getPoints(Activity activity, int editTextResId, int defaultValue) {
        EditText editText = (EditText) activity.findViewById(editTextResId);
        String points = editText.getText().toString().trim();
        return !TextUtils.isEmpty(points) && TextUtils.isDigitsOnly(points) ? points : String.valueOf(defaultValue);
    }
}
