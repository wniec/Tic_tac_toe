import scala.collection.mutable.ArrayBuffer

trait Grid{
  def generate: Array[Array[Sign]]
  val arr: Array[Array[Sign]]
  def place(sign:Sign,x:Int,y:Int): Unit
  def check:Int
  def get_free:ArrayBuffer[Array[Int]]

  def toString:String
}
object Grid {
  def apply(): Grid =
    new DefaultImpl()

  private class DefaultImpl() extends Grid{
    def generate: Array[Array[Sign]] = {
      val temp = Array.ofDim[Sign](10,10)

      for (i: Int <- 0 until 3; j: Int <- 0 until 3) {
          temp(i)(j) = new None
      }

      temp
    }
    override val arr: Array[Array[Sign]] = generate

    override def toString: String = {
      var temp = "_______\n|"

      for (i: Int <- 0 until 3){
        for( j: Int <- 0 until 3){
          temp = temp +""+ arr(i)(j).toString+"|"
        }
        temp = temp+"\n_______\n"
        if(!(i==2))
          temp = temp+"|"
      }

      temp
    }

    override def place(sign: Sign, x: Int, y: Int): Unit = {
      arr(x)(y)=sign
    }
    override def check:Int = {
      var flag_diag1 = true
      var flag_diag2 = true
      for (i: Int <- 0 until 3) {
        var flag_col = true
        var flag_row = true
        for (j: Int <- 0 until 3) {
          flag_col = flag_col&&arr(j)(i)==arr(0)(i)
          flag_row = flag_row&&arr(i)(j) ==arr(i)(0)
        }
        if(flag_col&&arr(0)(i).index>0)
          return arr(0)(i).index
        if (flag_row && arr(i)(0).index > 0)
          return arr(i)(0).index
        flag_diag1 = flag_diag1 && arr(i)(i)==arr(0)(0)
        flag_diag2 = flag_diag2 && arr(i)(2-i)==arr(0)(2)
      }
      if (flag_diag1)
        return arr(0)(0).index
      if (flag_diag2)
        return arr(0)(2).index
      0
    }
    def get_free:ArrayBuffer[Array[Int]]={
      val res: ArrayBuffer[Array[Int]] = ArrayBuffer()
      for (i: Int <- 0 until 3) {
        for (j: Int <- 0 until 3) {
          if(arr(i)(j).index == 0) {
            res.addOne(Array(i,j))
          }
        }
      }
      res
    }
  }

  }