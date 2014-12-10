package umbc.veggie;
import java.net.URL;
import java.util.HashMap;
import java.util.List;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.ExpandableListView;


public class GetRestaurantListDataTask extends AsyncTask<URL, Integer, String>{

	String getRestaurantsURL = "http://userpages.umbc.edu/~cpatel4/CMSC331/Project2.php";
	String returned = "this is the default string in GetRestaurantListDataTask";
	
	Context ctx;
	
	HashMap<String, List<String>> Restaurants_category;
	List<String> restaurants_list;
	ExpandableListView Exp_list;
		
		
	public GetRestaurantListDataTask(ExpandableListView e_list, Context context, HashMap<String, List<String>> restaurants_category2, List<String> rests_list){
		Exp_list = e_list;
		ctx = context;
		Restaurants_category = restaurants_category2;  //empty
		restaurants_list = rests_list;                   //empty 
	}
	
	@Override
	protected String doInBackground(URL... params) {
		GetMethodEx test = new GetMethodEx(getRestaurantsURL);
		
		
		try {
			 returned = test.getInternetData();
			
		} catch (Exception e) {
			e.printStackTrace();
		}



		return returned;
	}
	
	protected void onPostExecute(String result){
		//parse returned into restaurants_list
		String[] r_list = result.trim().split("null");
		
		for (int i = 0; i<r_list.length; i=i+2){
			restaurants_list.add(r_list[i].trim());
			System.out.println("getrestDL: "+r_list[i]);
		}
		
		GetFooDataTask aTask = new GetFooDataTask(Exp_list, ctx, Restaurants_category, restaurants_list, r_list);
		aTask.execute();			
	}
	
	public List<String> getRestaurantsList() {
		return restaurants_list;
	}
	

}
