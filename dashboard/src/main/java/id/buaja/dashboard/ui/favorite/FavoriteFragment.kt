package id.buaja.dashboard.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.buaja.dashboard.databinding.FragmentFavoriteBinding
import id.buaja.dashboard.ui.detail.DetailMovieActivity
import id.buaja.dashboard.utils.hideKeyboard
import id.buaja.domain.model.MovieFavorite
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<FavoriteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list: ArrayList<MovieFavorite> = arrayListOf()
        val adapter = FavoriteAdapter(list) {
            val intent = Intent(requireContext(), DetailMovieActivity::class.java)
            intent.putExtra("id", it.id)
            startActivity(intent)
        }
        viewModel.success.observe(viewLifecycleOwner, {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })

        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter

            etSearch.addTextChangedListener {
                adapter.filter.filter(it.toString())
            }

            etSearch.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard()
                }
                return@setOnEditorActionListener false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}