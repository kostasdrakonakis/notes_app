package com.kostasdrakonakis.notes.android.activity

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.kostasdrakonakis.notes.R
import com.kostasdrakonakis.notes.mvp.IActivityPresenter
import com.kostasdrakonakis.notes.mvp.IActivityView
import com.kostasdrakonakis.notes.util.LogUtil
import org.slf4j.Logger

abstract class BaseMVPActivity<V : IActivityView, P : IActivityPresenter<V>> : AppCompatActivity(), IActivityView {

    private var content: FrameLayout? = null
    private var wait: View? = null
    private lateinit var presenter: P

    protected val logger: Logger = LogUtil.getLogger(javaClass)

    private var isWaiting: Boolean = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_base)

        content = findViewById(R.id.content)

        wait = findViewById(R.id.wait)

        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this as V)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this as V)
    }

    override fun finish() {
        presenter.onClose()
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

    protected fun getPresenter(): P {
        return presenter
    }
}