package com.zachw.controller;


import com.zachw.dto.ProductDTO;
import com.zachw.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl service;

    @GetMapping
    public List<ProductDTO> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ProductDTO create(@Valid @RequestBody ProductDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public ProductDTO update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
