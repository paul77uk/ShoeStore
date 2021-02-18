package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeLabel
import com.udacity.shoestore.viewModels.ShoeListViewModel

class ShoeDetailFragment : Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    private val shoeLabel: ShoeLabel = ShoeLabel(
        "Shoe Name:",
        "Company:",
        "Shoe Size:",
        "Description:",
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)

        binding.shoeLabel = shoeLabel

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }

        binding.saveButton.setOnClickListener {
            binding.apply {
                if (shoesizeEdittext.text.isNotEmpty()) {
                    val shoe = Shoe(
                        shoenameEdittext.text.toString(),
                        shoesizeEdittext.text.toString().toDouble(),
                        companyEdittext.text.toString(),
                        descriptionEdittext.text.toString(),
                    )

                    viewModel.addShoe(shoe)

                    findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
                }
            }

        }

        return binding.root
    }


}
