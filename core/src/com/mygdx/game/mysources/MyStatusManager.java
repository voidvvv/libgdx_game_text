package com.mygdx.game.mysources;

import com.sun.org.apache.bcel.internal.generic.FADD;

/**
 * @Classname MyStatusManager
 * @Description
 * @Date 2022/7/17 12:18
 * @Created by zkj
 */
public class MyStatusManager {
    public static float totalTime = 0;

    public static int TOTAL_HEAL = 5;

    public static int CUR_HEAL = 1;

    public static int TOTAL_SCORE = 0;

    public static boolean PAUSE = false;

    public static boolean GAME_OVER = false;

    public static void init() {
        MyStatusManager.GAME_OVER = false;
        MyStatusManager.CUR_HEAL = MyStatusManager.TOTAL_HEAL;
        TOTAL_SCORE = 0;
        PAUSE =false;
        totalTime = 3;
    }
}
