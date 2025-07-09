package edu.vt.mobiledev.crochetmaker

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.vt.mobiledev.crochetmaker.databinding.PatternStepBinding

class PatternStepAdapter (private val steps: List<String>): RecyclerView.Adapter<StepHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PatternStepBinding.inflate(inflater,parent,false)
        return StepHolder(binding)
    }

    override fun onBindViewHolder(holder: StepHolder, position: Int) {
        val step = steps[position]
        holder.apply {
            binding.textView3.text = step
            binding.checkBox2.setOnClickListener{
                if (!binding.checkBox2.isChecked)
                {
                    binding.textView3.paintFlags = (binding.textView3.getPaintFlags() and (Paint.STRIKE_THRU_TEXT_FLAG.inv()))
                }else{
                    binding.textView3.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }

            }
        }
    }

    override fun getItemCount() = steps.size

}

class StepHolder(val binding: PatternStepBinding): RecyclerView.ViewHolder(binding.root)