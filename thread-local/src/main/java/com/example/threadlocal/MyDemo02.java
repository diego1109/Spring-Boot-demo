package com.example.threadlocal;

public class MyDemo02 {

  public static String t0;

  //  private String content;
  public static ThreadLocal<String> t1 = new ThreadLocal<>();

  public static void main(String[] args) {

//    VariableInThreadLocal();
    VariableNotInThreadLocal();

  }
  private static void VariableNotInThreadLocal() {
    for(int i=0;i<5;i++){
      Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
         write();
         System.out.println("-----"+ Thread.currentThread().getName() + "写---------");
         read();
        }

        public void write(){
          t0 = Thread.currentThread().getName()+ "的数据";
        }

        public void read(){
          System.out.println(Thread.currentThread().getName() + " 拿到" + t0);
        }

      },"线程:"+ i);
      thread.start();
    }
  }


  private static void VariableInThreadLocal() {
    for(int i=0;i<5;i++){
      Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
          write();
          System.out.println("-----"+ Thread.currentThread().getName() + "写---------");
          read();
        }

        public void write(){
          // 通过 t1 将数据绑定到 i 线程。
          t1.set(Thread.currentThread().getName()+ "的数据");
        }

        public void read(){
          // 通过 t1 将 i 线程中拿出数据。
          System.out.println(Thread.currentThread().getName() + " 拿到" + t1.get());
        }

      },"线程："+ i);
      thread.start();
    }
  }
}
