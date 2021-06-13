package kg.tutorialapp.weather

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter:RecyclerView.Adapter<MyViewHolder>() {

    private val items = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        Log.i(MyAdapter.tag, "onCreateViewHolder()")
        return  MyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.i(MyAdapter.tag, "onBindViewHolder()")
        holder.bind(items[position])
    }

    override fun getItemCount() = items.count()

    fun setItems (newItems: List<Item>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    companion object{
        const val tag = "ADAPTER"
    }

}