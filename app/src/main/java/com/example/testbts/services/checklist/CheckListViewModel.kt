package com.example.testbts.services.checklist

import android.content.Context
import com.digitaloasis.openweather.network.servicesutils.ApiClient.getClient
import com.example.testbts.R
import com.example.testbts.model.*
import com.example.testbts.model.entities.CheckListResponse
import com.example.testbts.model.entities.LoginResponse
import com.example.testbts.services.ApiService
import com.example.testbts.utils.Session
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

public class CheckListViewModel {
    var iView: IViewCheckList? = null
    private lateinit var apiService: ApiService
    private var context: Context? = null
    private lateinit var session: Session

    constructor(context: Context?, iView: IViewCheckList) {
        this.context = context
        this.iView= iView
        session = Session(context)
    }

    fun getList(){
        val disposable = CompositeDisposable()
        // call end point api
        apiService = getClient(context!!)!!.create(ApiService::class.java)
        disposable.add(
            apiService.getCheckList(session.token)
                !!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .sorted()
                .subscribeWith(object : DisposableObserver<CheckListResponseModel>() {
                    override fun onNext(response: CheckListResponseModel) {
                        // Show response from body when success
                        iView?.onSuccessGetList(response)
                    }

                    override fun onError(e: Throwable) {
                        // Show response from body when error
                        if (e is HttpException) {
                            iView?.onError(
                                context!!.getString(R.string.error_network_problem_with_server)
                            )
                        }
                    }

                    override fun onComplete() {}
                })
        )
    }

    fun addList(checkList: String) {
        val disposable = CompositeDisposable()
        var checkListModel = CheckListModel(checkList)
        // call end point api
        apiService = getClient(context!!)!!.create(ApiService::class.java)
        disposable.add(
            apiService.addCheckList(session.token, checkListModel)
            !!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .sorted()
                .subscribeWith(object : DisposableObserver<GenericResponse<CheckListResponse>>() {
                    override fun onNext(responseModel: GenericResponse<CheckListResponse>) {
                        // Show response from body when success
                        iView?.onSuccessAddList(responseModel)
                    }

                    override fun onError(e: Throwable) {
                        // Show response from body when error
                        if (e is HttpException) {
                            iView?.onError(
                                context!!.getString(R.string.error_network_problem_with_server)
                            )
                        }
                    }

                    override fun onComplete() {}
                })
        )
    }

    fun deleteList(id: Int){
        val disposable = CompositeDisposable()
        // call end point api
        apiService = getClient(context!!)!!.create(ApiService::class.java)
        disposable.add(
            apiService.deleteCheckList(session.token, id)
            !!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .sorted()
                .subscribeWith(object : DisposableObserver<GenericResponse<CheckListResponse>>() {
                    override fun onNext(response: GenericResponse<CheckListResponse>) {
                        // Show response from body when success
                        iView?.onSuccessDeleteList(response)
                    }

                    override fun onError(e: Throwable) {
                        // Show response from body when error
                        if (e is HttpException) {
                            iView?.onError(
                                context!!.getString(R.string.error_network_problem_with_server)
                            )
                        }
                    }

                    override fun onComplete() {}
                })
        )
    }
}