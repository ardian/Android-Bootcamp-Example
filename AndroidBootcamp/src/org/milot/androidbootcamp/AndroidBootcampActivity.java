package org.milot.androidbootcamp;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;

public class AndroidBootcampActivity extends ListActivity {
    
	public static List<News> newsList = new ArrayList<News>();
	NewsAdapter newsAdapter;
	HttpClient client = null;	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        client = new DefaultHttpClient();        
        
        new AsyncNewsUpdate().execute("http://zeri.info/rss-all.php?category=1");
    }
    
    class AsyncNewsUpdate extends AsyncTask<String, Void, Void> {

		Parser p = new Parser(AndroidBootcampActivity.this);

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... params) {
			try{
			updateNews(params[0]);
			}catch(Exception e){}
			return null;
		}		

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			newsAdapter = new NewsAdapter();
			setListAdapter(newsAdapter);
			newsAdapter.notifyDataSetChanged();
		}

	}

	private void updateNews(String url) {
		Parser p = new Parser(this);
		HttpGet getMethod = new HttpGet(url);

		try {
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = client.execute(getMethod, responseHandler);
			newsList = p.fillList(responseBody, 0);

		} catch (Throwable t) {
			Log.e("updateNews Error", t.getMessage());
		}

	}

	public Drawable getImageFromUrl(String url) {
		try {
			InputStream is = (InputStream) new URL(url).getContent();
			Drawable tmpDrawable = Drawable.createFromStream(is, "image1");
			return tmpDrawable;
		} catch (Exception e) {
			return null;
		}
	}
    
    class NewsAdapter extends ArrayAdapter<News> {

		public NewsAdapter() {
			super(AndroidBootcampActivity.this, R.layout.row, newsList);

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			ViewHolder holder = null;
			final int _pos = position;

			if (row == null) {
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.row, parent, false);
				holder = new ViewHolder(row);
				row.setTag(holder);
			} else {
				holder = (ViewHolder) row.getTag();
			}
			
			
				holder.getTitleView().setText(newsList.get(position).title());
				holder.getDateView().setText(
						newsList.get(position).publishedDate());
				holder.getThumbnail().setImageDrawable(newsList.get(position).getImage());
			

			row.setOnClickListener(new View.OnClickListener() {

				public void onClick(View arg0) {
					try {
						Intent intentNewsDetails = new Intent(AndroidBootcampActivity.this,
								NewsReadingActivity.class);
						intentNewsDetails.putExtra("newsTitle",
								newsList.get(_pos).title());
						intentNewsDetails.putExtra("newsBody",
								newsList.get(_pos).description());
						intentNewsDetails.putExtra("newsLink",
								newsList.get(_pos).link());
						startActivity(intentNewsDetails);

					} catch (Exception e) {
					}

				}
			});

			return row;
		}
	}
}