package id.buaja.dashboard.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.buaja.dashboard.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBanner()
        viewModel.apply {
            banner.observe(viewLifecycleOwner, {
                it.map {
                    Log.d("Sukses", it.backdropPath.toString())
                }
            })

            popular.observe(viewLifecycleOwner, {
                it.map {
                    Log.d("Sukses Popular", it.backdropPath.toString())
                }
            })

            comingSoon.observe(viewLifecycleOwner, {
                it.map {
                    Log.d("Sukses Coming Soon", it.backdropPath.toString())
                }
            })
        }
    }
}