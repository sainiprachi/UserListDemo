package com.info.userlistdemo.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.info.userlistdemo.R
import com.info.userlistdemo.dao.DataRepository
import com.info.userlistdemo.model.UserModel
import kotlinx.android.synthetic.main.actvity_add_info.*
import java.util.*


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class UpdateInfoActivity :AppCompatActivity (){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_add_info)
        initView()
    }


    /*
    * initialize the view and update data
    * */

    private fun initView() {
        var userModel=UserModel()
        if (intent!=null){
            val intent = intent
            userModel = intent.getParcelableExtra("userModel")
            edtFirstName.setText(userModel.fName)
            edtLastName.setText(userModel.lName)
            edtCity.setText(userModel.city)
            edtState.setText(userModel.state)
            edtPhone.setText(userModel.phNo)
            edtPinCode.setText(userModel.pinCode)

        }

        button.setOnClickListener {
            when {
                TextUtils.isEmpty(edtFirstName.text) -> {
                    Toast.makeText(this, "Enter First Name", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(edtLastName.text) -> {
                    Toast.makeText(this, "Enter Last Name", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(edtPhone.text) -> {
                    Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(edtCity.text) -> {
                    Toast.makeText(this, "Enter City", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(edtState.text) -> {
                    Toast.makeText(this, "Enter State", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(edtPhone.text) -> {
                    Toast.makeText(this, "Enter Pin Code", Toast.LENGTH_SHORT).show()
                }
                else -> {

                    userModel.fName=edtFirstName.text.toString()
                    userModel.lName=edtLastName.text.toString()
                    userModel.phNo=edtPhone.text.toString()
                    userModel.city=edtCity.text.toString()
                    userModel.state=edtState.text.toString()
                    userModel.pinCode=edtPinCode.text.toString()
                    DataRepository.update(userModel.id,userModel.fName,userModel.lName,userModel.phNo,userModel.city,userModel.state,userModel.pinCode,this)
                    intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)








                }
            }
        }


    }
}