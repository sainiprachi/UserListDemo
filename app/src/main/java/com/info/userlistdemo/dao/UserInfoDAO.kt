package com.info.userlistdemo.dao

import androidx.room.*
import com.info.userlistdemo.model.UserModel
import io.reactivex.Flowable
@Dao
interface UserInfoDAO {

    @Query("Select * from UserModel")
    fun getUserInfo(): Flowable<List<UserModel>?>



    @Query("UPDATE UserModel SET fName = :fName,lName=:lName,phNo=:phNo,city=:city,state=:state,pinCode=:pinCode WHERE id=:tid")
    fun updateInfo(tid: Int,fName:String,lName:String,phNo:String,city:String,state:String,pinCode:String)



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userModel: UserModel)
}