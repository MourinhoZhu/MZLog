# About MZLog
## 日志工具文件，最新版本1.0.0
1.0.0更新内容（2024-01-06）：
- 1.支持原生日志输出
- 2.支持debug模式日志输出
- 3.支持自定义Base Tag, 二级Tag，Tag文本字符串
- 4.支持文件行号输出
- 5.支持方法调用堆栈输出


# How to use

## Gradle 集成使用

```
 //TODO:
```

## 基础设置代码概要

```
参见示例代码，初始化代码可以放到application里面

    companion object {
        const val BASE_TAG = "MZLOG"
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLog()
        testLog()
    }

    private fun initLog() {
        MZLog.baseTagPrefix = BASE_TAG
        MZLog.isDebug = MZLog.isApkInDebug(this)
        MZLog.isShowClassNumber = true
        MZLog.classNumberMessageFormat = "[%s()+%d]->"
    }

    private fun testLog() {
        MZLog.v(TAG,"log v")
        MZLog.d(TAG,"log d")
        MZLog.i(TAG,"log i")
        MZLog.w(TAG,"log w")
        MZLog.e(TAG,"log e")
        MZLog.e(TAG,"log e2", Throwable("t"))
        MZLog.debug(TAG,"log debug")
        MZLog.printStackTrace(TAG)
    }
```



