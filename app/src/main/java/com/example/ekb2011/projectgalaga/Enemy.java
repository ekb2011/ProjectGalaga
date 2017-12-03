package com.example.ekb2011.projectgalaga;

import android.graphics.Bitmap;
import android.graphics.Rect;

import Framework.AppManager;
import Framework.GraphicObject;

/**
 * Created by ekb2011 on 2017-11-02.
 */

public class Enemy extends GraphicObject {
    protected int hp;
    protected float speed;
    public static final int MOVE_PATTERN_1=0;
    public static final int MOVE_PATTERN_2=1;
    public static final int MOVE_PATTERN_3=2;
    protected int movetype;

    public static final int STATE_NORMAL=0;
    public static final int STATE_OUT=1;

    public int state=STATE_NORMAL;
    Rect m_BoundBox=new Rect();
    long LastShoot=System.currentTimeMillis();



    int display_width=1440;
    int display_height=2792;



    public Enemy(Bitmap bitmap) {
        super(bitmap);
    }
    public void Damage(int damage){
        hp-=damage;
    }
    public int GetHP(){
        return hp;
    }
    void Move(){
        if (movetype==MOVE_PATTERN_1){
            if (m_y<=(display_height-40)/2){
                m_y+=speed*3;
            }
            if (m_y>display_height || m_x>display_width){
                state=STATE_OUT;
            }
            else{
                m_y+=speed*2;
            }
        }
        else if (movetype==MOVE_PATTERN_2){
            if (m_y<=(display_height-40)/2){
                m_y+=speed*3;
            }
            if (m_y>display_height || m_x>display_width){
                state=STATE_OUT;
            }
            else{
                m_x+=speed*2;
                m_y+=speed*2;
            }
        }
        else if (movetype==MOVE_PATTERN_3){
            if (m_y<=(display_height-40)/2){
                m_y+=speed*3;
            }
            if (m_y>display_height || m_x>display_width){
                state=STATE_OUT;
            }
            else{
                m_x-=speed*2;
                m_y+=speed*2;
            }
        }
    }
    void Attack(){
        if (System.currentTimeMillis()-LastShoot>=2000){
            LastShoot=System.currentTimeMillis();
            AppManager.getInstance().m_gamestate.m_enemmslist.add(new Missile_Enemy(m_x+10, m_y+104));
        }
    }


    public void Update(long GameTime) {

        Attack();
        Move();
    }
}
