package com.tepia.cmnwsevice.view.common;

import android.view.View;

/**
 * @author : liying (from Center Of Wuhan)
 * 创建时间 : 2018-11-22
 * 更新时间 :
 * Version : 1.0
 * 功能描述 :
 */
public interface OnItemClickListener {
    /**
     * 当RecyclerView某个被点击的时候回调
     *
     * @param view     点击item的视图
     * @param position 点击得到的数据
     */
    void onItemClick(View view, int position);
}
