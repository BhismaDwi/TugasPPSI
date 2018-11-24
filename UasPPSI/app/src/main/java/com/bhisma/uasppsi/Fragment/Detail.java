package com.bhisma.uasppsi.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bhisma.uasppsi.InfoActivity;
import com.bhisma.uasppsi.R;
import com.bhisma.uasppsi.app.Server;


public class Detail extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public Detail() {}
    LinearLayout view;
    ImageView jTampol;

    SwipeRefreshLayout swipeRefreshLayout;
    WebView webView;
    WebSettings webSettings;

    String URL = Server.URL + "laporan/table.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = (LinearLayout) inflater.inflate(R.layout.fragment_detail, container, false);

//        jTampol = (ImageView) view.findViewById(R.id.tampol);
//        jTampol.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goes = new Intent(getActivity(), InfoActivity.class);
//                startActivity(goes);
//            }
//        });

        getActivity().setTitle("DATA DETAIL KUNJUNGAN TAMU");
        Log.e("Detail", "Detail");

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        webView = (WebView) view.findViewById(R.id.web_view);

        swipeRefreshLayout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) this);

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Untuk mengaktifkan javascript
        webSettings.getUseWideViewPort();

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // Menampilkan loading ketika webview proses load halaman
                swipeRefreshLayout.setRefreshing(true);
            }
        });

        webView.setWebViewClient(new WebViewClient(){
            // Ketika webview error atau selesai load page loading akan dismiss

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        webView.loadUrl(URL);

        return view;
    }

    @Override
    public void onRefresh() {
        webView.reload();
    }
}
