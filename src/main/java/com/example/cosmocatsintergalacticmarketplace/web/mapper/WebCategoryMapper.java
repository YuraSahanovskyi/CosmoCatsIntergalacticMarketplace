package com.example.cosmocatsintergalacticmarketplace.web.mapper;

import com.example.cosmocatsintergalacticmarketplace.domain.Category;
import com.example.cosmocatsintergalacticmarketplace.dto.category.CategoryDto;
import com.example.cosmocatsintergalacticmarketplace.dto.category.CategoryEntry;
import com.example.cosmocatsintergalacticmarketplace.dto.category.CategoryListDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface WebCategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    Category toCategory(CategoryDto categoryDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "categoryDto.name")
    @Mapping(target = "description", source = "categoryDto.description")
    Category toCategoryWithId(CategoryDto categoryDto, Long id);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    CategoryEntry toCategoryEntry(Category category);

    default CategoryListDto toCategoryListDto(List<CategoryEntry> categoryEntryList) {
        return CategoryListDto.builder().categoryEntries(categoryEntryList).build();
    }
}
