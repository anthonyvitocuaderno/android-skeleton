package com.vitocuaderno.skeleton.utils.ext

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

@BindingAdapter("avatarUrl")
fun ImageView.loadAvatarUrl(url: String?) {
    Glide.with(context)
        .load(url)
//        .placeholder(R.drawable.ic_default_avatar)
//        .error(R.drawable.ic_default_avatar)
//        .fallback(R.drawable.ic_default_avatar)
        .skipMemoryCache(false)
        .dontAnimate()
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Log.e("imageview.ext", "Glide.onLoadFailed Error $e")
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
        })
        .into(this)
}
//
//@BindingAdapter("imageSrc")
//fun ImageView.loadImageUrl(url: String?) {
//    if (url != null && url.isNotEmpty()) {
//        GlideApp.with(this.context)
//            .load("$url")
//            .placeholder(R.drawable.ic_create_post_placeholder)
//            .skipMemoryCache(false)
//            .centerCrop()
//            .listener(object : RequestListener<Drawable> {
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    Timber.e("onLoadFailed Error $e ")
//                    return false
//                }
//
//                override fun onResourceReady(
//                    resource: Drawable?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    dataSource: DataSource?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    return false
//                }
//            })
//            .into(this)
//    }
//}
//
//fun ImageView.loadOriginalImageUrl(url: String?) {
//    if (url != null && url.isNotEmpty()) {
//        GlideApp.with(this.context)
//            .load("$url")
//            .placeholder(R.drawable.ic_create_post_placeholder)
//            .skipMemoryCache(false)
//            .listener(object : RequestListener<Drawable> {
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    Timber.e("onLoadFailed Error $e ")
//                    return false
//                }
//
//                override fun onResourceReady(
//                    resource: Drawable?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    dataSource: DataSource?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    return false
//                }
//            })
//            .into(this)
//    }
//}
//
//fun ImageView.loadDrawable(@DrawableRes resId: Int?) {
//    resId?.let {
//        GlideApp.with(context)
//            .load(resId)
//            .dontAnimate()
//            .into(this)
//    }
//}
