package gc.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author phil.zhang
 * @date 2019/7/11
 */
public class ReferenceTest {


  static class Strong{
    String str = "hello";

    Strong(String str){
      this.str=str;
    }

    @Override
    public String toString() {
      return str;
    }
  }

  public static void main(String[] args) {
    ReferenceQueue<Object> queue = new ReferenceQueue<>();
    // 软引用
    SoftReference<Strong> softReference = new SoftReference<>(new Strong("aaa"),
        queue);
    System.out.println(softReference.get());    // aaa
    System.gc();
    System.out.println(softReference.get());    // aaa
    System.out.println(queue.poll());           // null
    // 弱引用
    WeakReference<Strong> weakReference = new WeakReference<>(new Strong("bbb"), queue);
    System.out.println(weakReference.get());    // bbb
    System.gc();
    System.out.println(weakReference.get());    // null
    System.out.println(queue.poll().getClass());  // WeakRegerence

    // 虚引用
    Strong x = new Strong("ccc");
    WeakReference<Strong> reference = new WeakReference<Strong>(x);
    PhantomReference<Strong> phantomReference = new PhantomReference<>(
        x, queue);
    System.out.println(reference.get());        // null
  }


}
