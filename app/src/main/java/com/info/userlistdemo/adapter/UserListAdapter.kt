package com.info.userlistdemo.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.info.userlistdemo.R
import com.info.userlistdemo.model.UserModel
import kotlinx.android.synthetic.main.user_detail_item.view.*

class UserListAdapter(var userList: ArrayList<UserModel>,var adapterOnClick: onClickEditIv) :
    RecyclerView.Adapter<ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEMS = 1



    fun updateUserList(newUsers: List<UserModel>) {
        userList.clear()
        userList.addAll(newUsers)
        notifyDataSetChanged()
    }

   public interface onClickEditIv{
        fun getPoss(position: Int)


    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val userView: View
        if (viewType == 0) {
            userView =LayoutInflater.from(parent.context).inflate(R.layout.user_header, parent, false)
            return  VHHeaderTitle(userView);
        } else {
            userView=  (LayoutInflater.from(parent.context)
                .inflate(R.layout.user_detail_item, parent, false))
            return UserDetailHolder(userView,adapterOnClick)
        }
        throw RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly.");
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (holder is UserDetailHolder) {
            val userModel :UserModel=userList[position]
            holder.bind(userModel)
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (isPositionHeader(position)) {

            return TYPE_HEADER;
        }

        return TYPE_ITEMS;
    }


    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }
    class UserDetailHolder(v: View, val adapterOnClick: onClickEditIv) : ViewHolder(v), View.OnClickListener {
        private var view: View = v

        private val txtFirstNameVal = view.txtFirstNameVal as EditText
        private val txtLastNameVal = view.txtLastNameVal as EditText
        private val txtPhoneNoVal = view.txtPhoneNoVal as EditText
        private val txtCityVal = view.txtCityVal as EditText
        private val txtStateVal = view.txtStateVal as EditText
        private val txtPinCodeVal = view.txtPinCodeVal as EditText
        private val btnEdit = view.btnEdit
        fun bind(userModel: UserModel) {
            txtFirstNameVal.setText(userModel.fName)
            txtLastNameVal.setText(userModel.lName)
            txtPhoneNoVal.setText(userModel.phNo)
            txtCityVal.setText(userModel.city)
            txtStateVal.setText(userModel.state)
            txtPinCodeVal.setText(userModel.pinCode)

            btnEdit.setOnClickListener {
                adapterOnClick.getPoss(adapterPosition)
            }
        }

        override fun onClick(v: View?) {



        }


    }

    private class VHHeaderTitle(view: View?) : ViewHolder(view!!)
}