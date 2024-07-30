package com.devmasterteam.tasks.view.ui.alunos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.databinding.FragmentAlunosBinding
import com.devmasterteam.tasks.service.constants.Constants
import com.devmasterteam.tasks.service.listener.OnAlunoListener
import com.devmasterteam.tasks.service.model.Aluno
import com.devmasterteam.tasks.view.adapter.AlunoAdapter
import com.devmasterteam.tasks.view.ui.viewmodel.AlunoViewModel
import dev.jvitor.gerenciadordematriculas.view.UpdateAlunoActivity

class AlunosFragment : Fragment() {

    private lateinit var viewModel: AlunoViewModel
    private lateinit var binding: FragmentAlunosBinding
    private val adapter = AlunoAdapter()
    private lateinit var aluno: Aluno

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {

        viewModel = ViewModelProvider(this)[AlunoViewModel::class.java]
        binding = FragmentAlunosBinding.inflate(inflater, container, false)

        // Layout
        binding.recyclerListAlunos.layoutManager = LinearLayoutManager(context)
        // Adapter
        binding.recyclerListAlunos.adapter = adapter

        clickable()

        viewModel.listAllAlunos()

        val listener = object : OnAlunoListener {
            override fun onUpdate(cpf: String) {
                val intent = Intent(context, UpdateAlunoActivity::class.java)
                intent.putExtra(Constants.Attributs.CPF, aluno.cpf)
                intent.putExtra(Constants.Attributs.NAME, aluno.name)
                intent.putExtra(Constants.Attributs.SPORT, aluno.sport)
                startActivity(intent)
            }

            override fun onDelete(cpf: String) {
                AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.deleteAluno))
                    .setMessage(getString(R.string.textConfirmationDelete))
                    .setPositiveButton(getString(R.string.yes)) { dialog, which ->
                        viewModel.deleteAluno(cpf)
                        viewModel.listAllAlunos()
                    }
                    .setNegativeButton(getString(R.string.cancel)) { dialog, which -> null }
                    .create()
                    .show()
            }

            override fun OnPayment(cpf: String) {
                // startActivity(Intent(context, Payment::class.java))
            }
        }

//      Responsável por passar o listener para o adapter
        adapter.attachListener(listener)

        // Cria os observadores
        observe()

        return binding.root
    }

    // Observadores dos eventos da viewModel
    private fun observe() {
        viewModel.listAlunos.observe(viewLifecycleOwner) {
            adapter.updateAlunos(it)
        }
        viewModel.deleteAluno.observe(viewLifecycleOwner) {
            if (it.status())
                Toast.makeText(context, R.string.textConfirmationDelete, Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(context, it.message(), Toast.LENGTH_SHORT).show()
        }
        viewModel.aluno.observe(viewLifecycleOwner) {
            aluno = it
        }
        viewModel.alunoFailure.observe(viewLifecycleOwner){
            if(!it.status()) Toast.makeText(context, it.message(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.listAllAlunos()
    }

    private fun clickable() {
        binding.btnScreenAdd.setOnClickListener {
            startActivity(Intent(context, AddAlunoActivity::class.java))
        }
    }
}