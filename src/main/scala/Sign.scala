import scalafx.scene.image.Image

trait Sign{
  def toString:String
  val index:Int
  val img:Image
}
case class X() extends Sign{
  override def toString: String = "X"
  override val index: Int = 1
  override val img = new Image(this,"imgs/X.png")
}

case class O() extends Sign{
  override def toString: String = "O"
  override val index: Int = 2
  override val img = new Image(this,"imgs/O.png")
}
case class None() extends Sign{
  override def toString: String = " "
  override val index: Int = 0
  override val img = new Image(this,"imgs/None.png")
}