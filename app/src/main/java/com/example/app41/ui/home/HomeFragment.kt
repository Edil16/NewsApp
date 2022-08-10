package com.example.app41.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.app41.App
import com.example.app41.R
import com.example.app41.databinding.FragmentHomeBinding
import com.example.app41.models.News

class HomeFragment : Fragment() {



    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NewsAdapter()

        adapter.onLongCLick = {
            val news = adapter.getItem(it)
            Toast.makeText(requireContext(),news.createdAt.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchview()
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.newsFragment)
        }
        parentFragmentManager.setFragmentResultListener("rk_news" , viewLifecycleOwner) { requestKey,bunbdle ->
            val news = bunbdle.getSerializable("news") as News
            adapter.addItem(news)
            Log.wtf("Home","news: $news")
            Log.wtf("Home", "text: ${news.title}")
        }

        binding.recyclerView.adapter = adapter

    }

    private fun searchview() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,

        androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val list = newText.let { it?.let { it1 -> App.database.newsDao().search(it1) } }
                adapter.setList(list as ArrayList<News>)
                return false
            }
        })
    }


}