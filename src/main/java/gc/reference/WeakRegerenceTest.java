package gc.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import gc.reference.ReferenceTest.Strong;

/**
 * @author phil.zhang
 * @date 2019/7/11
 */
public class WeakRegerenceTest {


  public static void main(String[] args) {
    Strong obj = new Strong("ddd");

    ReferenceQueue<Object> queue = new ReferenceQueue<>();
    PhantomReference<Strong> phantomReference = new PhantomReference<>(obj, queue);


    System.gc();

    Reference<?> poll1 = queue.poll();
    System.out.println("--- 虚引用对象被jvm回收了 ---- " + poll1);   // null

    obj=null;

    System.gc();

    Reference<?> poll2 = queue.poll();

    System.out.println("--- 虚引用对象被jvm回收了 ---- " + poll2);   // null

  }

}
