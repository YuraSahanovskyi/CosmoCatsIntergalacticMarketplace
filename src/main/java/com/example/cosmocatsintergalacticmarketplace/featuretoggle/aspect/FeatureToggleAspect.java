package com.example.cosmocatsintergalacticmarketplace.featuretoggle.aspect;

import com.example.cosmocatsintergalacticmarketplace.featuretoggle.FeatureToggleService;
import com.example.cosmocatsintergalacticmarketplace.featuretoggle.FeatureToggles;
import com.example.cosmocatsintergalacticmarketplace.featuretoggle.annotation.FeatureToggle;
import com.example.cosmocatsintergalacticmarketplace.featuretoggle.exception.FeatureToggleNotEnabledException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class FeatureToggleAspect {

    private final FeatureToggleService featureToggleService;

    @Before(value = "@annotation(featureToggle)")
    public void checkFeatureToggleAnnotation(FeatureToggle featureToggle) {
        checkToggle(featureToggle);
    }

    private void checkToggle(FeatureToggle featureToggle) {
        FeatureToggles toggle = featureToggle.value();
        if (!featureToggleService.check(toggle.getFeatureName())) {
            throw new FeatureToggleNotEnabledException(toggle.getFeatureName());
        }
    }
}