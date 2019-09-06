package mytestproj.com.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mytestproj.com.R
import mytestproj.com.model.Gender

class GenderAdapter(private val context: Context, private val genderList: List<Gender>) :
    RecyclerView.Adapter<GenderAdapter.GenderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenderAdapter.GenderViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_gender_list, parent, false)
        return GenderViewHolder(v)
    }

    override fun onBindViewHolder(holder: GenderAdapter.GenderViewHolder, position: Int) {
        holder.bindItems(context, genderList[position])
    }

    override fun getItemCount(): Int {
        return genderList.size
    }

   class GenderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

       private val textViewName = itemView.findViewById(R.id.text_gender) as TextView

       fun bindItems(context: Context, gender: Gender) {
           if (gender.isSelected) {
               textViewName.text = gender.name
           }
       }
    }
}