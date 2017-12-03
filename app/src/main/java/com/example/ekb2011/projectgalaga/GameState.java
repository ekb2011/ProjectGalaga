package com.example.ekb2011.projectgalaga;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import Framework.AppManager;
import Framework.CollisionManager;
import Framework.IState;

/**
 * Created by ekb2011 on 2017-11-02.
 */

public class GameState implements IState {

    private Player m_player;
    private BackGround m_background;
    long LastShoot=System.currentTimeMillis()+10;

    ArrayList<Missile_Player> m_pmslist = new ArrayList<Missile_Player>();
    ArrayList<Missile_Enemy> m_enemmslist = new ArrayList<Missile_Enemy>();

    ArrayList<Enemy> m_enemlist = new ArrayList<Enemy>();
    ArrayList<Effect_Explosion> m_explist=new ArrayList<Effect_Explosion>();
    long LastRegenEnemy=System.currentTimeMillis();

    Random randEnem=new Random();
    private static GameState s_instance;
    private int score=0;
    public GameState(){
        AppManager.getInstance().m_gamestate = this;
    }
    public static GameState getInstance(){
        if(s_instance == null){
            s_instance = new GameState();
        }
        return s_instance;
    }
    public void MakeEnemy(){
        if (System.currentTimeMillis()-LastRegenEnemy>=1000){
            LastRegenEnemy=System.currentTimeMillis();
            int enemtype=randEnem.nextInt(3);
            Enemy enem=null;
            if (enemtype==0){
                enem=new Enemy_1();
            }
            else if (enemtype==1){
                enem=new Enemy_2();
            }
            else if (enemtype==2){
                enem=new Enemy_3();
            }


            enem.SetPosition(randEnem.nextInt(800),-100);
            enem.movetype=randEnem.nextInt(3);
            m_enemlist.add(enem);
        }
    }


    @Override
    public void Init() {
        m_player=new Player(AppManager.getInstance().getBitmap(R.drawable.player));
        m_background=new BackGround();

    }

    @Override
    public void Destroy() {
        m_enemlist.clear();
        m_enemmslist.clear();
        m_pmslist.clear();
    }

    @Override
    public void Update() {
        long GameTime=System.currentTimeMillis();
        m_player.Update(GameTime);

        if (System.currentTimeMillis()-LastShoot>=500){
            LastShoot=System.currentTimeMillis();
            m_pmslist.add(new Missile_Player(m_player.GetX(), m_player.GetY()+150));
        }
        for (int i=m_pmslist.size()-1;i>=0;i--){
            Missile_Player pms=  m_pmslist.get(i);
            pms.Update();
            if (pms.state==Missile.STATE_OUT){
                m_pmslist.remove(i);
            }
        }
        for (int i=m_enemmslist.size()-1;i>=0;i--){
            Missile enemms=m_enemmslist.get(i);
            enemms.Update();
            if (enemms.state==Missile.STATE_OUT){
                m_enemmslist.remove(i);
            }
        }
        for (int i=m_enemlist.size()-1;i>=0;i--){
            Enemy enem=m_enemlist.get(i);
            enem.Update(GameTime);
            if (enem.state==Enemy.STATE_OUT){
                m_enemlist.remove(i);
            }
        }
        for (int i=m_explist.size()-1;i>=0;i--){
            Effect_Explosion exp=m_explist.get(i);
            exp.Update(GameTime);
            if (exp.getEnd()){
                m_explist.remove(i);
            }
        }
        MakeEnemy();
        CheckCollision();
    }

    @Override
    public void Render(Canvas canvas) {

        m_background.Draw(canvas);

        for (Missile pms : m_pmslist){
            pms.Draw(canvas);
        }
        for (Missile enemms: m_enemmslist){
            enemms.Draw(canvas);
        }
        for (Enemy enem:m_enemlist){
            enem.Draw(canvas);
        }
        for (Effect_Explosion exp:m_explist){
            exp.Draw(canvas);
        }
        m_player.Draw(canvas);

        Paint p=new Paint();
        p.setTextSize(60);
        p.setColor(Color.WHITE);
        canvas.drawText("남은 목숨: "+String.valueOf(m_player.getLife()),0,150,p);
        canvas.drawText("남은 체력: "+String.valueOf(m_player.GetHP()),0,210,p);
        canvas.drawText("점수: "+String.valueOf(score),0,260,p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        double user_x=event.getX();
        double user_y=event.getY();
        if (m_player.GetX()>user_x){
            //왼쪽
            m_player.SetPosition(m_player.GetX()-10, m_player.GetY());
        }
        if (m_player.GetX()<user_x){
            //오른쪽
            m_player.SetPosition(m_player.GetX()+10, m_player.GetY());
        }
        if (m_player.GetY()>user_y){
            //위쪽
            m_player.SetPosition(m_player.GetX(), m_player.GetY()-10);
        }
        if (m_player.GetY()<user_y){
            //아래쪽
            m_player.SetPosition(m_player.GetX(), m_player.GetY()+10);
        }

        // Move 와 Up 의 이벤트를 받기위해 true 를 리턴해준다.
        return true;
    }
    public void CheckCollision(){
        for (int i=m_pmslist.size()-1;i>=0;i--){
            for (int j=m_enemlist.size()-1;j>=0;j--){
                if (CollisionManager.CheckBoxToBox(m_pmslist.get(i).m_BoundBox, m_enemlist.get(j).m_BoundBox)){
                    m_explist.add(new Effect_Explosion(m_enemlist.get(j).GetX(), m_enemlist.get(j).GetY()));
                    m_enemlist.get(j).Damage(m_player.GetPower());
                    if (m_enemlist.get(j).GetHP()<=0){
                        m_enemlist.remove(j);
                        m_pmslist.remove(i);

                        score++;
                    }
                    return;
                }
            }

        }
        for (int i=m_enemlist.size()-1;i>=0;i--){
            if (CollisionManager.CheckBoxToBox(m_player.m_BoundBox, m_enemlist.get(i).m_BoundBox)){
                m_explist.add(new Effect_Explosion(m_enemlist.get(i).GetX(), m_enemlist.get(i).GetY()));
                m_enemlist.remove(i);
                m_player.destroyPlayer();
                m_player.setHP(10);
                if (m_player.getLife()<=0){
                    AppManager.getInstance().getActivity().finish();
                }
            }
        }
        for (int i=m_enemmslist.size()-1;i>=0;i--){
            if (CollisionManager.CheckBoxToBox(m_player.m_BoundBox, m_enemmslist.get(i).m_BoundBox)){
                m_enemmslist.remove(i);
                m_player.hurt();
                if (m_player.GetHP()==0){
                    m_player.destroyPlayer();
                    m_player.setHP(10);
                }
                if (m_player.getLife()<=0){
                    AppManager.getInstance().getActivity().finish();
                }
            }
        }
    }

}
