package tensorflow;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

/**
 * @author phil.zhang
 * @date 2019/7/8
 */
public class PreProcessor {

  /**
   * 最大文本长度
   */
  private final static int maxSize = 2603;

  /**
   * 用来补充的默认词汇
   */
  private final static String paddingWord = "<PAD/>";


  private static Map<String,Integer> vocabularyMap = Maps.newHashMap();

  /**
   * 处理特殊字符
   * @param input
   * @return
   */
  private static String cleanStr(String input) {
    // 替换字符
    String s = input.replaceAll("[^a-zA-Z0-9(),!?\'`]", " ")
        .replaceAll("\'s"," \'s")
        .replaceAll("\'ve"," \'ve")
        .replaceAll("n\'t"," n\'t")
        .replaceAll("\'d", " \'d")
        .replaceAll("\'ll"," \'ll")
        .replaceAll(","," , ")
        .replaceAll("!"," ! ")
        .replaceAll("\\("," ( ")
        .replaceAll("\\)"," ) ")
        .replaceAll("\\?"," ? ")
        .replaceAll("\\s{2,}"," ")
        .trim()
        .toLowerCase();

    return s;
  }

  /**
   * 补齐list长度为最长的长度,如果超出的话,截取maxSize
   * @param list
   * @return
   */
  private static List<String> paddingWords(List<String> list) {
    if (list.size() >= maxSize) {
      return list.subList(0, maxSize);
    }else {
      int size = list.size();

      for (int i = 0; i< maxSize-size; i++) {
        list.add(paddingWord);
      }
      return list;
    }
  }

  /**
   * 对词汇Map集合添加元素
   */
  public static void buildVocabularyMap(InputStream in) {

    try {

      // 读取文件
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));

      // 构建词汇表
      Set<String> vocabulariesSet = Sets.newHashSet(vocabularyMap.keySet());

      String line;
      while (null != (line = reader.readLine())) {
        //清洗并分词
        List<String> words = Lists.newArrayList(cleanStr(line).split(" "));
        //补齐
        List<String> vocabularies = paddingWords(words);
        vocabulariesSet.addAll(vocabularies);
      }

      //按字典序排序
      List<String> fullList = Lists.newArrayList(vocabulariesSet);
      fullList.sort(new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
          return o1.compareTo(o2);
        }
      });

      for(int i=0; i < fullList.size(); i++) {
        vocabularyMap.put(fullList.get(i),i);
      }


    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * 见字符串转化为一维数组
   * @return
   */
  public static int[][] buildInputs(String line) {

    String cleanStr = cleanStr(line);
    List<String> words = Lists.newArrayList(cleanStr.split(" "));

    List<String> paddingWords = paddingWords(words);
    int[][] arr = new int[1][maxSize];

    for (int i=0; i< paddingWords.size(); i++) {
      arr[0][i] = vocabularyMap.getOrDefault(paddingWords.get(i),vocabularyMap.get(paddingWord));
    }

    return arr;

  }

  public static Map<String, Integer> getVocabularyMap() {
    return vocabularyMap;
  }

  public static void setVocabularyMap(Map<String, Integer> vocabularyMap) {
    PreProcessor.vocabularyMap = vocabularyMap;
  }

  public static void main(String[] args) {
    List<Object> list = Lists.newArrayList();

    int i = 0;

    while(true) {
      list.add(String.valueOf(i++));
    }

  }
}
