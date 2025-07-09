package edu.vt.mobiledev.crochetmaker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.vt.mobiledev.crochetmaker.databinding.PatternbrowserBinding
import kotlinx.coroutines.launch
import java.util.UUID

class PatternListFragment: Fragment() {

    private var _binding: PatternbrowserBinding? = null
    private val patternRepository = PatternRepository.get()
    private val vm = PatternViewModel()
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
        _binding = PatternbrowserBinding.inflate(inflater,container,false)

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.add_pattern,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId) {
                    R.id.add_new_pattern -> {
                        menuItem.isVisible = false
                        newPattern()

                        true
                    }
                    else -> false
                }
            }
        })
        return binding.root
    }

    private fun newPattern() {
        viewLifecycleOwner.lifecycleScope.launch {
            val newPattern = Pattern(UUID.randomUUID(),"title","desc",Category.Clothing,"gage", Skill.Beginner)
            vm.addPattern(newPattern)
            findNavController().navigate(
                PatternListFragmentDirections.createPattern()
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.patterns.collect{patterns->
                    binding.patternRecycler.adapter =
                        PatternListAdapter(patterns){ patternId->
                            findNavController().navigate(
                                PatternListFragmentDirections.showPatternDetail(patternId)
                            )

                        }
                }
            }
        }
        binding.apply {
            patternRecycler.layoutManager = LinearLayoutManager(context)
        }
    }
}