package id.buaja.dashboard.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import id.buaja.dashboard.databinding.ActivityDetailMovieBinding
import id.buaja.dashboard.utils.setTransparentStatusBar
import id.buaja.domain.model.Cast
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private val viewModel by viewModel<DetailMovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTransparentStatusBar()

        viewModel.getDetail(intent.getStringExtra("id").toString())

        val listCast: MutableList<Cast> = mutableListOf()
        val adapter = CastAdapter(listCast)

        with(viewModel) {
            success.observe(this@DetailMovieActivity, {
                with(binding) {
                    tvTitle.text = it.title
                    tvDuration.text = it.duration
                    tvGenre.text = it.genre
                    tvDescription.text = it.overview
                    ivImage.load(it.image)

                    listCast.clear()
                    listCast.addAll(it.cast)
                    adapter.notifyDataSetChanged()
                }
            })

            error.observe(this@DetailMovieActivity, {
                Toast.makeText(this@DetailMovieActivity, it, Toast.LENGTH_SHORT).show()
            })
        }

        with(binding) {
            btnBack.setOnClickListener {
                finish()
            }

            rvCast.layoutManager =
                LinearLayoutManager(this@DetailMovieActivity, LinearLayoutManager.HORIZONTAL, false)
            rvCast.adapter = adapter
        }
    }
}