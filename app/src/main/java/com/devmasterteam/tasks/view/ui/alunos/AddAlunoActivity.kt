package com.devmasterteam.tasks.view.ui.alunos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.databinding.ActivityAddAlunoBinding
import com.devmasterteam.tasks.service.model.Aluno
import com.devmasterteam.tasks.view.ui.viewmodel.AlunoViewModel
import java.time.LocalDate

class AddAlunoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddAlunoBinding
    private lateinit var viewModel: AlunoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAlunoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Criando viewModel
        viewModel = ViewModelProvider(this)[AlunoViewModel::class.java]

        clickable()

        observer()

    }

    // Eventos de click dos botões
    private fun clickable(){
        binding.buttonAdd.setOnClickListener {
            addAluno()
        }

        binding.iconBack.setOnClickListener {
            finish()
        }
    }

    // Responsável por adicionar novos alunos
    private fun addAluno() {
        val cpf = binding.editCpf.text.toString()
        val name = binding.editName.text.toString()
        val sport = binding.editSport.text.toString()
        if (viewModel.saveAluno(applicationContext, Aluno(cpf, name, sport,"${LocalDate.now().plusMonths(1)}")))
            finish()
        else return
    }

    private fun observer(){
        viewModel.saveAluno.observe(this){
            if (it.status())
                Toast.makeText(applicationContext, R.string.textSucessRegister, Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
        }
    }
}