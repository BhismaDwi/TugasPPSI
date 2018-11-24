 package com.bhisma.uasppsi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.bhisma.uasppsi.Fragment.Detail;
import com.bhisma.uasppsi.app.AppController;
import com.bhisma.uasppsi.app.Server;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

 public class DetailKunjungan extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

     NetworkImageView thumb_image;
     TextView id_kunjungan, nama, alamat, instansi, no_ktp, keperluan, no_pol, jam_masuk, jam_keluar, tanggal, status, nama_security, nama_employee;
     ImageLoader imageLoader = AppController.getInstance().getImageLoader();
     SwipeRefreshLayout swipe;
     String id_news;
     Button btnMasuk, btnKeluar;
     int success;
     ImageView gambar;

     private static final String TAG = DetailKunjungan.class.getSimpleName();;
     private static final String TAG_SUCCESS     = "success";
     private static final String TAG_MESSAGE     = "message";
     public static final String TAG_ID = "id_kujungan";
     public static final String TAG_NAMA = "nama";
     public static final String TAG_ALAMAT = "alamat";
     public static final String TAG_INSTANSI = "instansi";
     public static final String TAG_GAMBAR = "gambar";
     public static final String TAG_NO_KTP = "no_ktp";
     public static final String TAG_KEPERLUAN = "keperluan";
     public static final String TAG_NO_POL = "no_pol";
     public static final String TAG_JAM_MASUK = "jam_masuk";
     public static final String TAG_JAM_KELUAR = "jam_keluar";
     public static final String TAG_TANGGAL = "tanggal";
     public static final String TAG_STATUS = "status";
     public static final String TAG_SECURITY = "id_security";
     public static final String TAG_EMPLOYEE = "id_employee";
     private static String url_masuk           = Server.URL + "masuk.php";
     private static String url_keluar          = Server.URL + "keluar.php";

     private static final String url_detail = Server.URL + "detail_news.php";
     String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kunjungan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Detail Kunjungan");

//        thumb_image = (NetworkImageView) findViewById(R.id.gambar_news);
        id_kunjungan  = (TextView) findViewById(R.id.id_kunjungan);
        nama          = (TextView) findViewById(R.id.nama_kunjungan);
        alamat        = (TextView) findViewById(R.id.alamat_kunjungan);
        instansi      = (TextView) findViewById(R.id.instansi_kunjungan);
        no_ktp        = (TextView) findViewById(R.id.no_ktp_kunjungan);
        keperluan     = (TextView) findViewById(R.id.keperluan_kunjungan);
        no_pol        = (TextView) findViewById(R.id.no_pol_kunjungan);
        jam_masuk     = (TextView) findViewById(R.id.jam_masuk_kunjungan);
        jam_keluar    = (TextView) findViewById(R.id.jam_keluar_kunjungan);
        tanggal       = (TextView) findViewById(R.id.tanggal_kunjungan);
        status        = (TextView) findViewById(R.id.status_kunjungan);
        nama_security = (TextView) findViewById(R.id.nama_satpam_kunjungan);
        nama_employee = (TextView) findViewById(R.id.nama_pegawai_kunjungan);
        gambar        = (ImageView) findViewById(R.id.gambar_news);

        btnMasuk      = (Button) findViewById(R.id.btn_masuk);
        btnKeluar     = (Button) findViewById(R.id.btn_keluar);

        swipe         = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        id_news       = getIntent().getStringExtra(TAG_ID);
//        String gambar = getIntent().getStringExtra(TAG_GAMBAR);

        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           if (!id_news.isEmpty()) {
                               callDetailNews(id_news);
                           }
                       }
                   }
        );

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Masuk(id_news);
            }
        });

        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Keluar(id_news);
            }
        });
    }

     private void Masuk(final String id_news) {
         String url = url_masuk;
         final ProgressDialog loading = ProgressDialog.show(DetailKunjungan.this, "Uploading...", "Please wait...", false, false);
         StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

             @Override
             public void onResponse(String response) {
                 Log.d(TAG, "Response: " + response.toString());

                 try {
                     JSONObject jObj = new JSONObject(response);
                     success = jObj.getInt(TAG_SUCCESS);

                     // Cek error node pada json
                     if (success == 1) {
                         Log.d("Add", jObj.toString());

                         Toast.makeText(DetailKunjungan.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                         Intent goes = new Intent(DetailKunjungan.this,HomeActivity.class);
                         startActivity(goes);
                     } else {
                         Toast.makeText(DetailKunjungan.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                     }
                 } catch (JSONException e) {
                     // JSON error
                     e.printStackTrace();
                 }

                 loading.dismiss();

             }
         }, new Response.ErrorListener() {

             @Override
             public void onErrorResponse(VolleyError error) {
                 loading.dismiss();
                 Log.e(TAG, "Error: " + error.getMessage());
                 Toast.makeText(DetailKunjungan.this, "Foto Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
             }
         }) {

             @Override
             protected Map<String, String> getParams() {
                 // Posting parameters ke post url
                 Map<String, String> params = new HashMap<String, String>();

                 params.put("id_kujungan", id_news);

                 return params;
             }

         };

         AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
     }

     private void Keluar(final String id_news) {
         String url = url_keluar;
         final ProgressDialog loading = ProgressDialog.show(DetailKunjungan.this, "Uploading...", "Please wait...", false, false);
         StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

             @Override
             public void onResponse(String response) {
                 Log.d(TAG, "Response: " + response.toString());

                 try {
                     JSONObject jObj = new JSONObject(response);
                     success = jObj.getInt(TAG_SUCCESS);

                     // Cek error node pada json
                     if (success == 1) {
                         Log.d("Add", jObj.toString());

                         Toast.makeText(DetailKunjungan.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                         Intent goes = new Intent(DetailKunjungan.this,HomeActivity.class);
                         startActivity(goes);
                     } else {
                         Toast.makeText(DetailKunjungan.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                     }
                 } catch (JSONException e) {
                     // JSON error
                     e.printStackTrace();
                 }

                 loading.dismiss();

             }
         }, new Response.ErrorListener() {

             @Override
             public void onErrorResponse(VolleyError error) {
                 loading.dismiss();
                 Log.e(TAG, "Error: " + error.getMessage());
                 Toast.makeText(DetailKunjungan.this, "Foto Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
             }
         }) {

             @Override
             protected Map<String, String> getParams() {
                 // Posting parameters ke post url
                 Map<String, String> params = new HashMap<String, String>();

                 params.put("id_kujungan", id_news);

                 return params;
             }

         };

         AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
     }

     private void callDetailNews(final String id) {
         swipe.setRefreshing(true);

         StringRequest strReq = new StringRequest(Request.Method.POST, url_detail, new Response.Listener<String>() {

             @Override
             public void onResponse(String response) {
                 Log.e(TAG, "Response " + response.toString());
                 swipe.setRefreshing(false);

                 try {
                     JSONObject obj = new JSONObject(response);

                     String Gambar = obj.getString(TAG_GAMBAR);
                     String Nama = obj.getString(TAG_NAMA);
                     String Alamat = obj.getString(TAG_ALAMAT);
                     String Instansi = obj.getString(TAG_INSTANSI);
                     String No_ktp = obj.getString(TAG_NO_KTP);
                     String Keperluan = obj.getString(TAG_KEPERLUAN);
                     String No_pol = obj.getString(TAG_NO_POL);
                     String Jam_masuk = obj.getString(TAG_JAM_MASUK);
                     String Jam_keluar = obj.getString(TAG_JAM_KELUAR);
                     String Tanggal = obj.getString(TAG_TANGGAL);
                     String Status = obj.getString(TAG_STATUS);
                     String Nama_security = obj.getString(TAG_SECURITY);
                     String Nama_employee = obj.getString(TAG_EMPLOYEE);

                     id_kunjungan.setText(id);
                     nama.setText(Nama);
                     alamat.setText(Alamat);
                     instansi.setText(Instansi);
                     no_ktp.setText(No_ktp);
                     keperluan.setText(Keperluan);
                     no_pol.setText(No_pol);
                     jam_masuk.setText(Jam_masuk);
                     jam_keluar.setText(Jam_keluar);
                     tanggal.setText(Tanggal);
                     status.setText(Status);
                     nama_security.setText(Nama_security);
                     nama_employee.setText(Nama_employee);
                     Glide.with(DetailKunjungan.this).load(Gambar)
                             .thumbnail(0.5f)
                             .crossFade()
                             .override(300, 300)
                             .into(gambar);

//                     if (obj.getString(TAG_GAMBAR) != "") {
//                         thumb_image.setImageUrl(Gambar, imageLoader);
//                     }

                 } catch (JSONException e) {
                     e.printStackTrace();
                 }

             }
         }, new Response.ErrorListener() {

             @Override
             public void onErrorResponse(VolleyError error) {
                 Log.e(TAG, "Detail News Error: " + error.getMessage());
                 Toast.makeText(DetailKunjungan.this,
                         error.getMessage(), Toast.LENGTH_LONG).show();
                 swipe.setRefreshing(false);
             }
         }) {

             @Override
             protected Map<String, String> getParams() {
                 // Posting parameters to post url
                 Map<String, String> params = new HashMap<String, String>();
                 params.put("id_kujungan", id);

                 return params;
             }

         };

         // Adding request to request queue
         AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
     }

     @Override
     public void onBackPressed() {
         finish();
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         switch (item.getItemId()) {
             case android.R.id.home:
                 this.finish();
                 return true;
             default:
                 return super.onOptionsItemSelected(item);
         }
     }

    @Override
    public void onRefresh() {

    }
}
