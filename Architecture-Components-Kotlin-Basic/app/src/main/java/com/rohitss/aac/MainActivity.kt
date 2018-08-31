package com.rohitss.aac

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = MainActivityViewModelFactory(AppsRepository.getInstance(AppDatabase.getInstance(this).getArticlesDAO()))
        mainActivityViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        mainActivityViewModel.getAllArticlesREPO().observe(this, Observer { articles ->
            toast("Observer Called")
            if (articles != null) {
                val adapter = ArticlesListAdapter(emptyList()) {
                    toast("Initiated")
                }
                recyclerView.adapter = adapter
            }
        })
    }
}
