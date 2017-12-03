package com.example.ekb2011.projectgalaga;

import android.graphics.Bitmap;
import android.graphics.Rect;

import Framework.GraphicObject;

/**
 * Created by ekb2011 on 2017-11-03.
 */

public class Missile extends GraphicObject {

    public static final int STATE_NORMAL = 0;
    public static final int STATE_OUT = 1;

    public int state =  STATE_NORMAL;

    Rect m_BoundBox = new Rect();

    public Missile(Bitmap bitmap) {
        super(bitmap);
        // TODO Auto-generated constructor stub
    }

    public void Update() {
        // TODO Auto-generated method stub

    }

}
