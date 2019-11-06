package demo

/**
  * @author phil.zhang
  * @date 2019/8/12
  */
object ScalaTest {

  def main(args: Array[String]): Unit = {
    val a = "a"
    var b = "b"
    val x = 100
    var y = 200
    new Thread(new Runnable {
      override def run(): Unit = {
        print(x)
        print(y)
      }
    }).start()
  }
}
