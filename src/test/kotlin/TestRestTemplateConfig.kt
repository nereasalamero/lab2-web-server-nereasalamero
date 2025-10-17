package es.unizar.webeng.lab2

import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory
import org.apache.hc.core5.http.config.RegistryBuilder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.function.Supplier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

// Create
@TestConfiguration
class TestRestTemplateConfig {
    @Bean
    fun testRestTemplate(): TestRestTemplate {
        // Accept every certificate (TrustAll)
        val trustAll =
            arrayOf<TrustManager>(
                object : X509TrustManager {
                    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()

                    // Don't check client certificates
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>,
                        authType: String,
                    ) {}

                    // Don't check server certificates
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>,
                        authType: String,
                    ) {}
                },
            )

        // Create a SSLContext with TLS protocol
        val sslContext =
            SSLContext.getInstance("TLS").apply {
                init(null, trustAll, SecureRandom())
            }

        // SSL socket factory that doesn't verify hostnames
        val sslSocketFactory = SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE)

        // Register the SSL socket factory for HTTPS connection
        val sslSocketFactoryRegistry =
            RegistryBuilder
                .create<org.apache.hc.client5.http.socket.ConnectionSocketFactory>()
                .register("https", sslSocketFactory)
                .build()

        // Connection manager
        val cm = PoolingHttpClientConnectionManager(sslSocketFactoryRegistry)

        //  Create a custom client using connection manager
        val client =
            HttpClients
                .custom()
                .setConnectionManager(cm)
                .build()

        // HTTP Request factory that creates HTTP requests using the custom client.
        val fact = HttpComponentsClientHttpRequestFactory(client)

        // Build and return a TestRestTemplate
        val builder = RestTemplateBuilder().requestFactory(Supplier { fact })
        return TestRestTemplate(builder)
    }
}
