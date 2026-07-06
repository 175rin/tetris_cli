package org.example.model

/**
 * テトリスの１マスを表すクラス
 * @property value マスの値（例: 0=空, 1=ブロック, 色分け等も考慮する場合はIntで色ID等）
 */
data class Cell(var value: Int)