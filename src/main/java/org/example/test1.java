package org.example;

/**
 * @author lizhen created on 2021-08-18 14:40
 */
public class test1 {

    public static void main(String[] args) {
        mythread m = new mythread();
        Thread t1=new Thread(m,"Window 1");
        Thread t2=new Thread(m,"Window 2");
        Thread t3=new Thread(m,"Window 3");
        t1.start();
        t2.start();
        t3.start();
    }

}

class mythread implements Runnable{
    int a = 10;
    @Override
    public void run(){
        while (a > 0){
            System.out.println(a-- + " is saled by "+Thread.currentThread().getName());
        }
    }

}
