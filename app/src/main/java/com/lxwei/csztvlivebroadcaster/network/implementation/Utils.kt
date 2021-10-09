package com.lxwei.csztvlivebroadcaster.network.implementation

import java.security.MessageDigest
import java.util.*
import java.util.concurrent.TimeUnit

object Utils {

    /**
     * Generate a random alphanumeric string of a specific length
     *
     * @param n The length of targeted string
     * @return the random string
     */
    fun randomString(n: Int): String {
        val charPool = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..n)
            .map { charPool.random() }
            .joinToString("")
    }

    /**
     *
     */
    fun getTimeStamp(n: Int = 6): String {
        val time = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
        return "$time" + randomString(n)
    }

    /**
     *
     */
    fun getSignature(versionName: String, random: String): String {
        val apiKey = "eecca5b6365d9607ee5a9d336962c534"
        val magic = "eUxzMWNSM2l4RHR3SDVyaXNPT1pLZDVVWWZOVkR2UzI="
        val signString = "$apiKey&$magic&${versionName}&$random"

        val sha1 = MessageDigest.getInstance("SHA-1")
        sha1.update(signString.toByteArray())
        val digestInHex = sha1.digest().toHex()
        return Base64.getEncoder().encodeToString(digestInHex.toByteArray())
    }

    /**
     *
     */
    private fun ByteArray.toHex(): String {
        return joinToString { byte ->
            "%02x".format(byte)
        }
    }
}