package com.com.zlcd.firedata.adapter;
import android.content.Context;
import android.text.Html;
import android.view.View;
import com.com.zlcd.firedata.R;
import com.com.zlcd.firedata.callback.ApiCallBack;
import com.com.zlcd.firedata.db.Cbc;
import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.internal.SuperViewHolder;
import java.util.List;

/**
 * Created by Administrator on 2016/5/8.
 */
public class ListAdapter  extends SuperAdapter<Cbc> {

    private ApiCallBack<Cbc>   callBack;

    public ApiCallBack<Cbc> getCallBack() {
        return callBack;
    }

    public void setCallBack(ApiCallBack<Cbc> callBack) {
        this.callBack = callBack;
    }

    public ListAdapter(Context context, List<Cbc> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, final Cbc item) {
         holder.setText(R.id.tv_listnumber, Html.fromHtml(item.cbjhms));

         holder.setText(R.id.tv_listdemo, null == item.jhcbrq?"":item.jhcbrq);
         holder.setText(R.id.tv_listplantime,null == item.jlts?"":String.valueOf(item.jlts));
         holder.setOnClickListener(R.id.btn_down, new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (null != callBack) {
                     callBack.call(item,1);
                 }
             }
         });
         holder.setOnClickListener(R.id.btn_upload, new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (null != callBack) {
                     callBack.call(item,2);
                 }
             }
         });
    }
}
