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
        bind.textDoCpf.text = " ${aluno.cpf}"
        bind.textDoName.text = " ${aluno.nome}"
        bind.textDoSport.text = " ${aluno.esporte}"
        bind.textDoDay.text = " ${aluno.dataPagamento}"

        bind.iconEdit.setOnClickListener {
            listener.onUpdate(aluno.cpf)
        }

        bind.iconDelete.setOnClickListener{
            listener.onDelete(aluno.cpf)
        }

        bind.iconPayment.setOnClickListener{
            listener.OnPayment(aluno.cpf)
        }
    }
 }