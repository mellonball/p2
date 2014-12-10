package umbc.veggie;

import java.net.URL;
import java.util.ArrayList;

import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;

public class GetReviewsTask extends AsyncTask<URL, Integer, String> {

	TextView disp_avg;
	String urlString = "http://userpages.umbc.edu/~cpatel4/CMSC331/proj2.php";
	String returned; //will hold the data returned from get request
	String restaurant;
	String fooditem;
	ListView all_reviews;
	Context ctx;
	
	public GetReviewsTask(String rest, String food, TextView txt, ListView all_revs, Context second) {
		restaurant = rest;
		fooditem = food;
		disp_avg = txt;
		all_reviews = all_revs;
		ctx = second;
	}
	@Override
	protected String doInBackground(URL... params) {
		GetMethodEx test = new GetMethodEx( urlString );

		
		try {
			returned = test.getInternetData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return returned;
	}
	
	protected void onPostExecute(String result){
		//parse returned into restaurants_list
		String[] results = result.trim().split("null");
		//String fooditemtemp = fooditem.split("-")[0].trim();
		System.out.println("restaurant=" + restaurant + "fooditem=" + fooditem + "END");
		
		int count = 0;
		float sum = 0;
			
			for (int i=0; i<results.length; i++)
				System.out.println("results"+i+" : "+results[i]);
			
			
			 final ArrayList<String> list = new ArrayList<String>();
			    for (int i = 0; i < results.length; i=i+4) {
			      if (results[i].trim().equals(restaurant) && results[i+1].trim().equals(fooditem)){
			    	  list.add(results[i+2] + " / 5.0 :    " + results[i+3]);
			    	  count += 1;
			    	  sum += Float.valueOf(results[i+2]);
			      }
			    }
			    if (count == 0){
			    	list.add("This item has not yet been reviewed.");
			    }
			    else{
			    	//then we know the first line is a rating
					disp_avg.setText("The average rating is: " + sum/count);
			    }
			    
			    ArrayAdapter<String> adapter = 
			    	    new ArrayAdapter<String>(ctx, android.R.layout.simple_list_item_1, list.toArray(new String[list.size()]));
			    all_reviews.setAdapter(adapter);
			    
		
		
	}
	
	

}
