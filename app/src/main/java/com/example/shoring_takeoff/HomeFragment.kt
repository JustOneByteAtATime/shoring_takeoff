package com.example.shoring_takeoff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {
    var picker: DatePicker? = null
    var btnGet: Button? = null
    var tvw: TextView? = null

    // Binding object instance corresponding to the fragment_start.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentHomeBinding? = null

    // 1. In StartFragment class, get a reference to the shared view model as a class variable. Use the
    // by activityViewModels() Kotlin property delegate from the fragment-ktx library.
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    // Setup click listeners..
    // 2. In StartFragment.kt, in onViewCreated() method, bind the new data variable to the fragment
    // instance. You can access the fragment instance inside the fragment using this keyword.
    // Remove the binding?.apply block and along with the code within. The completed method should
    // look like this.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.homeFragment = this

//        binding?.apply {
//            // Set up the button click listeners
//            orderOneCupcake.setOnClickListener { orderCupcake(1) }
//            orderSixCupcakes.setOnClickListener { orderCupcake(6) }
//            orderTwelveCupcakes.setOnClickListener { orderCupcake(12) }
//        }
    }

    /**
     * Start an order with the desired quantity of cupcakes and navigate to the next screen.
     */
    // Going back to the StartFragment class, you can now use the view model. At the beginning of
    // the orderCupcake() method, call the setQuantity()method in the shared view model to update
    // quantity, before navigating to the flavor fragment.

    // 5. In StartFragment class, inside orderCupcake() method, after setting the quantity, set the
    // default flavor as Vanilla if no flavor is set, before navigating to the flavor fragment.
    // Your complete method will look like this:

    fun orderCupcake(quantity: Int)
    {
        sharedViewModel.setQuantity(quantity)
        if (sharedViewModel.hasNoFlavorSet())
        {
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }
        findNavController().navigate(R.id.action_homeFragment2_to_northWallFragment)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}