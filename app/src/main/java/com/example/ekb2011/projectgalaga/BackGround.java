package com.example.ekb2011.projectgalaga;

import android.graphics.Canvas;

import Framework.AppManager;
import Framework.GraphicObject;

/**
 * Created by ekb2011 on 2017-11-02.
 */

public class BackGround extends GraphicObject {

    private float m_scroll=-2000+480;

    public BackGround() {

        super(null);
        m_bitmap= AppManager.getInstance().getBitmap(R.drawable.galaga_background);
        SetPosition(0,(int)m_scroll);
    }
    @Override
    public void Draw(Canvas canvas) {
        super.Draw(canvas);
        canvas.drawBitmap(m_bitmap, m_x, m_y, null);
    }

}
