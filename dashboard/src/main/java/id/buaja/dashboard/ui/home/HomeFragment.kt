package id.buaja.dashboard.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import id.buaja.dashboard.databinding.FragmentHomeBinding
import id.buaja.dashboard.ui.detail.DetailMovieActivity
import id.buaja.dashboard.ui.home.adapter.BannerAdapter
import id.buaja.dashboard.ui.home.adapter.ComingSoonAdapter
import id.buaja.dashboard.ui.home.adapter.PopularHomeAdapter
import id.buaja.dashboard.utils.PeekingLinearLayoutManager
import id.buaja.dashboard.utils.gone
import id.buaja.dashboard.utils.visible
import id.buaja.domain.model.Banner
import id.buaja.domain.model.ComingSoon
import id.buaja.domain.model.Popular
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<HomeViewModel>()

    private var listBanner: MutableList<Banner> = mutableListOf()
    private var bannerAdapter: BannerAdapter? = null

    private var listPopular: MutableList<Popular> = mutableListOf()
    private var popularHomeAdapter: PopularHomeAdapter? = null

    private var listComingSoon: MutableList<ComingSoon> = mutableListOf()
    private var comingSoonAdapter: ComingSoonAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBanner()
        viewModel.apply {
            loading.observe(viewLifecycleOwner, {
                when (it) {
                    true -> {
                        binding.progress.visible()
                        binding.scrollView.gone()
                    }

                    false -> {
                        binding.progress.gone()
                        binding.scrollView.visible()
                    }
                }
            })

            banner.observe(viewLifecycleOwner, {
                listBanner.clear()
                listBanner.addAll(it)
                bannerAdapter?.notifyDataSetChanged()
            })

            popular.observe(viewLifecycleOwner, {
                listPopular.clear()
                listPopular.addAll(it)
                popularHomeAdapter?.notifyDataSetChanged()
            })

            comingSoon.observe(viewLifecycleOwner, {
                listComingSoon.clear()
                listComingSoon.addAll(it)
                comingSoonAdapter?.notifyDataSetChanged()
            })
        }

        bannerAdapter = BannerAdapter(listBanner)

        popularHomeAdapter = PopularHomeAdapter(listPopular) {
            toDetails(it.idMovie.toString())
        }

        comingSoonAdapter = ComingSoonAdapter(listComingSoon) {
            toDetails(it.idMovie)
        }

        with(binding) {
            vpBanner.adapter = bannerAdapter
            vpBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            rvPopular.layoutManager =
                PeekingLinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvPopular.adapter = popularHomeAdapter

            rvComingSoon.layoutManager =
                PeekingLinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvComingSoon.adapter = comingSoonAdapter
        }
    }

    private fun toDetails(idMovie: String) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java)
        intent.putExtra("id", idMovie)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}