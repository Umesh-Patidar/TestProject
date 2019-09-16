package mytestproj.com

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.activity_blog.*
import com.google.android.material.appbar.AppBarLayout



class BlogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)


        main_appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {

            internal var isVisible = true
            internal var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    text_heading.visibility = View.VISIBLE
                    isVisible = true
                } else if (isVisible) {
                    text_heading.visibility = View.GONE
                    isVisible = false
                }
            }
        })

        img_back.setOnClickListener {
            Blurry.with(this@BlogActivity).radius(25).sampling(2).onto(container)
        }


    }
}
