package me.sunny.demo.algos.dp;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者消费者
 */
public class ProducerAndConsumer {

  private static BlockingQueue<Object> queue = new ArrayBlockingQueue<>(50);

  public static void main(String[] args) {
    new Producer().start();
    new Consumer().start();
  }

  public static class Producer extends Thread {
    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(1000);
          queue.put(new Object());
          System.out.println("produce, task queue size :" + queue.size());
        } catch (InterruptedException ex) {

        }
      }
    }
  }

  public static class Consumer extends Thread {
    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(1500);
          queue.take();
          System.out.println("consume, task queue size :" + queue.size());
        } catch (InterruptedException ex) {

        }
      }
    }
  }
}
