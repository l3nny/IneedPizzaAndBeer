package com.example.ineedpizzabeer.utils

object Keys {

    init {
        System.loadLibrary("native-lib")
    }

    external fun apiKeyYelp(): String
}