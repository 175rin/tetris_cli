package org.example.view

import org.example.model.Board

class GameView {
    fun render(board: Board) {
        Utils.resetScreen()
        Utils.hideCursor()

        for (row in board.cells) {
            print("#")
            for (cell in row) {
                print(
                    when (cell.value) {
                        0 -> " ."
                        1 -> "[]"
                        else -> "[]"
                    }
                )
            }
            print(" #")
            println()
        }
        println("#".repeat(board.cols * 2 + 1 + 2))
    }

}