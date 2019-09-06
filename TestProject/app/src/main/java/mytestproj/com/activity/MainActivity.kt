package mytestproj.com.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import mytestproj.com.BlogActivity
import mytestproj.com.R
import mytestproj.com.adapter.GenderAdapter
import mytestproj.com.fragment.GenderBottomDialog
import mytestproj.com.model.Gender
import mytestproj.com.utils.Dialogs


class MainActivity : AppCompatActivity(), GenderBottomDialog.IClickListener {

    var mListRecyclerView: RecyclerView? = null
    var mAdapter: GenderAdapter? = null
    var mButton: Button? = null
    val mGenderList = arrayListOf<Gender>()

    val mGenderBottomDialog = GenderBottomDialog().newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         //Dialogs.progressDialog(this@MainActivity).show()

        mListRecyclerView = findViewById(R.id.recycler_view)
        mButton = findViewById(R.id.button_submit)
        mListRecyclerView?.layoutManager = LinearLayoutManager(this,  LinearLayout.HORIZONTAL, false)

        editText.setOnClickListener {
            mGenderBottomDialog.show(supportFragmentManager, "add_photo_dialog_fragment")
        }

        text_edit.setOnClickListener {
            mGenderBottomDialog.show(supportFragmentManager, "add_photo_dialog_fragment")
        }

        button_next.setOnClickListener {
          startActivity(Intent(this@MainActivity, BlogActivity::class.java))
        }

        dialogPopUp()
        dataListRecycler()
    }

    override fun buttonClick(genderList: List<Gender>) {

        if (genderList.isNotEmpty()) {
            editText.visibility = View.GONE
            text_edit.visibility = View.VISIBLE
            mListRecyclerView?.visibility = View.VISIBLE
            mGenderBottomDialog.dismiss()

            mGenderList.clear()

            genderList.forEach {
                if (it.isSelected) {
                    mGenderList.add(it)
                }
            }

            mAdapter = GenderAdapter(this, mGenderList)
            mListRecyclerView?.adapter = mAdapter

        }
    }

    private  fun dialogPopUp(){

        button_dialog.setOnClickListener {
            Dialogs.showSuccessDialog(this, object : Dialogs.IDialogCallback {
                override fun onConfirmed() {}
                override fun onDenied() {}
            })
        }
        }

    private fun dataListRecycler(){
        button_recycler.setOnClickListener {

            recycler_list.setHasFixedSize(true)
            recycler_list.setItemViewCacheSize(20)

            recycler_list.setOnTouchListener(object: View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    Dialogs.dismissEvent()
                    return false
                }
            })

            recycler_list.layoutManager = LinearLayoutManager(this)
            mAdapter = GenderAdapter(this, mGenderList)
            mListRecyclerView?.adapter = mAdapter
            recycler_list.adapter = mAdapter

        }
        }
    }




