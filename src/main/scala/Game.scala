class Game() {
  private val grid: Grid = Grid()
  private val bot = new Bot(grid)

  private def bot_move(): Unit = {
    val move = bot.best_move(2)
    grid.place(new O, move(0), move(1))
  }
  def play_with_bot(player_first: Boolean): Unit = {
    if (!player_first) {
      App.change_side()
      bot_move()
    }
    App.set_grid(grid)
    App.set_bot()
    App.main(Array("None"))
  }

  def play_with_player(): Unit = {
    App.set_grid(grid)
    App.main(Array("None"))
  }
}
