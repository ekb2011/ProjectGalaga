package com.example.ekb2011.projectgalaga;

import Framework.AppManager;

/**
 * Created by ekb2011 on 2017-11-03.
 */

public class Enemy_3 extends Enemy {
    public Enemy_3(){
        super(AppManager.getInstance().getBitmap(R.drawable.enemy_high));
        //this.InitSpriteData(300,90,3,6);
        hp=3;
        speed=3.5f;
        movetype=Enemy.MOVE_PATTERN_3;
    }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        m_BoundBox.set(m_x,m_y,m_x+62,m_y+104);
    }
}
