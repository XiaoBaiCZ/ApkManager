package cc.xiaobaicz.apkmanager.util

import java.io.InputStream
import java.io.OutputStream
import java.io.Writer
import java.nio.CharBuffer

/**
 * 从输入流写到输出流
 */
fun InputStream.writeTo(out: OutputStream) {
    val buff = ByteArray(1024)
    do {
        val len = read(buff)
        out.write(buff, 0, len)
    } while (len > 0)
    out.flush()
}

/**
 * 从输入流读到输出流
 */
fun OutputStream.readFrom(input: InputStream) {
    val buff = ByteArray(1024)
    do {
        val len = input.read(buff)
        write(buff, 0, len)
    } while (len > 0)
    flush()
}

fun Readable.writeTo(out: Writer) {
    val buff = CharBuffer.allocate(1024)
    do {
        val len = read(buff)
        out.write(buff.array(), 0, len)
    } while (len > 0)
    out.flush()
}

fun Writer.readFrom(input: Readable) {
    val buff = CharBuffer.allocate(1024)
    do {
        val len = input.read(buff)
        write(buff.array(), 0, len)
    } while (len > 0)
    flush()
}