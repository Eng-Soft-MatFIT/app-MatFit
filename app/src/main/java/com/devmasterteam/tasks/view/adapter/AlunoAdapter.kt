package com.devmasterteam.tasks.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devmasterteam.tasks.databinding.CardModelBinding
import com.devmasterteam.tasks.service.listener.OnAlunoListener
import com.devmasterteam.tasks.service.model.Aluno

import com.devmasterteam.tasks.view.viewholder.AlunoViewHolder

class AlunoAdapter : RecyclerView.Adapter<AlunoViewHolder>() {

    private var listAlunos: List<Aluno> = arrayListOf()
    private lateinit var listener: OnAlunoListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlunoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = CardModelBinding.inflate(inflater, parent, false)
        return AlunoViewHolder(itemBinding, listener)
    }

    override fun onBindViewHolder(holder: AlunoViewHolder, position: Int) {
        holder.bindData(listAlunos[position])
    }

    override fun getItemCount(): Int {
        return listAlunos.count()
    }

    fun attachListener(taskListener: OnAlunoListener) {
        listener = taskListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAlunos(list: List<Aluno>){
        listAlunos = list
        notifyDataSetChanged()
    }
 }