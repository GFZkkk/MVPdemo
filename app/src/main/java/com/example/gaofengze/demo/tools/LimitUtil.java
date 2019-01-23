package com.example.gaofengze.demo.tools;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

/**
 * Created by gaofengze on 2019/1/15
 */
public class LimitUtil {
    public static void setEditTextInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(" ") || source.toString().contentEquals("\n")) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }
}
