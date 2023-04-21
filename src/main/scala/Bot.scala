class Bot(val grid:Grid) {
  private def get_sign(n: Int): Sign = {
    var sign: Sign = new O
    if (n == 1)
      sign = new X
    sign
  }
  def best_move(side:Int):Array[Int] ={
    val free = grid.get_free
    var best_move = free(0)
    var value = -100
    val sign = get_sign(side)
    for (move <- free) {
      grid.place(sign, move(0), move(1))
      val new_minmax = -minmax(grid, 3 - side, 0)
      if (new_minmax > value) {
        value = new_minmax
        best_move = move
      }
      grid.place(new None, move(0), move(1))
    }
    best_move
  }
  private def minmax(grid:Grid,side:Int,depth:Int): Int = {
    val check = grid.check
    if(check==side){
      return 10-depth
    }
    else if(check==3-side){
      return -10+depth
    }
    val free = grid.get_free
    if(free.isEmpty){
      0
    }
    else{
      val sign = get_sign(side)
      var value = -10+depth
      for(move <- free){
        grid.place(sign,move(0),move(1))
        val new_minmax = -minmax(grid, 3 - side,depth+1)
        if (new_minmax>value){
          value = new_minmax
        }
        grid.place(new None,move(0),move(1))
      }
      value
    }
  }
}
