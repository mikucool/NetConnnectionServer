package com.hzz.netconnectionserver.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class DefaultController {
    @GetMapping("/")
    @ResponseBody
    fun default() {
    }
}