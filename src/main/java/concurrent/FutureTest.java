package concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author phil.zhang
 * @date 2019/11/6
 */
public class FutureTest {

  public static void main(String[] args)  {
    ExecutorService executorService = Executors.newCachedThreadPool();

    // Future + Callable
    Future<Integer> future = executorService.submit(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        return new Random().nextInt(100);
      }
    });

    try {
      System.out.println(future.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }


    // Runable
    FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        return new Random().nextInt(1000);
      }
    });

    new Thread(futureTask).start();

    try {
      System.out.println(futureTask.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    /**
     * 局限性: 很难直接表述多个Future, 以下功能是我们经常需要的
     * 1. 将两个异步计算合并为一个(两个异步计算相互独立,同时第二个依赖第一个)
     * 2. 等待Future集合中的所有任务都完成
     * 3. 等待Future集合中最快结束的任务我弄成,并返回他的结果
     */

  }


  private static void completableFuture() {

    /**
     * CompletableFuture: runAsync(Runnable) => CompletableFuture<void>
     * CompletableFuture: supplyAsync(Supplier<U> supplier) => CompletableFuture<U>
     *
     *
     */

    CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "hello";
    });

    /**
     * 变换结果
     * thenApply(Function<? super T,? extends U> fn) => CompletionStage<U>
     * thenApplyAsync(Function<? super T,? extends U> fn) => CompletionStage<U>
     */

    CompletableFuture<String> completableFuture1 = completableFuture.thenApplyAsync(str -> {
      return str + "world";
    });

    System.out.println(completableFuture1.join());


  }

}
