package org.devvikram.ktorkoin.data.db.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import org.devvikram.ktorkoin.contants.AppConst

class ApiService(
    private val client: HttpClient
) {

    suspend fun getStates() : HttpResponse {
        val response = client.post {
            url("${AppConst.BASE_URL}states.php")
            formData {
                append("state", "1")
            }
        }
        return response
    }

}