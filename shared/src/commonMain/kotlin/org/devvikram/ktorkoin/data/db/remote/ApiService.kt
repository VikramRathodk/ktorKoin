package org.devvikram.ktorkoin.data.db.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import org.devvikram.ktorkoin.contants.AppConst
import org.devvikram.ktorkoin.data.model.States
import org.devvikram.ktorkoin.utils.NetworkError
import org.devvikram.ktorkoin.utils.Result

class ApiService(
    private val client: HttpClient
) {

    suspend fun getStates() : HttpResponse {
        val response = client.get {
            "${AppConst.BASE_URL}states"
        }
        return  response
    }

}