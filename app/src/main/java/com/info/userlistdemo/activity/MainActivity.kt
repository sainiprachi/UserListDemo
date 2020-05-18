package com.info.userlistdemo.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.info.userlistdemo.R
import com.info.userlistdemo.adapter.UserListAdapter
import com.info.userlistdemo.dao.DataRepository
import com.info.userlistdemo.dao.OnUserDataCallback
import com.info.userlistdemo.model.UserModel

class MainActivity : AppCompatActivity() ,UserListAdapter.onClickEditIv {

    var userList = ArrayList<UserModel>()
    private lateinit var horizontalScrollView: HorizontalScrollView
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnSave:Button
    private lateinit var btnCancel:Button
    private lateinit var userListAdapter: RecyclerView.Adapter<*>
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    //simply initializing the tag for the screen logs.
    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initiliseView()
    }

    /**
     * initialize the components.
     */
    fun initiliseView() {
        linearLayoutManager = LinearLayoutManager(this)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)
        //getting recyclerview from xml
        recyclerView = findViewById(R.id.recUserList) as RecyclerView
        recyclerView?.layoutManager = linearLayoutManager

        btnSave.setOnClickListener(){
           val intent=Intent(this,UpdateInfoActivity::class.java)
            intent.putExtra("userModel",userModel)
            startActivity(intent)
        }
        btnCancel.setOnClickListener(){
            Toast.makeText(this,"Cancel clicked", Toast.LENGTH_SHORT).show()
        }


    }

    /**
     * fatch user data from db.
     */
    fun getUserData() {
        DataRepository.getUserInfo(this@MainActivity, object : OnUserDataCallback<List<UserModel>> {
            override fun onUserDataAvailable(data: List<UserModel>?) {
                Log.d(TAG, "userData ${data?.size}")
                userList = data as ArrayList<UserModel>
                val userModel =UserModel()
                userModel.fName=""
                userModel.lName=""
                userModel.city=""
                userModel.state=""
                userModel.pinCode=""
                userList.add(0,userModel)
                setAdapter(userList)
            }
        })
    }


    override fun onResume() {
        super.onResume()
        getUserData()
    }

    /**
     * set adapter to recycler view.
     */
    fun setAdapter(userList: ArrayList<UserModel>) {
        userListAdapter = UserListAdapter(userList,this)
        //now adding the adapter to recyclerview
        recyclerView?.adapter = userListAdapter
    }
    var userModel =UserModel()

    override fun getPoss(position: Int) {
        userModel=userList.get(position)

    }

    /**
     * this method insert multiple records into the database.
     */


}

