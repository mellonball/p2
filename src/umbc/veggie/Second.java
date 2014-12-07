package umbc.veggie;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class Second extends Activity{
	
	Button submit;
	EditText review;
	RatingBar rating;
	
	
	/**
	 * all activities must implement this method 
	 * initialize essential components of your activity
	 * this is where you MUST call setContentView() to define the layout for the activity's UI
	 */
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_layout);
		
		submit = (Button) findViewById(R.id.bnsubmit);
		review = (EditText) findViewById(R.id.etreview);
		rating = (RatingBar) findViewById(R.id.rbrating);
		rating.setStepSize((float) 1.0);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//get info from mainactivity
				
				
				// send data back to mainactivity
				int rate = rating.getNumStars();
				String reviewString = review.getText().toString();
				
				Intent data = new Intent();
				data.putExtra("rating", rate);
				data.putExtra("review", reviewString);
				
				//data.getExtras();	
				//CAN YOU PASS INFO 2 WAYS IN AN INTENT? CAN I GET NAME OF CHILD CLICKED?
				data.putExtra("selectedItem", data.getStringExtra("fooditem"));
				
				
				//data.setData(Uri.parse(reviewString));  //for now his only passes the review
				setResult(RESULT_OK, data);
				finish();
			}
		});
	}

}
