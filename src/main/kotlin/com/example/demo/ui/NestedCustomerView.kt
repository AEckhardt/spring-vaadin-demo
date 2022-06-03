package com.example.demo.ui

import com.example.demo.data.Customer
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import org.slf4j.LoggerFactory

class NestedCustomerView : VerticalLayout() {
    private val log = LoggerFactory.getLogger(this::class.java)

    init {
        val customerForm = NestedCustomerForm()
        add(customerForm)
        customerForm.setCustomer(Customer())
        add(HorizontalLayout().apply {
            val save = Button("Save") {
                val customer = customerForm.getCustomer()
                log.info("Customer {}", customer)
            }
            val cancel = Button("Cancel") {
                customerForm.removeCustomer()
            }
            val new = Button("New") {
                customerForm.setCustomer(Customer())
            }
            add(new, save, cancel)
        })
    }
}