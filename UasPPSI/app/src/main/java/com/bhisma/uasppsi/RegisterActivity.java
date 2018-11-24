package com.bhisma.uasppsi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bhisma.uasppsi.app.AppController;
import com.bhisma.uasppsi.app.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    ProgressDialog pDialog;
    TextView jButtonBack;
    Button jRegisterNow;
    EditText jetInputNama, jetInputUsername, jetNotelp, jetEmail ,jetInputPassword, jetInputConfirmPassword;
    RadioButton jrbSecurity, jrbEmployee;
    RadioGroup jrgJabatan;
    String tipejabatan = "satpam";

    ConnectivityManager conMgr;

    String tag_json_obj = "json_obj_req";

    int success;
    private String url = Server.URL + "register.php";
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        jetInputNama            = (EditText)    findViewById(R.id.etInputNama);
        jetInputUsername        = (EditText)    findViewById(R.id.etInputUsername);
        jetNotelp               = (EditText)    findViewById(R.id.etInputNotelp);
        jetEmail                = (EditText)    findViewById(R.id.etInputEmail);
        jetInputPassword        = (EditText)    findViewById(R.id.etInputPassword);
        jetInputConfirmPassword = (EditText)    findViewById(R.id.etInputConfirmPassword);
        jrbSecurity             = (RadioButton) findViewById(R.id.cbSecurity);
        jrbEmployee             = (RadioButton) findViewById(R.id.cbEmployee);
        jrgJabatan              = (RadioGroup)  findViewById(R.id.rbg);

        jrgJabatan.setOnCheckedChangeListener(this);

        jButtonBack  = (TextView) findViewById(R.id.backLogin);
        jButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goes = new Intent(RegisterActivity.this, LoginActivity.class);
                finish();
                startActivity(goes);
            }
        });

        jRegisterNow = (Button) findViewById(R.id.btnRegisterNow);
        jRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama             = jetInputNama.getText().toString();
                String username         = jetInputUsername.getText().toString();
                String notelp           = jetNotelp.getText().toString();
                String email            = jetEmail.getText().toString();
                String password         = jetInputPassword.getText().toString();
                String confirm_password = jetInputConfirmPassword.getText().toString();

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    checkRegister(nama, username, notelp, email, password, confirm_password, tipejabatan);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkRegister(final String nama, final String username, final String notelp, final String email, final String password, final String confirm_password, final String tipe_jabatan) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Register ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Successfully Register!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        jetInputUsername.setText("");
                        jetInputNama.setText("");
                        jetEmail.setText("");
                        jetNotelp.setText("");
                        jetInputPassword.setText("");
                        jetInputConfirmPassword.setText("");
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        finish();
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("nama", nama);
                params.put("username", username);
                params.put("notelp", notelp);
                params.put("email", email);
                params.put("tipe_jabatan", tipe_jabatan);
                params.put("password", password);
                params.put("confirm_password", confirm_password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        finish();
        startActivity(intent);
    }

    public void onCheckedChanged(RadioGroup group,
                                 int checkedId) {
        if(checkedId==R.id.cbEmployee)
        {
            tipejabatan="satpam";
        }
        if(checkedId==R.id.cbSecurity)
        {
            tipejabatan="pegawai";
        }
    }
}