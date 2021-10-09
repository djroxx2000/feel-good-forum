package com.example.feelgoodmentalwellness.Utility;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.example.feelgoodmentalwellness.Model.AppConfig;

public class App extends Application {
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;

    private static final String TAG = "App123";
    @Override
    public void onCreate() {
        super.onCreate();
        mAuth= FirebaseAuth.getInstance();
        sharedPreferences= getSharedPreferences(AppConfig.SHARED_PREF, Context.MODE_PRIVATE);

        if (mAuth.getCurrentUser() != null && sharedPreferences.getString(AppConfig.PROFILE_STATE, "com.example.feelgoodmentalwellness.PROFILE_STATE").equals("com.example.feelgoodmentalwellness.PROFILE_STATE")) {
            Log.d(TAG, "onAppGotoForeground: ");
        } else if (mAuth.getCurrentUser() != null) {
            Log.d(TAG, "onCreate: ");
            AppVisibilityDetector.init(App.this, new AppVisibilityDetector.AppVisibilityCallback() {
                @Override
                public void onAppGotoForeground() {
                    //app is from background to foreground
                    new Util().setOnline();
                }
                @Override
                public void onAppGotoBackground() {
                    //app is from foreground to background
                    new Util().setOffline();
                }
            });
        }
    }


}
