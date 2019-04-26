package com.tepia.cmdbsevice.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.esri.android.map.GraphicsLayer;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.tepia.base.utils.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2019-02-26
 * Time            :       下午4:20
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :
 **/
public class ARCGISUTIL {

    /**
     * 添加图片 attributes 不能传自定义对象
     *
     * @param id         图片id
     * @param point      坐标点
     */
    public static Graphic addPic(int id, com.esri.core.geometry.Point point, GraphicsLayer logGraphicsLayer) {
        Map<String, Object> attributes = new HashMap<>(1);
        PictureMarkerSymbol pictureMarkerSymbol1 = null;

        Bitmap bitmap = BitmapFactory.decodeResource(Utils.getContext().getResources(), id);
        if (bitmap == null) {
            return null;
        }
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight() * 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(bitmap, 0, 0, null);
        pictureMarkerSymbol1 = new PictureMarkerSymbol(Utils.getContext(), bitmap2Drawable(result));
        Graphic picGraphic = new Graphic(point, pictureMarkerSymbol1, attributes);
        logGraphicsLayer.addGraphic(picGraphic);
        return picGraphic;

    }

    /**
     * 添加图片 attributes 不能传自定义对象
     *
     * @param id         图片id
     * @param point      坐标点
     */
    public static Graphic addPicForPos(int id, com.esri.core.geometry.Point point, GraphicsLayer logGraphicsLayer,int pos) {
        Map<String, Object> attributes = new HashMap<>(1);
        attributes.put("pos",pos);
        PictureMarkerSymbol pictureMarkerSymbol1 = null;

        Bitmap bitmap = BitmapFactory.decodeResource(Utils.getContext().getResources(), id);
        if (bitmap == null) {
            return null;
        }
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight() * 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(bitmap, 0, 0, null);
        pictureMarkerSymbol1 = new PictureMarkerSymbol(Utils.getContext(), bitmap2Drawable(result));
        Graphic picGraphic = new Graphic(point, pictureMarkerSymbol1, attributes);
        logGraphicsLayer.addGraphic(picGraphic);
        return picGraphic;

    }

    private static Drawable bitmap2Drawable(Bitmap bitmap){
        return new BitmapDrawable(Utils.getContext().getResources(),bitmap);

    }
}
