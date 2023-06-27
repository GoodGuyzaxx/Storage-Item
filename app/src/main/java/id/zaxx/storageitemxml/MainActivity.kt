package id.zaxx.storageitemxml

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.ClipDrawable.VERTICAL
import android.icu.text.Transliterator.Position
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.zaxx.storageitemxml.adapter.ItemAdapter
import id.zaxx.storageitemxml.data.AppDatabase
import id.zaxx.storageitemxml.data.entity.Item

class MainActivity : AppCompatActivity() {

    private lateinit var recycleview : RecyclerView
    private lateinit var fab : FloatingActionButton
    private var list = mutableListOf<Item>()
    private lateinit var adapter: ItemAdapter
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleview = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.fab)

        database = AppDatabase.getInstances(applicationContext)
        adapter = ItemAdapter(list)

        adapter.setDialog(object : ItemAdapter.Dialog{
            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setTitle(list[position].itemName)
                dialog.setItems(R.array.items_options,DialogInterface.OnClickListener { dialog, which ->
                    if (which== 0 ){
                        //Ubah
                        val intent = Intent(this@MainActivity,EditorActivity::class.java)
                        intent.putExtra("id",list[position].uid)
                        startActivity(intent)
                    }else if (which == 1){
                       //Hapus
                        database.itemDao().delete(list[position])
                        getData()
                    }else {
                        dialog.dismiss()
                    }
                })
                val dialogView = dialog.create()
                dialogView.show()
            }

        })

        recycleview.adapter = adapter
        recycleview.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recycleview.addItemDecoration(DividerItemDecoration(applicationContext,RecyclerView.VERTICAL))

        fab.setOnClickListener{
            startActivity(Intent(this,EditorActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.itemDao().getAll())
        adapter.notifyDataSetChanged()
    }
}