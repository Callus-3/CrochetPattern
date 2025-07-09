package edu.vt.mobiledev.crochetmaker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import edu.vt.mobiledev.crochetmaker.databinding.StitchIndexBinding
import kotlinx.coroutines.launch

class StitchListFragment :Fragment() {

    private var _binding: StitchIndexBinding? = null
    private val stitchRepository = StitchRepository.get()
    private val vm = StitchViewModel()
    private val binding
        get() = checkNotNull(_binding)
        {
            "binding is null"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StitchIndexBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.stitches.collect{ stitches ->
                    binding.stitchlist.adapter =
                        StitchIndexAdapter(stitches)
                }
            }
        }

        binding.apply {
            //var stitches = listOf(Stitch("sc","single crochet"),Stitch("dc","double crochet")
            stitchlist.layoutManager = LinearLayoutManager(context)

            }
        }
    }
