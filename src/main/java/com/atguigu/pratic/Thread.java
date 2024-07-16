package com.atguigu.pratic;

/**
 * ClassName: Thread
 * Package: com.atguigu.pratic
 * Description：
 *
 * @Author LeoHuang
 * @Create 2024/7/14 20:36
 * @Version 1.0
 */
public class Thread implements Runnable{

    @Override
    public void run() {
        Integer a=new Integer(5);
        int b=a;
        System.out.println(1);
    }
}
