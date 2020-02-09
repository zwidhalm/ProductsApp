package com.zachw.controllerclient;


import com.zachw.dtoclient.ProductDTOClient;
import com.zachw.svcclient.ProductSVCClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class ProductControllerClient {

    @Autowired
    private ProductSVCClient service;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("products", service.findAll());
        model.addAttribute("newProduct", new ProductDTOClient());
        return "products";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestParam Long id, ProductDTOClient product) {
        service.update(id, product);
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String delete(@RequestParam Long id) {
        service.delete(id);
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("newProduct") ProductDTOClient product) {
        service.create(product);
        return "redirect:/";
    }


}
