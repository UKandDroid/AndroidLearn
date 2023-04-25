package test.chat.domain.model.ui

data class MessageDate(val day: String, val hour: String, val minute: String) {

    override fun toString(): String {
        return "$day $hour:$minute"
    }

}
