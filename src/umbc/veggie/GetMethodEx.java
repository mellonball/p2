package umbc.veggie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetMethodEx {

	public String getInternetData() throws Exception{
		BufferedReader in = null;
		String data = null;
		try{
			
			HttpClient client = new DefaultHttpClient();
			URI website = new URI("http://userpages.umbc.edu/~cpatel4/CMSC331/331.php?Restaurant=3");
			HttpGet request = new HttpGet();
			request.setURI(website);
			
			HttpResponse response = client.execute(request);
			
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String l = "";
			String nl = System.getProperty("line.seperator");
			
			while ((l = in.readLine()) != null){
				sb.append(l + nl);
			}
			
			in.close();
			data = sb.toString();
			System.out.println("Data is " + data);
			
			return data;
			
		}finally{
			if (in != null){
				//force close
				try{
					in.close();
					return data;
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
