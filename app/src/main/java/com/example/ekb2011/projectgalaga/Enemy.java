package com.example.ekb2011.projectgalaga;

import android.graphics.Bitmap;

import Framework.SpriteAnimation;

/**
 * Created by ekb2011 on 2017-11-02.
 */

public class Enemy extends SpriteAnimation {
    public int hp;
    public float speed;
    public static final int MOVE_PATTERN_1=0;
    public static final int MOVE_PATTERN_2=1;
    public static final int MOVE_PATTERN_3=2;
    protected int movetype;
    public Enemy(Bitmap bitmap) {
        super(bitmap);
    }
    void Move(){
        if (movetype==MOVE_PATTERN_1){
            if (m_y<=200){
                m_y+=speed;
            }
            else{
                m_y+=speed*2;
            }
        }
        else if (movetype==MOVE_PATTERN_2){
            if (m_y<=200){
                m_y+=speed;
            }
            else{
                m_x+=speed;
                m_y+=speed;
            }
        }
        else if (movetype==MOVE_PATTERN_3){
            if (m_y<=200){
                m_y+=speed;
            }
            else{
                m_x-=speed;
                m_y+=speed;
            }
        }
    }
    void Attack(){

    }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        Move();
    }
}
