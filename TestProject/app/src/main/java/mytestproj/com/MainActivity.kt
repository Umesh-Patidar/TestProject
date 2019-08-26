package mytestproj.com

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import mytestproj.com.adapter.GenderAdapter
import mytestproj.com.fragment.GenderBottomDialog
import mytestproj.com.model.Gender


class MainActivity : AppCompatActivity(), GenderBottomDialog.IClickListener {


    var mListRecyclerView: RecyclerView? = null
    var mAdapter: GenderAdapter? = null
    var mButton: Button? = null
    val mGenderList = arrayListOf<Gender>()

    val mGenderBottomDialog = GenderBottomDialog().newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mListRecyclerView = findViewById(R.id.recycler_view)
        mButton = findViewById(R.id.button_submit)
        mListRecyclerView?.layoutManager = LinearLayoutManager(this,  LinearLayout.HORIZONTAL, false)


        editText.setOnClickListener {
            mGenderBottomDialog.show(supportFragmentManager, "add_photo_dialog_fragment")
        }

        text_edit.setOnClickListener {
            mGenderBottomDialog.show(supportFragmentManager, "add_photo_dialog_fragment")
        }
    }

    override fun buttonClick(genderList: List<Gender>) {
        if (genderList.isNotEmpty()) {
            editText.visibility = View.GONE
            text_edit.visibility = View.VISIBLE
            mListRecyclerView?.visibility = View.VISIBLE
            mGenderBottomDialog.dismiss()

            genderList.forEach {
                if (it.isSelected) {
                    mGenderList.add(it)
                }
            }

            mAdapter = GenderAdapter(this, mGenderList)
            mListRecyclerView?.adapter = mAdapter

        }
    }
}

