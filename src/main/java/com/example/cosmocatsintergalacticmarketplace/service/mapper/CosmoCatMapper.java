package com.example.cosmocatsintergalacticmarketplace.service.mapper;

import com.example.cosmocatsintergalacticmarketplace.domain.CosmoCat;
import com.example.cosmocatsintergalacticmarketplace.dto.CosmoCatEntry;
import com.example.cosmocatsintergalacticmarketplace.dto.CosmoCatListDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CosmoCatMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "color", source = "color")
    CosmoCatEntry toCosmoCatEntry(CosmoCat cosmoCat);

    default CosmoCatListDto toCosmoCatListDto(List<CosmoCat> cosmoCats) {
        return CosmoCatListDto.builder().cosmoCatEntries(toCosmoCatEntry(cosmoCats)).build();
    }

    List<CosmoCatEntry> toCosmoCatEntry(List<CosmoCat> cosmoCats);
}
