package com.hackathon.zero.base

import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.w3c.dom.Text

object DataBindingAdapters {

    @BindingAdapter("bind:lengthText", "bind:maxLength")
    fun setLengthText(view: TextView, length: Int, max: Int) {
        view.text = "$length/$max"
    }

    @BindingAdapter("bind:heightFormat")
    fun setHeightFormat(view: TextView, height: Int) {
        view.text = "${height}cm"
    }

    @BindingAdapter("bind:weightFormat")
    fun setWeightFormat(view: TextView, weight: Int) {
        view.text = "${weight}kg"
    }
}