package concurrent;

import java.util.concurrent.CompletableFuture;

/**
 * @author phil.zhang
 * @date 2019/11/6
 */
public class CompletableFutureTest {

  public static void main(String[] args) {
      /**
       * CompletableFuture: runAsync(Runnable) => CompletableFuture<void>
       * CompletableFuture: supplyAsync(Supplier<U> supplier) => CompletableFuture<U>
       *
       *
       */

      CompletableFuture<String> completableFuture = CompletableFuture
          .supplyAsync(() -> {
            try {
              Thread.sleep(10000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            return "hello";
          });

      /**
       * 变换结果
       * thenApply(Function<? super T,? extends U> fn) => CompletableStage<U>
       * thenApplyAsync(Function<? super T,? extends U> fn) => CompletableStage<U>
       */

      CompletableFuture<String> completableFuture1 = completableFuture.thenApplyAsync(str -> {
        return str + "world";
      });


    /**
     * 消费结果
     * thenAccept(Consumer<? super T> action) => CompletableStage<Void>
     * thenAcceptAsync(Consumer<? super T> action) => CompletableStage<Void>
     */

    CompletableFuture<Void> completableFuture2 = completableFuture
        .thenAcceptAsync(str -> System.out.println(str));



    /**
     * 结合两个CompletableFuture的结果并进行转化后返回
     * thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn) => CompletionStage<V>
     */
    CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> "hi,")
        .thenCombine(CompletableFuture.supplyAsync(() -> "你好世界"), (s1, s2) -> s1 + s2);

    /**
     * 两个CompletionStage 谁计算的快,就用那个CompletionStage
     * applyToEither(CompletionStage<? extends T> other,Function<? super T, U> fn) => CompletionStage<U>
     */

    System.out.println(CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "1 ";
    }).applyToEither(CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "2 ";
    }), s -> s).join());

    /**
     * 运行时出现了异常, 可以通过exceptionally进行补偿
     * exceptionally(Function<Throwable, ? extends T> fn) => CompletionStage<T>
     */
    String result = CompletableFuture.supplyAsync(()->{
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if(true) {
        throw new RuntimeException("exception test!");
      }

      return "Hi Boy";
    }).exceptionally(e->{
      System.out.println(e.getMessage());
      return "Hello world!";
    }).join();
    System.out.println(result);
    


  }

}
