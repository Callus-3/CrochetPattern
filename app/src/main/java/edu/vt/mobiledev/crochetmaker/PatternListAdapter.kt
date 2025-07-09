package edu.vt.mobiledev.crochetmaker

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.vt.mobiledev.crochetmaker.databinding.PatternitemBinding
import java.util.UUID

class PatternListAdapter(private val patterns: List<Pattern>, private val onPatternClicked: (patternId: UUID) -> Unit): RecyclerView.Adapter<PatternHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatternHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PatternitemBinding.inflate(inflater,parent,false)
        return PatternHolder(binding)
    }

    override fun onBindViewHolder(holder: PatternHolder, position: Int) {
        val pattern = patterns[position]
        holder.bind(pattern, onPatternClicked)
        holder.apply {
            if (position % 2 == 0)
            {
                itemView.setBackgroundColor(Color.parseColor("#b8e6dc"))
            }
            binding.skillbutton.setBackgroundColor(Color.parseColor("#4ca896"))
            binding.catergorybutton2.setBackgroundColor(Color.parseColor("#4ca896"))
            binding.patternTitleList.text = pattern.title
            binding.skillbutton.text = pattern.skillLevel.toString()
            binding.catergorybutton2.text = pattern.category.toString()
        }
    }

    override fun getItemCount() = patterns.size
}

class PatternHolder(val binding: PatternitemBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(pattern:Pattern, onPatternClicked: (patternId:UUID) -> Unit) {
        binding.root.setOnClickListener{
            Log.d("runtime", pattern.id.toString())
            onPatternClicked(pattern.id)
        }
    }
}