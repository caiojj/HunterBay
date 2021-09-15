package br.com.hunterbay.core

import android.os.RemoteException
import br.com.hunterbay.data.model.ErrorResponse
import com.google.gson.Gson

class ErrorThrow() {
    companion object {
        fun errorRequest(errorBody: String) {
            val errorBodyObj = Gson().fromJson(errorBody, ErrorResponse::class.java)
            throw RemoteException(errorBodyObj.message)
        }
    }
}