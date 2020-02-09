package com.zachw.svcclient;

import com.zachw.dtoclient.ProductDTOClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductSVCClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${resource.products}")
    private String resource;

    @Value("${resource.products}/{id}")
    private String idResource;

    public List<ProductDTOClient> findAll() {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(resource, ProductDTOClient[].class))).collect(Collectors.toList());
    }

    public ProductDTOClient create(ProductDTOClient product) {
        return restTemplate.postForObject(resource, product, ProductDTOClient.class);
    }

    public ProductDTOClient update(Long id, ProductDTOClient product) {
        return restTemplate.exchange(idResource, HttpMethod.PUT, new HttpEntity<>(product), ProductDTOClient.class, id).getBody();
    }

    public void delete(Long id) {
        restTemplate.delete(idResource, id);
    }

}
