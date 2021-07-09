package com.example.attendence;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class employee_in_out extends AppCompatActivity {
    RecyclerView recyclerView;
    WebView webView;
//    List<in_out_adminResponse.EmployeeList> list = new ArrayList<>();
//    in_out_adapter in_out_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_in_out);
        webView = findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://believerz.in/attendence/check_in_out_table.php/");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
    }

    public class mywebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}




//        //recyclerView=findViewById(R.id.recycle_inout);
//        checkinoutresponseApi();
//        show();
//    }
//    public  void show(){
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        in_out_adapter=new in_out_adapter(list);
//        recyclerView.setAdapter(in_out_adapter);
//    }
//    public  void checkinoutresponseApi(){
//        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
//        Call<in_out_adminResponse> call = service.in_out_fetch("", "","");
//        call.enqueue(new Callback<in_out_adminResponse>() {
//            @Override
//            public void onResponse(Call<in_out_adminResponse> call, Response<in_out_adminResponse> response) {
//
//                // pbar.setVisibility(View.GONE);
//                if (response.isSuccessful()) {
//                    try {
//
//                        list.clear();
//                        list.addAll(response.body().employeeLists);
//                        show();
//                    }
//                    catch (Exception e)
//                    {
//
//                    }
//                } else {
//                    list.clear();
//                    Toast.makeText(employee_in_out.this, "somthing went wrong", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<in_out_adminResponse> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
//                //pbar.setVisibility(View.GONE);
//
//            }
//        });
//    }

