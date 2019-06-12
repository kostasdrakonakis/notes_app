package com.kostasdrakonakis.notes.android.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.kostasdrakonakis.notes.R
import com.kostasdrakonakis.notes.mvp.IActivityPresenter
import com.kostasdrakonakis.notes.mvp.IActivityView
import com.kostasdrakonakis.notes.util.LogUtil
import org.slf4j.Logger

abstract class BaseMVPActivity<V : IActivityView, P : IActivityPresenter<V>> : AppCompatActivity(), IActivityView {

    private var content: FrameLayout? = null
    private var wait: View? = null
    private lateinit var mPresenter: P

    protected val logger: Logger = LogUtil.getLogger(javaClass)

    private var isWaiting: Boolean = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_base)

        content = findViewById(R.id.content)

        wait = findViewById(R.id.wait)

        mPresenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        mPresenter.attachView(this as V)
    }

    override fun onStop() {
        super.onStop()
        mPresenter.detachView(this as V)
    }

    override fun finish() {
        mPresenter.onClose()
    }

    override fun setContentView(@LayoutRes id: Int) {
        content?.removeAllViews()
        layoutInflater.inflate(id, content)
    }

    override fun setContentView(view: View) {
        content?.removeAllViews()
        content?.addView(view)
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
        content?.removeAllViews()
        content?.addView(view, params)
    }

    override fun goBack() {
        super.finish()
    }

    override fun showWaiting(show: Boolean) {
        isWaiting = show
        showWaiting()
    }

    private fun showWaiting() {
        wait?.visibility = if (isWaiting) View.VISIBLE else View.GONE
    }

    protected abstract fun createPresenter(): P

    protected val presenter: P get() = mPresenter

    protected fun AppCompatActivity.isEmpty(@Nullable value: String?): Boolean {
        return TextUtils.isEmpty(value)
    }
}