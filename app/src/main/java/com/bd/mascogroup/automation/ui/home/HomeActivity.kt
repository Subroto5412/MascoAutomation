package com.bd.mascogroup.automation.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityHomeBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.gpms.bwpd.BWPDActivity
import com.bd.mascogroup.automation.ui.gpms.hp_details.HPDetailsActivity
import com.bd.mascogroup.automation.ui.gpms.hpd.HPDActivity
import com.bd.mascogroup.automation.ui.gpms.lwp.LWPActivity
import com.bd.mascogroup.automation.ui.hr_info.HRInfoActivity
import com.bd.mascogroup.automation.ui.hr_info.attendance.AttendanceActivity
import com.bd.mascogroup.automation.ui.hr_info.leave.LeaveActivity
import com.bd.mascogroup.automation.ui.hr_info.tax.TaxActivity
import com.bd.mascogroup.automation.ui.hr_info_system.HrInfoSystemActivity
import com.bd.mascogroup.automation.ui.login.LoginActivity
import com.bd.mascogroup.automation.ui.production_management.ProductionManagementActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home_side_menu.*
import kotlinx.android.synthetic.main.activity_home_side_menu_footer.*
import kotlinx.android.synthetic.main.activity_hr_info.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_home_body.*
import kotlinx.android.synthetic.main.layout_top_search_header.*
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), IHomeNavigator {


    @Inject
    override lateinit var viewModel: HomeViewModel

    private var mActivityHomeBinding: ActivityHomeBinding? = null

    @Inject
    lateinit var mHomeViewModel: HomeViewModel



    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_home

//    lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: NavigationRVAdapter
    var items = arrayListOf(
            NavigationItemModel(R.drawable.masco, "Visit Our Website"),
            NavigationItemModel(R.drawable.facebook, "Visit Our Facebook Page"),
            NavigationItemModel(R.drawable.youtube, "Visit Our Youtube"),
            NavigationItemModel(R.drawable.linkedin, "Visit Our Linkedin Page"),
            NavigationItemModel(R.drawable.twitter, "Visit Our Twitter Page"),
            NavigationItemModel(R.drawable.instagram, "Visit Our Instagram Page")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityHomeBinding = viewDataBinding
        viewModel.navigator = this

        viewModel.getSearchName(this, search_name_actv)

        layout_production_management_cl.setOnClickListener {
            openProductManagement()
        }

        layout_supply_chain_down_cl.setOnClickListener {
            Toast.makeText(this, "under construction!", Toast.LENGTH_LONG).show()
        }

        layout_merchandising_marketing_down_cl.setOnClickListener {
            Toast.makeText(this, "under construction!", Toast.LENGTH_LONG).show()
        }

        layout_asset_tracking_cl.setOnClickListener {
            Toast.makeText(this, "under construction!", Toast.LENGTH_LONG).show()
        }

        layout_automobile_management_cl.setOnClickListener {
            Toast.makeText(this, "under construction!", Toast.LENGTH_LONG).show()
        }

        layout_search_engine_cl.setOnClickListener {
            Toast.makeText(this, "under construction!", Toast.LENGTH_LONG).show()
        }

        layout_dispatch_slip_cl.setOnClickListener {
            Toast.makeText(this, "under construction!", Toast.LENGTH_LONG).show()
        }

        layout_idea_laboratory_cl.setOnClickListener {
            Toast.makeText(this, "under construction!", Toast.LENGTH_LONG).show()
        }
        layout_documentation_management_cl.setOnClickListener {
            Toast.makeText(this, "under construction!", Toast.LENGTH_LONG).show()
        }

        layout_human_resource_cl.setOnClickListener {
            val intent = HRInfoActivity.newIntent(this@HomeActivity)
            startActivity(intent)
        }

        layout_approval_management_cl.setOnClickListener {

            val intent = HrInfoSystemActivity.newIntent(this@HomeActivity)
            startActivity(intent)
        }
        viewModel.sideMenuDisplayInfo(this, activity_side_menu_name_tv, activity_side_menu_empcode_tv, activity_side_menu_unit_tv, activity_side_menu_phone_tv, activity_side_menu_user_im, activity_home_side_menu_logout_layout)

        setSupportActionBar(activity_main_toolbar)

        // Setup Recyclerview's Layout
        navigation_item_rv.layoutManager = LinearLayoutManager(this)
        navigation_item_rv.setHasFixedSize(true)
        updateAdapter()

        // Add Item Touch Listener
        navigation_item_rv.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                when (position) {
                    0 -> {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mascoknit.com/"))
                        startActivity(browserIntent)
                    }
                    1 -> {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/mascofamily/"))
                        startActivity(browserIntent)
                    }
                    2 -> {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCc98wa0Vj7KcXYZPhRKxvgw"))
                        startActivity(browserIntent)
                    }
                    3 -> {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/masco-group-bangladesh"))
                        startActivity(browserIntent)
                    }
                    4 -> {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Masco_Group"))
                        startActivity(browserIntent)
                    }
                    5 -> {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/mascogroupbd"))
                        startActivity(browserIntent)
                    }
                }

                Handler().postDelayed({
                    order_delivery_drawer_layout.closeDrawer(GravityCompat.START)
                }, 200)
            }
        }))

        // Close the soft keyboard when you open or close the Drawer
        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(this, order_delivery_drawer_layout, activity_main_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            override fun onDrawerClosed(drawerView: View) {
                // Triggered once the drawer closes
                super.onDrawerClosed(drawerView)
                try {
                    val inputMethodManager =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }

            override fun onDrawerOpened(drawerView: View) {
                // Triggered once the drawer opens
                super.onDrawerOpened(drawerView)
                try {
                    val inputMethodManager =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }
        }
        order_delivery_drawer_layout.addDrawerListener(toggle)

        toggle.syncState()
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setHomeButtonEnabled(true);
        getSupportActionBar()!!.setHomeAsUpIndicator(R.drawable.side_menu)


    }

    private fun updateAdapter() {
        adapter = NavigationRVAdapter(items)
        navigation_item_rv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun openLoginScreen(){
        val intent = LoginActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun openAttendanceActivity(){
        val intent = AttendanceActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun openLeaveActivity(){
        val intent = LeaveActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun openTaxActivity(){
        val intent = TaxActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    fun openProductManagement(){
        val intent = ProductionManagementActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun openLWPActivity(){
        val intent = LWPActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun openHPDActivity(){
        val intent = HPDActivity.newIntent(this)
        startActivity(intent)
        finish()
    }
    override fun openHPDetailsActivity(){
        val intent = HPDetailsActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun openBWPDActivity(){
        val intent = BWPDActivity.newIntent(this)
        startActivity(intent)
        finish()
    }
}