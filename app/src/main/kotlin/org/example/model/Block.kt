package org.example.model

/**
 * テトリミノの種類
 */
enum class BlockType {
    I,
    O,
    T,
    S,
    Z,
    J,
    L,
}

/**
 * 操作中のテトリミノ
 * @property relativeCells 回転0のときの相対座標（行, 列）
 * @property row 盤面上端からの行位置（形状の基準点）
 * @property col 盤面左端からの列位置（形状の基準点）
 * @property rotation 時計回りの回転状態（0〜3）
 */
class Block(
    val relativeCells: List<Pair<Int, Int>>,
    var row: Int = 0,
    var col: Int = 3,
    rotation: Int = 0,
) {
    var rotation: Int = rotation % 4
        private set

    /** 現在の回転を反映した相対座標 */
    fun rotatedCells(): List<Pair<Int, Int>> {
        var shape = relativeCells
        repeat(rotation) { shape = rotateClockwise(shape) }
        return shape
    }

    /** 盤面上の占有セル座標（行, 列） */
    fun cells(): List<Pair<Int, Int>> =
        rotatedCells().map { (dr, dc) -> row + dr to col + dc }

    fun rotateRight() {
        rotation = (rotation + 1) % 4
    }

    fun rotateLeft() {
        rotation = (rotation + 3) % 4
    }

    fun move(dr: Int, dc: Int) {
        row += dr
        col += dc
    }

    companion object {
        private typealias Shape = List<Pair<Int, Int>>

        /** 回転0の基本形状 */
        private val BASE_SHAPES: Map<BlockType, Shape> = mapOf(
            BlockType.I to listOf(0 to 0, 0 to 1, 0 to 2, 0 to 3),
            BlockType.O to listOf(0 to 0, 0 to 1, 1 to 0, 1 to 1),
            BlockType.T to listOf(0 to 1, 1 to 0, 1 to 1, 1 to 2),
            BlockType.S to listOf(0 to 1, 0 to 2, 1 to 0, 1 to 1),
            BlockType.Z to listOf(0 to 0, 0 to 1, 1 to 1, 1 to 2),
            BlockType.J to listOf(0 to 0, 1 to 0, 1 to 1, 1 to 2),
            BlockType.L to listOf(0 to 2, 1 to 0, 1 to 1, 1 to 2),
        )

        private fun rotateClockwise(shape: Shape): Shape {
            val rotated = shape.map { (r, c) -> c to -r }
            val minRow = rotated.minOf { it.first }
            val minCol = rotated.minOf { it.second }
            return rotated.map { (r, c) -> r - minRow to c - minCol }
        }

        fun of(type: BlockType, row: Int = 0, col: Int = 3, rotation: Int = 0): Block =
            Block(BASE_SHAPES.getValue(type), row, col, rotation)

        fun random(): Block = of(BlockType.entries.random())
    }
}
