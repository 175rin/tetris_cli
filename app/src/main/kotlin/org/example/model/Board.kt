package org.example.model

/**
 * テトリスの盤面を表すクラス
 * @property cells セルの２次元配列
 */
class Board(val rows: Int = 20, val cols: Int = 10) {
    val cells: Array<Array<Cell>> = Array(rows) { Array(cols) { Cell(0) } }
    var block: Block = Block.random()

    /**
     * 固定済みセル+操作中ブロックを反映して更新する
     *
     * - 固定済みセルは 2 を値として保持する
     * - 操作中ブロックのセルは 1 を値として保持する
     */
    fun syncCells() {
        cells.forEach { row ->
            row.forEach { cell ->
                cell.value = if (cell.value != 2) 0 else 2
            }
        }
   
        for ((br, bc) in block.cells()) {
            if (br in 0 until rows && bc in 0 until cols) {
                // 2(固定)セルを上書きしない
                if (cells[br][bc].value != 2) {
                    cells[br][bc].value = 1
                }
            }
        }
    }

    fun update(playerInput: PlayerInput) {
        when (playerInput.input) {
            'w' -> {
                // None(TBD: change NextBlock)
            }
            'a' -> {
                // Move left
                block.move(0, -1)
            }
            's' -> {
                // Move down
                block.move(1, 0)
            }
            'd' -> {
                // Move right
                block.move(0, 1)
            }
            'q' -> {
                // Rotate left
                block.rotateLeft()
            }
            'e' -> {
                // Rotate right
                block.rotateRight()
            }
        }
    }
}