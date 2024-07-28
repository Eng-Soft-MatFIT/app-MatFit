package com.devmasterteam.tasks.view.ui.alunos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.databinding.ActivityAddAlunoBinding
import com.devmasterteam.tasks.service.model.Aluno
import com.devmasterteam.tasks.view.ui.viewmodel.AlunoViewModel

class AddAlunoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddAlunoBinding
    private lateinit var viewModel: AlunoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddAlunoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Criando viewModel
        viewModel = ViewModelProvider(this)[AlunoViewModel::class.java]

        clickable()

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
        val day = binding.editDay.text.toString()
        if (viewModel.validation(applicationContext, Aluno(cpf, name, sport, day))) finish()
        else return
    }
}