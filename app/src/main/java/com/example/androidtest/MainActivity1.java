package com.example.androidtest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity1 extends ActionBarActivity {

    private LinearLayout layout;
    public static ExecutorService mExecutor = Executors.newCachedThreadPool();
//    public static ExecutorService mExecutor = Executors.newFixedThreadPool(5);
    		

    
    private void task(final int i){
        MainExecutorAsyncTask<String, Void, String> task = new MainExecutorAsyncTask<String, Void, String>() {
			
			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				if(i == 2){
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return i+"";
			}
			
			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				Log.i("king", "result "+result);
				super.onPostExecute(result);
			}
		};
		task.executeOnMainExecutor();
    }

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        
//        task(0);
//        task(1);
//        task(2);
//        task(3);
//        task(4);

        
//        word();
        
//        layout = (LinearLayout)findViewById(R.id.layout);
//        final TabAnimationView view = new TabAnimationView(this);
//        view.setBackgroundColor(Color.parseColor("#abcdef"));
//        layout.addView(view);
        
//        final TabAnimationView view = (TabAnimationView)findViewById(R.id.tab_view);
//        
//        
//        final Handler handler = new Handler();
//        Runnable  run = new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				view.postInvalidate();
//				handler.postDelayed(this, 200);
//			}
//		};
//		handler.post(run);
        

//        Animation anim = AnimationUtils.loadAnimation(this, R.anim.a1);
//        ImageView animationTopRightView = (ImageView)this.findViewById(R.id.animation_top_right);
//        animationTopRightView.startAnimation(anim);
        

//Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.a2);
//ImageView animationTopLeftView = (ImageView)this.findViewById(R.id.animation_top_left); 
//animationTopLeftView.startAnimation(anim2);

//Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.a3);
//ImageView animationTopLeftView = (ImageView)this.findViewById(R.id.animation_top_left); 
//animationTopLeftView.startAnimation(anim2);
        
    }
    
    public void word(){
//    	 String word = getWord();
//         word = word.replace("   ", "\",\"");
//         word = word.replace("  ", "\",\"");
//         word = word.replace(" ", "\",\"");
//         word = word.replace(")", "\r\n");
//         ((TextView)findViewById(R.id.tv)).setText(word);
//         Log.i("king", "word "+word);
    }


    public String getWord(){
    	try {
			InputStream open = getAssets().open("word.txt");
			InputStreamReader inputStreamReader = new InputStreamReader(open);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			String result = "";
			while((line = bufferedReader.readLine()) != null){
				result += line;
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return "";
    }
}
