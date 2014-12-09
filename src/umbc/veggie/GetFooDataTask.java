package umbc.veggie;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ExpandableListView;

/**
 * 
 * This class will populate the expandableListView for the MainActivity
 *
 */
public class GetFooDataTask extends AsyncTask<URL, Integer, String> {

	String returned = "this is the default string in getfoodatatask";
	String[] restaurants_urls;

	HashMap<String, List<String>> Restaurants_category;
			ExpandableListView Exp_list;
			RestaurantsAdapter adapter;
			Context ctx;
			List<String> restaurants_list;
	
	public GetFooDataTask(ExpandableListView e_list, Context context, HashMap<String, List<String>> rc, List<String> rests_list, String[] rest_urls) {
		Exp_list = e_list;
		ctx = context;
		Restaurants_category = rc;
		restaurants_list = rests_list; 	//list of just restaurants
		restaurants_urls = rest_urls;	//list of restaurant name, followed by url for getting its food items 
	}

	

	@Override
	protected String doInBackground(URL... params) {
		
		for (int i=0; i<restaurants_urls.length; i=i+2){
			
			String rest = restaurants_urls[i];
			String url = restaurants_urls[i+1];
			
			
			System.out.println("restaurants urls IS populating with: " + restaurants_urls[i]);
			
			
			
			GetMethodEx test = new GetMethodEx( url );
				
				try {
					returned = test.getInternetData();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				//parse each returned into foodlist
				List<String> Foods_list = new ArrayList<String>();
				
				String[] tokens = returned.split("null");
				for (String s:tokens)
				{
					Foods_list.add(s);
				}
				
				Restaurants_category.put(rest, Foods_list);
	
		}
	
		
		return "default ret in getfoodatatask";
	}
	

	

}
