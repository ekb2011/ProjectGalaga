package com.example.ekb2011.projectgalaga;

import Framework.AppManager;

/**
 * Created by ekb2011 on 2017-11-03.
 */

public class Missile_Player extends Missile {
    int display_height=2792;
    public Missile_Player(int x, int y) {
        super(AppManager.getInstance().getBitmap(R.drawable.missile_1));
        this.SetPosition(x,y);
    }
    public void Update(){
        // 미사일이 위로 발사되는 효과를 준다.
        m_y-=10;
        if(m_y >display_height) {
            state = STATE_OUT;
        }

        m_BoundBox.left=m_x;
        m_BoundBox.top=m_y;
        m_BoundBox.right=m_x+43;
        m_BoundBox.bottom=m_y+43;

    }
}
