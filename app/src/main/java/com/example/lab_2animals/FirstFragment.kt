package com.example.lab_2animals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab_2animals.databinding.FragmentFirstBinding

class FirstFragment : Fragment(), AnimalAdapter.Listener {

    private var _binding : FragmentFirstBinding? = null
    private val binding get() = _binding!!

    var animals = ArrayList<Animal>()
    lateinit var adapter: AnimalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var chameleon = Animal("Chameleon", "Master of disguise", getString(R.string.urlChameleon), getString(R.string.DescChameleon))
        var owl = Animal("Owl", "Night vampire", getString(R.string.urlOwl), getString(R.string.DescOwl))
        var octopus = Animal ("Octopus", "Marine predator", getString(R.string.urlOctopus), getString(R.string.DescOctopus))

        animals.add(chameleon)
        animals.add(owl)
        animals.add(octopus)
        adapter = AnimalAdapter(this ,animals, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        binding.rV.layoutManager = layoutManager
        binding.rV.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(itemView: Int) {
        val bundle = bundleOf(SecondFragment.numberInArray to itemView,
            SecondFragment.name to animals.get(itemView).name,
            SecondFragment.desc to animals.get(itemView).shortDescription,
            SecondFragment.url to animals.get(itemView).urlPhoto,
            SecondFragment.allDesc to animals.get(itemView).allDescription
        )
        findNavController().navigate(R.id.action_fragmentFirst_to_fragmentSecond, bundle)
    }


}

