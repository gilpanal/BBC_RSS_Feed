package bbcfeed.jose.com.bbc_rss_feed;

import java.util.List;
import java.util.Vector;

/**
 * Created by josemanuelgil on 16/10/16.
 */
public class RSSFeed {

    private String _title = null;
    private String _pubdate = null;
    private String _description = null;
    private String _thumbnail = null;
    private int _itemcount = 0;
    private List<RSSItem> _itemlist;


    RSSFeed()
    {
        _itemlist = new Vector(0);
    }
    int addItem(RSSItem item)
    {
        _itemlist.add(item);
        _itemcount++;
        return _itemcount;
    }
    RSSItem getItem(int location)
    {
        return _itemlist.get(location);
    }
    List getAllItems()
    {
        return _itemlist;
    }
    int getItemCount()
    {
        return _itemcount;
    }
    void setTitle(String title)
    {
        _title = title;
    }
    void setPubDate(String pubdate)
    {
        _pubdate = pubdate;
    }
    void setDescription (String description) {_description = description;}
    void setThumbnail(String thumbnail)
    {
        _thumbnail = thumbnail;
    }
    String getTitle()
    {
        return _title;
    }
    String getPubDate()
    {
        return _pubdate;
    }
    String getDescription(){ return _description;}
    String getThumbnail()
    {
        return _thumbnail;
    }
}
