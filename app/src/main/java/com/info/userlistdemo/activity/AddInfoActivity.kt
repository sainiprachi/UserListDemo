package com.info.userlistdemo.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.info.userlistdemo.R
import com.info.userlistdemo.dao.DataRepository
import com.info.userlistdemo.model.UserModel
import kotlinx.android.synthetic.main.actvity_add_info.*
import java.util.*

class AddInfoActivity : AppCompatActivity() {

    private val handler = Handler(Looper.myLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_add_info)
        initView()
    }

    fun initView() {
        button.setOnClickListener {
            when {
                TextUtils.isEmpty(edtFirstName.text) -> {
                    Toast.makeText(this@AddInfoActivity, "Enter First Name", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(edtLastName.text) -> {
                    Toast.makeText(this@AddInfoActivity, "Enter Last Name", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(edtPhone.text) -> {
                    Toast.makeText(this@AddInfoActivity, "Enter Phone Number", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(edtCity.text) -> {
                    Toast.makeText(this@AddInfoActivity, "Enter City", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(edtState.text) -> {
                    Toast.makeText(this@AddInfoActivity, "Enter State", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(edtPhone.text) -> {
                    Toast.makeText(this@AddInfoActivity, "Enter Pin Code", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val userModel=UserModel()
                    userModel.fName=edtFirstName.text.toString()
                    userModel.lName=edtLastName.text.toString()
                    userModel.phNo=edtPhone.text.toString()
                    userModel.city=edtCity.text.toString()
                    userModel.state=edtState.text.toString()
                    userModel.pinCode=edtPinCode.text.toString()
                    val min = 1
                    val max = 1000
                    val r = Random()
                    val nu: Int= r.nextInt(max - min + 1) + min
                    userModel.id=nu
                    DataRepository.insert(userModel, context = this)
                    intent = Intent(this@AddInfoActivity, MainActivity::class.java)
                    startActivity(intent)








                }
            }
        }


    }


}
