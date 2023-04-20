import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import scalafx.geometry.Insets
import scalafx.scene.layout.{HBox, VBox}
/*This is a skeleton for creating future GUI
*/
object App extends JFXApp3 {
  private val w_width = 640.0:Double
  private val w_height = 640.0:Double
  //private val Ximg = Image(new InputStream)
  //private val Oimg = ImageIO.read(new File("imgs/O.png"))
  private def square(xr: Int, yr: Int): HBox = new HBox {
    padding = Insets(10,10,10,10)
    children = Seq(
      new Rectangle{
        x = 10+xr*(w_width-60)/3
        x = 10+yr*(w_height-100)/3
        width = (w_width-60)/3
        height = (w_height-100)/3
        fill = scalafx.scene.paint.Color.Blue
      }
      //,new ImageView(Ximg)
    )
  }

  private def vbox(xr: Int): VBox = new VBox{
    children = Seq(
      square(xr,0),
      square(xr,1),
      square(xr,2)
    )
  }
  private def make_VBox_seq():Seq[VBox] ={
    Seq(vbox(0),vbox(1),vbox(2))
  }
  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "Tic Tac Toe Game"
      width = w_width
      height = w_height
      scene = new Scene {
        fill = LightGrey
        content = new HBox {
          children = make_VBox_seq()
        }
      }
    }
  }
}