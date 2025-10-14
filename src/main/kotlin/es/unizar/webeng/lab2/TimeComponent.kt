package es.unizar.webeng.lab2

import java.time.LocalDateTime
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


// Define a Data Transfer Object (DTO)
data class TimeDTO(val time: LocalDateTime)

// Create a Time Provider Interface
interface TimeProvider {
    fun now(): LocalDateTime
}

// Implement the Time Provider Service
@Service
class TimeService : TimeProvider {
    override fun now(): LocalDateTime = LocalDateTime.now()
}

// Create an Extension Function
fun LocalDateTime.toDTO(): TimeDTO = TimeDTO(time = this)

// Create a REST Controller
@RestController
class TimeController(private val service: TimeProvider) {
    @GetMapping("/time")
    fun time(): TimeDTO = service.now().toDTO()
}