package com.example.ekb2011.projectgalaga;

import android.graphics.Bitmap;

import Framework.SpriteAnimation;

/**
 * Created by ekb2011 on 2017-11-02.
 */

public class Player extends SpriteAnimation {
    public Player(Bitmap bitmap) {
        super(bitmap);
        this.InitSpriteData(104,62,3,6);
        this.SetPosition(140,380);
    }
}
