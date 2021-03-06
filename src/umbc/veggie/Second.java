package umbc.veggie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class Second extends Activity{
	
	Button submit;
	EditText review;
	RatingBar rating;
	Bundle extras;
	float rate;
	TextView restaurant;
	TextView item;
	ListView all_reviews;
	Context ctx;
	/**
	 * all activities must implement this method 
	 * initialize essential components of your activity
	 * this is where you MUST call setContentView() to define the layout for the activity's UI
	 */
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_layout);
		
		Intent it = getIntent();
		restaurant = (TextView) findViewById(R.id.restaurant);
		restaurant.setText(it.getStringExtra("restaurant"));
		String restaurantname = it.getStringExtra("restaurant");
		
		item = (TextView) findViewById(R.id.foodname);
		item.setText(it.getStringExtra("item"));
		String fooditem = it.getStringExtra("item");
		
		
		submit = (Button) findViewById(R.id.bnsubmit);
		review = (EditText) findViewById(R.id.etreview);
		rating = (RatingBar) findViewById(R.id.rating);
		//rating.setStepSize((float) 0.5);  	can change this to 1.0 if don't want half star ratings
		
		ctx = this;
		all_reviews = (ListView) findViewById(R.id.listreviews);
		TextView txt = (TextView) findViewById(R.id.avgrating);
		/// HERE ASYNCTTASK TO GET reviews and rating asap.
		GetReviewsTask aTask = new GetReviewsTask(restaurantname, fooditem, txt, all_reviews, (Context) this);
		aTask.execute();
		
		rating.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

	        public void onRatingChanged(RatingBar ratingBar, float rating,  boolean fromUser) {

	            rate = rating;

	        }

	    });

	  
		
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//get info from mainactivity
				
				String reviewString = review.getText().toString();
				
			    if (reviewString.trim().length() == 0){
			    	Toast.makeText(getBaseContext(), "You did not enter a review. Please enter both a review and rating.", Toast.LENGTH_SHORT).show();
			    }
			    else{
			    				
				Intent data = new Intent();
				data.putExtra("rating", rate);
				data.putExtra("review", reviewString);

				setResult(RESULT_OK, data);
				finish();
			    }
			}
		});
	}
	
	

}
