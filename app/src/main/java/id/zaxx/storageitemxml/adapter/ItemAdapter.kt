package id.zaxx.storageitemxml.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.zaxx.storageitemxml.R
import id.zaxx.storageitemxml.data.entity.Item

class ItemAdapter(var list: List<Item>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog{
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var itemName: TextView
        var infoItem: TextView

        init {
            itemName = view.findViewById(R.id.name_item)
            infoItem = view.findViewById(R.id.item_info)
            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = list[position].itemName
        holder.infoItem.text = list[position].infoItem
    }
}