package com.example.myfirstapp;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	boolean loaded = false;
	private HashMap<Integer, String> pendingSamples = new HashMap<Integer, String>();
	private HashMap<String, Integer> loadedSamples = new HashMap<String, Integer>();
	private HashMap<String, Integer> streamVolumes = new HashMap<String, Integer>();
	private static SoundPool soundPool;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
		@Override
		public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
	    	Log.e("Test", "Loaded sound " + sampleId);
	    	loadedSamples.put(pendingSamples.get(sampleId), sampleId);
		}
	});
	
		pendingSamples.put(soundPool.load(this, R.raw.basic_drums, 1), "guitar");
		pendingSamples.put(soundPool.load(this, R.raw.basic_drums, 1), "drum");
		
		
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		AudioManager mgr = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
		float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = streamVolumeCurrent / streamVolumeMax;
        
		setContentView(R.layout.activity_main);
        
//        Button guitarButton = new Button(this);
//        guitarButton.setText("Guitar");
//        guitarButton.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				Log.e("Test", "Played tone from guitar handler:" + loadedSamples.get("tone").toString());
//		    	soundPool.play(loadedSamples.get("tone"), 1.0f, 1.0f, 1, -1, 1f);
//			}
//		});
//        
//        LinearLayout thisLayout = (LinearLayout) findViewById(R.layout.activity_main);
//        thisLayout.addView(guitarButton);
        
       // setContentView(thisLayout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void toneClick(View view) {
		Log.e("Test", "Played tone:" + loadedSamples.get("guitar").toString());
		soundPool.setVolume(streamVolumes.get("guitar"), 1.0f, 1.0f);
    }
    
    public void drumsClick(View view) {
		Log.e("Test", "Played tone:" + loadedSamples.get("drum").toString());
		//streamVolumes.put("drum", soundPool.play(loadedSamples.get("drum"), 1.0f, 1.0f, 1, -1, 1f));
    }
    
    public void startClick(View view) {
    	Log.e("Test", "Starting samples");
		streamVolumes.put("drum", soundPool.play(loadedSamples.get("drum"), 1.0f, 1.0f, 1, -1, 1f));
		streamVolumes.put("guitar", soundPool.play(loadedSamples.get("guitar"), 0f, 0f, 1, -1, 1f));
    }
}
