package com.bhisma.uasppsi.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bhisma.uasppsi.R;
import com.bhisma.uasppsi.data.DataMasuk;
import com.bhisma.uasppsi.data.DataTunggu;

import java.util.List;

public class DataMasukAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataMasuk> DataMasuk;
    ImageLoader imageLoader;

    public DataMasukAdapter(Activity activity, List<com.bhisma.uasppsi.data.DataMasuk> dataMasuk) {
        this.activity = activity;
        DataMasuk = dataMasuk;
    }

    @Override
    public int getCount() {
        return DataMasuk.size();
    }

    @Override
    public Object getItem(int location) {
        return DataMasuk.get(location);
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
            convertView = inflater.inflate(R.layout.list_data_masuk, null);

//        if (imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();

//        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.news_gambar);
        TextView nama = (TextView) convertView.findViewById(R.id.data_nama_masuk);
        TextView instansi = (TextView) convertView.findViewById(R.id.data_instansi_masuk);
        TextView tujuan = (TextView) convertView.findViewById(R.id.data_tujuan_masuk);
//        TextView karyawan    = (TextView) convertView.findViewById(R.id.data_karyawa);

        DataMasuk news = DataMasuk.get(position);

//        thumbNail.setImageUrl(news.getGambar(), imageLoader);
        nama.setText(news.getNamaMasuk());
        instansi.setText(news.getInstansiMasuk());
        tujuan.setText(news.getTujuanMasuk());
//        karyawan.setText(news.getKaryawan());

        return convertView;
    }
}
