package com.example.demo.ui

import com.vaadin.flow.component.splitlayout.SplitLayout
import com.vaadin.flow.router.Route

@Route("customer")
class CustomerView : SplitLayout() {
    init {
        val flatCustomerView = FlatCustomerView()
        val nestedCustomerView = NestedCustomerView()
        addToPrimary(flatCustomerView)
        addToSecondary(nestedCustomerView)
    }
}