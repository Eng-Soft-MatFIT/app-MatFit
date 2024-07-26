package com.devmasterteam.tasks.view.ui.alunos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.databinding.FragmentAlunosBinding
import com.devmasterteam.tasks.service.constants.Constants
import com.devmasterteam.tasks.service.listener.OnAlunoListener
import com.devmasterteam.tasks.view.adapter.AlunoAdapter
import com.devmasterteam.tasks.view.ui.viewmodel.AlunoViewModel

class AlunosFragment : Fragment() {

    private lateinit var viewModel: AlunoViewModel
    private lateinit var binding: FragmentAlunosBinding
    private val adapter = AlunoAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {

        viewModel = ViewModelProvider(this)[AlunoViewModel::class.java]
        binding = FragmentAlunosBinding.inflate(inflater, container, false)

        // Layout
        binding.recyclerListAlunos.layoutManager = LinearLayoutManager(context)
        // Adapter
        binding.recyclerListAlunos.adapter = adapter

        val listener = object : OnAlunoListener{
            override fun onUpdate(cpf: String) {
             //   val intent = Intent(context, UpdateAlunoActivity::class.java)
              //  val aluno = viewModel.get(cpf)
//                intent.putExtra(Constants.Attributs.CPF, aluno.cpf)
//                intent.putExtra(Constants.Attributs.NAME, aluno.name)
//                intent.putExtra(Constants.Attributs.SPORT, aluno.sport)
//                intent.putExtra(Constants.Attributs.DAY, aluno.day)
//                startActivity(intent)
            }

            override fun onDelete(cpf: String) {
                AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.deleteAluno))
                    .setMessage(getString(R.string.textConfirmationDelete))
                    .setPositiveButton(getString(R.string.yes)) { dialog, which ->
                     //  viewModel.delete(cpf)
                      //  viewModel.getAll()
                    }
                    .setNegativeButton(getString(R.string.cancel)) { dialog, which -> null }
                    .create()
                    .show()
            }

            override fun OnPayment(cpf: String) {
             //  startActivity(Intent(context, Payment::class.java))
            }

        }
//      Responsável por passar o listener para o adapter
        adapter.attachListener(listener)
//
//        // Cria os observadores
//        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        //  viewModel.list( )
    }
}