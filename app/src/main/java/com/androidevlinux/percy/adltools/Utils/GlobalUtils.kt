package com.androidevlinux.percy.adltools.Utils

import java.io.DataOutputStream
import java.io.File


/**
 * Created by percy on 10/11/2017.
 */
class GlobalUtils {


    fun checkRooted(): Boolean {
        try {
            val p = Runtime.getRuntime().exec("su", null, File("/"))
            val os = DataOutputStream(p.outputStream)
            os.writeBytes("pwd\n")
            os.writeBytes("exit\n")
            os.flush()
            p.waitFor()
            p.destroy()
        } catch (e: Exception) {
            return false
        }

        return true
    }


}