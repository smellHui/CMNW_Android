package com.tepia.cmnwsevice.view.common;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tepia.base.utils.Utils;
import com.tepia.cmnwsevice.R;
import com.tepia.photo_picker.utils.AndroidLifecycleUtils;


import java.io.File;
import java.util.ArrayList;

/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:图片选择适配                                       *
 * 项目名:贵州水务                                           *
 * 包名:com.elegant.river_system.adapter                    *
 * 创建时间:2017年10月12日11:16                               *
 * 更新时间:2017年10月24日11:16                               *
 * 版本号:1.1.0                                              *
 *************************************************************/
public class PhotoRecycleViewAdapter extends RecyclerView.Adapter<PhotoRecycleViewAdapter.PhotoViewHolder> {

    private Context mContext;
    private ArrayList<String> photoPaths = new ArrayList<>();
    public final static int TYPE_ADD = 1;
    public final static int TYPE_PHOTO = 2;
    public final static int MAX = 4;

    private OnItemClickListener onItemClickListener;

    public PhotoRecycleViewAdapter(Context mContext, ArrayList<String> photoPaths) {
        this.photoPaths =photoPaths;
        this.mContext = mContext;

    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        switch (viewType) {
            case TYPE_ADD:
                itemView = Utils.inflate(R.layout.item_add);
                break;
            case TYPE_PHOTO:
                itemView = Utils.inflate(R.layout.picker_item_photo);
                break;
            default:
                break;
        }
        return new PhotoViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_PHOTO) {
            Uri uri = Uri.fromFile(new File(photoPaths.get(position)));
            boolean canLoadImage = AndroidLifecycleUtils.canLoadImage(holder.ivPhoto.getContext());
            if (canLoadImage) {
                Glide.with(mContext)
                        .load(uri)
                        .into(holder.ivPhoto);
            }
        }

        holder.itemView.setOnClickListener((v) -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        int count = photoPaths.size() + 1;
        if (count > MAX) {
            count = MAX;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == photoPaths.size() && position != MAX) ? TYPE_ADD : TYPE_PHOTO;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPhoto;
        private View vSelected;
        public PhotoViewHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            vSelected = itemView.findViewById(R.id.v_selected);
            if (vSelected != null) {
                vSelected.setVisibility(View.GONE);
            }

        }
    }

}
