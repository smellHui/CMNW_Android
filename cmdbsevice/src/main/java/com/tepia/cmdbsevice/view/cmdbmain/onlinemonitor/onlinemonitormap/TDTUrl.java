package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.onlinemonitormap;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/5/14
 * Time            :       15:53
 * Version         :       1.0
 * 功能描述        :
 **/
import java.util.Random;

public class TDTUrl {
    private TianDiTuTiledMapServiceType _tiandituMapServiceType;
    private int _level;
    private int _col;
    private int _row;

    public TDTUrl(int level, int col, int row, TianDiTuTiledMapServiceType tiandituMapServiceType) {
        this._level = level;
        this._col = col;
        this._row = row;
        this._tiandituMapServiceType = tiandituMapServiceType;
    }

    public String getUrl() {
        StringBuilder url1 = new StringBuilder("http://t");
        Random random = new Random();
        int subdomain = (random.nextInt(6) + 1);

        url1.append(subdomain);
        switch (this._tiandituMapServiceType) {
            case VEC_C://矢量
                url1.append(".tianditu.com/DataServer?T=").append("vec_w").append("&X=").append(this._col).append("&Y=")
                        .append(this._row).append("&L=").append(this._level)
                        .append("&tk=2ce94f67e58faa24beb7cb8a09780552");
                break;
            case CVA_C://矢量注记
                url1.append(".tianditu.com/DataServer?T=").append("cva_w").append("&X=").append(this._col).append("&Y=")
                        .append(this._row).append("&L=").append(this._level)
                        .append("&tk=2ce94f67e58faa24beb7cb8a09780552");
                break;
            case CIA_C://影像注记
                url1.append(".tianditu.com/DataServer?T=").append("cia_w").append("&X=").append(this._col).append("&Y=")
                        .append(this._row).append("&L=").append(this._level)
                        .append("&tk=2ce94f67e58faa24beb7cb8a09780552");
                break;
            case IMG_C://影像
                url1.append(".tianditu.com/DataServer?T=").append("img_w").append("&X=").append(this._col).append("&Y=")
                        .append(this._row).append("&L=").append(this._level)
                        .append("&tk=2ce94f67e58faa24beb7cb8a09780552");

                break;
            default:
                break;
        }
        //System.out.println(url1.toString());
        return url1.toString();

    }
}

