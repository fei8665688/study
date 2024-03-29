package gc.reference;

import java.lang.ref.SoftReference;

/**
 * @author phil.zhang
 * @date 2019/7/11
 */
public class SoftReferenceTest {

  public static void main(String[] args) throws InterruptedException {

    // 总堆内存设置200M
    //100M的缓存数据
    byte[] cacheData = new byte[100 * 1024 * 1024];
    //将缓存数据用软引用持有
    SoftReference<byte[]> cacheRef = new SoftReference<>(cacheData);
    //将缓存数据的强引用去除
    cacheData = null;
    System.out.println("第一次GC前" + cacheRef.get());  //第一次GC前[B@123772c4
    //进行一次GC后查看对象的回收情况
    System.gc();
    //等待GC
    Thread.sleep(500);
    System.out.println("第一次GC后" + cacheRef.get());  //第一次GC后[B@123772c4
    //在分配一个120M的对象，看看缓存对象的回收情况
    byte[] newData = new byte[120 * 1024 * 1024];
    System.out.println("分配后" + cacheRef.get());      // 分配后null

  }

}
