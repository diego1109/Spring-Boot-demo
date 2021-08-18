package com.example.threadlocal;


/**
 *  需求：线程隔离
 *      在多线程并发的场景下，每个线程中的变量都是相互独立
 *      线程A： 设置（变量1）  获取（变量1）
 *      线程B： 设置（变量2）  获取（变量2）
 *      ThreadLocal：
 *        set(): 将变量绑定到当前线程中。
 *        get(): 获取当前线程绑定的变量。
 *
 */

public class MyDemo01 {

//  private String content;
  ThreadLocal<String> t1 = new ThreadLocal<>();

  public String getContent() {
    return t1.get();
  }

  public void setContent(String content) {
    // 将变量绑定到当前线程。
    t1.set(content);
  }

  public static void main(String[] args) {
    MyDemo01 demo = new MyDemo01();

    for(int i=0;i<5;i++){
      Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
          demo.setContent(Thread.currentThread().getName()+ "的数据");
          System.out.println("--------------");
          System.out.println(Thread.currentThread().getName() + "--->" + demo.getContent());
        }
      },"线程："+ i);
      thread.start();
    }

  }
}
