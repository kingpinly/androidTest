package com.example.androidtest;



import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

public abstract class MainExecutorAsyncTask<Params, Progress, Result> extends
		AsyncTask<Params, Progress, Result> {

	@SuppressLint("NewApi")
	public final AsyncTask<Params, Progress, Result> executeOnMainExecutor(
			Params... params) {

		//3.0之后
		if (Build.VERSION.SDK_INT >= 11) {
			return executeOnExecutor(MainActivity1.mExecutor, params);
		} else {
			return execute(params);
		}

	}

}
