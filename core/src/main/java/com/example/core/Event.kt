package com.example.core



data class EventResponse(val _embedded: ListEvents)
data class ListEvents(val events: List<Event>)
data class Image(val url: String, )

data class Event(
    val name: String,
    val desc: String,
    val images: List<Image>
)

