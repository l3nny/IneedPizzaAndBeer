package com.example.ineedpizzabeer.utils

sealed class ResultException(
    val messageResource: Any?
) : RuntimeException() {

    class Connection(messageResource: Int) : ResultException(messageResource)

    class Unexpected(messageResource: Int) : ResultException(messageResource)

    class Timeout(messageResource: Int) : ResultException(messageResource)

    class Client(messageResource: Int) : ResultException(messageResource)

    class Server(messageResource: Any?) : ResultException(messageResource)

    class DataBase(messageResource: Any?): ResultException(messageResource)
}