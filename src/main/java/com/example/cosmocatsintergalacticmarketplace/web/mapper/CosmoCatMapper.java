package com.example.cosmocatsintergalacticmarketplace.web.mapper;

import com.example.cosmocatsintergalacticmarketplace.domain.CosmoCat;
import com.example.cosmocatsintergalacticmarketplace.dto.cosmocat.CosmoCatEntry;
import com.example.cosmocatsintergalacticmarketplace.dto.cosmocat.CosmoCatListDto;
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
