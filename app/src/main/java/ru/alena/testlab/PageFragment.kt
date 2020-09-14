package ru.alena.testlab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.alena.testlab.databinding.FragmentPageBinding

class PageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPageBinding>(
            inflater,
            R.layout.fragment_page, container, false
        )
        binding.greetingButton.setOnClickListener {view: View ->
            var dialog = DialogFragment()
            dialog.show(childFragmentManager, "dialogFragment")
        }
        return binding.root
    }
}