package tensorflow;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import org.tensorflow.Graph;
import org.tensorflow.Operation;
import org.tensorflow.Session;
import org.tensorflow.Session.Runner;
import org.tensorflow.Tensor;
import org.tensorflow.Tensors;

/**
 * @author phil.zhang
 * @date 2019/7/5
 */
public class PredictModel {

  /**
   * 表示模型的计算公式
   */
  private Graph graph;

  /**
   * 用来运行模型的执行者
   */
  private Session session;

  /**
   * 加载模型文件
   * @throws IOException
   */
  public void initModel(String path) {

    try {

      if (null != session) {
        return;
      }
      // 读取文件模型
      byte[] graphDef = Files.readAllBytes(Paths.get(path));

      // 导入模型进入计算公式
      this.graph = new Graph();
      graph.importGraphDef(graphDef);

      //根据计算公式构建执行器
      this.session = new Session(graph);

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * 将输入特征转换为Tensor的输入特征
   * @param input
   * @return
   */
  private Tensor<String> convertInputToTensors (String input) {
    return Tensors.create(input);
  }



  public float predict(int[][] inputs) throws NoSuchFieldException, IllegalAccessException {
    // 将输入转化为对应的tensor输入
    Tensor<Integer> tensorInput = Tensors.create(inputs);

    // 这里的 "input" 和 "output" 需与模型训练时的一致
    Runner runner = session.runner();

    Iterator<Operation> operations = graph.operations();
    while (operations.hasNext()) {
      Operation next = operations.next();
    }


    Tensor<?> result = runner.feed("input_1", tensorInput).fetch("output").run().get(0);

    //获取模型返回的shape 如：[1,1,1] 第一个1表示最外层的元素只有1个，第二个1表示拿掉一次括号后的元素仍然只有1个
    //第三个1表示再拿掉一次括号后，还是只有一个元素， 即表示 [[[a]]]这种数据
    // 如： [2,2,1] 表示[[[a],[b]],[[c],[d]]]
    long[] rshape = result.shape();

    // 获取最外层的元素数
    int rs = (int) rshape[0];

    float[][] x = new float[1][2];

    result.copyTo(x);


    return x[0][1];
  }


}
