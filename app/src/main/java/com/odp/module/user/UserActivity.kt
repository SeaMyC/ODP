package com.odp.module.user

import com.odp.R
import com.odp.base.BaseActivity
import com.odp.databinding.ActivityUserBinding

/**
 * @author  ChenHh
 * @time   2019/3/25 16:42
 * @des  用户个人信息页面
 **/
class UserActivity : BaseActivity<ActivityUserBinding>() {
    override fun getLayout(): Int {
        return R.layout.activity_user
    }
}