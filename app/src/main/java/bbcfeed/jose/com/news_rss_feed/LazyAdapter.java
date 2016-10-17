package bbcfeed.jose.com.news_rss_feed;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by josemanuelgil on 16/10/16.
 */
public class LazyAdapter extends ArrayAdapter<RSSItem> {

    private static String TAG = "LazyAdapter";
    private Activity activity;
    //private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader _imageLoader;


    public LazyAdapter(Context context, ArrayList<RSSItem> feeds, ImageLoader imageLoader) {

        super(context, 0, feeds);
        _imageLoader =imageLoader;

    }


    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position
        RSSItem feed = getItem(position);

        // Default image - will be shown before loading image
        int loader = R.drawable.loader;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
        }

        TextView title = (TextView)convertView.findViewById(R.id.title); // title
        TextView description = (TextView)convertView.findViewById(R.id.description); // description
        TextView pubdate = (TextView)convertView.findViewById(R.id.pubdate); // pubdate
        ImageView thumb_image=(ImageView)convertView.findViewById(R.id.list_image); // thumb image


        // Setting all values in listview
        title.setText(feed.getTitle());
        description.setText(feed.getDescription());
        pubdate.setText(feed.getPubDate());
        _imageLoader.DisplayImage(feed.getThumbnail(),loader,  thumb_image);

        return convertView;
    }
}