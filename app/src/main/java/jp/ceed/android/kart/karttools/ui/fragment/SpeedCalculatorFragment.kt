package jp.ceed.android.kart.karttools.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jp.ceed.android.kart.karttools.R
import jp.ceed.android.kart.karttools.databinding.FragmentSpeedCalculatorBinding
import jp.ceed.android.kart.karttools.viewmodel.SpeedCalculatorFragmentViewModel

@AndroidEntryPoint
class SpeedCalculatorFragment: Fragment() {

    private lateinit var binding: FragmentSpeedCalculatorBinding

    private val viewModel: SpeedCalculatorFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_speed_calculator, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

}