package id.buaja.dashboard.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.buaja.dashboard.R
import id.buaja.dashboard.databinding.FragmentPopularBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularFragment : Fragment() {
    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<PopularViewModel>()

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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}