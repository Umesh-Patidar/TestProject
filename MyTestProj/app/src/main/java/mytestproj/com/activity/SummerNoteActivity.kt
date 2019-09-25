package mytestproj.com.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mytestproj.com.R
import `in`.nashapp.androidsummernote.Summernote
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_summer_note.*


class SummerNoteActivity : AppCompatActivity() {
    var textSummernote : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summer_note)
        val summernote = findViewById<Summernote>(R.id.summernote)
        summernote.setRequestCodeforFilepicker(5)

        button_summernote.setOnClickListener {
            textSummernote = summernote.text.toString()
            Toast.makeText(this@SummerNoteActivity, textSummernote, Toast.LENGTH_SHORT).show()
        }
    }
}
