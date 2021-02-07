package cc.xiaobaicz.apkmanager.util

import java.nio.charset.Charset
import java.security.MessageDigest

/**
 * md5
 */
fun ByteArray.md5(): String {
    val md5 = MessageDigest.getInstance("MD5")
    val digest = md5.digest(this)
    val result = StringBuilder()

    digest.forEach {
        result.append(String.format("%02x", it))
    }

    return result.toString()
}

/**
 * md5
 */
fun String.md5(charset: Charset = Charsets.UTF_8): String {
    return this.toByteArray(charset).md5()
}

/**
 * sha1
 */
fun ByteArray.sha1(): String {
    val sha1 = MessageDigest.getInstance("SHA-1")
    val digest = sha1.digest(this)
    val result = StringBuilder()

    digest.forEach {
        result.append(String.format("%02x", it))
    }

    return result.toString()
}

/**
 * sha1
 */
fun String.sha1(charset: Charset = Charsets.UTF_8): String {
    return this.toByteArray(charset).sha1()
}

/**
 * sha224
 */
fun ByteArray.sha224(): String {
    val sha1 = MessageDigest.getInstance("SHA-224")
    val digest = sha1.digest(this)
    val result = StringBuilder()

    digest.forEach {
        result.append(String.format("%02x", it))
    }

    return result.toString()
}

/**
 * sha224
 */
fun String.sha224(charset: Charset = Charsets.UTF_8): String {
    return this.toByteArray(charset).sha224()
}

/**
 * sha256
 */
fun ByteArray.sha256(): String {
    val sha1 = MessageDigest.getInstance("SHA-256")
    val digest = sha1.digest(this)
    val result = StringBuilder()

    digest.forEach {
        result.append(String.format("%02x", it))
    }

    return result.toString()
}

/**
 * sha256
 */
fun String.sha256(charset: Charset = Charsets.UTF_8): String {
    return this.toByteArray(charset).sha256()
}

/**
 * sha384
 */
fun ByteArray.sha384(): String {
    val sha1 = MessageDigest.getInstance("SHA-384")
    val digest = sha1.digest(this)
    val result = StringBuilder()

    digest.forEach {
        result.append(String.format("%02x", it))
    }

    return result.toString()
}

/**
 * sha384
 */
fun String.sha384(charset: Charset = Charsets.UTF_8): String {
    return this.toByteArray(charset).sha384()
}

/**
 * sha512
 */
fun ByteArray.sha512(): String {
    val sha1 = MessageDigest.getInstance("SHA-512")
    val digest = sha1.digest(this)
    val result = StringBuilder()

    digest.forEach {
        result.append(String.format("%02x", it))
    }

    return result.toString()
}

/**
 * sha512
 */
fun String.sha512(charset: Charset = Charsets.UTF_8): String {
    return this.toByteArray(charset).sha512()
}