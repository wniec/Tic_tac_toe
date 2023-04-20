object Main {
  def main(args: Array[String]): Unit = {
    val grid:Grid = Grid()
    val game:Game=new Game(grid)
    game.play_with_player()
  }
}