package com.devmasterteam.tasks.view.viewholder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.devmasterteam.tasks.databinding.CardModelBinding
import com.devmasterteam.tasks.service.listener.OnAlunoListener
import com.devmasterteam.tasks.service.model.Aluno

class AlunoViewHolder(private val bind: CardModelBinding, val listener: OnAlunoListener) :
    RecyclerView.ViewHolder(bind.root) {

    /**
     * Atribui valores aos elementos de interface do card model
     */
    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    fun bindData(aluno: Aluno) {
        bind.textDoName.text = " ${aluno.name}"
        bind.textDoSport.text = " ${aluno.sport}"
        bind.textDoDay.text = " ${aluno.datePayment}"

    }
 }