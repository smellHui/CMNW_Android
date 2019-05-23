package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationdetail.GlideImageLoader;

/**
 * Author:xch
 * Date:2019/5/20
 * Description:
 */
public class ImageSeeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ImageSeeAdapter() {
        super(R.layout.item_image_see);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        new GlideImageLoader().displayImage(mContext, item, helper.getView(R.id.img));
    }
}
