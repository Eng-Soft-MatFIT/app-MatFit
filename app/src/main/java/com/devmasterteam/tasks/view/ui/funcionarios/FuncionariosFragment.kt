package com.devmasterteam.tasks.view.ui.funcionarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devmasterteam.tasks.databinding.FragmentFuncionariosBinding

class FuncionariosFragment : Fragment() {

    private lateinit var binding: FragmentFuncionariosBinding

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?, ): View {

        binding = FragmentFuncionariosBinding.inflate(inflater, container, false)


        return binding.root
    }


}