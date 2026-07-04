package org.example.view

object Utils {
    /**
     * カーソルを非表示
     */
    fun hideCursor() {
        print("\u001B[?25l")
    }

    /**
     * カーソルを表示
     */
    fun showCursor() {
        print("\u001B[?25h")
    }

    /**
     * 画面をリセットし、カーソルを左上に移動
     */
    fun resetScreen() {
        print("\u001B[2J")
        print("\u001B[H") 
    }
}