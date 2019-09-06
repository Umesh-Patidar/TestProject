package mytestproj.com.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import mytestproj.com.R
import mytestproj.com.model.Gender

class ListAdapter(private val context: Context, private val genderList: List<Gender>) :
    RecyclerView.Adapter<ListAdapter.GenderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.GenderViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_gender, parent, false)
        return GenderViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListAdapter.GenderViewHolder, position: Int) {
        holder.bindItems(context, genderList[position])
    }

    override fun getItemCount(): Int {
        return genderList.size
    }

   class GenderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

       private val textViewName = itemView.findViewById(R.id.text_gender) as TextView
       private val viewContainer = itemView.findViewById(R.id.container) as RelativeLayout
       private val imageDot = itemView.findViewById(R.id.img_dot) as ImageView

       fun bindItems(context: Context, gender: Gender) {
           textViewName.text = gender.name

           if (gender.isSelected) {
               imageDot.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_yellow_circle))
               viewContainer.setBackgroundResource(R.drawable.round_corner_yellow_boundary)
           } else {
               imageDot.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_gray_circle))
               viewContainer.setBackgroundResource(R.drawable.round_corner_edit_text_grey)
           }

           viewContainer.setOnClickListener {
               if (!gender.isSelected) {
                   gender.isSelected = true
                   imageDot.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_yellow_circle))
                   viewContainer.setBackgroundResource(R.drawable.round_corner_yellow_boundary)
               } else {
                   gender.isSelected = false
                   imageDot.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_gray_circle))
                   viewContainer.setBackgroundResource(R.drawable.round_corner_edit_text_grey)
               }
           }
       }
    }

     fun getValue() : List<Gender> {
        return genderList
    }
}