package com.bhisma.uasppsi.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bhisma.uasppsi.R;
import com.bhisma.uasppsi.app.AppController;
import com.bhisma.uasppsi.data.DataTunggu;

import java.util.List;

public class DataAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataTunggu> DataTunggu;
    ImageLoader imageLoader;

    public DataAdapter(Activity activity, List<com.bhisma.uasppsi.data.DataTunggu> dataTunggu) {
        this.activity = activity;
        DataTunggu = dataTunggu;
    }

    @Override
    public int getCount() {
        return DataTunggu.size();
    }

    @Override
    public Object getItem(int location) {
        return DataTunggu.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_data_tunggu, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

//        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.imgFotoTamu2);
        TextView nama        = (TextView) convertView.findViewById(R.id.data_nama);
        TextView instansi    = (TextView) convertView.findViewById(R.id.data_instansi);
        TextView tujuan      = (TextView) convertView.findViewById(R.id.data_tujuan);
//        TextView karyawan    = (TextView) convertView.findViewById(R.id.data_karyawa);

        DataTunggu news = DataTunggu.get(position);

//        thumbNail.setImageUrl(news.getGambar(), imageLoader);
        nama.setText(news.getNama());
        instansi.setText(news.getInstansi());
        tujuan.setText(news.getTujuan());
//        karyawan.setText(news.getKaryawan());

        return convertView;
    }
}
