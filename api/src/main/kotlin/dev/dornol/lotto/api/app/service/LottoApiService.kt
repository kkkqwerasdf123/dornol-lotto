package dev.dornol.lotto.api.app.service

import com.fasterxml.jackson.databind.ObjectMapper
import dev.dornol.lotto.api.app.dto.LottoApiRoundResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class LottoApiService(
    private val objectMapper: ObjectMapper,

    @Value("\${dornol.lotto.api.host}")
    private val apiHost: String
) {

    fun getLottoByRoundNo(roundNo: Long): LottoApiRoundResponse {
        val template = RestTemplate()
        val response = template.exchange("$apiHost$roundNo", HttpMethod.GET, null, String::class.java)
        return objectMapper.readValue(response.body, LottoApiRoundResponse::class.java)
    }

}
