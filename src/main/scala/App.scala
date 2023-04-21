import scalafx.scene.image.{ImageView, Image}
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
  private var grid:Grid = Grid()
  def set_grid(_grid: Grid): Unit ={
    grid = _grid
  }

  private def square(xr: Int, yr: Int): HBox = new HBox {
    padding = Insets(10,10,10,10)
    val img:Image = grid.arr(yr)(xr).img:Image
    val view  = new ImageView(img)
    view.setFitWidth((w_width-60)/3)
    view.setFitHeight((w_height-100)/3)
    children = Seq(
      view
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