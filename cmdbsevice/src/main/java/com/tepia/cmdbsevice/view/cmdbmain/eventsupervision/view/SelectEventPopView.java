package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lxj.xpopup.core.DrawerPopupView;
import com.tepia.cmdbsevice.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

public class SelectEventPopView extends DrawerPopupView {

    private TagFlowLayout tagFlowLayout ,comptyFlowLayout,cateFlowLayout,stateFlowLayout;
    private String[] mVals = new String[]{"处理站", "提升井"};

    public SelectEventPopView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        final LayoutInflater mInflater = LayoutInflater.from(getContext());
        tagFlowLayout = findViewById(R.id.id_flowlayout);
        comptyFlowLayout = findViewById(R.id.flowlayout_compty);
        cateFlowLayout = findViewById(R.id.flowlayout_cate);
        stateFlowLayout = findViewById(R.id.flowlayout_state);
        tagFlowLayout.setAdapter(new TagAdapter<String>(mVals) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_tv,
                        tagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
        comptyFlowLayout.setAdapter(new TagAdapter<String>(mVals) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_tv,
                        tagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
        cateFlowLayout.setAdapter(new TagAdapter<String>(mVals) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_tv,
                        tagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
        stateFlowLayout.setAdapter(new TagAdapter<String>(mVals) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_tv,
                        tagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.view_select_event_pop;
    }
}
