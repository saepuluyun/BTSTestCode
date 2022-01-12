package com.example.testbts.ui.ceklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testbts.adapter.CheckListAdapter
import com.example.testbts.databinding.ActivityCheckListBinding
import com.example.testbts.model.CheckListResponseModel
import com.example.testbts.model.GenericResponse
import com.example.testbts.model.entities.CheckListResponse
import com.example.testbts.model.entities.LoginResponse
import com.example.testbts.services.checklist.CheckListViewModel
import com.example.testbts.services.checklist.IViewCheckList
import com.example.testbts.services.login.LoginViewModel
import kotlinx.android.synthetic.main.activity_check_list.*

class CheckListActivity : AppCompatActivity(), IViewCheckList {
    private lateinit var checkListAdapter: CheckListAdapter
    private lateinit var binding: ActivityCheckListBinding
    private val checkList = mutableListOf<CheckListResponse>()

    private lateinit var presenter: CheckListViewModel
    private lateinit var iView: IViewCheckList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iView = this
        presenter = CheckListViewModel(binding.root.context, iView)
        presenter.getList()

        checkListAdapter = CheckListAdapter(this, checkList)
        binding.rvChecklist.adapter = checkListAdapter
        binding.rvChecklist.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false)

        checkListAdapter.setListener(object : CheckListAdapter.OnClickListener{
            override fun onLongClick(model: CheckListResponse) {
                presenter.deleteList(model.id)
            }

            override fun onClick(model: CheckListResponse) {

            }
        })

        binding.btnSubmit.setOnClickListener {
            var name = etChecklist.text.toString().trim()
            presenter.addList(name)
        }
    }

    override fun onSuccessGetList(data: CheckListResponseModel) {
        checkList.clear()
        checkList.addAll(data.data)
        checkListAdapter.notifyDataSetChanged()
    }

    override fun onSuccessAddList(data: GenericResponse<CheckListResponse>) {
        presenter.getList()
    }

    override fun onSuccessDeleteList(data: GenericResponse<CheckListResponse>) {
        presenter.getList()
    }

    override fun onError(message: String?) {
        Toast.makeText(this, "Server Sedang terjadi masalah", Toast.LENGTH_SHORT).show()
    }
}