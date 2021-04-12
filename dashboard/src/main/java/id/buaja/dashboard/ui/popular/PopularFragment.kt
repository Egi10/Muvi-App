package id.buaja.dashboard.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import id.buaja.dashboard.databinding.FragmentPopularBinding
import id.buaja.dashboard.ui.popular.adapter.PopularAdapter
import id.buaja.domain.model.Popular
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularFragment : Fragment() {
    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<PopularViewModel>()

    private var popularAdapter: PopularAdapter? = null
    private val listPopular: MutableList<Popular> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllFavorite()

        with(viewModel) {
            success.observe(viewLifecycleOwner, {
                listPopular.clear()
                listPopular.addAll(it)
                popularAdapter?.notifyDataSetChanged()
            })

            error.observe(viewLifecycleOwner, {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })

            loading.observe(viewLifecycleOwner, {
                binding.swipe.isRefreshing = it
            })
        }

        popularAdapter = PopularAdapter(listPopular) {

        }

        with(binding) {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerView.adapter = popularAdapter

            swipe.setOnRefreshListener {
                viewModel.getAllFavorite()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}