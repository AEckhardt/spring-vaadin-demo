package com.example.demo.ui

import com.example.demo.data.Customer
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.BeanValidationBinder
import com.vaadin.flow.data.binder.PropertyId
import com.vaadin.flow.data.binder.ValidationException
import org.slf4j.LoggerFactory

class FlatCustomerForm : FormLayout(){
    private val log = LoggerFactory.getLogger(this::class.java)

    private lateinit var customer: Customer
    private val binder = BeanValidationBinder(Customer::class.java, true)

    private val id = TextField("Id")
    private val firstName = TextField("Vorname")
    private val lastName = TextField("Nachname")

    @PropertyId("address.street")
    private val addressStreet = TextField("Straße")

    @PropertyId("address.number")
    private val addressNumber = TextField("Hausnummer")

    @PropertyId("address.zipcode")
    private val addressZipcode = TextField("PLZ")

    @PropertyId("address.city")
    private val addressCity = TextField("Ort")

    init {
        add(id, firstName, lastName, addressStreet, addressNumber, addressZipcode, addressCity)
        binder.bindInstanceFields(this)
    }

    fun setCustomer(customer: Customer) {
        this.customer = customer
        binder.bean = customer
    }

    fun getCustomer(): Customer? {
        return try {
            binder.writeBean(customer)
            customer
        } catch (e: ValidationException) {
            log.error("Customer invalid {}", e.validationErrors.map { it.errorMessage })
            null
        }
    }

    fun removeCustomer() = binder.removeBean()
}