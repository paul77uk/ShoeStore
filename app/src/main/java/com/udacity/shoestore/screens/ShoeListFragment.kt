package com.udacity.shoestore.screens

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.viewModels.ShoeListViewModel

class ShoeListFragment : Fragment() {

    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate view and obtain an instance of the binding class
        // Inflate the layout for this fragment
        val binding: ShoeListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.shoe_list_fragment, container, false
        )

        viewModel.shoeList.observe(viewLifecycleOwner, { it ->
            it.forEach {

                val imageView = ImageView(this.context)
                val companyTextView = TextView(this.context)
                val nameDescriptionAndSizeTextView = TextView(this.context)

                imageView.setImageResource(R.drawable.shoe_icon)
                imageView.setPadding(16)
                binding.linearLayout.addView(imageView)

                val company = it.company
                companyTextView.text = company
                companyTextView.textSize = 20F
                companyTextView.setTextColor(Color.GRAY)
                companyTextView.setPadding(64, 16, 0, 0)
                binding.linearLayout.addView(companyTextView)

                val nameDescriptionAndSize = "${it.name} ${it.description} ${it.size}"
                nameDescriptionAndSizeTextView.text = nameDescriptionAndSize
                nameDescriptionAndSizeTextView.textSize = 14F
                nameDescriptionAndSizeTextView.setPadding(64, 0, 0, 0)
                binding.linearLayout.addView(nameDescriptionAndSizeTextView)

            }
        })

        setHasOptionsMenu(true)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}