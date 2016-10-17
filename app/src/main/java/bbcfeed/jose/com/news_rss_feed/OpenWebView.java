package bbcfeed.jose.com.news_rss_feed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by josemanuelgil on 16/10/16.
 */
public class OpenWebView extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        /*ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/

        Intent startingIntent = getIntent();

        if (startingIntent != null)
        {
            Bundle b = startingIntent.getBundleExtra("android.intent.extra.INTENT");
            if (b != null){

                WebView myWebView = (WebView) findViewById(R.id.webview);
                WebSettings webSettings = myWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                myWebView.loadUrl(b.getString("link"));
                myWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        return false;
                    }
                });
            }

        }

    }


}