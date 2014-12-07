package umbc.veggie;

import java.net.URL;

import android.os.AsyncTask;
import android.widget.TextView;

public class GetFooDataTask extends AsyncTask<URL, Integer, String> {

	TextView httpStuff;
	String returned = "this is the default string in getfoodatatask";
	
	public GetFooDataTask(TextView httpStuff2) {
		httpStuff = httpStuff2;
	}


	@Override
	protected String doInBackground(URL... params) {
		// TODO Auto-generated method stub
		
		//set up new get method ex
		GetMethodEx test = new GetMethodEx();
				
				
				try {
					returned = test.getInternetData();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
		
		
		return returned;
	}
	

	protected void onPostExecute(String result){
				httpStuff.setText(result);
		
	}

}
