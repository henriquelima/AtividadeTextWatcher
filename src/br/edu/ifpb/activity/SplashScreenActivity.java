package br.edu.ifpb.activity;

import com.example.atividadetextwatcher.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class SplashScreenActivity extends Activity implements Runnable {

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_splash_screen);

	        Handler SplashScreen = new Handler();
	        SplashScreen.postDelayed(SplashScreenActivity.this, 3000);
	    }
	    @Override
	    public void run() {
	        Intent intent = new Intent(this, BuscarNomeActivity.class);
	        this.startActivity(intent);
	        this.finish();
	    }

	
}
