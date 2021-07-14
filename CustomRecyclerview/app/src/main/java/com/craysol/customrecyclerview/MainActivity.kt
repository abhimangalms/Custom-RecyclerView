package com.craysol.customrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class MainActivity : AppCompatActivity(), RecyclerItemAdapter.ItemClickedListener {

    private val boxItemList = ArrayList<RecyclerItemModel>()
    private val adapter = RecyclerItemAdapter(boxItemList, this)

    private var clickedItemPosition: Int? = null


    private lateinit var fabAddNew: FloatingActionButton
    private lateinit var recycler_view: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.show()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(false)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        recycler_view = findViewById(R.id.recyclerView)
        fabAddNew = findViewById(R.id.fabAddNewBox)


        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

        fabAddNew.setOnClickListener {

            val newItem = RecyclerItemModel("New item")
            boxItemList.add(newItem)
            adapter.notifyDataSetChanged()
            recycler_view.smoothScrollToPosition(adapter.itemCount - 1)

        }

    }

    override fun onItemClick(position: Int) {

        clickedItemPosition = position

        Toast.makeText(applicationContext, "onBoxClick: $position", Toast.LENGTH_SHORT).show()

    }
}