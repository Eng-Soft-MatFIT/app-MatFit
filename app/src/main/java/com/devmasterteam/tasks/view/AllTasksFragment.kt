package com.devmasterteam.tasks.view

import androidx.fragment.app.Fragment

class AllTasksFragment : Fragment() {
//
//    private lateinit var viewModel: TaskListViewModel
//    private var _binding: FragmentAllTasksBinding? = null
//    private val binding get() = _binding!!
//
//    private val adapter = TaskAdapter()
//    private var taskFilter = TaskConstants.FILTER.ALL
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
//        viewModel = ViewModelProvider(this)[TaskListViewModel::class.java]
//        _binding = FragmentAllTasksBinding.inflate(inflater, container, false)
//
//        binding.recyclerAllTasks.layoutManager = LinearLayoutManager(context)
//        binding.recyclerAllTasks.adapter = adapter
//
//        taskFilter = requireArguments().getInt(TaskConstants.BUNDLE.TASKFILTER, 0)
//
//        val listener = object : TaskListener {
//            override fun onListClick(id: Int) {
////              //  val intent = Intent(context, TaskFormActivity::class.java)
////                val bundle = Bundle()
////                bundle.putInt(TaskConstants.BUNDLE.TASKID, id);
////                intent.putExtras(bundle)
////                startActivity(intent)
//            }
//
//            // Responsável por deletar tasks
//            override fun onDeleteClick(id: Int) {
//                viewModel.delete(id)
//            }
//
//            // Responsável por marcar como completa
//            override fun onCompleteClick(id: Int) {
//                viewModel.status(id, true)
//            }
//
//            // Responsável por marcar como incompleta
//            override fun onUndoClick(id: Int) {
//                viewModel.status(id, false)
//            }
//        }
//
//        // Responsável por passar o listener para o adapter
//        adapter.attachListener(listener)
//
//        // Cria os observadores
//        observe()
//
//        return binding.root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    override fun onResume() {
//        super.onResume()
//        viewModel.list(taskFilter)
//    }
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