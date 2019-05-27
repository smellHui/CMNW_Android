package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.adapter.ImageSeeAdapter;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.util.GridSpacingItemDecoration;
import com.tepia.cmnwsevice.view.main.views.ViewBase;

import java.util.ArrayList;
import java.util.List;

import cc.shinichi.library.ImagePreview;

/**
 * Author:xch
 * Date:2019/5/20
 * Description:
 */
public class ImageListView extends ViewBase {

    private RecyclerView mRecyclerView;
    private ImageSeeAdapter imageSeeAdapter;
    private List<String> imageList;

    public ImageListView(Context context) {
        super(context);
    }

    public ImageListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getViewId() {
        return R.layout.view_image_list;
    }

    @Override
    public void initData() {
        mRecyclerView = findViewById(R.id.rv);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 50, false));
        imageSeeAdapter = new ImageSeeAdapter();
        mRecyclerView.setAdapter(imageSeeAdapter);


         imageList = new ArrayList<>();
//        imageList.add(
//                "http://img2.weishoot.com/?4583B5ECE38DC1B27FD1269F4E70B7670C426883D16355F21B46193F75071C599A3E6AED54A235489F4C38835361546D7648065651316476F7E30592DAF1CD6C50358835D5DD10D029DA4A9C59B56C3EA48055CE4E0A7627AF8C90303A4D1FBC2D88218AB0F699E74425A61E5D9E2B378E199A2906934C52DAC2D3920C74DBAF34BD945EEA7E86AC72AF12CD00F1179846E1DA2926B782C1D2215DB087EFDE04A1C0F46B1352527F");
//        imageList.add(
//                "https://sacasnap.neusoft.com/snap-engine-file/image/obtain/1264c125-387e-4af0-a361-f26c3f7fd12e?tenantId=neusoft");
//        imageList.add(
//                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544679916731&di=89e3775c25c5f21254cd0a5aa3b0b1b1&imgtype=0&src=http%3A%2F%2Fimage.biaobaiju.com%2Fuploads%2F20180210%2F20%2F1518266167-ActRaEkWDS.jpg");
//        imageList.add("http://s1.dwstatic.com/group1/M00/82/DB/c9d0b4c9fdba07709071784bce709c26.gif");
//        imageList.add("https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg");
//        imageList.add("http://cache.house.sina.com.cn/citylifehouse/citylife/de/26/20090508_7339__.jpg");
//        imageList.add("http://s1.dwstatic.com/group1/M00/EE/9C/701cab3f6f04b8e7f8f5562ed65f8639.gif");
//        imageList.add("http://img3.16fan.com/live/origin/201903/12/3EB3b3070c803.jpg?imageView2/0/h/1600/interlace/1/q/50/format/bmp");

        imageSeeAdapter.setNewData(imageList);
        imageSeeAdapter.setOnItemClickListener((adapter, view, position) -> {
            ImagePreview.getInstance()
                    .setContext(mContext)
                    .setIndex(position)
                    .setImageList(imageList).start();
        });
    }

    public void addImages(List<String> imgs) {
        imageList.clear();
        imageList.addAll(imgs);
    }
}
