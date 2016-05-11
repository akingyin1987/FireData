package com.com.zlcd.firedata.adapter;

/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import android.content.Context;

import android.view.View;
import com.com.zlcd.firedata.R;
import com.com.zlcd.firedata.db.Cbjl;
import java.util.List;
import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.internal.SuperViewHolder;

/**
 * @ Description:
 *
 * Company:重庆中陆承大科技有限公司
 * @ Author king
 * @ Date 2016/5/10 18:01
 * @ Version V1.0
 */
public class MeterListAdapter extends SuperAdapter<Cbjl> {

  public MeterListAdapter(Context context, List<Cbjl> items, int layoutResId) {
    super(context, items, layoutResId);
  }

  @Override
  public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, Cbjl item) {
    holder.setText(R.id.tv_listnumber, item.yhmc);

    holder.setText(R.id.tv_listdemo,item.yhdzbm );
    holder.setText(R.id.tv_listplantime,item.yhh);
    holder.setVisibility(R.id.btn_down, View.GONE);
    holder.setVisibility(R.id.btn_upload,View.GONE);

  }
}
