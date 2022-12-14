package com.example.shoring_takeoff.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_POUND_BEAM = 0.70
private const val PRICE_PER_LF_STRAND = 0.63
// Using 4x12 lagging so ~2.60 per board foot yields $10.4/sqft
private const val PRICE_LAGGING_SQFT = 2.60 * 4

class OrderViewModel : ViewModel() {

    // Weight of beam in this order
    private val _beamWeight = MutableLiveData<Int>()
    val beamWeight: LiveData<Int> = _beamWeight

    // Length of strand in this order
    private val _strandLength = MutableLiveData<Int>()
    val strandLength: LiveData<Int> = _strandLength

    // Square footage of lagging this order
    private val _laggingSqft = MutableLiveData<Int>()
    val laggingSqft: LiveData<Int> = _laggingSqft

    // Price of the order so far
    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        // Format the price into the local currency and return this as LiveData<String>
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        // Set initial values for the order
        resetOrder()
    }

// set the beam weight to be ordered
    fun setBeamWeight(beamOrderWeight: Int) {
        _beamWeight.value = beamOrderWeight
        updatePrice()
    }

    /**
     * Set the flavor of cupcakes for this order. Only 1 flavor can be selected for the whole order.
     *
     * @param desiredFlavor is the cupcake flavor as a string
     */
    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    /**
     * Set the pickup date for this order.
     *
     * @param pickupDate is the date for pickup as a string
     */
    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        updatePrice()
    }

    /**
     * Returns true if a flavor has not been selected for the order yet. Returns false otherwise.
     */
    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    /**
     * Reset the order by using initial default values for the quantity, flavor, date, and price.
     */
    fun resetOrder() {
        _beamWeight.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

    /**
     * Updates the price based on the order details.
     */
    private fun updatePrice() {
        var calculatedPrice = (beamWeight.value ?: 0) * PRICE_PER_POUND_BEAM

        _price.value = calculatedPrice
    }

}