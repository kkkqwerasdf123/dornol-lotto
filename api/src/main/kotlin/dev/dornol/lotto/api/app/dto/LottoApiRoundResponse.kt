package dev.dornol.lotto.api.app.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class LottoApiRoundResponse(
    val returnValue: String,
    @field:JsonProperty("drwNo")
    val roundNo: Long?,
    @field:JsonProperty("drwNoDate")
    @field:JsonFormat(pattern = "yyyy-MM-dd")
    val date: LocalDate?,
    @field:JsonProperty("totSellamnt")
    val totalAmount: Long?,
    @field:JsonProperty("firstWinamnt")
    val firstWinnerAmount: Long?,
    @field:JsonProperty("firstPrzwnerCo")
    val firstWinnerCount: Long?,
    @field:JsonProperty("drwtNo1")
    val number1: Int?,
    @field:JsonProperty("drwtNo2")
    val number2: Int?,
    @field:JsonProperty("drwtNo3")
    val number3: Int?,
    @field:JsonProperty("drwtNo4")
    val number4: Int?,
    @field:JsonProperty("drwtNo5")
    val number5: Int?,
    @field:JsonProperty("drwtNo6")
    val number6: Int?,
    @field:JsonProperty("bnusNo")
    val bonusNumber: Int?,
)
