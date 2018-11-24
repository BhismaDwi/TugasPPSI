package com.bhisma.uasppsi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bhisma.uasppsi.Fragment.Catat;
import com.bhisma.uasppsi.Fragment.Pantau;
import com.bhisma.uasppsi.app.AppController;
import com.bhisma.uasppsi.app.Server;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class KonfirmasiActivity extends AppCompatActivity {
    Button jButtonYa, jButtonTidak, jBtnSms, jBtnEmail, jBtnCall;
    TextView namasatpam;
    ImageView fototamu;
    String id_satpam,id_kunjungan, Snama_satpam, Stelp, Semail, gambar;
    String tag_json_obj = "json_obj_req";

    private static String url_call              = Server.URL + "call.php";
    private static String url_confirm           = Server.URL + "confirm.php";
    private static String url_confirm_no        = Server.URL + "confirm_no.php";
    private static final String TAG             = KonfirmasiActivity.class.getSimpleName();
    private static final String TAG_SUCCESS     = "success";
    private static final String TAG_MESSAGE     = "message";
    public static final String TAG_NAMA_SATPAM  = "nama_satpam";
    public static final String TAG_EMAIL_SATPAM = "email";
    public static final String TAG_TELP_SATPAM  = "notelp";
    int success;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);
        jButtonYa       = (Button) findViewById(R.id.btnYa);
        jButtonTidak    = (Button) findViewById(R.id.btnTidak);
        jBtnEmail       = (Button) findViewById(R.id.btnEmail);
        jBtnCall        = (Button) findViewById(R.id.btnTelp);
        jBtnSms         = (Button) findViewById(R.id.btnSMS);
        namasatpam      = (TextView) findViewById(R.id.etNama);
        fototamu        = (ImageView) findViewById(R.id.imgFotoTamu);

        Bundle extras = getIntent().getExtras();
        id_satpam    = extras.getString("id_satpam");
        id_kunjungan = extras.getString("id_kunjungan");
        gambar       = extras.getString("gambar");

        Glide.with(KonfirmasiActivity.this).load(gambar)
                .thumbnail(0.5f)
                .crossFade()
                .override(300, 300)
                .into(fototamu);

        jButtonYa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dapat_menemui(id_satpam, id_kunjungan);
            }
        });

        jButtonTidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Tidak_menemui(id_satpam, id_kunjungan);
            }
        });

        jBtnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + Stelp));
                smsIntent.putExtra("sms_body","Apakah Anda bisa menerima tamu hari ini ?");
                startActivity(smsIntent);

            }
        });

        jBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                    String dial = "tel:" + Stelp;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));

            }
        });

        jBtnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL,Semail);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Konfirmasi Kunjungan Tamu");
                intent.putExtra(Intent.EXTRA_TEXT,"Apakah Anda bisa menerima tamu hari ini ?");
                intent.setData(Uri.parse("mailto:"));
                    startActivity(Intent.createChooser(intent,"Email"));
            }
        });

        isi(id_satpam);

    }

    private void isi(final String id_satpam) {
        String url = url_call;
        final ProgressDialog loading = ProgressDialog.show(KonfirmasiActivity.this, "Uploading...", "Please wait...", false, false);
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

                        Toast.makeText(KonfirmasiActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        Snama_satpam = jObj.getString(TAG_NAMA_SATPAM);
                        Semail       = jObj.getString(TAG_EMAIL_SATPAM);
                        Stelp        = jObj.getString(TAG_TELP_SATPAM);
                        namasatpam.setText(Snama_satpam);

                    } else {
                        Toast.makeText(KonfirmasiActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(KonfirmasiActivity.this, "Foto Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                params.put("id_satpam", id_satpam);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void Dapat_menemui(final String id_satpam, final String id_kunjungan) {
        String url = url_confirm;
        final ProgressDialog loading = ProgressDialog.show(KonfirmasiActivity.this, "Uploading...", "Please wait...", false, false);
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
                        Log.d("Add", id_kunjungan);

                        Toast.makeText(KonfirmasiActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        Intent goes = new Intent(KonfirmasiActivity.this,HomeActivity.class);
                        startActivity(goes);
                    } else {
                        Toast.makeText(KonfirmasiActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(KonfirmasiActivity.this, "Foto Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                params.put("id_satpam", id_satpam);
                params.put("id_kunjungan", id_kunjungan);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void Tidak_menemui(final String id_satpam, final String id_kunjungan) {
        String url = url_confirm_no;
        final ProgressDialog loading = ProgressDialog.show(KonfirmasiActivity.this, "Uploading...", "Please wait...", false, false);
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
                        Log.d("Add", id_kunjungan);

                        Toast.makeText(KonfirmasiActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        Intent goes = new Intent(KonfirmasiActivity.this,HomeActivity.class);
                        startActivity(goes);
                    } else {
                        Toast.makeText(KonfirmasiActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(KonfirmasiActivity.this, "Foto Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                params.put("id_satpam", id_satpam);
                params.put("id_kunjungan", id_kunjungan);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

}
