package com;

import org.openjdk.jol.info.ClassLayout;

public class LockUpgraded {

  public static void main(String[] args) throws InterruptedException {
//    test1();
//    test2();
//    test3();
//    test0();
//      test1();
    test9();
  }

  // 无状态和开启偏向锁
  public static void test0() throws InterruptedException {
    User user1 = new User();
    System.out.println("无状态(001):" + ClassLayout.parseInstance(user1).toPrintable());

    // 默认 4s 以后开启偏向锁
    Thread.sleep(5000);
    User user2 = new User();
    System.out.println("开启偏向(101):" + ClassLayout.parseInstance(user2).toPrintable());
  }

  // 无状态 --> 轻量级锁
  private static void test6() {
    User user1 = new User();
    System.out.println("无状态(001):" + ClassLayout.parseInstance(user1).toPrintable());
    synchronized (user1) {
      System.out.println("轻量级锁(00),带着线程id:" + ClassLayout.parseInstance(user1).toPrintable());
    }
  }

  // 偏向锁未执行完，发生了并发,会直接加到重量级锁
  public static void test9() throws InterruptedException {
    Thread.sleep(5000);
    User user2 = new User();

    new Thread(()->{
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("------ 应该加重量级锁 --------- ");
      synchronized (user2) {
        System.out.println("测试下结果：" + ClassLayout.parseInstance(user2).toPrintable());
      }
    }).start();

    for (int i = 0; i < 1; i++) {
      System.out.println("------ 加偏向锁 --------- ");
      synchronized (user2) {
        System.out.println("加偏向锁(101),并带着线程id：" + ClassLayout.parseInstance(user2).toPrintable());
//        Thread.sleep(30000);
      }
      System.out.println("------ 释放偏向锁 --------- ");
    }
  }


  // 偏向锁
  public static void test1() throws InterruptedException {
    Thread.sleep(5000);
    User user2 = new User();

    for (int i = 0; i < 2; i++) {
      synchronized (user2) {
        System.out.println("加偏向锁(101),并带着线程id：" + ClassLayout.parseInstance(user2).toPrintable());
      }
      System.out.println("释放偏向锁(101),并带着线程id：" + ClassLayout.parseInstance(user2).toPrintable());
    }
  }

  //  轻量级锁
  public static void test2() throws InterruptedException {
    Thread.sleep(5000);
    User user2 = new User();
    // 主线程给 user2 加偏向锁
    synchronized (user2) {
      System.out.println("加偏向锁(101),并带着线程id：" + ClassLayout.parseInstance(user2).toPrintable());
    }
    Thread.sleep(100);
    // 新线程给 user2 加轻量级锁
    new Thread(() -> {
      synchronized (user2) {
        System.out.println("轻量级锁(00),带着线程id:" + ClassLayout.parseInstance(user2).toPrintable());
      }
      System.out.println("轻量级锁释放，变成无状态(001)" + ClassLayout.parseInstance(user2).toPrintable());
    }).start();

    Thread.sleep(500);
    synchronized (user2) {
      System.out.println("再次加锁" + ClassLayout.parseInstance(user2).toPrintable());
    }
    System.out.println("再次释放锁" + ClassLayout.parseInstance(user2).toPrintable());

  }

  //  重量级锁
  private static void test3() throws InterruptedException {
    Thread.sleep(5000);
    User user2 = new User();
    System.out.println("开启偏向(101):" + ClassLayout.parseInstance(user2).toPrintable());

    synchronized (user2) {
      System.out.println("加偏向锁(101),并带着线程id：" + ClassLayout.parseInstance(user2).toPrintable());
    }

    new Thread(() -> {
      synchronized (user2) {
        System.out.println("轻量级锁(00),带着线程id:" + ClassLayout.parseInstance(user2).toPrintable());
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();

    Thread.sleep(1000);
    new Thread(() -> {
      synchronized (user2) {
        System.out.println(" 重量级锁(10),带着线程id:" + ClassLayout.parseInstance(user2).toPrintable());
      }
    }).start();

    Thread.sleep(3000);
    System.out.println(" 重量级锁释放:" + ClassLayout.parseInstance(user2).toPrintable());
  }

  // 无状态 --> 偏向锁 --> 轻量级锁 --> 重量级锁
  private static void test5() throws InterruptedException {
    User user1 = new User();
    System.out.println("无状态(001):" + ClassLayout.parseInstance(user1).toPrintable());

    // 默认 4s 以后开启偏向锁
    Thread.sleep(5000);
    User user2 = new User();
    System.out.println("开启偏向(101):" + ClassLayout.parseInstance(user2).toPrintable());

    for (int i = 0; i < 2; i++) {
      synchronized (user2) {
        System.out.println("加偏向锁(101),并带着线程id：" + ClassLayout.parseInstance(user2).toPrintable());
      }
      System.out.println("释放偏向锁(101),并带着线程id：" + ClassLayout.parseInstance(user2).toPrintable());
    }

    new Thread(() -> {
      synchronized (user2) {
        System.out.println("轻量级锁(00),带着线程id:" + ClassLayout.parseInstance(user2).toPrintable());
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();

    Thread.sleep(1000);
    new Thread(() -> {
      synchronized (user2) {
        System.out.println(" 重量级锁(10),带着线程id:" + ClassLayout.parseInstance(user2).toPrintable());
      }
    }).start();
  }

}
