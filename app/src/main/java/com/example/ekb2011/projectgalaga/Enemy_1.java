package com.example.ekb2011.projectgalaga;

import Framework.AppManager;

/**
 * Created by ekb2011 on 2017-11-02.
 */

public class Enemy_1 extends Enemy {
    public Enemy_1(){
        super(AppManager.getInstance().getBitmap(R.drawable.enemy_lowest));
        //this.InitSpriteData(320,90,3,6);

        hp=1;
        speed=1.5f;
        movetype=Enemy.MOVE_PATTERN_2;
    }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        m_BoundBox.set(m_x,m_y,m_x+62,m_y+104);
    }
}
