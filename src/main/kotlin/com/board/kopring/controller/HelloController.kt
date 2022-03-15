package com.board.kopring.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.reflect.KProperty

@RestController
class HelloController {

    @GetMapping
    fun hello(): JsonResult {
        val result = JsonResult("content", "title")
        println(result)
        return result
    }

    @GetMapping("/api")
    fun apiHello(): JsonResult {
        return JsonResult("api hello", "api")
    }
}

class Content(private val value: String) {
    init {
        println("checking")
        checkValue(value)
    }

    private fun checkValue(value: String) {
        if (value.length > 10) throw IllegalArgumentException("Content length is over 10")
    }

    operator fun getValue(jsonResult: JsonResult, property: KProperty<*>): String {
        return value
    }
}

class Title(private val value: String) {
    operator fun getValue(jsonResult: JsonResult, property: KProperty<*>): String {
        return value
    }
}

data class JsonResult(
    private val _content: String,
    private val _title: String
    ) {
    val content by Content(_content)
    val title by Title(_title)
    val value = 1
}
