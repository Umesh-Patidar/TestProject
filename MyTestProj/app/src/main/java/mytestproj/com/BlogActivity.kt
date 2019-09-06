package mytestproj.com

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.activity_blog.*

class BlogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)

        img_back.setOnClickListener {
            Blurry.with(this@BlogActivity).radius(25).sampling(2).onto(container)

        }
    }
}
