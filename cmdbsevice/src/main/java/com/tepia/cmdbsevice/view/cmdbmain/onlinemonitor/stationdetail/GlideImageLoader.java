package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationdetail;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/26
 * Time            :       11:09
 * Version         :       1.0
 * 功能描述        :
 **/
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        Glide.with(context).load(path).into(imageView);
    }
}

