package com.bhisma.uasppsi.Fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.bhisma.uasppsi.HomeActivity;
import com.bhisma.uasppsi.KonfirmasiActivity;
import com.bhisma.uasppsi.LoginActivity;
import com.bhisma.uasppsi.R;
import com.bhisma.uasppsi.adapter.Adapter;
import com.bhisma.uasppsi.app.AppController;
import com.bhisma.uasppsi.app.Server;
import com.bhisma.uasppsi.data.DataPegawai;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class Catat extends Fragment {
    public Catat(){}

    LinearLayout rootView;
    Button jBtnSimpan, jBtnReset;
    EditText jetNama, jetTujuan, jetNoKtp, jetNoKen, jetAlamat, jetInstansi;
    String id, username;
    SharedPreferences sharedpreferences;
    TextView jIsi, jTanggal, jisiSatpam;
    ImageView jimgGambar;
    String currentDateTimeString;
    String currentDateString;
    Spinner spinner_pegawai;
    Adapter adapter;
    List<DataPegawai> listPegawai = new ArrayList<DataPegawai>();
    ProgressDialog pDialog;
    Intent intent;
    String mCurrentPhotoPath;
    Bitmap bitmap, decoded;
    Uri fileUri;
    String id_satpam, SNama, SKtemuSiapa, STujuan, SNoKtp, SNoKen, SAlamat, SInstansi, SGambar, SId_kunjungan;
    String tag_json_obj = "json_obj_req";

    public final int REQUEST_CAMERA = 0;
    int success;
    private static final int REQUEST_TAKE_PHOTO = 0;
    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    public final int SELECT_FILE                = 1;
    public static final String TAG_ID_PEGAWAI   = "id_user";
    public static final String TAG_NAMA         = "nama";
    private static final String TAG_SUCCESS     = "success";
    private static final String TAG_MESSAGE     = "message";
    private static final String TAG_GAMBAR      = "gambar";
    private static final String TAG_ID_KUNJUNGAN= "id_kunjungan";
    private static final String TAG             = HomeActivity.class.getSimpleName();
    int max_resolution_image                    = 800;
    int bitmap_size                             = 40; // image quality 1 - 100;
    private static String url_spinner 	        = Server.URL + "menu.php";
    private static String url_insert            = Server.URL + "insert.php";
    private String KEY_IMAGE                    = "image";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = (LinearLayout) inflater.inflate(R.layout.fragment_catat, container, false);

        jetNama         = (EditText)  rootView.findViewById(R.id.etNama);
        jetTujuan       = (EditText)  rootView.findViewById(R.id.etTujuan);
        jetNoKtp        = (EditText)  rootView.findViewById(R.id.etNoKtp);
        jetNoKen        = (EditText)  rootView.findViewById(R.id.etNoKen);
        jetAlamat       = (EditText)  rootView.findViewById(R.id.etAlamat);
        jetInstansi     = (EditText)  rootView.findViewById(R.id.etInstansi);
        jIsi            = (TextView)  rootView.findViewById(R.id.isiID);
        jTanggal        = (TextView)  rootView.findViewById(R.id.isiTanggal);
        jisiSatpam      = (TextView)  rootView.findViewById(R.id.isiNamaSatpam);
        jimgGambar      = (ImageView) rootView.findViewById(R.id.imgFoto);
        spinner_pegawai = (Spinner)   rootView.findViewById(R.id.spinnerKtemuSiapa);

        sharedpreferences = this.getActivity().getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        id_satpam = sharedpreferences.getString(TAG_ID, null);

        id = this.getActivity().getIntent().getStringExtra(TAG_ID);
        username = this.getActivity().getIntent().getStringExtra(TAG_USERNAME);
        jIsi.setText(id);
        jisiSatpam.setText(username);

        currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        currentDateString     = DateFormat.getDateInstance().format(new Date());
        jTanggal.setText(currentDateString);

        getActivity().setTitle("Catat Tamu");
        Log.e("Catat", "Catat");

        // Inflate the layout for this fragment
        jBtnSimpan = (Button) rootView.findViewById(R.id.btnSimpan);
        jBtnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SNama       = jetNama.getText().toString();
                SInstansi   = jetInstansi.getText().toString();
                STujuan     = jetTujuan.getText().toString();
                //SKtemuSiapa = jetKtemuSiapa.getText().toString();
                SNoKtp      = jetNoKtp.getText().toString();
                SNoKen      = jetNoKen.getText().toString();
                SAlamat     = jetAlamat.getText().toString();

                simpan();
            }
        });

        jBtnReset = (Button) rootView.findViewById(R.id.btnReset);
        jBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kosong();
            }
        });

        jimgGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                selectImage();
            }
        });

        spinner_pegawai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
//                txt_hasil.setText("Pendidikan Terakhir : " + listPendidikan.get(position).getId());
                SKtemuSiapa = listPegawai.get(position).getIdPegawai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        jimgGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        adapter = new Adapter(getActivity(), listPegawai);
        spinner_pegawai.setAdapter(adapter);

        callData();

        return rootView;
    }

    private void kosong(){
        jetNama.setText(null);
        jetTujuan.setText(null);
        jetNoKtp.setText(null);
        jetNoKen.setText(null);
        jetAlamat.setText(null);
        jetInstansi.setText(null);
//        jimgGambar.setImageResource(R.drawable.profil);
    }

    private void callData() {
        listPegawai.clear();
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        showDialog();
        String url = url_spinner;
        // Creating volley request obj
        JsonArrayRequest jArr = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, response.toString());

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);

                                DataPegawai item = new DataPegawai();

                                item.setIdPegawai(obj.getString(TAG_ID_PEGAWAI));
                                item.setNamaPegawai(obj.getString(TAG_NAMA));

                                listPegawai.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();

                        hideDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void selectImage() {
        jimgGambar.setImageResource(0);
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    fileUri = getOutputMediaFileUri();
                    intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, REQUEST_CAMERA);
//                    CatatPermissionsDispatcher.startCameraWithCheck(getActivity());
                } else if (items[item].equals("Choose from Library")) {
                    intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("onActivityResult", "requestCode " + requestCode + ", resultCode " + resultCode);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                try {
                    Log.e("CAMERA", fileUri.getPath());

                    bitmap = BitmapFactory.decodeFile(fileUri.getPath());
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE && data != null && data.getData() != null) {
                try {
                    // mengambil gambar dari Gallery
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Untuk menampilkan bitmap pada ImageView
    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        jimgGambar.setImageBitmap(decoded);
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    private static File getOutputMediaFile() {

        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "DeKa");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e("Monitoring", "Oops! Failed create Monitoring directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_DeKa_" + timeStamp + ".jpg");

        return mediaFile;
    }

    private void simpan() {
        String url = url_insert;
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Uploading...", "Please wait...", false, false);
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


                        kosong();

                        Toast.makeText(getActivity(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        SGambar = jObj.getString(TAG_GAMBAR);
                        SId_kunjungan = jObj.getString(TAG_ID_KUNJUNGAN);
                        Intent goes = new Intent(getActivity(),KonfirmasiActivity.class);
                        goes.putExtra("id_satpam",SKtemuSiapa);
                        goes.putExtra("id_kunjungan",SId_kunjungan);
                        goes.putExtra("gambar",SGambar);
                        startActivity(goes);
//                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getActivity(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(getActivity(), "Foto Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                params.put("id_satpam", id_satpam);
                params.put("nama", SNama);
                params.put("KtemuSiapa", SKtemuSiapa);
                params.put("keperluan", STujuan);
                params.put("no_ktp", SNoKtp);
                params.put("no_pol", SNoKen);
                params.put("alamat", SAlamat);
                params.put("instansi", SInstansi);
                params.put(KEY_IMAGE, getStringImage(decoded));
                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

}
