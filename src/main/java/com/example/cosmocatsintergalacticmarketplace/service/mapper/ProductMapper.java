package com.example.cosmocatsintergalacticmarketplace.service.mapper;

import com.example.cosmocatsintergalacticmarketplace.domain.Product;
import com.example.cosmocatsintergalacticmarketplace.dto.ProductDto;
import com.example.cosmocatsintergalacticmarketplace.dto.ProductEntry;
import com.example.cosmocatsintergalacticmarketplace.dto.ProductListDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    ProductEntry toProductEntry(Product product);

    default ProductListDto toProductListDto(List<Product> products) {
        return ProductListDto.builder().productEntries(toProductEntry(products)).build();
    }

    List<ProductEntry> toProductEntry(List<Product> products);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    Product toProduct(ProductDto productDto);
}
