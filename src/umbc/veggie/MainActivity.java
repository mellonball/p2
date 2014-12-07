package umbc.veggie;

//import com.example.lupolisplash.Main;
//import com.example.lupolisplash.Splash;


import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.media.MediaPlayer;
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

public class MainActivity extends ActionBarActivity {
	
	
	//for extended list view
	HashMap<String, List<String>> Restaurants_category;
	List<String> Foods_list;
	ExpandableListView Exp_list;
	
	RestaurantsAdapter adapter;
	
	HttpExample a = new HttpExample();
	
	//for ratings and review page
	String review;
	int rating;
	int request_code;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	
		
		Thread splashTimer = new Thread(){
			
			public void run() {
				// TODO Auto-generated method stub
				try {
					//making this small reduced the time hello world was on the screen before the splash.
					sleep(1);
					startActivity(new Intent(MainActivity.this, Splash.class)); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally{ /*finish(); */ }
			}
		
		};
				
		splashTimer.start();
		
		
		//for the extendedlistview 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Exp_list = (ExpandableListView) findViewById(R.id.exp_list);
        Restaurants_category = DataProvider.getInfo();
        Foods_list = new ArrayList<String>(Restaurants_category.keySet());
        adapter = new RestaurantsAdapter(this, Restaurants_category, Foods_list);
        Exp_list.setAdapter(adapter);
        
     // for the review and ratings (Second.java) activity
        request_code = 1;
        
        Exp_list.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			
			@Override
			public void onGroupCollapse(int groupPosition) {
				// TODO Auto-generated method stub
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
		        
		        //display popup 
				Toast.makeText(getBaseContext(), 
						Restaurants_category.get(Foods_list.get(groupPosition)).get(childPosition) + 
						" from category " + Foods_list.get(groupPosition) + " is selected ", Toast.LENGTH_LONG).show();
			    
				Intent i = new Intent("umbc.veggie.Second");
				//can I PUT THINGS IN this intent that Second can access??
				//i.putExtra("fooditem", Restaurants_category.get(Foods_list.get(groupPosition)).get(childPosition));
				
				
				startActivityForResult(i, request_code);
				
				return false;
			}
		});
    }      
        
        
   public void onActivityResult(int requestcode, int resultcode, Intent data){
    	 
        if (request_code == requestcode){
        	if (resultcode == RESULT_OK)
        	{
        		int rate = data.getIntExtra("rating", -1);
        		String review = data.getStringExtra("review");
        		String item = data.getStringExtra("selectedItem");
        		
        		//String message = "You rated: " + rate;
        		
        		String message = "You rated " + 
        		item + 
        		" : " + rate;
				
        		
        		//Toast.makeText(getBaseContext(), data.getData().toString(), Toast.LENGTH_SHORT).show();
        		Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
}
