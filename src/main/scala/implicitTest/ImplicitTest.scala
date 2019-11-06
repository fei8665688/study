package implicitTest

/**
  * @author phil.zhang
  * @date 2019/11/6
  */
object ImplicitTest {

  implicit def person2SuperPerson(person: Person)= {new SuperPreson(person)}

  implicit val name1 = "zhangsan"

  def getName(implicit name:String): String = {
    name
  }

  def main(args: Array[String]): Unit = {
    val person = new Person

    person.sayHello()

    println(getName)
  }

}
