package com.example.demo.ui

import com.example.demo.data.Address
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.BeanValidationBinder

class AddressForm : FormLayout() {

    private lateinit var address: Address
    private val binder = BeanValidationBinder(Address::class.java)

    private val street = TextField("Straße")
    private val number = TextField("Hausnummer")
    private val zipcode = TextField("PLZ")
    private val city = TextField("Ort")

    init {
        add(street, number, zipcode, city)
        binder.bindInstanceFields(this)
    }

    fun setAddress(address: Address) {
        this.address = address
        binder.bean = address
    }

    fun getAddress(): Address {
        binder.writeBean(address)
        return address
    }

    fun removeAddress() = binder.removeBean()
}