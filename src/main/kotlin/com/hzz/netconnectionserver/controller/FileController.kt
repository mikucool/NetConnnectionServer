package com.hzz.netconnectionserver.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.io.File
import java.io.RandomAccessFile
import java.lang.Exception
import java.nio.channels.Channels

@Controller
class FileController {
    @GetMapping("/toyaudio")
    @ResponseBody
    fun toyaudio(response: HttpServletResponse, request: HttpServletRequest): List<String> {
        val file = File("/Users/hzz/Music")
        val listFiles = file.list { _, fileName ->
            fileName.endsWith(".mp3")
        }
        listFiles?.forEach {
            println(it)
        }

        return listFiles?.toList() ?: listOf()
    }

    @GetMapping("/downloadFile")
    fun downLoadFile(response: HttpServletResponse, request: HttpServletRequest, @RequestParam("fileName") fileName: String) {
        println("FileName: $fileName")
        if (fileName.isEmpty()) return
        val file = File("/Users/hzz/Music/$fileName")
        val randomAccessFile = RandomAccessFile(file, "rw")
        response.setHeader("Content-Disposition", "attachment; filename=\"$fileName\"")
        try {
            val responseOutputStream = Channels.newChannel(response.outputStream)
            randomAccessFile.seek(0)
            val channel = randomAccessFile.channel
            var size = randomAccessFile.length()
            var position = 0L
            while (size > 0) {
                val count = channel.transferTo(position, size, responseOutputStream)
                if (count > 0) {
                    size -= count
                    position += count
                }
            }
            responseOutputStream.close()
            channel.close()
            randomAccessFile.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return
    }
}