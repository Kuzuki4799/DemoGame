package com.shockwave.demogame

import android.app.Activity
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class DialogSuccess(i: Int, i2: Int, activity: Activity) :
    BaseDialog(activity, Gravity.CENTER, false), View.OnClickListener {

    init {
        val imgClose = (dialogPlus?.findViewById(R.id.btn_dialog_custom_ok) as ImageView)
        val successImg = (dialogPlus?.findViewById(R.id.success_img) as ImageView)
        val jifenTv = (dialogPlus?.findViewById(R.id.jifen_tv) as TextView)
        jifenTv.text = "+$i COIN"
        successImg.clipToOutline = true
        successImg.setImageResource(i2)
        imgClose.setOnClickListener(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.dialog_beauty_success
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_dialog_custom_ok -> dialogPlus?.dismiss()
        }
    }
}