package com.wadexhong.keepmealive

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import java.util.*

object Helper {

    private const val TAG = "KeepMeAlive"

    // Huawei(華為)
    private val BRAND_HUAWEI = "huawei"
    private val PACKAGE_HUAWEI = "com.huawei.systemmanager"
    private val CLASS_HUAWEI = "com.huawei.systemmanager.optimize.process.ProtectActivity"
    private val CLASS_HUAWEI_FALLBACK = "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"

    private val CASE_HUAWEI_FIRST = Pair(PACKAGE_HUAWEI, CLASS_HUAWEI)
    private val CASE_HUAWEI_SECOND = Pair(PACKAGE_HUAWEI, CLASS_HUAWEI_FALLBACK)
    private val CASES_HUAWEI = listOf(CASE_HUAWEI_FIRST, CASE_HUAWEI_SECOND)

    // Oppo
    private val BRAND_OPPO = "o"
    private val PACKAGE_OPPO = "com.coloros.safecenter"
    private val PACKAGE_OPPO_FALLBACK = "com.oppo.safe"
    private val CLASS_OPPO = "com.coloros.safecenter.permission.startup.StartupAppListActivity"
    private val CLASS_OPPO_FALLBACK_FIRST = "com.oppo.safe.permission.startup.StartupAppListActivity"
    private val CLASS_OPPO_FALLBACK_SECOND = "com.coloros.safecenter.startupapp.StartupAppListActivity"
    private val CLASS_OPPO_FALLBACK_THIRD = "com.coloros.privacypermissionsentry.PermissionTopActivity"

    private val CASE_OPPO_FIRST = Pair(PACKAGE_OPPO, CLASS_OPPO)
    private val CASE_OPPO_SECOND = Pair(PACKAGE_OPPO_FALLBACK, CLASS_OPPO_FALLBACK_FIRST)
    private val CASE_OPPO_THIRD = Pair(PACKAGE_OPPO, CLASS_OPPO_FALLBACK_SECOND)
    private val CASE_OPPO_FORTH = Pair(PACKAGE_OPPO, CLASS_OPPO_FALLBACK_THIRD)
    private val CASES_OPPO = listOf(CASE_OPPO_FIRST, CASE_OPPO_SECOND, CASE_OPPO_THIRD, CASE_OPPO_FORTH)

    // Vivo
    private val BRAND_VIVO = "vivo"
    private val PACKAGE_VIVO = "com.iqoo.secure"
    private val PACKAGE_VIVO_FALLBACK = "com.vivo.permissionmanager"
    private val CLASS_VIVO = "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity"
    private val CLASS_VIVO_FALLBACK_FIRST = "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"
    private val CLASS_VIVO_FALLBACK_SECOND = "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager"

    private val CASE_VIVO_FIRST = Pair(PACKAGE_VIVO, CLASS_VIVO)
    private val CASE_VIVO_SECOND = Pair(PACKAGE_VIVO_FALLBACK, CLASS_VIVO_FALLBACK_FIRST)
    private val CASE_VIVO_THIRD = Pair(PACKAGE_VIVO, CLASS_VIVO_FALLBACK_SECOND)
    private val CASES_VIVO = listOf(CASE_VIVO_FIRST, CASE_VIVO_SECOND, CASE_VIVO_THIRD)

    // Xiaomi(小米)
    private val BRAND_XIAOMI = "xiaomi"
    private val BRAND_XIAOMI_REDMI = "redmi"
    private val PACKAGE_XIAOMI = "com.miui.securitycenter"
    private val CLASS_XIAOMI = "com.miui.permcenter.autostart.AutoStartManagementActivity"

    private val CASE_XIAOMI_FIRST = Pair(PACKAGE_XIAOMI, CLASS_XIAOMI)
    private val CASES_XIAOMI = listOf(CASE_XIAOMI_FIRST)

    // Letv(樂視)
    private val BRAND_LETV = "letv"
    private val PACKAGE_LETV = "com.letv.android.letvsafe"
    private val CLASS_LETV = "com.letv.android.letvsafe.AutobootManageActivity"

    private val CASE_LETV_FIRST = Pair(PACKAGE_LETV, CLASS_LETV)
    private val CASES_LETV = listOf(CASE_LETV_FIRST)

    // Asus(華碩)
    private val BRAND_ASUS = "asus"
    private val PACKAGE_ASUS = "com.asus.mobilemanager"
    private val CLASS_ASUS = "com.asus.mobilemanager.powersaver.PowerSaverSettings"
    private val CLASS_ASUS_FALLBACK = "com.asus.mobilemanager.autostart.AutoStartActivity"

    private val CASE_ASUS_FIRST = Pair(PACKAGE_ASUS, CLASS_ASUS)
    private val CASE_ASUS_SECOND = Pair(PACKAGE_ASUS, CLASS_ASUS_FALLBACK)
    private val CASES_ASUS = listOf(CASE_ASUS_FIRST, CASE_ASUS_SECOND)

    // Honor(榮耀)
    private val BRAND_HONOR = "honor"
    private val PACKAGE_HONOR = "com.huawei.systemmanager"
    private val CLASS_HONOR = "com.huawei.systemmanager.optimize.process.ProtectActivity"

    private val CASE_HONOR_FIRST = Pair(PACKAGE_HONOR, CLASS_HONOR)
    private val CASES_HONOR = listOf(CASE_HONOR_FIRST)

    // Nokia
    private val BRAND_NOKIA = "nokia"
    private val PACKAGE_NOKIA = "com.evenwell.powersaving.g3"
    private val CLASS_NOKIA = "com.evenwell.powersaving.g3.exception.PowerSaverExceptionActivity"

    private val CASE_NOKIA_FIRST = Pair(PACKAGE_NOKIA, CLASS_NOKIA)
    private val CASES_NOKIA = listOf(CASE_NOKIA_FIRST)

    // Samsung(三星)
    private val BRAND_SAMSUNG = "samsung"
    private val PACKAGE_SAMSUNG = "com.samsung.android.lool"
    private val CLASS_SAMSUNG = "com.samsung.android.sm.ui.battery.BatteryActivity"

    private val CASE_SAMSUNG_FIRST = Pair(PACKAGE_SAMSUNG, CLASS_SAMSUNG)
    private val CASES_SAMSUNG = listOf(CASE_SAMSUNG_FIRST)

    // OnePlus(一加)
    private val BRAND_ONE_PLUS = "oneplus"
    private val PACKAGE_ONE_PLUS = "com.oneplus.security"
    private val CLASS_ONE_PLUS = "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity"

    private val CASE_ONE_PLUS_FIRST = Pair(PACKAGE_ONE_PLUS, CLASS_ONE_PLUS)
    private val CASES_ONE_PLUS = listOf(CASE_ONE_PLUS_FIRST)


    private val CASES_TO_CHECK_FOR_PERMISSION: List<Pair<String, String>> =
        listOf(
            CASES_ASUS,
            CASES_XIAOMI,
            CASES_LETV,
            CASES_HONOR,
            CASES_OPPO,
            CASES_VIVO,
            CASES_NOKIA,
            CASES_HUAWEI,
            CASES_SAMSUNG,
            CASES_ONE_PLUS
        ).flatten()

    fun launchPermissionSettingPage(context: Context): Boolean {
        return launch(context, getCasesByBrand())
    }

    fun isCustomPermissionSettingSupport(context: Context): Boolean {
        return isPackageExists(context, getCasesByBrand())
    }

    private fun launch(context: Context, cases: List<Pair<String, String>>): Boolean {
        return if (isPackageExists(context, cases)) {
            startIntentByRecursive(context, cases)
        } else {
            false
        }
    }

    private fun isPackageExists(context: Context, pairs: List<Pair<String, String>>): Boolean {
        val pm = context.packageManager
        for (pair in pairs) {
            val intent = Intent().setComponent(ComponentName(pair.first, pair.second))
            if (pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null)
                return true
        }
        return false
    }

    private fun getCasesByBrand(): List<Pair<String, String>> =
        when (Build.BRAND.toLowerCase(Locale.getDefault())) {
            BRAND_HUAWEI -> CASES_HUAWEI
            BRAND_XIAOMI, BRAND_XIAOMI_REDMI -> CASES_XIAOMI
            BRAND_OPPO -> CASES_OPPO
            BRAND_VIVO -> CASES_VIVO
            BRAND_HONOR -> CASES_HONOR
            BRAND_LETV -> CASES_LETV
            BRAND_NOKIA -> CASES_NOKIA
            BRAND_SAMSUNG -> CASES_SAMSUNG
            BRAND_ONE_PLUS -> CASES_ONE_PLUS
            BRAND_ASUS -> CASES_ASUS
            else -> emptyList()
        }

    private fun startIntentByRecursive(
        context: Context,
        cases: List<Pair<String, String>>
    ): Boolean {
        if (cases.isEmpty())
            return false

        val case = cases.first()

        try {
            val intent = Intent()
            intent.component = ComponentName(case.first, case.second)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            context.startActivity(intent)
            return true
        } catch (exception: Exception) {
            Log.d(TAG, "Intent failed for pkg: ${case.first}, clz: ${case.second}", exception)
            return startIntentByRecursive(context, cases.drop(1))
        }
    }
}