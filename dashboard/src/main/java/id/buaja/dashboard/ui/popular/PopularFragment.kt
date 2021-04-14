package id.buaja.dashboard.ui.popular

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import id.buaja.dashboard.databinding.FragmentPopularBinding
import id.buaja.dashboard.ui.detail.DetailMovieActivity
import id.buaja.dashboard.ui.popular.adapter.PopularAdapter
import id.buaja.dashboard.utils.hideKeyboard
import id.buaja.domain.model.Popular
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularFragment : Fragment() {
    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<PopularViewModel>()

    private var popularAdapter: PopularAdapter? = null
    private val listPopular: ArrayList<Popular> = arrayListOf()

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
            val intent = Intent(requireContext(), DetailMovieActivity::class.java)
            intent.putExtra("id", it.idMovie.toString())
            startActivity(intent)
        }

        with(binding) {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerView.adapter = popularAdapter

            swipe.setOnRefreshListener {
                viewModel.getAllFavorite()
            }
            
            etSearch.addTextChangedListener {
                popularAdapter?.filter?.filter(it.toString())
            }

            etSearch.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard()
                }
                return@setOnEditorActionListener false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}