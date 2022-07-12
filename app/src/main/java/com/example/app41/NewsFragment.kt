package com.example.app41

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.app41.databinding.FragmentHomeBinding
import com.example.app41.databinding.FragmentNewsBinding


class NewsFragment : Fragment() {

    private lateinit var _binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.btnSave.setOnClickListener {
            save()
        }
    }

    private fun save() {
        val text = _binding.editText.text.toString()
        val bundle =Bundle()
        bundle.putString("text", text)
        parentFragmentManager.setFragmentResult("rk_news", bundle)
        findNavController().navigateUp()
    }
}