package com.example.cosmocatsintergalacticmarketplace.web;

import com.example.cosmocatsintergalacticmarketplace.featuretoggle.FeatureToggleExtension;
import com.example.cosmocatsintergalacticmarketplace.featuretoggle.FeatureToggles;
import com.example.cosmocatsintergalacticmarketplace.featuretoggle.annotation.DisabledFeatureToggle;
import com.example.cosmocatsintergalacticmarketplace.featuretoggle.annotation.EnabledFeatureToggle;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc
@DisplayName("Greeting Controller IT")
@ExtendWith(FeatureToggleExtension.class)
public class CosmoCatControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    @DisabledFeatureToggle(FeatureToggles.COSMO_CATS)
    void shouldGet404FeatureDisabled() throws Exception {
        mockMvc.perform(get("/api/v1/cats")).andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    @EnabledFeatureToggle(FeatureToggles.COSMO_CATS)
    void shouldGet200() throws Exception {
        mockMvc.perform(get("/api/v1/cats")).andExpect(status().isOk());
    }
}
