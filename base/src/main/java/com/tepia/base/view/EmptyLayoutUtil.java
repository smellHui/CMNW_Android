package com.tepia.base.view;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tepia.base.R;
import com.tepia.base.utils.Utils;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-10-19
 * Time            :       11:59
 * Version         :       1.0
 * 功能描述        :
 **/
public class EmptyLayoutUtil {
    public static View getView(String msg){
        View view = LayoutInflater.from(Utils.getContext()).inflate(R.layout.view_empty_list_view,null);
        TextView tv_empty_view_text = view.findViewById(R.id.tv_empty_view_text);
        tv_empty_view_text.setGravity(Gravity.CENTER);
        tv_empty_view_text.setText(msg);
        return view;
    }
}
