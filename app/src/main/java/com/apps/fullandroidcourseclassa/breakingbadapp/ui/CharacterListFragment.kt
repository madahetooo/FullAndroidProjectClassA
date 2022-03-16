package com.apps.fullandroidcourseclassa.breakingbadapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.breakingbadapp.BreakingBadApplication
import com.apps.fullandroidcourseclassa.breakingbadapp.ui.utils.CharacterListAdapter

class CharacterListFragment : Fragment() {

    private val characterListViewModel:CharacterListViewModel by viewModels {
        CharacterListViewModelFactory((requireActivity().application as BreakingBadApplication).charachterRepository)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val rvCharacterList = requireActivity().findViewById<RecyclerView>(R.id.rvCharacterList)
        val adapter = CharacterListAdapter{bbCharacter ->
            //TODO
            if (bbCharacter.img !=null){
                findNavController().navigate(CharacterListFragmentDirections.showCharacterImageFragment(bbCharacter.img))
            }
        }

        rvCharacterList.adapter = adapter
        characterListViewModel.characters.observe(viewLifecycleOwner,{
            bbCharacter ->
            adapter.submitList(bbCharacter)
        })

        val refreshLayout = requireActivity().findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        refreshLayout.setOnRefreshListener {
            characterListViewModel.refreshDataFromRepository()
            Toast.makeText(requireContext(),"Data Refreshed",Toast.LENGTH_LONG).show()
            refreshLayout.isRefreshing = false
        }
    }
}