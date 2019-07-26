package gc.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author phil.zhang
 * @date 2019/7/11
 */
public class ReferenceQueueTest {

  public static void main(String[] args) {
    final ReferenceQueue q = new ReferenceQueue();
    String str = new String("test");
    WeakReference wr = new WeakReference(str, q);
    Thread t = new Thread(){
      @Override
      public void run() {
        try {
          Reference reference = q.remove();
          System.out.println(reference +                     " event fired.");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    t.setDaemon(true);
    t.start();
    System.out.println("Reference Queue is listening.");
    str = null;
    // clear strong reference
     System.out.println("Ready to gc");
     System.gc();
     try {
     Thread.sleep(2000);
     } catch (InterruptedException e) {
     e.printStackTrace();
     }
     System.out.println("wr.get: " + wr.get());
  }

}
