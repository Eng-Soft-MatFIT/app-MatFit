package com.devmasterteam.tasks.view.ui.aluno

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.databinding.ActivityUpdateAlunoBinding
import com.devmasterteam.tasks.service.constants.ConstantsAluno
import com.devmasterteam.tasks.service.model.Aluno
import com.devmasterteam.tasks.viewmodel.AlunoViewModel
import java.time.LocalDate

class UpdateAlunoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateAlunoBinding
    private lateinit var viewModel: AlunoViewModel
    private lateinit var cpf: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateAlunoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Criando viewModel
        viewModel = ViewModelProvider(this)[AlunoViewModel::class.java]

        valuesFields()

        clickable()

    }

    // Eventos de clicks dos botões
    private fun clickable(){
        binding.buttonUpdate.setOnClickListener {
            doUpdate()
        }

        binding.iconBack.setOnClickListener {
            finish()
        }
    }

    // Responsável por atualizar informações do aluno
    private fun doUpdate() {
        val name = binding.editName.text.toString()
        val sport = binding.editSport.text.toString()
        val day = LocalDate.now()
        if (viewModel.validation(applicationContext.applicationContext, Aluno(cpf, name, sport, day))) finish()
        else return
    }


  /* Responsável por receber os valores vindo pela intent da MainActivity
  *  e alterar os valores dos campos para serem alterados
  */
    private fun valuesFields() {
        cpf = intent?.extras?.getString(ConstantsAluno.AttributosAluno.CPF) ?: throw IllegalStateException(getString(R.string.cpfNotFound))
        val name = intent?.extras?.getString(ConstantsAluno.AttributosAluno.NAME) ?: throw IllegalStateException(getString(R.string.nameNotFound))
        val sport = intent?.extras?.getString(ConstantsAluno.AttributosAluno.SPORT) ?: throw IllegalStateException(getString(R.string.sportNotFound))
        val day = intent?.extras?.getString(ConstantsAluno.AttributosAluno.DAY) ?: throw IllegalStateException(getString(R.string.dayNotFound))

        binding.editCpf.setText(cpf)
        binding.editName.setText(name)
        binding.editSport.setText(sport)
        binding.editDay.setText(day)
    }
}