package dev.dornol.lotto.api.app.service.lottoapi

import dev.dornol.lotto.api.app.dto.lottoapi.DhLottoApiDrawResponse

interface LottoApiService {

    fun fetchLottoDraw(drawNo: Long): DhLottoApiDrawResponse

}