package com.example.demo.ui

import com.example.demo.data.Customer
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.BeanValidationBinder
import com.vaadin.flow.data.binder.ValidationException
import org.slf4j.LoggerFactory

class NestedCustomerForm : FormLayout() {
    private val log = LoggerFactory.getLogger(this::class.java)

    private lateinit var customer: Customer
    private val binder = BeanValidationBinder(Customer::class.java)

    private val id = TextField("Id")
    private val firstName = TextField("Vorname")
    private val lastName = TextField("Nachname")
    private val addressForm = AddressForm()

    init {
        add(id, firstName, lastName)
        add(addressForm)
        binder.bindInstanceFields(this)
    }

    fun setCustomer(customer: Customer) {
        this.customer = customer
        binder.bean = customer
        addressForm.setAddress(customer.address)
    }

    fun getCustomer(): Customer? {
        return try {
            binder.writeBean(customer)
            val address = addressForm.getAddress()
            customer.copy(address = address)
        } catch (e: ValidationException) {
            log.error("Customer invalid {}", e.validationErrors.map { it.errorMessage })
            addressForm.getAddress()
            return null
        }
    }

    fun removeCustomer() {
        addressForm.removeAddress()
        binder.removeBean()
    }
}
