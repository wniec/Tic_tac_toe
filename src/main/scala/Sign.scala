import scalafx.scene.image.Image

trait Sign{
  def toString:String
  val index:Int
}
case class X() extends Sign{
  override def toString: String = "X"
  override val index: Int = 1
}

case class O() extends Sign{
  override def toString: String = "O"
  override val index: Int = 2
}
case class None() extends Sign{
  override def toString: String = " "
  override val index: Int = 0
}