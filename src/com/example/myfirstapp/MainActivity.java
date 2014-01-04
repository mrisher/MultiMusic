package com.example.myfirstapp;

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
import android.widget.EditText;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	boolean loaded = false;
	int soundID = -1;
	int soundID2 = -1;
	private static SoundPool soundPool;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundID = soundPool.load(this, R.raw.drum, 1);
        soundID2 = soundPool.load(this, R.raw.tone, 1);
        
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void toneClick(View view) {
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		AudioManager mgr = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
		float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = streamVolumeCurrent / streamVolumeMax;

    	soundPool.play(soundID2, 1.0f, 1.0f, 1, -1, 1f);
    }
    
    public void drumsClick(View view) {
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	//EditText editText = (EditText) findViewById(R.id.edit_message);
    	//String message = editText.getText().toString();
    	//intent.putExtra(EXTRA_MESSAGE, message);
    	
		
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		AudioManager mgr = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
		float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = streamVolumeCurrent / streamVolumeMax;

    	soundPool.play(soundID, 1.0f, 1.0f, 1, -1, 1f);
    	soundPool.play(soundID2, 1.0f, 1.0f, 1, -1, 1f);
    	Log.e("Test", "Played sound");
    	
//		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
//			@Override
//			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
//				loaded = true;
//				AudioManager mgr = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
//				float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
//				float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//				float volume = streamVolumeCurrent / streamVolumeMax;
//
//		    	soundPool.play(sampleId, 1.0f, 1.0f, 1, -1, 1f);
//		    	Log.e("Test", "Played sound");
//
//			}
//		});
		//int soundID = soundPool.load(this, R.raw.tone, 1);
		//int soundID = soundPool.load(this, R.raw.drum, 1);
//		MediaPlayer mp = MediaPlayer.create(this, R.raw.drum);
//		mp.start();
	    
    	//startActivity(intent);
    }
    
}
