package nl.computerhok.cruddie.controller;

import nl.computerhok.cruddie.repositories.CustomerRepository;
import nl.computerhok.cruddie.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @RequestMapping("/{id}")
    public String showCustomerForm(@PathVariable("id") Customer customer, Model model) {

        model.addAttribute("customer", customer);
        return "customerForm";
    }

    @RequestMapping
    public String showCustomers(Model model, Pageable pageable) {
        model.addAttribute("customers", repository.findAll(pageable));
        return "users";
    }
}