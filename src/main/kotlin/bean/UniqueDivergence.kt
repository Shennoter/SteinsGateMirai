package pers.shennoter.bean

class UniqueDivergence : ArrayList<Divergence>()

data class Divergence(
    val number: String,
    val abstract: String,
    val detail: String
)