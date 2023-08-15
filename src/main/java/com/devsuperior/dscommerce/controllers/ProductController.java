package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProdutcDTO;
import com.devsuperior.dscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutcDTO> findById(@PathVariable Long id){
       ProdutcDTO dto = service.findById(id);
       return ResponseEntity.ok(dto);
    }
    @GetMapping
    public ResponseEntity<Page<ProdutcDTO>> findByAll(Pageable pageable){
        Page<ProdutcDTO> dto = service.findByAll(pageable);
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<ProdutcDTO> insert(@Valid @RequestBody ProdutcDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutcDTO> update(@PathVariable Long id,@Valid @RequestBody ProdutcDTO dto ){
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
