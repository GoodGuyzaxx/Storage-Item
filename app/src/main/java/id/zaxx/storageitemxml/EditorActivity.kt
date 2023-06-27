package id.zaxx.storageitemxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import id.zaxx.storageitemxml.data.AppDatabase
import id.zaxx.storageitemxml.data.entity.Item

class EditorActivity : AppCompatActivity() {
    private lateinit var itemName : EditText
    private lateinit var infoItem : EditText
    private lateinit var btnSave : Button
    private lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        //ID dari Layout Editor
        itemName = findViewById(R.id.item_name)
        infoItem = findViewById(R.id.info_item)
        btnSave = findViewById(R.id.btnsave)

        //Database
        database = AppDatabase.getInstances(applicationContext)

        var intent = intent.extras
        if (intent!=null){
            var item = database.itemDao().get(intent.getInt("id"))

            itemName.setText(item.itemName)
            infoItem.setText(item.infoItem)
        }

        btnSave.setOnClickListener {
            if (itemName.text.isNotEmpty() && infoItem.text.isNotEmpty()) {
                if (intent!= null){
                    //Edit
                    database.itemDao().update(
                        Item(
                            intent.getInt("id"),
                            itemName.text.toString(),
                            infoItem.text.toString()
                    )
                    )
                }else {
                    //Buat
                    database.itemDao().insertAll(Item(
                        null,
                        itemName.text.toString(),
                        infoItem.text.toString()
                    ))
                }
                finish()
            } else {
                Toast.makeText(applicationContext, "Silahkan Isi semua data", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}