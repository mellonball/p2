package umbc.veggie;



import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;

public class MainActivity extends ActionBarActivity{
	
	ExpandableListView Exp_list;
	HashMap<String, List<String>> Restaurants_category;
	List<String> restaurants_list;
	
	Button seereviews;
		
	RestaurantsAdapter adapter;
		
	//for ratings and review page
	String review;

	int request_code;
	String item = "default food item in Mainactivity";
	String restaurant = "default rest in MainActivity";
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	
		
		Thread splashTimer = new Thread(){
			
			public void run() {
				try {
					//time before the splash.
					sleep(1);
					startActivity(new Intent(MainActivity.this, Splash.class)); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally{ /*finish(); */ }
			}
		
		};
				
		splashTimer.start();
		
		
		//for the expandablelistview 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //button for seeing the reviews	on main page ---- NOT USING ANYMORE
        //seereviews = (Button) findViewById(R.id.bn_seereviews);
        //seereviews.setOnClickListener(this);
        
        Exp_list = (ExpandableListView) findViewById(R.id.exp_list);
      
        Context ctx = this;
        Restaurants_category = new HashMap<String, List<String>>();
        restaurants_list = new ArrayList<String>();
        
        GetRestaurantListDataTask aTask = new GetRestaurantListDataTask(Exp_list, ctx, Restaurants_category, restaurants_list);
                
        
        
        try {
			
        	adapter = new RestaurantsAdapter(ctx, Restaurants_category, restaurants_list);
            Exp_list.setAdapter(adapter);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        aTask.execute();
        
        // for the review and ratings (Second.java) activity
        request_code = 1;
        
        Exp_list.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			
			@Override
			public void onGroupCollapse(int groupPosition) {
				
				MediaPlayer buttonClickSound = MediaPlayer.create(MainActivity.this, R.raw.click);
		        buttonClickSound.start();
			}
		});
        
        Exp_list.setOnGroupExpandListener(new OnGroupExpandListener() {
			
			@Override
			public void onGroupExpand(int groupPosition) {
				// TODO Auto-generated method stub
				MediaPlayer buttonClickSound = MediaPlayer.create(MainActivity.this, R.raw.click);
		        buttonClickSound.start();
			}
		});
        Exp_list.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				
				// play a sound
				MediaPlayer buttonClickSound = MediaPlayer.create(MainActivity.this, R.raw.click);
		        buttonClickSound.start();
		       
		        
		        /*
		        //display popup Toast
				Toast.makeText(getBaseContext(), 
						Restaurants_category.get(restaurants_list.get(groupPosition)).get(childPosition) + 
						" from category " + restaurants_list.get(groupPosition) + " is selected ", Toast.LENGTH_LONG).show();
				*/
		        
		        
				//open browser and visit pages that have rotating menus since we can't scrape them at this time
				String[] test = Restaurants_category.get(restaurants_list.get(groupPosition)).get(childPosition).split("null");
				
				if (test[0].equals("All foods served at Fresh Fusions are vegan, and are served on a 7 day rotating menu that changes each semester. Click here to go to see what is on today's menu"))
				{	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.dineoncampus.com/umbc/show.cfm?cmd=menus2&venueName=Fresh%20Fusions"));
		        	startActivity(browserIntent);
				}
				else if (test[0].equals("The menu at True Grit's rotates daily. Click here to see what is on today's menu")) {
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.dineoncampus.com/umbc/show.cfm?cmd=menus2&venueName=True%20Grit%27s"));
		        	startActivity(browserIntent);
				}
				else if (test[0].equals("The menu at Skylight rotates daily. Click here to see what is on today's menu")) {
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.dineoncampus.com/umbc/show.cfm?cmd=menus2&venueName=Skylight%20Room"));
		        	startActivity(browserIntent);
				}
				else if (test[0].equals("Check the daily soup for vegetarian or vegan options") ||
						test[0].equals("Ask the server for vegan or vegetarian sushi options")){
					;
				}
				// for static menu options let people rate the food item below
				else{
					
				Intent i = new Intent("umbc.veggie.Second");
				
				
				item = Restaurants_category.get(restaurants_list.get(groupPosition)).get(childPosition);
				restaurant = restaurants_list.get(groupPosition);
				
				i.putExtra("restaurant", restaurant);
				i.putExtra("item", item);
				
				//waiting for rating and review of selected food item
				startActivityForResult(i, request_code);
				}
				
		        System.out.println("DEBUG in Main Activity!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		        System.out.println(Restaurants_category.get(restaurants_list.get(groupPosition)).get(childPosition));
		        
				return false;
			}
		});
    }      
        
        
   public void onActivityResult(int requestcode, int resultcode, Intent data){
    	 
        if (request_code == requestcode){
        	if (resultcode == RESULT_OK)
        	{
        		float rate = data.getFloatExtra("rating", -1);
        		String review = data.getStringExtra("review");
        		
        		String message = "You rated " + 
        		item + 
        		" : " + rate;
        		
        		//Toast.makeText(getBaseContext(), data.getData().toString(), Toast.LENGTH_SHORT).show();
        		Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
        		
        		//make a post here with (text) restaurant name, (text) item, (float) rating, (text) review
        		//will need to be an asynctask
        		PostReviewsDataTask aTask = new PostReviewsDataTask(restaurant, item, rate, review);
        		aTask.execute();
        	}
        		
        }
        	
      
		
		
    }
   

    	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) 
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

/* NOT USING ANYMORE for to reviews button on main page
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent("android.intent.action.DisplayReviews"));
	}
 */   
    
}
