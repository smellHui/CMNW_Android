package com.tepia.cmnwsevice.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmnwsevice.R;
import com.tepia.photo_picker.utils.AndroidLifecycleUtils;

import java.io.File;
import java.util.List;

/**
 * Author:xch
 * Date:2019/4/10
 * Do:
 */
public class OrderPhotoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public OrderPhotoAdapter(@Nullable List<String> data) {
        super(R.layout.picker_item_photo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView img = helper.getView(R.id.iv_photo);
        helper.setVisible(R.id.v_selected, false);
        Uri uri = Uri.fromFile(new File(item));
        boolean canLoadImage = AndroidLifecycleUtils.canLoadImage(mContext);
        if (canLoadImage) {
            Glide.with(mContext)
                    .load(uri)
                    .into(img);
        }
    }
}
