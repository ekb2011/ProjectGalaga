package com.example.ekb2011.projectgalaga;

import Framework.AppManager;

/**
 * Created by ekb2011 on 2017-11-04.
 */

public class Missile_Enemy extends Missile {
    int display_width=1440;
    int display_height=2792;

    public Missile_Enemy(int x, int y) {
        super(AppManager.getInstance().getBitmap(R.drawable.missile_2));

        this.SetPosition(x,y);
    }

    public void Update(){
        m_y+=10;
        if (m_y>display_height){
            state=STATE_OUT;
        }
        m_BoundBox.set(m_x,m_y,m_x+43,m_y+43);
    }
}
