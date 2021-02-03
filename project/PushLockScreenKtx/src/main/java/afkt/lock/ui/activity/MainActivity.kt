package afkt.lock.ui.activity

import afkt.lock.R
import afkt.lock.base.BaseActivity
import afkt.lock.databinding.ActivityMainBinding
import afkt.lock.receiver.DeviceReceiver
import dev.utils.app.DevicePolicyUtils
import dev.utils.app.toast.ToastTintUtils

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun baseLayoutId(): Int = R.layout.activity_main

    override fun initListener() {
        super.initListener()

        DevicePolicyUtils.getInstance()
            .setComponentName(DeviceReceiver::class.java)

        // 激活组件 ( 申请权限 )
        binding.vidAmActiveBtn.setOnClickListener {
            if (DevicePolicyUtils.getInstance().isAdminActive()) {
                ToastTintUtils.success("已激活组件了")
                return@setOnClickListener
            }
            DevicePolicyUtils.getInstance().activeAdmin("需开启权限允许对设备进行操作!")
        }

        // 移除组件
        binding.vidAmRemoveActiveBtn.setOnClickListener {
            DevicePolicyUtils.getInstance().removeActiveAdmin()
        }
    }
}