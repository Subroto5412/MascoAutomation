package com.bd.mascogroup.automation.ui.home

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.library.baseAdapters.BR
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityHomeBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.hr_info.HRInfoActivity
import com.bd.mascogroup.automation.ui.production_management.ProductionManagementActivity
import com.bd.mascogroup.automation.ui.signup.SignupActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_hr_info.*
import kotlinx.android.synthetic.main.layout_home_body.*
import kotlinx.android.synthetic.main.layout_home_body.layout_human_resource_cl
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
            NavigationItemModel(R.drawable.masco,"Visit Our Website"),
            NavigationItemModel(R.drawable.facebook,"Visit Our Facebook Page"),
            NavigationItemModel(R.drawable.youtube,"Visit Our Youtube"),
            NavigationItemModel(R.drawable.linkedin,"Visit Our Linkedin Page"),
            NavigationItemModel(R.drawable.twitter,"Visit Our Twitter Page"),
            NavigationItemModel(R.drawable.instagram,"Visit Our Instagram Page")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityHomeBinding = viewDataBinding
        viewModel.navigator = this

        layout_production_management_cl.setOnClickListener {
            Toast.makeText(this,"under construction!",Toast.LENGTH_LONG).show()
        }

        layout_supply_chain_down_cl.setOnClickListener {
            Toast.makeText(this,"under construction!",Toast.LENGTH_LONG).show()
        }

        layout_merchandising_marketing_down_cl.setOnClickListener {
            Toast.makeText(this,"under construction!",Toast.LENGTH_LONG).show()
        }

        layout_asset_tracking_cl.setOnClickListener {
            Toast.makeText(this,"under construction!",Toast.LENGTH_LONG).show()
        }

        layout_automobile_management_cl.setOnClickListener {
            Toast.makeText(this,"under construction!",Toast.LENGTH_LONG).show()
        }

        layout_search_engine_cl.setOnClickListener {
            Toast.makeText(this,"under construction!",Toast.LENGTH_LONG).show()
        }

        layout_dispatch_slip_cl.setOnClickListener {
            Toast.makeText(this,"under construction!",Toast.LENGTH_LONG).show()
        }

        layout_idea_laboratory_cl.setOnClickListener {
            Toast.makeText(this,"under construction!",Toast.LENGTH_LONG).show()
        }
        layout_documentation_management_cl.setOnClickListener {
            Toast.makeText(this,"under construction!",Toast.LENGTH_LONG).show()
        }

        layout_human_resource_cl.setOnClickListener {
            val intent = HRInfoActivity.newIntent(this@HomeActivity)
            startActivity(intent)
        }

//        drawerLayout = findViewById(R.id.order_delivery_drawer_layout)
        setSupportActionBar(activity_main_toolbar)

        // Setup Recyclerview's Layout
        order_delivery_navigation_rv.layoutManager = LinearLayoutManager(this)
        order_delivery_navigation_rv.setHasFixedSize(true)
        updateAdapter()

        // Add Item Touch Listener
        /*order_delivery_navigation_rv.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                when (position) {
                    0 -> {
                        val builder = AlertDialog.Builder(this@HomeActivity)
                        builder.setTitle("Do You Want to Logout from App?")
                        //  builder.setMessage("Are you want to set the app background color to RED?")
                        builder.setPositiveButton("YES"){dialog, which ->
                           // viewModel.dataManager.deliveryManwiseId = ""
                            //viewModel.dataManager.deliveryAppCategory = ""
                           // val intent = Intent(this@OrderDeliverySuperviserActivity, LoginActivity::class.java)
                          //  startActivity(intent)
                          //  finish()
                        }
                        builder.setNegativeButton("No"){dialog,which ->
                        }
                        val dialog: AlertDialog = builder.create()
                        dialog.show()

                    }

                }

                Handler().postDelayed({
                    drawerLayout.closeDrawer(GravityCompat.START)
                }, 200)
            }
        }))*/

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
        order_delivery_navigation_rv.adapter = adapter
        adapter.notifyDataSetChanged()
    }


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}