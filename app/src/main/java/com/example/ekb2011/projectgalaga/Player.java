package com.example.ekb2011.projectgalaga;

import android.graphics.Bitmap;
import android.graphics.Rect;

import Framework.GraphicObject;

/**
 * Created by ekb2011 on 2017-11-02.
 */

public class Player extends GraphicObject {


    Rect m_BoundBox = new Rect();

    int m_Life = 3;
    int m_power = 1;
    int hp=10;
    public Player(Bitmap bitmap) {
        super(bitmap);
        // 애니메이션 정보를 세팅해준다.

        //this.InitSpriteData(104	, 372, 3, 6);
        // 초기 위치값을 설정해준다.
        this.SetPosition(600, 2400);
    }



    public int GetPower(){
        return m_power;
    }
    public void SetPower(int _power){
        m_power = _power;
    }
    public int GetHP(){
        return hp;
    }
    public void setHP(int _hp){
        hp=_hp;
    }
    // 움직이는 상태값을 가지는 플래그
    private boolean bMove = false;
    // 방향값을 가지는 변수
    private int _dirX = 0;
    private int _dirY = 0;

    public int getLife(){
        return m_Life;
    }
    public void destroyPlayer(){
        m_Life--;
    }
    public void hurt(){
        hp--;
    }


    // onTouchEvent 에서 DOWN 메세지를 받았을때 호출할 메서드
    /*public void startMove(int dirX,int dirY){
        // 움직임을 활성화시켜준다
        bMove = true;
        // 방향값을 저장한다
        _dirX = dirX;
        _dirY = dirY;
    }
    // onTouchEvent 에서 UP 메세지를 받았을때 호출할 메서드
    public void stopMove(){
        // 움직임을 비활성화시켜준다
        bMove =false;
        // 방향값을 초기화시킨다
        _dirX = 0;
        _dirY = 0;
    }*/
    // 프레임워크 Update에서 지속적으로 호출시킬메서드

    public void Update(long GameTime){
        super.Update(GameTime);
        // 움직임이 활성화되있을경우
        if(bMove){
            this.m_x += _dirX;
            this.m_y += _dirY;
        }

        m_BoundBox.set(m_x,m_y,m_x+62,m_y+104);
    }

}
