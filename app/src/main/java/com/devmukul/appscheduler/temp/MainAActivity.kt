package com.devmukul.appscheduler.temp

import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.util.Collections

class MainAActivity : AppCompatActivity(),
    ActivityDynamics, OnItemLongClickListener, OnItemClickListener {
    /* access modifiers changed from: private */
    var activity: Activity? = null
    private var canSettingsManageOverlayPermissionIntentBeHandled = false
    private var lTemplates: ListView? = null

    /* access modifiers changed from: private */
    var mAdapter: ListTemplatesAdapter? = null

    /* access modifiers changed from: private */
    var mBillingClient: BillingClient? = null

    /* access modifiers changed from: private */
    var mIsBillingServiceConnected = false

    /* access modifiers changed from: private */
    var mListTemplates: List<Template>? = null

    /* access modifiers changed from: private */
    var mTemplateDao: TemplateDAO? = null
    private var notificationReceiver: BroadcastReceiver? = null
    var res: Resources? = null
    private var tEmptyListTemplates: TextView? = null
    private var tTopBar: Toolbar? = null
    private var updatedTemplate: Long = -1
    override fun acknowledgePurchase(
        str: String,
        acknowledgePurchaseResponseListener: AcknowledgePurchaseResponseListener
    ) {
    }

    override fun consumePurchase(str: String) {}
    override fun handlePurchase(purchase: Purchase) {}
    override fun initiatePurchaseFlow(str: String) {}
    fun onPurchasesUpdated(billingResult: BillingResult?, list: List<Purchase?>?) {}

    /* access modifiers changed from: protected */
    override fun onCreate(bundle: Bundle?) {
        LicenseClientV3.onActivityCreate(this)
        super.onCreate(bundle)
        notificationReceiver = Receiver()
        val intentFilter = IntentFilter(Constants.BROADCAST_ACTION_SERVICE_STATUS_CHANGED)
        intentFilter.addAction(Constants.BROADCAST_ACTION_SERVICE_RUNNING)
        registerReceiver(notificationReceiver, intentFilter)
        setContentView(C0491R.layout.activity_main as Int)
        val toolbar = findViewById<View>(C0491R.C0342id.tTopBar) as Toolbar
        tTopBar = toolbar
        setSupportActionBar(toolbar)
        activity = this
        res = getResources()
        initViews()
        mTemplateDao = TemplateDAO(this)
        getIntent()
        val sb = StringBuilder("package:")
        sb.append(getPackageName())
        val z = Intent(
            alXttc.ywXocpLBx,
            Uri.parse(sb.toString())
        ).resolveActivity(getPackageManager()) != null
        canSettingsManageOverlayPermissionIntentBeHandled = z
        if (!z || Globals.canDrawOverlays(activity)) {
            if (!Globals.loadBooleanPreferences(
                    Constants.FIRST_ACCESS_MADE_XML_KEY,
                    Constants.GENERAL_XML_FILENAME,
                    activity,
                    false
                )
            ) {
                Globals.startGenericActivity(
                    activity as Context?,
                    null as Bundle?,
                    268435456,
                    TroubleshootingActivity::class.java as Class<*>
                )
                Globals.savePreferences(
                    Constants.FIRST_ACCESS_MADE_XML_KEY,
                    true,
                    Constants.GENERAL_XML_FILENAME,
                    activity as Context?
                )
            }
            createList()
            initBillingClient()
        }
    }

    /* access modifiers changed from: protected */
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val extras: Bundle = intent.getExtras()
        if (extras != null) {
            val j: Long = extras.getLong(EXTRA_NEW_TEMPLATE, -1)
            updatedTemplate = j
            if (j != -1L) {
                createList()
            }
        }
    }

    override fun initBillingClient() {
        mBillingClient =
            BillingClient.newBuilder(activity).enablePendingPurchases().setListener(this).build()
        startBillingConnection {
            Log.d(MainActivity.TAG, "Setup successful. Querying inventory.")
            this@MainActivity.getBillingProducts()
        }
    }

    override fun startBillingConnection(runnable: Runnable) {
        mBillingClient.startConnection(object : BillingClientStateListener() {
            fun onBillingSetupFinished(billingResult: BillingResult) {
                Log.d(MainActivity.TAG, rbfGwJ.jgSjsR + billingResult.getResponseCode())
                if (billingResult.getResponseCode() === 0) {
                    this@MainActivity.mIsBillingServiceConnected = true
                    val unused: Boolean = this@MainActivity.mIsBillingServiceConnected
                    val runnable: Runnable? = runnable
                    runnable?.run()
                }
            }

            fun onBillingServiceDisconnected() {
                this@MainActivity.mIsBillingServiceConnected = false
                val unused: Boolean = this@MainActivity.mIsBillingServiceConnected
            }
        })
    }


    fun createList() {
        mListTemplates = mTemplateDao.getAllTemplates()
        val listTemplatesAdapter = ListTemplatesAdapter(this, mListTemplates, mTemplateDao)
        mAdapter = listTemplatesAdapter
        lTemplates!!.adapter = listTemplatesAdapter
        refreshList()
    }

    private inner class Receiver private constructor() : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val invoke: Any =
                VMRunner.invoke("1WN2Fjj58BBwsRvt", arrayOf<Any>(this, context, intent))
        }
    }

    /* access modifiers changed from: protected */
    override fun onDestroy() {
        super.onDestroy()
        releaseResources()
    }

    private fun initViews() {
        lTemplates = findViewById<View>(C0491R.C0342id.lTemplates) as ListView?
        tEmptyListTemplates = findViewById<View>(C0491R.C0342id.tEmptyListTemplates) as TextView?
        lTemplates!!.setOnItemClickListener(this)
        lTemplates!!.setOnItemLongClickListener(this)
    }

    /* access modifiers changed from: protected */
    override fun onResume() {
        super.onResume()
        if (dataChanged) {
            createList()
            dataChanged = false
        }
        val listTemplatesAdapter: ListTemplatesAdapter? = mAdapter
        if (listTemplatesAdapter != null) {
            listTemplatesAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        getMenuInflater().inflate(C0491R.C0344menu.menu_main, menu)
        menu.findItem(C0491R.C0342id.goPremium)
            .setTitle(if (Globals.isPremiumUser(this)) C0491R.string.premium_user else C0491R.string.go_premium)
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        var list: List<Template?>
        val bundle = Bundle()
        val itemId = menuItem.itemId
        if (itemId == 16908332) {
            finish()
        } else if (itemId == C0491R.C0342id.privacyPolicy) {
            if (Globals.isConnected(activity)) {
                Globals.startExternalActivity(
                    Constants.PRIVACY_LINK,
                    "android.intent.action.VIEW",
                    activity
                )
            } else {
                Globals.showToastMessage(
                    Globals.getStringFromResources(
                        C0491R.string.not_online,
                        activity
                    ), activity
                )
            }
        } else if (itemId == C0491R.C0342id.termsAndConditions) {
            if (Globals.isConnected(activity)) {
                Globals.startExternalActivity(
                    Constants.TERMS_AND_CONDITIONS_LINK,
                    "android.intent.action.VIEW",
                    activity
                )
            } else {
                Globals.showToastMessage(
                    Globals.getStringFromResources(
                        C0491R.string.not_online,
                        activity
                    ), activity
                )
            }
        } else if (itemId == C0491R.C0342id.help) {
            bundle.putString("HELP_TITLE", res!!.getString(C0491R.string.app_name))
            bundle.putString("HELP_DETAIL", res!!.getString(C0491R.string.app_help_info))
            bundle.putString("HELP_TROUBLESHOOTING_LINK", Constants.TROUBLESHOOTING_LINK)
            bundle.putString("HELP_TUTORIALS_PLAYLIST_LINK", alXttc.NKiwMP)
            Globals.startGenericActivity(
                activity as Context?,
                bundle,
                268435456,
                HelpActivity::class.java as Class<*>
            )
        } else if (itemId == C0491R.C0342id.overlaySettings) {
            if (canSettingsManageOverlayPermissionIntentBeHandled) {
                startActivity(
                    Intent(
                        "android.settings.action.MANAGE_OVERLAY_PERMISSION",
                        Uri.parse("package:" + getPackageName())
                    )
                )
            }
        } else if (itemId == C0491R.C0342id.troubleshooting) {
            Globals.startGenericActivity(
                activity as Context?,
                null as Bundle?,
                268435456,
                TroubleshootingActivity::class.java as Class<*>
            )
        } else if (itemId == C0491R.C0342id.goPremium) {
            startPremiumActivity(
                res!!.getString(C0491R.string.go_premium_be_happy),
                res!!.getString(C0491R.string.go_premium)
            )
        } else if (itemId == C0491R.C0342id.refresh) {
            createList()
        } else if (itemId == C0491R.C0342id.deleteAllTemplates) {
            if (Globals.isValidValue(mListTemplates as List<*>?)) {
                showDeleteAllDialogConfirmation()
            }
        } else if (itemId == C0491R.C0342id.restartAllTemplates) {
            if (Globals.isValidValue(mListTemplates as List<*>?)) {
                mTemplateDao.cancelAlarms(activity)
                mTemplateDao.setAlarms(activity)
                Toast.makeText(this, C0491R.string.reminders_restarted_successfully, 0).show()
            }
        } else if (itemId == C0491R.C0342id.addTemplate) {
            if (Globals.isPremiumUser(activity) || mListTemplates.also {
                    list = it!!
                } == null || list.size < 3) {
                val list2: List<Template>? = mListTemplates
                bundle.putInt(EXTRA_LIST_SIZE, list2?.size ?: 0)
                Globals.startGenericActivity(
                    activity as Context?,
                    bundle,
                    67108864,
                    AddTemplateActivity::class.java as Class<*>
                )
            } else {
                startPremiumActivity(
                    res!!.getString(C0491R.string.unlimited_services),
                    res!!.getString(C0491R.string.unlimited_services_detail)
                )
            }
        } else if (itemId == C0491R.C0342id.rate) {
            if (Globals.isConnected(activity)) {
                Globals.startExternalActivity(
                    Constants.PLAY_STORE_APP_LINK,
                    "android.intent.action.VIEW",
                    activity
                )
            } else {
                Globals.showToastMessage(
                    Globals.getStringFromResources(
                        C0491R.string.not_online,
                        activity
                    ), activity
                )
            }
        } else if (itemId == C0491R.C0342id.share) {
            Globals.startExternalActivity(Constants.WEB_APP_LINK, activity)
        } else if (itemId == C0491R.C0342id.moreApps) {
            if (Globals.isConnected(activity)) {
                Globals.startExternalActivity(
                    Constants.DEVELOPER_CONSOLE_LINK,
                    "android.intent.action.VIEW",
                    activity
                )
            } else {
                Globals.showToastMessage(
                    Globals.getStringFromResources(
                        C0491R.string.not_online,
                        activity
                    ), activity
                )
            }
        } else if (itemId == C0491R.C0342id.emailSupport) {
            if (Globals.isConnected(activity)) {
                val activity2: Activity? = activity
                Globals.startEmailActivity(
                    activity2,
                    arrayOf<String>(Constants.SUPPORT_EMAIL),
                    Globals.getSupportEmailSubject(activity2),
                    CfWQH.vecUJlVCEsJF
                )
            } else {
                Globals.showToastMessage(
                    Globals.getStringFromResources(
                        C0491R.string.not_online,
                        activity
                    ), activity
                )
            }
        }
        return false
    }

    fun startPremiumActivity(str: String?, str2: String?) {
        val bundle = Bundle()
        bundle.putString("PREMIUM_TITLE", str)
        bundle.putString("PREMIUM_MESSAGE", str2)
        Globals.startGenericActivity(
            activity as Context?,
            bundle,
            268435456,
            PremiumActivity::class.java as Class<*>
        )
    }

    fun refreshList() {
        val list: List<Template>? = mListTemplates
        var i = 0
        val z = list == null || list.isEmpty()
        tEmptyListTemplates.setVisibility(if (z) 0 else 8)
        val listView = lTemplates
        if (z) {
            i = 8
        }
        listView!!.visibility = i
    }

    override fun onItemClick(adapterView: AdapterView<*>?, view: View, i: Int, j: Long) {
        val item: Template = mAdapter.getItem(i)
        val bundle = Bundle()
        bundle.putSerializable(EXTRA_TEMPLATE, item)
        val list: List<Template>? = mListTemplates
        bundle.putInt(EXTRA_LIST_SIZE, list?.size ?: 0)
        Globals.startGenericActivity(
            activity as Context?,
            bundle,
            268435456,
            AddTemplateActivity::class.java as Class<*>
        )
    }

    override fun onItemLongClick(
        adapterView: AdapterView<*>?,
        view: View,
        i: Int,
        j: Long
    ): Boolean {
        showDeleteDialogConfirmation(mAdapter.getItem(i))
        return true
    }

    private fun showDeleteDialogConfirmation(template: Template) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(res!!.getString(C0491R.string.delete_template) as CharSequence)
        builder.setPositiveButton(17039379, object : DialogInterface.OnClickListener {
            override fun onClick(dialogInterface: DialogInterface, i: Int) {
                if (this@MainActivity.mTemplateDao != null) {
                    this@MainActivity.mTemplateDao.deleteTemplate(template)
                    this@MainActivity.mListTemplates.remove(template)
                    this@MainActivity.refreshList()
                    this@MainActivity.mAdapter.setItems(this@MainActivity.mListTemplates)
                    this@MainActivity.mAdapter.notifyDataSetChanged()
                }
                dialogInterface.dismiss()
                Toast.makeText(this@MainActivity, C0491R.string.template_deleted_successfully, 0)
                    .show()
            }
        } as DialogInterface.OnClickListener)
        builder.setNeutralButton(17039369, object : DialogInterface.OnClickListener {
            override fun onClick(dialogInterface: DialogInterface, i: Int) {
                dialogInterface.dismiss()
            }
        } as DialogInterface.OnClickListener)
        builder.create().show()
    }

    private fun showDeleteAllDialogConfirmation() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(res!!.getString(C0491R.string.delete_all) as CharSequence)
        builder.setMessage(res!!.getString(C0491R.string.delete_all_templates_confirmation) as CharSequence)
        builder.setPositiveButton(17039379, object : DialogInterface.OnClickListener {
            override fun onClick(dialogInterface: DialogInterface, i: Int) {
                this@MainActivity.mTemplateDao.deleteAllTemplates()
                this@MainActivity.mListTemplates.clear()
                this@MainActivity.refreshList()
                this@MainActivity.mAdapter.setItems(this@MainActivity.mListTemplates)
                this@MainActivity.mAdapter.notifyDataSetChanged()
                Toast.makeText(this@MainActivity, C0491R.string.templates_deleted_successfully, 0)
                    .show()
                dialogInterface.dismiss()
            }
        } as DialogInterface.OnClickListener)
        builder.setNeutralButton(17039369, object : DialogInterface.OnClickListener {
            override fun onClick(dialogInterface: DialogInterface, i: Int) {
                dialogInterface.dismiss()
            }
        } as DialogInterface.OnClickListener)
        builder.create().show()
    }

    override fun releaseResources() {
        try {
            unregisterReceiver(notificationReceiver)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mTemplateDao.close()
        endBillingConnection()
    }

    /* access modifiers changed from: protected */
    override fun onStart() {
        super.onStart()
        sendToSystemAlertWindowSettings()
    }

    private fun sendToSystemAlertWindowSettings() {
        try {
            if (canSettingsManageOverlayPermissionIntentBeHandled && !Globals.canDrawOverlays(
                    activity
                )
            ) {
                val bundle = Bundle()
                bundle.putString(
                    "WELCOME_CONTEXT",
                    SystemAlertWindowPermissionActivity.WELCOME_CONTEXT
                )
                Globals.startGenericActivity(
                    activity as Context?,
                    bundle,
                    268435456,
                    SystemAlertWindowPermissionActivity::class.java as Class<*>
                )
                finish()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        const val EXTRA_LIST_SIZE = "extra_list_size"
        const val EXTRA_NEW_TEMPLATE = "extra_key_new_template"
        const val EXTRA_TEMPLATE = "template"
        const val TAG = "MainActivity"
        var dataChanged = false
    }
}