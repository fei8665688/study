package ND4j;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * @author phil.zhang
 * @date 2019/7/9
 */
public class ND4jTest {

  public static void main(String[] args) {
/*    INDArray zeros1 = Nd4j.zeros(2, 3);
    System.out.println(zeros1);

    int[] x = {2,2};
    INDArray rand = Nd4j.rand(x);
    System.out.println(rand);

    INDArray randn = Nd4j.randn(x);
    System.out.println(randn);

    System.out.println(rand.getDouble(1, 1));

    System.out.println(rand.getRow(1));

    System.out.println(Nd4j.zeros());*/

    INDArray rand1 = Nd4j.rand(1, 2);
    System.out.println(rand1);

    INDArray rand = Nd4j.rand(new int[]{1, 1, 1, 1});

    System.out.println(rand.shape().length);

  }

}
