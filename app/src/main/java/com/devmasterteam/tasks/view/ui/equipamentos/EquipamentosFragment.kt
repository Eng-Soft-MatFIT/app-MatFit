package com.devmasterteam.tasks.view.ui.equipamentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devmasterteam.tasks.databinding.FragmentEquipamentosBinding

class EquipamentosFragment : Fragment(){

    private lateinit var binding: FragmentEquipamentosBinding

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        binding =FragmentEquipamentosBinding.inflate(inflater, container, false)

        return binding.root
    }





}