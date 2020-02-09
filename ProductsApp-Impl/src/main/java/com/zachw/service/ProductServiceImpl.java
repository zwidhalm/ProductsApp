package com.zachw.service;

import com.zachw.dto.ProductDTO;
import com.zachw.entities.Product;
import com.zachw.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductDAOService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * This method finds all Product entities, and converts them to DTOs
     *
     * Could this be abstracted further at an assembler level?
     *
     * @return a list of all DTOs
     */
    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(entity -> new ProductDTO(entity.getId(), entity.getDescription(), entity.getProductName()))
                .collect(Collectors.toList());
    }


    /**
     * Create a new Product entity and set the properties equal to the incoming DTO.
     * After saving the entity we convert the saved entity back to a DTO and that’s what we will send to the consumer
     *
     * Could this be abstracted further at an assembler level?
     *
     * @param prod - a DTO of type Product
     * @return
     */
    @Transactional
    @Override
    public ProductDTO create(ProductDTO prod) {
        Product newProduct = new Product();
        newProduct.setProductName(prod.getProductName());
        newProduct.setDescription(prod.getDescription());
        Product savedProduct = productRepository.saveAndFlush(newProduct);
        return new ProductDTO(savedProduct.getId(), savedProduct.getDescription(), savedProduct.getProductName());
    }

    @Transactional
    @Override
    public ProductDTO update(Long id, ProductDTO prod) {
        Product entity = findOneSafe(id);
        entity.setDescription(prod.getDescription());
        entity.setProductName(prod.getProductName());
        return new ProductDTO(entity.getId(), entity.getDescription(), entity.getProductName());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Product product = findOneSafe(id);
        productRepository.delete(product);
    }

    private Product findOneSafe(Long id) {
        Product product = productRepository.getOne(id);
        if (product == null) {
            throw new ProductNotFoundException();
        } else {
            return product;
        }
    }



}
