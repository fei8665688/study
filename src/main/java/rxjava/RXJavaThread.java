package rxjava;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

/**
 * @author phil.zhang
 * @date 2019/7/18
 */
public class RXJavaThread {

  public static void main(String[] args) {
    new ThreadPoolExecutor(1,512,0, TimeUnit.SECONDS,new LinkedBlockingQueue<>(64),
        new ThreadFactoryBuilder().setNameFormat("test").build(), new AbortPolicy());

  }



}
