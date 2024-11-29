package com.example.cosmocatsintergalacticmarketplace.service.mapper;

import com.example.cosmocatsintergalacticmarketplace.domain.Category;
import com.example.cosmocatsintergalacticmarketplace.domain.Product;
import com.example.cosmocatsintergalacticmarketplace.dto.ProductDto;
import com.example.cosmocatsintergalacticmarketplace.dto.ProductEntry;
import com.example.cosmocatsintergalacticmarketplace.dto.ProductListDto;
import com.example.cosmocatsintergalacticmarketplace.service.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    @Lazy
    protected CategoryService categoryService;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "categoryId", source = "category", qualifiedByName = "categoryToId")
    public abstract ProductEntry toProductEntry(Product product);

    @Named("categoryToId")
    protected Long categoryToId(Category category) {
        if (category == null) {
            return null;
        }
        return category.getId();
    }

    public ProductListDto toProductListDto(List<Product> products) {
        return ProductListDto.builder().productEntries(toProductEntry(products)).build();
    }

    public abstract List<ProductEntry> toProductEntry(List<Product> products);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "category", source = "categoryId", qualifiedByName = "idToCategory")
    public abstract Product toProduct(ProductDto productDto);

    @Named("idToCategory")
    protected Category idToCategory(Long id) {
        return categoryService.getCategoryById(id);
    }
}
