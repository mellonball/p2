package umbc.veggie;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HttpExample extends Activity {
	
	TextView httpStuff ;
	
	String returned = "default returned string in HttpExample class";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpex);
		
		
		
		httpStuff = (TextView) findViewById(R.id.tvHttp);
		GetFooDataTask aTask = new GetFooDataTask(httpStuff);
		
		//create task, call doinbackground, get the result. update the textview in HERE
				aTask.execute();
		
		try {
			
			httpStuff.setText(returned);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
