package paperworks.studio.testtabandroid

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_dash -> {
                val dashFragment = DashFragment.newInstance("Dash", "Cool")
                openFragment(dashFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_lottie -> {
                val lottieFragment = LottieFragment.newInstance("Lottie", "Cool again")
                openFragment(lottieFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_canvas -> {
                val canvasFragment = CanvasFragment.newInstance("Canvas", "Cool third")
                openFragment(canvasFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_list -> {
                val listCanvasFragment = ListCanvasFragment.newInstance("Canvas", "Cool third")
                openFragment(listCanvasFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        openFragment(DashFragment.newInstance("Dash", "Cool"))
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
