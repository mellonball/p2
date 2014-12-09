package umbc.veggie;

import java.text.DateFormat;
import java.util.Date;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;


public class Splash extends Activity {

	String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        MediaPlayer buttonClickSound = MediaPlayer.create(Splash.this, R.raw.click);
        buttonClickSound.start();
                
        Thread splashTimer = new Thread(){
			
			public void run() {
				// TODO Auto-generated method stub
				try {
					//this controls how long the splash screen stays up
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally{ finish(); }
			}
		
		};
		splashTimer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }
}

