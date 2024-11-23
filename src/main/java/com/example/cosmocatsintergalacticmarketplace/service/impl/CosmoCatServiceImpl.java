package com.example.cosmocatsintergalacticmarketplace.service.impl;

import com.example.cosmocatsintergalacticmarketplace.domain.CosmoCat;
import com.example.cosmocatsintergalacticmarketplace.service.CosmoCatService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CosmoCatServiceImpl implements CosmoCatService {
    private final List<CosmoCat> cats = buildAllCosmoCatsMock();


    public static List<CosmoCat> buildAllCosmoCatsMock() {
        return new ArrayList<>(List.of(
                CosmoCat.builder()
                        .id(1L)
                        .name("Luna")
                        .color("Gray")
                        .build(),
                CosmoCat.builder()
                        .id(2L)
                        .name("Cosmo")
                        .color("Brown")
                        .build(),
                CosmoCat.builder()
                        .id(3L)
                        .name("Stella")
                        .color("Spotted")
                        .build(),
                CosmoCat.builder()
                        .id(4L)
                        .name("Nova")
                        .color("Black")
                        .build(),
                CosmoCat.builder()
                        .id(5L)
                        .name("Orion")
                        .color("White")
                        .build(),
                CosmoCat.builder()
                        .id(6L)
                        .name("Mira")
                        .color("Orange")
                        .build(),
                CosmoCat.builder()
                        .id(7L)
                        .name("Leo")
                        .color("Golden")
                        .build(),
                CosmoCat.builder()
                        .id(8L)
                        .name("Vega")
                        .color("Silver")
                        .build()
        ));
    }

    @Override
    public List<CosmoCat> getCosmoCats() {
        return cats;
    }
}
