package mytestproj.com.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import mytestproj.com.R
import mytestproj.com.adapter.ListAdapter
import mytestproj.com.model.Gender


class GenderBottomDialog : BottomSheetDialogFragment() {
    var mListRecyclerView: RecyclerView? = null
    var mAdapter: ListAdapter? = null
    var mButton: Button? = null
    lateinit var mContext: Context
    private lateinit var iClickListener : IClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        iClickListener = activity as IClickListener
    }

    private var mNicolasCageGenders = listOf(
        Gender("1_", "Male", false),
        Gender("1_2", "Female", false),
        Gender("1_3", "Other", false),
        Gender("1_4", "Gay", false),
        Gender("1_5", "Lasbian", false),
        Gender("1_6", "Duo", false),
        Gender("1_7", "Ghost", false),
        Gender("1_8", "Knowing", false)
    )

    fun newInstance(): GenderBottomDialog {
        return GenderBottomDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gender_bottom_dialog, container, false)

        mListRecyclerView = view.findViewById(R.id.recycler_view)
        mButton = view.findViewById(R.id.button_submit)
        mListRecyclerView?.layoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)

        mButton?.setOnClickListener {
            iClickListener.buttonClick(mAdapter?.getValue()!!)
        }

        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = ListAdapter(mContext, mNicolasCageGenders)
        mListRecyclerView?.adapter = mAdapter
    }

    interface IClickListener {
        fun buttonClick(genderList: List<Gender>)
    }


}
