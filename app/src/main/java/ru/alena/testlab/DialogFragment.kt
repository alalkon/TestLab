package ru.alena.testlab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import ru.alena.testlab.databinding.FragmentDialogBinding

class DialogFragment : DialogFragment() {
    private lateinit var binding: FragmentDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_dialog,
            container, false
        )
        return binding.root
    }
}