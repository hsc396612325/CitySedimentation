package com.heshucheng.graduation.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.baidu.location.d.j.v


import com.heshucheng.graduation.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*

import java.util.regex.Pattern

/**
 * A login screen that offers login via phone/password.
 */
class LoginActivity : AppCompatActivity() {


    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private var mPhoneView: AutoCompleteTextView? = null
    private var mPasswordView: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        initToolbar()
        initView()

    }

    private fun initToolbar() {
        toolbarTitle.text = "tpplbar"

        toolbar.setBackgroundColor(Color.WHITE)
        toolbar.setTitleTextColor(Color.BLACK)
        toolbarTitle.setTextColor(Color.BLACK)
        toolbarTitle.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        toolbar.background.alpha = 255
        toolbarTitle.text = "login"
        toolbar.title = "登录"
    }

    private fun initView() {
        // Set up the login form.
        password.setOnEditorActionListener(TextView.OnEditorActionListener { textView, id, keyEvent ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })


        phone_sign_in_button.setOnClickListener { attemptLogin() }

        phone_register_button.setOnClickListener({ v ->
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        })
    }

    private fun attemptLogin() {

        // Reset errors.
        mPhoneView!!.error = null
        mPasswordView!!.error = null

        // Store values at the time of the login attempt.
        val phone = mPhoneView!!.text.toString()
        val password = mPasswordView!!.text.toString()

        var cancel = false
        var focusView: View? = null


        //检查一个有效的电话地址。
        if (TextUtils.isEmpty(phone)) {
            mPhoneView!!.error = getString(R.string.error_field_required)
            focusView = mPhoneView
            cancel = true
        } else if (!isPhoneValid(phone)) {
            mPhoneView!!.error = getString(R.string.error_invalid_phone)
            focusView = mPhoneView
            cancel = true
        }

        if (cancel) {
            //有一个错误;不要尝试登录，并将第一个表单字段以错误的方式集中。
            focusView!!.requestFocus()
        } else {
            // showProgress(true);
        }
    }

    private fun isPhoneValid(phone: String): Boolean { //判断是否为手机号
        //TODO: Replace this with your own logic
        val pattern = Pattern.compile("[0-9]*")
        return phone.length == 11 && pattern.matcher(phone).matches()
    }

}

