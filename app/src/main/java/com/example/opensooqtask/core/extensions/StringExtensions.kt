package com.example.opensooqtask.core.extensions

fun String.removeCurlyBraces(): String {
    return this.replace("{", "").replace("}", "")
}