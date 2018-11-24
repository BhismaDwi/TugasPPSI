package com.bhisma.uasppsi.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bhisma.uasppsi.DetailKunjungan;
import com.bhisma.uasppsi.HomeActivity;
import com.bhisma.uasppsi.R;
import com.bhisma.uasppsi.adapter.DataAdapter;
import com.bhisma.uasppsi.adapter.DataMasukAdapter;
import com.bhisma.uasppsi.app.AppController;
import com.bhisma.uasppsi.app.Server;
import com.bhisma.uasppsi.data.DataMasuk;
import com.bhisma.uasppsi.data.DataTunggu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Pantau extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    ListView list, list_masuk;
    SwipeRefreshLayout swipe, swipe_masuk;
    List<DataTunggu> newsList = new ArrayList<DataTunggu>();
    List<DataMasuk> newsList_masuk = new ArrayList<DataMasuk>();

    private static final String TAG = HomeActivity.class.getSimpleName();

    private static String url_list       = Server.URL + "datatunggu.php?offset=";
    private static String url_list_masuk = Server.URL + "datamasuk.php?offset=";

    private int offSet = 0;
    private int offSetMasuk = 0;

    int no,no_masuk;

    DataAdapter adapter;
    DataMasukAdapter adapter_masuk;

    public static final String TAG_NO        = "no";
    public static final String TAG_ID        = "id_kujungan";
    public static final String TAG_NAMA      = "nama";
    public static final String TAG_INSTANSI  = "instansi";
    public static final String TAG_TUJUAN    = "keperluan";
    public static final String TAG_KARYAWAN  = "nama";
    public static final String TAG_GAMBAR    = "gambar";

    Handler handler, handlermasuk;
    Runnable runnable, runnablemasuk;

    public Pantau(){}
    LinearLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (LinearLayout) inflater.inflate(R.layout.fragment_pantau, container, false);

        getActivity().setTitle("Pantau Tamu");
        Log.e("Pantau", "Pantau");

        swipe       = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipe_masuk = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout2);
        list        = (ListView) view.findViewById(R.id.list_news);
        list_masuk  = (ListView) view.findViewById(R.id.list_news2);
        newsList.clear();
        newsList_masuk.clear();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), DetailKunjungan.class);
                intent.putExtra(TAG_ID, newsList.get(position).getId_kujungan());
                intent.putExtra(TAG_GAMBAR, newsList.get(position).getId_kujungan());
                startActivity(intent);
            }
        });

        list_masuk.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), DetailKunjungan.class);
                intent.putExtra(TAG_ID, newsList_masuk.get(position).getId_kujungan());
                startActivity(intent);
            }
        });

        adapter       = new DataAdapter(getActivity(), newsList);
        adapter_masuk = new DataMasukAdapter(getActivity(), newsList_masuk);

        list.setAdapter(adapter);
        list_masuk.setAdapter(adapter_masuk);

        swipe.setOnRefreshListener(this);
        swipe_masuk.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                                 @Override
                                 public void run() {
                                     swipe.setRefreshing(true);
                                     newsList.clear();
                                     adapter.notifyDataSetChanged();
                                     callNews(0);
                                 }
                             }
        );
        swipe_masuk.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe_masuk.setRefreshing(true);
                           newsList_masuk.clear();
                           adapter_masuk.notifyDataSetChanged();
                           callNewsMasuk(0);
                       }
                   }
        );

        list.setOnScrollListener(new AbsListView.OnScrollListener() {

            private int currentVisibleItemCount;
            private int currentScrollState;
            private int currentFirstVisibleItem;
            private int totalItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.currentScrollState = scrollState;
                this.isScrollCompleted();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
                this.totalItem = totalItemCount;
            }

            private void isScrollCompleted() {
                if (totalItem - currentFirstVisibleItem == currentVisibleItemCount
                        && this.currentScrollState == SCROLL_STATE_IDLE) {

                    swipe.setRefreshing(true);
                    handler = new Handler();

                    runnable = new Runnable() {
                        public void run() {
                            callNews(offSet);
                        }
                    };

                    handler.postDelayed(runnable, 3000);
                }
            }

        });

        list_masuk.setOnScrollListener(new AbsListView.OnScrollListener() {

            private int currentVisibleItemCount;
            private int currentScrollState;
            private int currentFirstVisibleItem;
            private int totalItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.currentScrollState = scrollState;
                this.isScrollCompleted();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
                this.totalItem = totalItemCount;
            }

            private void isScrollCompleted() {
                if (totalItem - currentFirstVisibleItem == currentVisibleItemCount
                        && this.currentScrollState == SCROLL_STATE_IDLE) {

                    swipe_masuk.setRefreshing(true);
                    handlermasuk = new Handler();

                    runnablemasuk = new Runnable() {
                        public void run() {
                            callNewsMasuk(offSetMasuk);
                        }
                    };

                    handlermasuk.postDelayed(runnablemasuk, 3000);
                }
            }

        });

        // Inflate the layout for this fragment
        return view;
    }


    public void onRefresh() {
        newsList.clear();
        adapter.notifyDataSetChanged();
        callNews(0);
        newsList_masuk.clear();
        adapter_masuk.notifyDataSetChanged();
        callNewsMasuk(0);
    }
    private void callNews(int page) {

        swipe.setRefreshing(true);

        // Creating volley request obj
        JsonArrayRequest arrReq = new JsonArrayRequest(url_list + page,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, response.toString());

                        if (response.length() > 0) {
                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    DataTunggu news = new DataTunggu();

                                    no = obj.getInt(TAG_NO);

                                    news.setId_kujungan(obj.getString(TAG_ID));
                                    news.setNama(obj.getString(TAG_NAMA));
                                    news.setInstansi(obj.getString(TAG_INSTANSI));
                                    news.setTujuan(obj.getString(TAG_TUJUAN));
                                    news.setKaryawan(obj.getString(TAG_KARYAWAN));

                                    if (obj.getString(TAG_GAMBAR) != "") {
                                        news.setGambar(obj.getString(TAG_GAMBAR));
                                    }

                                    // adding news to news array
                                    newsList.add(news);

                                    if (no > offSet)
                                        offSet = no;

                                    Log.e(TAG, "offSet " + offSet);

                                } catch (JSONException e) {
                                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                                }

                                // notifying list adapter about data changes
                                // so that it renders the list view with updated data
                                adapter.notifyDataSetChanged();
                            }
                        }
                        swipe.setRefreshing(false);
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(arrReq);
    }

    private void callNewsMasuk(int page) {

        swipe_masuk.setRefreshing(true);

        // Creating volley request obj
        JsonArrayRequest arrReq = new JsonArrayRequest(url_list_masuk + page,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, response.toString());

                        if (response.length() > 0) {
                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    DataMasuk newsMasuk = new DataMasuk();

                                    no_masuk = obj.getInt(TAG_NO);

                                    newsMasuk.setId_kujungan(obj.getString(TAG_ID));
                                    newsMasuk.setNamaMasuk(obj.getString(TAG_NAMA));
                                    newsMasuk.setInstansiMasuk(obj.getString(TAG_INSTANSI));
                                    newsMasuk.setTujuanMasuk(obj.getString(TAG_TUJUAN));
                                    newsMasuk.setKaryawanMasuk(obj.getString(TAG_KARYAWAN));

                                    // adding news to news array
                                    newsList_masuk.add(newsMasuk);

                                    if (no_masuk > offSetMasuk)
                                        offSetMasuk = no_masuk;

                                    Log.e(TAG, "offSetMasuk " + offSetMasuk);

                                } catch (JSONException e) {
                                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                                }

                                // notifying list adapter about data changes
                                // so that it renders the list view with updated data
                                adapter_masuk.notifyDataSetChanged();
                            }
                        }
                        swipe_masuk.setRefreshing(false);
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                swipe_masuk.setRefreshing(false);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(arrReq);
    }


}
