import javafx.event.Event
import javafx.scene.input.MouseEvent
import scalafx.scene.image.{Image, ImageView}
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.geometry.Insets
import scalafx.scene.layout.{HBox, VBox}
/*This is a skeleton for creating future GUI
*/
object App extends JFXApp3 {
  private val w_width = 640.0: Double
  private val w_height = 640.0: Double
  private var grid: Grid = Grid()
  private var bot: Bot = new Bot(grid)
  private var hasBot: Boolean = false
  private var side = 1
  private var round = 0
  def change_side(): Unit = {
    side = 3 - side
  }

  private def get_sign(n: Int): Sign = {
    var sign: Sign = new O
    if (n == 1)
      sign = new X
    sign
  }

  def set_grid(_grid: Grid): Unit = {
    grid = _grid
  }

  def set_bot(): Unit = {
    hasBot = true
    bot = new Bot(grid)
  }

  def get_image(sign: Sign): Image = {
    sign.index match {
      case 0 => new Image(this, "imgs/None.png")
      case 1 => new Image(this, "imgs/X.png")
      case 2 => new Image(this, "imgs/O.png")
    }
  }

  private def square(xr: Int, yr: Int): HBox = new HBox {
    padding = Insets(10, 10, 10, 10)
    val sign_at: Sign = grid.arr(yr)(xr)
    val img: Image = get_image(sign_at)
    val view = new ImageView(img)
    view.setFitWidth((w_width - 80) / 3)
    view.setFitHeight((w_height - 100) / 3)
    if(sign_at.index==0){
      view.onMouseClicked = (_: MouseEvent) => {
        grid.place(get_sign(side), yr, xr)
        update()
      }
    }
    children = Seq(
      view
    )

  }

  private def vbox(xr: Int): VBox = new VBox {
    children = Seq(
      square(xr, 0),
      square(xr, 1),
      square(xr, 2)
    )
  }

  private def make_VBox_seq(): Seq[VBox] = {
    Seq(vbox(0), vbox(1), vbox(2))
  }

  private def make_Scene(): Scene = new Scene {
    fill = LightGrey
    content = new HBox {
      children = make_VBox_seq()
    }
  }
  def check(): Boolean = {
    round +=1
    val grid_check = grid.check
    if (round>=9 || grid_check>0){
      return true
    }
    false
  }

  def update(): Unit = {
    if (hasBot){
      stage.scene = make_Scene()
      if(check())
        return
      val move = bot.best_move(3 - side)
      grid.place(new O,move(0),move(1))
      stage.scene = make_Scene()
      if(check())
        return
    }
    else{
      stage.scene = make_Scene()
      change_side()
      check()
    }
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
