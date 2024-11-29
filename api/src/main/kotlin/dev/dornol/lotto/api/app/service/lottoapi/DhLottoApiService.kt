package dev.dornol.lotto.api.app.service.lottoapi

import com.fasterxml.jackson.databind.ObjectMapper
import dev.dornol.lotto.api.app.dto.lottoapi.DhLottoApiDrawResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class DhLottoApiService(
    @Value("\${dornol.lotto.api.host}")
    private val apiHost: String,

    private val objectMapper: ObjectMapper
) : LottoApiService {

    override fun fetchLottoDraw(drawNo: Long): DhLottoApiDrawResponse {
        val template = RestTemplate()
        val response = template.exchange("$apiHost$drawNo", HttpMethod.GET, null, String::class.java)
        return objectMapper.readValue(response.body, DhLottoApiDrawResponse::class.java)
    }

}
