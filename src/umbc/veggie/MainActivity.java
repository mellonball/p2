package umbc.veggie;

//import com.example.lupolisplash.Main;
//import com.example.lupolisplash.Splash;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {
	
	HashMap<String, List<String>> Restaurants_category;
	List<String> Foods_list;
	ExpandableListView Exp_list;
	
	RestaurantsAdapter adapter;
	
	
	//ListView lvName;
	/*String[] name = {"MONDO SUBS", "       Vegetable Wrap with Hummus (Vegan)", 
			"FAMOUS FAMIGLIA", "       Pasta and Marinara Sauce and Garlic Knots (Vegan)", "       Fresca Pizza and Pizza Bianca (Vegetarian)", 
			"OUTTAKES", "       Edamame Guacamole with Pita Triangles", "       Pita and Hummus", "       Edamame (Vegan)", 
			"STARBUCKS COFFEE", "       Juice and Coffee without flavoring syrups (Vegan)", 
			"SALSARITA'S FRESH CANTINA", "       Veggie Taco Salad without the Tortilla", "       Tortilla Chips", "       Guacamole and Salsa", "       Bare Burrito Bowl (Vegan)*bean seasoning contains animal-derived preservatives. Tortillas do not contain lard, but do contain casein (milk product) & animal-derived enzymes.", 
			"MESQUITE RANCH BBQ AND GRILL", "       Black Bean Garden Burger, without the roll", "       Fries", "       Whole Fruit. (Vegan)", 
			"JOW JING", "       Lo-Mein", "-Tofu", "       Ask the server for vegan or vegetarian sushi options. (Vegan)", 
			"FRESH FUSIONS", "       The menu rotates daily and always provides a vegan and vegetarian option.", 
			"CHICK-FIL-A", "       Garden Salad, without dressing", "       Fruit Cup", "       Waffle Fries (Vegan)", 
			"AU BON PAIN", "       Oatmeal", "       Fruit Cups", "       Black Bean Soup", "       Chick Peas and Tomato Salad", "       Cucumber Salad (Vegan)", "       Check the daily soup for vegetarian or vegan options."};
	*/
	
	

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
		
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Exp_list = (ExpandableListView) findViewById(R.id.exp_list);
        Restaurants_category = DataProvider.getInfo();
        Foods_list = new ArrayList<String>(Restaurants_category.keySet());
        adapter = new RestaurantsAdapter(this, Restaurants_category, Foods_list);
        Exp_list.setAdapter(adapter);
        

		
		
		
		//lvName = (ListView) findViewById(R.id.lv_Name);
		//lvName.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, name));
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
