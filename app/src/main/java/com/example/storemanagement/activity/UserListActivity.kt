package com.example.storemanagement.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storemanagement.R
import com.example.storemanagement.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_user_list.*

class UserListActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        val userAdapter= UserAdapter()
        rvUser.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvUser.adapter=userAdapter
    }
}