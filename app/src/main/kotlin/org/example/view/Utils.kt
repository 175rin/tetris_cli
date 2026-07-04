package org.example.view

class Utils {
    fun hideCursor() {
        print("\u001B[?25l")
    }

    fun showCursor() {
        print("\u001B[?25h")
    }

    fun resetScreen() {
        print("\u001B[2J")
        print("\u001B[H") // カーソルを左上に移動
    }
}