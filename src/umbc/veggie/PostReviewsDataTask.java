package umbc.veggie;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;

public class PostReviewsDataTask extends AsyncTask<URL, Integer, String>{

	String restaurant;
	String fooditem;
	float rating;
	String review;
	
	HttpClient httpclient = new DefaultHttpClient();
	
	// WEBPAGE TO SEND POST REQUEST TO ENTER THE USER REVIEW & RATING DATA INTO A DB
    HttpPost httppost = new HttpPost("http://userpages.umbc.edu/~cpatel4/CMSC331/reviews.php"); 

    
	public PostReviewsDataTask(String rest, String item, float rate,
			String rev) {
		
		restaurant = rest;
		fooditem = item;
		rating = rate;
		review = rev;
	}

	@Override
	protected String doInBackground(URL... params) {
		//do the POST here of all these items
		try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("restaurant", restaurant));
	        nameValuePairs.add(new BasicNameValuePair("fooditem", fooditem));
	        nameValuePairs.add(new BasicNameValuePair("rating", String.valueOf(rating) ));
	        nameValuePairs.add(new BasicNameValuePair("review", review));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);

	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
		
		return null;
	}

}
