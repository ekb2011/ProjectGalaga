package com.example.ekb2011.projectgalaga;

import Framework.AppManager;
import Framework.GraphicObject;

/**
 * Created by ekb2011 on 2017-11-27.
 */

public class Effect_Explosion extends GraphicObject {

    public Effect_Explosion(int x, int y) {
        super(AppManager.getInstance().getBitmap(R.drawable.explosion));
        m_x=x;
        m_y=y;
        mbReply=false;
    }

    public void Update(long GameTime) {
        super.Update(GameTime);

    }
}
