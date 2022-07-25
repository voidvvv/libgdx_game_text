package com.mygdx.game;

import java.util.concurrent.*;

/**
 * @Classname Service
 * @Description
 * @Date 2022/7/16 10:30
 * @Created by zkj
 */
public class Service {
    private static ThreadPoolExecutor threadPoolExecutor;

    public synchronized static void init(){
        if (threadPoolExecutor == null){
            threadPoolExecutor = new ThreadPoolExecutor(10,15,10, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(50),new ThreadPoolExecutor.AbortPolicy());
        }
    }

    public synchronized static void submit(Runnable runnable){
        if (threadPoolExecutor!=null){
            threadPoolExecutor.execute(runnable);

        }
    }

    public static void clear() {
        if (threadPoolExecutor!=null){
            threadPoolExecutor.shutdown();

        }
    }
}
