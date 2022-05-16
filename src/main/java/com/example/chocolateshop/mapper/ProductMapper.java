package com.example.chocolateshop.mapper;


import com.example.chocolateshop.dto.ProductDTO;
import com.example.chocolateshop.models.Chocolate;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Chocolate toProduct(ProductDTO productDTO);

    @InheritInverseConfiguration
    ProductDTO fromProduct(Chocolate product);

    List<Chocolate> toProductList(List<ProductDTO> productDTOS);
    List<ProductDTO> fromProductList(List<Chocolate> chocolateList);
}
