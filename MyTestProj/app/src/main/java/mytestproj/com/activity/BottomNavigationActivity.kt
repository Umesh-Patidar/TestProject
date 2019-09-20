package mytestproj.com.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_bottom_navigation2.*
import mytestproj.com.R
import mytestproj.com.utils.AnimationUtils

class BottomNavigationActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation2)
        bottom_navigation_view!!.setOnNavigationItemSelectedListener(this)
        view_dashboard.setOnClickListener {
            AnimationUtils.rotateView(image_user!!, 0, 800)
            Toast.makeText(this@BottomNavigationActivity,"Hii User",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.action_dashboard -> Toast.makeText(this@BottomNavigationActivity,"Hii DashBaord",Toast.LENGTH_SHORT).show()
            R.id.action_calender ->
                Toast.makeText(this@BottomNavigationActivity,"Hii Calender",Toast.LENGTH_SHORT).show()
            R.id.action_notification -> Toast.makeText(this@BottomNavigationActivity,"Hii Promotion",Toast.LENGTH_SHORT).show()
            R.id.action_profile ->  Toast.makeText(this@BottomNavigationActivity,"Hii Profile",Toast.LENGTH_SHORT).show()
        }
        return true
    }

}
