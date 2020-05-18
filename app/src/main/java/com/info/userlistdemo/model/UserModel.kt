package com.info.userlistdemo.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity

 class UserModel() : Parcelable {


    @NonNull
    @PrimaryKey
    @ColumnInfo(name="id")
    var id: Int = 0


   @ColumnInfo(name="fName")
    lateinit var fName: String


    @ColumnInfo(name="lName")
    lateinit var lName: String

    @ColumnInfo(name = "phNo")
    lateinit var phNo: String

    @ColumnInfo(name = "city")
    lateinit var city: String

    @ColumnInfo(name = "state")
    lateinit var state:String
    @ColumnInfo(name = "pinCode")
    lateinit var pinCode: String

   constructor(parcel: Parcel) : this() {
      id = parcel.readInt()
      fName = parcel.readString()!!
      lName = parcel.readString()!!
      phNo = parcel.readString()!!
      city = parcel.readString()!!
      state = parcel.readString()!!
      pinCode = parcel.readString()!!
   }

   override fun writeToParcel(parcel: Parcel, flags: Int) {
      parcel.writeInt(id)
      parcel.writeString(fName)
      parcel.writeString(lName)
      parcel.writeString(phNo)
      parcel.writeString(city)
      parcel.writeString(state)
      parcel.writeString(pinCode)
   }

   override fun describeContents(): Int {
      return 0
   }

   companion object CREATOR : Parcelable.Creator<UserModel> {
      override fun createFromParcel(parcel: Parcel): UserModel {
         return UserModel(parcel)
      }

      override fun newArray(size: Int): Array<UserModel?> {
         return arrayOfNulls(size)
      }
   }
}






