package kotlin.dev.engine.compress

import dev.engine.compress.ICompressEngine
import dev.engine.compress.listener.CompressFilter
import dev.engine.compress.listener.OnCompressListener
import dev.engine.compress.listener.OnRenameListener
import dev.other.LubanUtils
import top.zibin.luban.CompressionPredicate
import java.io.File

/**
 * detail: Image Compress Config
 * @author Ttt
 */
class CompressConfig @JvmOverloads constructor(
    // 单位 KB 默认 100 kb 以下不压缩
    val ignoreSize: Int,
    // 是否保留透明通道
    val focusAlpha: Boolean = true,
    // 压缩图片存储路径
    val targetDir: String? = null
) : ICompressEngine.EngineConfig() {

    // 压缩失败、异常是否结束压缩
    var isFailFinish = false
        private set

    constructor(targetDir: String?) : this(100, true, targetDir)

    constructor(
        ignoreSize: Int,
        targetDir: String?
    ) : this(ignoreSize, true, targetDir)

    fun setFailFinish(failFinish: Boolean): CompressConfig {
        isFailFinish = failFinish
        return this
    }
}

/**
 * detail: Luban Image Compress Engine 实现
 * @author Ttt
 */
class LubanEngineImpl : ICompressEngine<CompressConfig> {

    override fun compress(
        data: Any?,
        config: CompressConfig?,
        compressListener: OnCompressListener?
    ): Boolean {
        return compress(data, config, null, null, compressListener)
    }

    override fun compress(
        data: Any?,
        config: CompressConfig?,
        filter: CompressFilter?,
        renameListener: OnRenameListener?,
        compressListener: OnCompressListener?
    ): Boolean {
        if (data == null || config == null || compressListener == null) return false
        val lists = ArrayList<Any>()
        lists.add(data)
        return compress(lists, config, filter, renameListener, compressListener)
    }

    override fun compress(
        lists: MutableList<Any?>?,
        config: CompressConfig?,
        compressListener: OnCompressListener?
    ): Boolean {
        return compress(lists, config, null, null, compressListener)
    }

    override fun compress(
        lists: MutableList<Any?>?,
        config: CompressConfig?,
        filter: CompressFilter?,
        renameListener: OnRenameListener?,
        compressListener: OnCompressListener?
    ): Boolean {
        if (lists == null || config == null || compressListener == null) return false
        var predicate: CompressionPredicate? = null
        if (filter != null) {
            predicate = CompressionPredicate { path: String? -> filter.apply(path) }
        }
        var rename: top.zibin.luban.OnRenameListener? = null
        if (renameListener != null) {
            rename = top.zibin.luban.OnRenameListener { filePath: String? ->
                renameListener.rename(filePath)
            }
        }
        return LubanUtils.compress(
            lists, LubanUtils.Config(
                config.ignoreSize, config.focusAlpha, config.targetDir
            ).setFailFinish(config.isFailFinish), predicate, rename,
            object : LubanUtils.OnCompressListener {
                override fun onStart(
                    index: Int,
                    count: Int
                ) {
                    compressListener?.onStart(index, count)
                }

                override fun onSuccess(
                    file: File,
                    index: Int,
                    count: Int
                ) {
                    compressListener?.onSuccess(file, index, count)
                }

                override fun onError(
                    error: Throwable,
                    index: Int,
                    count: Int
                ) {
                    compressListener?.onError(error, index, count)
                }

                override fun onComplete(
                    lists: List<File>,
                    maps: Map<Int, File>,
                    count: Int
                ) {
                    compressListener?.onComplete(lists, maps, count)
                }
            }
        )
    }
}