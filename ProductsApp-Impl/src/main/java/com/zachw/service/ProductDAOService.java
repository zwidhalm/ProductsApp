package com.zachw.service;


import com.zachw.dto.ProductDTO;
import java.util.List;

public interface ProductDAOService {
    List<ProductDTO> findAll();
    ProductDTO create(ProductDTO prod);
    ProductDTO update(Long id, ProductDTO prod);
    void delete(Long id);


}
