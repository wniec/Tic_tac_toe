import scala.io.StdIn.readLine

class Game(val grid:Grid) {
  private val bot = new Bot(grid)
  private def get_input(): Array[Int] = {
    val input = readLine("Enter position: ")
    val coords: Array[String] = input.split(" ")
    Array(coords(0).toInt, coords(1).toInt)
  }
  private def player_move():Unit ={
    val position = get_input()
    grid.place(new X, position(0), position(1))
    println(grid.toString)
  }

  private def player_move(side:Int): Unit = {
    val position = get_input()
    if (side ==1)
      grid.place(new X, position(0), position(1))
    else
      grid.place(new O, position(0), position(1))
    println(grid.toString)
  }

  private def bot_move(): Unit = {
    val move = bot.best_move(2)
    grid.place(new O, move(0), move(1))
    println(grid.toString)
  }

  def play_with_bot(player_first: Boolean): Unit = {
    var i = 0
    if(!player_first){
      bot_move()
      i += 1
    }
    while (grid.check == 0 && i < 9) {
      player_move()
      i += 1
      if (i >= 9)
        return
      bot_move()
      i += 1
    }
  }

  def play_with_player(): Unit = {
    var i = 0
    while (grid.check == 0 && i < 9) {
      player_move(1)
      i += 1
      if (i >= 9)
        return
      player_move(2)
      i += 1
    }
  }

}
