package pers.shennoter.bean

class SGTimeline : ArrayList<Event>()

data class Event(
    val attractorField: String,
    val year: String,
    val date: String,
    val detail: String
)