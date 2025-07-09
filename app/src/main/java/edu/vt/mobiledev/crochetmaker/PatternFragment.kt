package edu.vt.mobiledev.crochetmaker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.vt.mobiledev.crochetmaker.databinding.FragmentPatternDetailBinding
import edu.vt.mobiledev.crochetmaker.databinding.PatternfragcontainerBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.UUID

class PatternFragment : Fragment() {

    private lateinit var binding: FragmentPatternDetailBinding
    private lateinit var containerBinding: PatternfragcontainerBinding
    private lateinit var pattern: Pattern
    private val args: PatternFragmentArgs by navArgs()
    private val vm: PatternDetailViewModel by viewModels {
        PatternDetailViewModelFactory(args.patternId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

//        pattern = Pattern(
//            id = UUID.randomUUID(),
//            title = "Aestas Top",
//            description = "Aestas means summer in latin, and this top is just that – a perfect summer top. Crocheted in\n" +
//                    "breathable worsted cotton with decorative openwork panels down the sides and enough coverage at\n" +
//                    "the front to wear out and about, this piece may just become a staple of your summer wardrobe!",
//            category = Category.Clothing,
//            gauge = "14 dc x 8 rows = 10×10 cm / 4×4 inches",
//            skillLevel = Skill.Beginner
//        )


    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentPatternDetailBinding.inflate(inflater,container,false)
        binding.stitchesUsedHolder.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.patternSteps.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
        binding.imageButton.setOnClickListener{fullFragment()}

        return binding.root
    }

    fun fullFragment()
    {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            //binding.stitchesUsedHolder.setPadding(0,0,150,0)
//            patternTitle.text = pattern.title
//            patternDescription.text = pattern.description
//            categoryText.text = pattern.category.toString()
//            skillText.text = pattern.skillLevel.toString()
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.pattern.collect(){ pattern->
                    pattern?.let { updateUI(it) }
                }
            }
        }
        //populateStitches()
        //populateSteps()
    }

    private fun updateUI(pattern: Pattern)
    {
        Log.d("title",pattern.title)
        binding.apply {
            binding.patternTitle.text = pattern.title
            binding.patternDescription.text = pattern.description
            binding.categoryText.text = pattern.category.toString()
            binding.skillText.text = pattern.skillLevel.toString()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.stitch_index_menu,menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigate_to_stitch_list -> {
                openStitchIndex()
                true

            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    fun openStitchIndex(){
        findNavController().navigate(R.id.open_stitch_index)
    }

    fun populateSteps(){
        var steps = listOf(" ch1 (does not count as a st), hdc in each ch around, working into the back bump of the ch’s,\n" +
                "close with slst in first hdc (102 (108, 114, 120, 126) sts)"," ch1 (does not count as a st), sc in first st, [ch5, sk 2 sts, sc in next st] repeat around, except\n" +
              "for the last repeat: instead of ch5: ch2 and dc in first sc to close the round. (34 (36, 38, 40, 42) ch-sp’s)")
        val adapter = PatternStepAdapter(steps)
        binding.patternSteps.adapter = adapter
    }

    fun populateStitches()
    {
        var stitches = listOf("slip stitch", "single crochet","double crochet","half double crochet")
        val adapter = StitchListAdapter(stitches)
        binding.stitchesUsedHolder.adapter = adapter
    }
}