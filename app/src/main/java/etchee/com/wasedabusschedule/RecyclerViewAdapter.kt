package etchee.com.wasedabusschedule

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


/**
 * Adapter for the RecyclerView
 * Created by rikutoechigoya on 2017/05/23.
 */
class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.Viewholder>() {


    override fun onBindViewHolder(viewHolder: Viewholder?, position: Int) {

    }

    override fun getItemCount(): Int {

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Viewholder? {

    }

class Viewholder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
//        val textView = itemView!!.findViewById(R.id.recyclerView) as TextView     みたいなノリ
    }
}