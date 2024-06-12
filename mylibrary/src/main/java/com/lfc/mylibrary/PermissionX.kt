package com.lfc.mylibrary

import androidx.fragment.app.FragmentActivity

/**
 * @Author: 赖富城
 * @CreateTime: 2024/6/12
 * @Description:
 */
object PermissionX {
    private const val TAG = "InvisibleFragment"
    fun request(activity: FragmentActivity, vararg permissions: String, callback:
    PermissionCallback) {
        val fragmentManager = activity.supportFragmentManager

        //判断传入的Activity参数中是否已经包含了指定TAG的Fragment，也就是我们编写的InvisibleFragment
        val existedFragment = fragmentManager.findFragmentByTag(TAG)

        //如果已经包含则直接使用该Fragment，否则就创建一个新的InvisibleFragment实例，并将它添加到Activity中，同时指定一个TAG
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback, *permissions)
    }
}
