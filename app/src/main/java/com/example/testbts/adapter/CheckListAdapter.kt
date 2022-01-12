package com.example.testbts.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import com.example.testbts.R
import com.example.testbts.databinding.ItemCheckListBinding
import com.example.testbts.model.entities.CheckListResponse
import java.text.SimpleDateFormat
import java.util.*

class CheckListAdapter(private val context:Context, private val dataSet: MutableList<CheckListResponse>) : RecyclerView.Adapter<CheckListAdapter.ViewHolder>() {
    var currentUser:CheckListResponse? = null

    interface OnClickListener{
        fun onLongClick(model: CheckListResponse)
        fun onClick(model: CheckListResponse)
    }


    private lateinit var listener: CheckListAdapter.OnClickListener

    fun setListener(onClickListener: CheckListAdapter.OnClickListener){

        this.listener = onClickListener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCheckListBinding.bind(view)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_check_list, viewGroup, false)

        return ViewHolder(view)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder) {
            val data = dataSet[position]

            binding.tvName.text = data.name

            binding.tvName.setOnClickListener{
                listener.onClick(data)
            }

            binding.tvName.setOnLongClickListener{
                listener.onLongClick(data)
                true
            }

        }
    }

    override fun getItemCount() = dataSet.size

}