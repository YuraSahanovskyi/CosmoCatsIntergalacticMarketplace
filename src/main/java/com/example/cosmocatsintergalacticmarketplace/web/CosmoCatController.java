package com.example.cosmocatsintergalacticmarketplace.web;

import com.example.cosmocatsintergalacticmarketplace.dto.CosmoCatListDto;
import com.example.cosmocatsintergalacticmarketplace.featuretoggle.FeatureToggles;
import com.example.cosmocatsintergalacticmarketplace.featuretoggle.annotation.FeatureToggle;
import com.example.cosmocatsintergalacticmarketplace.service.CosmoCatService;
import com.example.cosmocatsintergalacticmarketplace.service.mapper.CosmoCatMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/cats")
public class CosmoCatController {

    private final CosmoCatService cosmoCatsService;
    private final CosmoCatMapper cosmoCatMapper;

    public CosmoCatController(CosmoCatService cosmoCatsService, CosmoCatMapper cosmoCatMapper) {
        this.cosmoCatsService = cosmoCatsService;
        this.cosmoCatMapper = cosmoCatMapper;
    }

    @GetMapping
    @FeatureToggle(FeatureToggles.COSMO_CATS)
    public ResponseEntity<CosmoCatListDto> getCats() {
        return ResponseEntity.ok().body(
                cosmoCatMapper.toCosmoCatListDto(cosmoCatsService.getCosmoCats()));
    }
}
