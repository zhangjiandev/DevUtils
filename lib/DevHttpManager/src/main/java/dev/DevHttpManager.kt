package dev

import dev.http.BuildConfig
import dev.http.manager.*
import okhttp3.HttpUrl

/**
 * detail: OkHttp 管理库 ( Retrofit 多 BaseUrl 等 )
 * @author Ttt
 * <p></p>
 * GitHub
 * @see https://github.com/afkT/DevUtils
 * DevApp Api
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevApp/README.md
 * DevAssist Api
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevAssist/README.md
 * DevBase README
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevBase/README.md
 * DevBaseMVVM README
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevBaseMVVM/README.md
 * DevEngine README
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevEngine/README.md
 * DevHttpCapture Api
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevHttpCapture/README.md
 * DevHttpManager Api
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevHttpManager/README.md
 * DevJava Api
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevJava/README.md
 * DevWidget Api
 * @see https://github.com/afkT/DevUtils/blob/master/lib/DevWidget/README_API.md
 * DevEnvironment Api
 * @see https://github.com/afkT/DevUtils/blob/master/lib/Environment
 */
object DevHttpManager {

    // ============
    // = 工具类版本 =
    // ============

    /**
     * 获取 DevHttpManager 版本号
     * @return DevHttpManager versionCode
     */
    fun getDevHttpManagerVersionCode(): Int {
        return BuildConfig.DevHttpManager_VersionCode
    }

    /**
     * 获取 DevHttpManager 版本
     * @return DevHttpManager versionName
     */
    fun getDevHttpManagerVersion(): String {
        return BuildConfig.DevHttpManager_Version
    }

    /**
     * 获取 DevApp 版本号
     * @return DevApp versionCode
     */
    fun getDevAppVersionCode(): Int {
        return BuildConfig.DevApp_VersionCode
    }

    /**
     * 获取 DevApp 版本
     * @return DevApp versionName
     */
    fun getDevAppVersion(): String {
        return BuildConfig.DevApp_Version
    }

    // =============
    // = 对外公开方法 =
    // =============

    // ===================
    // = RetrofitManager =
    // ===================

    // =================
    // = OkHttpBuilder =
    // =================

    /**
     * 获取全局 OkHttp Builder 接口对象
     * @return OkHttpBuilder
     */
    fun getOkHttpBuilder(): OkHttpBuilder? {
        return RetrofitManager.getOkHttpBuilder()
    }

    /**
     * 设置全局 OkHttp Builder 接口对象
     * @param builder [OkHttpBuilder]
     */
    fun setOkHttpBuilder(builder: OkHttpBuilder?) {
        RetrofitManager.setOkHttpBuilder(builder)
    }

    /**
     * 移除全局 OkHttp Builder 接口对象
     */
    fun removeOkHttpBuilder() {
        RetrofitManager.removeOkHttpBuilder()
    }

    // ===========================
    // = OnRetrofitResetListener =
    // ===========================

    /**
     * 获取全局 Retrofit 重新构建监听事件
     * @return OnRetrofitResetListener
     */
    fun getRetrofitResetListener(): OnRetrofitResetListener? {
        return RetrofitManager.getRetrofitResetListener()
    }

    /**
     * 设置全局 Retrofit 重新构建监听事件
     * @param listener [OnRetrofitResetListener]
     */
    fun setRetrofitResetListener(listener: OnRetrofitResetListener?) {
        RetrofitManager.setRetrofitResetListener(listener)
    }

    /**
     * 移除全局 Retrofit 重新构建监听事件
     */
    fun removeRetrofitResetListener() {
        RetrofitManager.removeRetrofitResetListener()
    }

    // ===================
    // = RetrofitBuilder =
    // ===================

    /**
     * 获取 Retrofit Operation 操作对象
     * @param key Key
     * @return Retrofit Operation
     */
    fun getOperation(key: String): RetrofitOperation? {
        return RetrofitManager.getOperation(key)
    }

    /**
     * 通过 Key 判断是否存在 Retrofit Operation 操作对象
     * @param key Key
     * @return `true` yes, `false` no
     */
    fun containsOperation(key: String): Boolean {
        return RetrofitManager.containsOperation(key)
    }

    /**
     * 通过 Key 绑定存储 RetrofitBuilder 并返回 Operation 操作对象
     * @param key Key
     * @param builder [RetrofitBuilder]
     * @return Retrofit Operation
     */
    fun putRetrofitBuilder(
        key: String,
        builder: RetrofitBuilder
    ): RetrofitOperation {
        return RetrofitManager.putRetrofitBuilder(key, builder)
    }

    /**
     * 通过 Key 解绑移除 RetrofitBuilder 并返回 Operation 操作对象
     * @param key Key
     * @return Retrofit Operation
     */
    fun removeRetrofitBuilder(key: String): RetrofitOperation? {
        return RetrofitManager.removeRetrofitBuilder(key)
    }

    // =====================
    // = RetrofitOperation =
    // =====================

    /**
     * 重置处理 ( 重新构建 Retrofit )
     * @param key Key
     * @param httpUrl 构建使用指定 baseUrl
     * @return Retrofit Operation
     */
    fun reset(
        key: String,
        httpUrl: HttpUrl? = null
    ): RetrofitOperation? {
        return RetrofitManager.reset(key, httpUrl)
    }

    /**
     * 重置处理 ( 重新构建全部 Retrofit )
     * @param mapHttpUrl MutableMap<String?, HttpUrl?>
     */
    fun resetAll(mapHttpUrl: MutableMap<String?, HttpUrl?>? = null) {
        RetrofitManager.resetAll(mapHttpUrl)
    }
}