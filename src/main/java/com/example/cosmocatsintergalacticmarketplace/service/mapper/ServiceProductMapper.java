package com.example.cosmocatsintergalacticmarketplace.service.mapper;

import com.example.cosmocatsintergalacticmarketplace.domain.Category;
import com.example.cosmocatsintergalacticmarketplace.domain.Product;
import com.example.cosmocatsintergalacticmarketplace.repository.entity.CategoryEntity;
import com.example.cosmocatsintergalacticmarketplace.repository.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = ServiceCategoryMapper.class)
@Component
public abstract class ServiceProductMapper {

    @Autowired
    protected ServiceCategoryMapper serviceCategoryMapper;


    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "category", source = "category", qualifiedByName = "toCategory")
    public abstract Product toProduct(ProductEntity productEntity);

    @Named("toCategory")
    protected Category toCategory(CategoryEntity categoryEntity) {
        return serviceCategoryMapper.toCategory(categoryEntity);
    }

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "category", source = "category", qualifiedByName = "toCategoryEntity")
    @Mapping(target = "orderProducts", ignore = true)
    public abstract ProductEntity toProductEntity(Product product);

    @Named("toCategoryEntity")
    protected CategoryEntity toCategoryEntity(Category category) {
        return serviceCategoryMapper.toCategoryEntity(category);
    }

    public List<Product> toProductList(List<ProductEntity> productEntities) {
        return productEntities.stream().map(this::toProduct).collect(Collectors.toList());
    }
}
