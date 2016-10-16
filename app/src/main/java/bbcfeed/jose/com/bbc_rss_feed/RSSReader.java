package bbcfeed.jose.com.bbc_rss_feed;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RSSReader extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public final String RSSFEEDOFCHOICE = "http://feeds.bbci.co.uk/news/world/rss.xml";

    private RSSFeed feed_result = null;
    private static String TAG ="RSSReader";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        new RetrieveFeedTask().execute(RSSFEEDOFCHOICE);
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, RSSFeed> {

        private Exception exception;

        protected RSSFeed doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser parser = factory.newSAXParser();
                XMLReader xmlreader = parser.getXMLReader();
                RSSHandler theRSSHandler = new RSSHandler();
                xmlreader.setContentHandler(theRSSHandler);
                InputSource is = new InputSource(url.openStream());
                xmlreader.parse(is);

                return theRSSHandler.getFeed();
            } catch (Exception e) {
                this.exception = e;

                return null;
            }
        }

        protected void onPostExecute(RSSFeed feed) {
            // TODO: check this.exception
            // TODO: do something with the feed

            UpdateDisplay(feed);
        }
    }

    private void UpdateDisplay(RSSFeed feed)
    {
        TextView feedtitle = (TextView) findViewById(R.id.feedtitle);
        TextView feedpubdate = (TextView) findViewById(R.id.feedpubdate);
        ListView itemlist = (ListView) findViewById(R.id.itemlist);


        if (feed == null)
        {
            feedtitle.setText("No RSS Feed Available");
            return;
        }

        else{

            feed_result = feed;
        }

        feedtitle.setText(feed.getTitle());
        feedpubdate.setText(feed.getPubDate());

        ArrayAdapter<RSSItem> adapter = new ArrayAdapter<RSSItem>(this,android.R.layout.simple_list_item_1,feed.getAllItems());

        itemlist.setAdapter(adapter);

        itemlist.setOnItemClickListener(this);

        itemlist.setSelection(0);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Log.i(TAG,"item clicked! [" + feed_result.getItem(position).getTitle() + "]");

        Intent itemintent = new Intent(this,OpenWebView.class);

        Bundle b = new Bundle();
        //b.putString("title", feed_result.getItem(position).getTitle());
        //b.putString("description", feed_result.getItem(position).getDescription());
        b.putString("link", feed_result.getItem(position).getLink());
        //b.putString("pubdate", feed_result.getItem(position).getPubDate());

        itemintent.putExtra("android.intent.extra.INTENT", b);

        this.startActivity(itemintent,b);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rssreader, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
