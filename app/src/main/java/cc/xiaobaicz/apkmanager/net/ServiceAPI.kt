package cc.xiaobaicz.apkmanager.net

import cc.xiaobaicz.apkmanager.BuildConfig
import cc.xiaobaicz.apkmanager.util.gson
import com.google.gson.reflect.TypeToken
import com.tencent.mmkv.MMKV
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 服务接口
 */
object ServiceAPI {

    //服务客户端
    val client = createClient()

    //创建客户端
    private fun createClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(createOkHttpClient())
            .build()
    }

    //创建请求客户端
    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .cookieJar(createCookieJar())
            .build()
    }

    //创建Cookie
    private fun createCookieJar(): CookieJar {
        return object : CookieJar {
            override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
                val key = getKey(url)
                MMKV.defaultMMKV()?.encode(key, gson.toJson(cookies))
            }

            override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
                val key = getKey(url)
                val json = MMKV.defaultMMKV()?.decodeString(key, "[]")
                return gson.fromJson(json, object : TypeToken<MutableList<Cookie>>(){}.type)
            }

            private fun getKey(url: HttpUrl) = url.url().toString()
        }
    }

}