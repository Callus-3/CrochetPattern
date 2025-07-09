package edu.vt.mobiledev.crochetmaker

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.vt.mobiledev.crochetmaker.databinding.StitchIndexBinding
import edu.vt.mobiledev.crochetmaker.databinding.StitchindexitemsBinding
import edu.vt.mobiledev.crochetmaker.databinding.StitchtagBinding

class StitchIndexAdapter (private val stitches: List<Stitch>): RecyclerView.Adapter<StitchIndexHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StitchIndexHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StitchindexitemsBinding.inflate(inflater,parent,false)
        return StitchIndexHolder(binding)
    }

    override fun getItemCount() = stitches.size


    override fun onBindViewHolder(holder: StitchIndexHolder, position: Int) {
        val abb = stitches[position].abbreviation
        val full = stitches[position].title
        Log.d("stitches", abb)
        holder.apply {
            binding.stitchabb.text = abb
            binding.stitchfull.text = full
        }
    }
}
class StitchIndexHolder(val binding: StitchindexitemsBinding): RecyclerView.ViewHolder(binding.root)