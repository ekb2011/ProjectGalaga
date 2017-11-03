package com.example.ekb2011.projectgalaga;

import android.graphics.Canvas;

import Framework.AppManager;
import Framework.GraphicObject;

/**
 * Created by ekb2011 on 2017-11-02.
 */

public class BackGround extends GraphicObject {
    static final float SCROLL_SPEED=0.2f;
    private float m_scroll=-2000+480;

    @Override
    public void Draw(Canvas canvas) {
        canvas.drawBitmap(m_bitmap, m_x, m_y, null);
    }

    public BackGround() {
        /*super(AppManager.getInstance().getBitmap(R.drawable.galaga_background));
        SetPosition(0,(int)m_scroll);*/
        super(null);
        m_bitmap= AppManager.getInstance().getBitmap(R.drawable.galaga_background);
        SetPosition(0,(int)m_scroll);
    }
    void Update(long GameTime){
        m_scroll=m_scroll+SCROLL_SPEED;
        if (m_scroll>=0){
            m_scroll=0;
        }
        SetPosition(0,(int)m_scroll);
    }
}
