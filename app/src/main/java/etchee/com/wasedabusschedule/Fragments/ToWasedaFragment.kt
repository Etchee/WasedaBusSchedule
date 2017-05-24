package etchee.com.wasedabusschedule.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.*
import etchee.com.wasedabusschedule.R

/**
 * Fragment to actually display the list of bus departures
 * Created by rikutoechigoya on 2017/05/24.
 */
class ToWasedaFragment: Fragment() {

    var recyclerView: RecyclerView? = null
    var adapter: ToWasedaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (inflater != null) {
            return inflater.inflate(R.layout.layout_fragment_waseda, container, false)
        } else throw InflateException("Inflation failed")
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view?.findViewById(R.id.recyclerView_toWaseda) as RecyclerView
        adapter = ToWasedaAdapter(context)
        (recyclerView as RecyclerView).adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }
}