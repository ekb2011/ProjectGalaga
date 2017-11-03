package com.example.ekb2011.projectgalaga;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import Framework.AppManager;
import Framework.GraphicObject;
import Framework.IState;

/**
 * Created by ekb2011 on 2017-11-02.
 */

public class GameState implements IState {

    private Player m_player;
    private BackGround m_background;
    private GraphicObject m_keypad;
    private Enemy_1 enem=new Enemy_1();
    ArrayList<Enemy> m_enemlist=new ArrayList<>();
    long LastRegenEnemy=System.currentTimeMillis();
    Random randEnem=new Random();
    public void MakeEnemy(){
        if (System.currentTimeMillis()-LastRegenEnemy>=1000){
            LastRegenEnemy=System.currentTimeMillis();

            Enemy enem=new Enemy_1();
            enem.SetPosition(randEnem.nextInt(280),-60);
            enem.movetype=randEnem.nextInt(3);
            m_enemlist.add(enem);
        }
    }
    public GameState(){
        AppManager.getInstance().m_gamestate = this;
    }

    @Override
    public void Init() {
        m_player=new Player(AppManager.getInstance().getBitmap(R.drawable.player));
        m_background=new BackGround();
        m_keypad=new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.keypad));
        m_keypad.SetPosition(0,460-120);
    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {
        long GameTime=System.currentTimeMillis();
        m_player.Update(GameTime);
        m_background.Update(GameTime);
        for (Enemy enem:m_enemlist){
            enem.Update(GameTime);
        }
        MakeEnemy();
    }

    @Override
    public void Render(Canvas canvas) {
        m_background.Draw(canvas);
        for (Enemy enem:m_enemlist){
            enem.Draw(canvas);
        }
        m_player.Draw(canvas);
        m_keypad.Draw(canvas);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int x=m_player.GetX();
        int y=m_player.GetY();
        if (keyCode==KeyEvent.KEYCODE_DPAD_LEFT){
            m_player.SetPosition(x-1,y);
        }
        if (keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){
            m_player.SetPosition(x+1,y);
        }
        if (keyCode==KeyEvent.KEYCODE_DPAD_UP){
            m_player.SetPosition(x,y-1);
        }
        if (keyCode==KeyEvent.KEYCODE_DPAD_DOWN){
            m_player.SetPosition(x,y+1);
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
