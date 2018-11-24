package com.bhisma.uasppsi.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bhisma.uasppsi.R;
import com.bhisma.uasppsi.data.DataPegawai;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataPegawai> item;

    public Adapter(Activity activity, List<DataPegawai> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int location) {
        return item.get(location);
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
            convertView = inflater.inflate(R.layout.list_pegawai, null);

        TextView pegawai = (TextView) convertView.findViewById(R.id.pegawai);

        DataPegawai datapegawai;
        datapegawai = item.get(position);

        pegawai.setText(datapegawai.getNamaPegawai());

        return convertView;
    }
}