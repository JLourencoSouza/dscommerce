package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProdutcDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.respositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    @Transactional(readOnly = true)
    public ProdutcDTO findById(Long id){
        Product product = repository.findById(id).get();
        return new ProdutcDTO(product);
    }
    @Transactional(readOnly = true)
    public Page<ProdutcDTO> findByAll(Pageable pageable){
        Page<Product> result = repository.findAll(pageable);
        return result.map(x -> new ProdutcDTO(x));
    }
}