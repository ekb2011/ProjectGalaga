package com.example.ekb2011.projectgalaga;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import Framework.AppManager;
import Framework.GameView;

/**
 * Created by ekb2011 on 2017-11-04.
 */

public class MainActivity extends Activity {
    public int display_width;
    public int display_height;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AppManager.getInstance().setActivity(this);
        setContentView(new GameView(this));

    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }
}
