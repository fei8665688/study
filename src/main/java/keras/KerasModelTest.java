package keras;

import java.io.Serializable;
import java.util.Map;
import org.deeplearning4j.nn.conf.ComputationGraphConfiguration;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.modelimport.keras.KerasModel;

import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.utils.KerasModelBuilder;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * @author phil.zhang
 * @date 2019/7/8
 */
public class KerasModelTest implements Serializable {


  /**
   * 输入模型
   */
  private static ComputationGraph graph;

  /**
   * 初始化加载模型
   */
  public void init(String path1,String path2) {
    try {
      // 加载模型
      graph = KerasModelImport.importKerasModelAndWeights(path1,path2, false);

/*      KerasModelBuilder kerasModelBuilder = new KerasModel().modelBuilder().modelHdf5Filename(path1)
          .enforceTrainingConfig(false);

      kerasModelBuilder.inputShape(new int[] {1,2603});

      KerasModel kerasModel = new KerasModel(kerasModelBuilder);
      graph = kerasModel.getComputationGraph();*/

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 构建一维张量
   * @param arr
   * @return
   */
  private INDArray buildFeatures(int[] arr) {
    //从输入参数中创建数据集

    INDArray features = Nd4j.zeros(new int[]{1,2603});

    for(int i = 0; i < arr.length; i++) {
      features.putScalar(0,i,arr[i]);
    }

    System.out.println(features);
    return features;
  }

  /**
   * 预测结果
   * @param
   * @return
   */
  public double predict(int[] inputs) {

    INDArray indArray = buildFeatures(inputs);

    // model
    INDArray[] output = graph.output(true, indArray);


    return 0.0;
  }

  public static void main(String[] args) {
    KerasModelTest model = new KerasModelTest();
    model.init("C:\\zf\\custom\\model_190709(2).json","C:\\zf\\custom\\weights_190709(2).h5");
  }



}
