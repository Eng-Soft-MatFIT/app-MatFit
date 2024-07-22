package com.devmasterteam.tasks.view.ui.alunos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devmasterteam.tasks.databinding.FragmentAlunosBinding
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.view.ui.viewmodel.AlunoViewModel

class AlunosFragment : Fragment() {

    private lateinit var viewModel: AlunoViewModel
    private lateinit var binding: FragmentAlunosBinding

//    private val adapter = TaskAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {

        viewModel = ViewModelProvider(this)[AlunoViewModel::class.java]
        binding = FragmentAlunosBinding.inflate(inflater, container, false)

//
//        binding.recyclerAllTasks.layoutManager = LinearLayoutManager(context)
//        binding.recyclerAllTasks.adapter = adapter
//
//        taskFilter = requireArguments().getInt(TaskConstants.BUNDLE.TASKFILTER, 0)
//
//        val listener = object : TaskListener {
//            override fun onListClick(id: Int) {
        // val intent = Intent(context, TaskFormActivity::class.java)

//                val bundle = Bundle()
//                bundle.putInt(TaskConstants.BUNDLE.TASKID, id);
//                intent.putExtras(bundle)
//                startActivity(intent)

//
//        // Responsável por passar o listener para o adapter
//        adapter.attachListener(listener)
//
//        // Cria os observadores
//        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
      //  viewModel.list(taskFilter)
    }
//
//    // Observadoers da view model
//    private fun observe() {
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.tasks.collect { value ->
//                    adapter.updateTasks(value)
//                }
//            }
//        }
//
//        viewModel.delete.observe(viewLifecycleOwner) {
//            if (!it.status())
//                Toast.makeText(context, it.message(), Toast.LENGTH_SHORT).show()
//        }
//
//        viewModel.status.observe(viewLifecycleOwner) {
//            if (!it.status())
//                Toast.makeText(context, it.message(), Toast.LENGTH_SHORT).show()
//        }
//    }
}