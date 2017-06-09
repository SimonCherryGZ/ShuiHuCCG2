package com.simoncherry.shuihuccg2.util

import android.widget.ImageView
import android.graphics.Bitmap
import android.graphics.BitmapFactory



/**
 * <pre>
 *     author : Donald
 *     e-mail : xxx@xx
 *     time   : 2017/06/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */

fun ImageView.setSampledBitmap(
        resId: Int, reqWidth: Int, reqHeight: Int) {

    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(resources, resId, options)
    // 调用上面定义的方法计算inSampleSize值
    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
    // 2016.03.08
    options.inPreferredConfig = Bitmap.Config.RGB_565
    // 使用获取到的inSampleSize值再次解析图片
    options.inJustDecodeBounds = false

    this.setImageBitmap(BitmapFactory.decodeResource(resources, resId, options))
}

fun calculateInSampleSize(
        options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    // 源图片的高度和宽度
    val height = options.outHeight
    val width = options.outWidth
    var inSampleSize = 1
    if (height > reqHeight || width > reqWidth) {
        // 计算出实际宽高和目标宽高的比率
        val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
        val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())
        // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
        // 一定都会大于等于目标的宽和高。
        inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
    }
    return inSampleSize
}