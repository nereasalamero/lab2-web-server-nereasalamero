package es.unizar.webeng.lab2.IntegrationTest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IntegrationTest {
    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun `in case of error it opens the error page designed`() {
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.TEXT_HTML)
        val entity = HttpEntity<String>(headers)

        val response = restTemplate.exchange(
            "http://localhost:$port/invalid-url",
            HttpMethod.GET,
            entity,
            String::class.java
        )

        // Verify status code
        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)

        // Verify HTML messages
        assertThat(response.body).contains("Oops! Something went wrong.")
        assertThat(response.body).contains("Go Back Home")
    }

    @Test
    fun `validate the use of the time endpoint`(){

    }
}
