package com.example.cosmocatsintergalacticmarketplace.service.mapper;

import com.example.cosmocatsintergalacticmarketplace.domain.Category;
import com.example.cosmocatsintergalacticmarketplace.repository.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Component
public interface ServiceCategoryMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    Category toCategory(CategoryEntity categoryEntity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "products", ignore = true)
    CategoryEntity toCategoryEntity(Category category);
    default List<Category> toCategoryList(List<CategoryEntity> categoryEntities) {
        return categoryEntities.stream().map(this::toCategory).collect(Collectors.toList());
    }
}
