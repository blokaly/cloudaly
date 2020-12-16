package blokaly.cloudaly.clouda.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @GetMapping("/")
    fun healthCheck(): ResponseEntity<Unit> = ResponseEntity.ok(Unit)

    @GetMapping("/envar")
    fun envar() = System.getenv()

    @GetMapping("/vmprop")
    fun vmprop() = System.getProperties()
}