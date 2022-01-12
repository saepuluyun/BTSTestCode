package com.example.testbts.services.register

import android.content.Context
import com.digitaloasis.openweather.network.servicesutils.ApiClient.getClient
import com.example.testbts.R
import com.example.testbts.model.RegisterModel
import com.example.testbts.model.entities.RegisterResponse
import com.example.testbts.model.GenericResponse
import com.example.testbts.services.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

public class RegisterViewModel {
    var iViewRegister: IViewRegister? = null
    private lateinit var apiService: ApiService
    private var context: Context? = null

    constructor(context: Context?, iViewRegister: IViewRegister) {
        this.context = context
        this.iViewRegister = iViewRegister
    }

    fun register(username: String, password: String, email: String) {
        val disposable = CompositeDisposable()
        var register = RegisterModel(username, password, email)
        // call end point api
        apiService = getClient(context!!)!!.create(ApiService::class.java)
        disposable.add(
            apiService.register(register)
                !!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .sorted()
                .subscribeWith(object : DisposableObserver<GenericResponse<RegisterResponse>>() {
                    override fun onNext(response: GenericResponse<RegisterResponse>) {
                        // Show response from body when success
                        iViewRegister?.onSuccessRegister(response)
                    }

                    override fun onError(e: Throwable) {
                        // Show response from body when error
                        if (e is HttpException) {
                            iViewRegister?.onError(
                                context!!.getString(R.string.error_network_problem_with_server)
                            )
                        }
                    }

                    override fun onComplete() {}
                })
        )
    }
}