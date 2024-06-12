package com.lfc.mylibrary

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * @Author: 赖富城
 * @CreateTime: 2024/6/12
 * @Description:
 */

//，typealias关键字可以用于给任意类型指定一个别名
typealias PermissionCallback = (Boolean, List<String>) -> Unit
class InvisibleFragment :Fragment() {

    //作为运行时权限申请结果的回调通知方式，并将它声明成了一种函数类型变量
    private var callback: PermissionCallback? = null
    fun requestNow(cb: PermissionCallback, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, 1)
    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 1) {
            //记录所有被用户拒绝的权限
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted, deniedList) }
        }
    }
}
