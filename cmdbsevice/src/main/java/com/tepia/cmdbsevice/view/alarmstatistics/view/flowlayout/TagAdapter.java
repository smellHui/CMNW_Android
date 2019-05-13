package com.tepia.cmdbsevice.view.alarmstatistics.view.flowlayout;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class TagAdapter<T> {
    private List<T> mTagDatas;

    public TagAdapter(List<T> datas) {
        mTagDatas = datas;
    }

    @Deprecated
    public TagAdapter(T[] datas) {
        mTagDatas = new ArrayList<T>(Arrays.asList(datas));
    }

    public int getCount() {
        return mTagDatas == null ? 0 : mTagDatas.size();
    }

    public T getItem(int position) {
        return mTagDatas.get(position);
    }

    public abstract View getView(FlowLayout parent, int position, T t);

}
