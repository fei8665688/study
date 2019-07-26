package tensorflow;

import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import keras.KerasModelTest;

/**
 * @author phil.zhang
 * @date 2019/7/8
 */
public class MainPredict {

  public static void main(String[] args)
      throws IOException, NoSuchFieldException, IllegalAccessException {
    // 初始化预处理器
    InputStream isPermitted0 = MainPredict.class
        .getResourceAsStream("/customRule/is_permitted_0_modified.txt");
    InputStream isPermitted1 = MainPredict.class
        .getResourceAsStream("/customRule/is_permitted_1_modified.txt");
    PreProcessor.buildVocabularyMap(isPermitted0);
    PreProcessor.buildVocabularyMap(isPermitted1);

    PredictModel predictModel = new PredictModel();
    predictModel.initModel("C:\\zf\\custom\\model2.pb");

    Set<String> set = PreProcessor.getVocabularyMap().keySet();

    List<String> list = Lists.newArrayList(set);


    BufferedReader bufferedReader1 = new BufferedReader(new FileReader("C:\\张飞\\study\\src\\main\\resources\\customRule\\is_permitted_0_modified.txt"));
    BufferedReader bufferedReader2 = new BufferedReader(new FileReader("C:\\张飞\\study\\src\\main\\resources\\customRule\\is_permitted_1_modified.txt"));

    String x ;
    while (null != (x = bufferedReader1.readLine())) {
      int[][] ints = PreProcessor.buildInputs(x);

      if (predictModel.predict(ints) > 0.5) {
        System.out.println("is_permitted_0:"+false);
      }
    }

    while (null != (x = bufferedReader2.readLine())) {
      int[][] ints = PreProcessor.buildInputs(x);

      if(predictModel.predict(ints) < 0.5) {
        System.out.println("is_permitted_1:"+false);
      }

    }



  }

}
