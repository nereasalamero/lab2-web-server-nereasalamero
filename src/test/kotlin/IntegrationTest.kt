package es.unizar.webeng.lab2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(TestRestTemplateConfig::class)
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

        val response =
            restTemplate.exchange(
                "https://localhost:$port",
                HttpMethod.GET,
                entity,
                String::class.java,
            )

        // Verify status code
        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)

        // Verify HTML messages
        assertThat(response.body).contains("Oops! Something went wrong")
    }

    @Test
    fun `validate the use of the time endpoint`() {
        val response = restTemplate.getForEntity("https://localhost:$port/time", String::class.java)
        // Verify HTTP 200 OK
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        // Verify body contains "timestamp"
        assertThat(response.body).isNotNull
    }
}
