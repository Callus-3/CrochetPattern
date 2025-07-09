package edu.vt.mobiledev.crochetmaker

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.content.res.Resources;
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.vt.mobiledev.crochetmaker.databinding.StitchtagBinding

class StitchListAdapter(private val stitches: List<String>): RecyclerView.Adapter<StitchHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StitchHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StitchtagBinding.inflate(inflater,parent,false)
        return StitchHolder(binding)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: StitchHolder, position: Int) {
        val stitch = stitches[position]
        holder.apply {
            binding.button.text = stitch
        //    binding.button.isEnabled = false
            binding.button.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#68BF9D"))
        }
    }

    override fun getItemCount() = stitches.size

}

class StitchHolder(val binding: StitchtagBinding): RecyclerView.ViewHolder(binding.root)