package com.example.testbts.services.login

import android.content.Context
import com.digitaloasis.openweather.network.servicesutils.ApiClient.getClient
import com.example.testbts.R
import com.example.testbts.model.RegisterModel
import com.example.testbts.model.entities.RegisterResponse
import com.example.testbts.model.GenericResponse
import com.example.testbts.model.LoginModel
import com.example.testbts.model.entities.LoginResponse
import com.example.testbts.services.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

public class LoginViewModel {
    var iViewLogin: IViewLogin? = null
    private lateinit var apiService: ApiService
    private var context: Context? = null

    constructor(context: Context?, iViewLogin: IViewLogin) {
        this.context = context
        this.iViewLogin = iViewLogin
    }

    fun login(username: String, password: String) {
        val disposable = CompositeDisposable()
        var register = LoginModel(username, password)
        // call end point api
        apiService = getClient(context!!)!!.create(ApiService::class.java)
        disposable.add(
            apiService.login(register)
                !!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .sorted()
                .subscribeWith(object : DisposableObserver<GenericResponse<LoginResponse>>() {
                    override fun onNext(response: GenericResponse<LoginResponse>) {
                        // Show response from body when success
                        iViewLogin?.onSuccessLogin(response)
                    }

                    override fun onError(e: Throwable) {
                        // Show response from body when error
                        if (e is HttpException) {
                            iViewLogin?.onError(
                                context!!.getString(R.string.error_network_problem_with_server)
                            )
                        }
                    }

                    override fun onComplete() {}
                })
        )
    }
}