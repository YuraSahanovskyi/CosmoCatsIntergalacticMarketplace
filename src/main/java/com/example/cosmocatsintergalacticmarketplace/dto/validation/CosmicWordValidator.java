package com.example.cosmocatsintergalacticmarketplace.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.regex.Pattern;

public class CosmicWordValidator implements ConstraintValidator<CosmicWordCheck, String> {
    private final Set<String> spaceWords = Set.of(
            "Anti-Gravity", "cosmic", "Galactic", "Andromeda galaxy", "space",
            "cosmic vitamins", "Meteorite", "intergalactic traveler", "Saturn", "space feline",
            "starship", "Intergalactic", "stardust", "Cosmic", "spaceships", "astronaut",
            "galaxy", "comet", "nebula", "constellation", "orbit", "zero gravity",
            "space debris", "cosmic dust", "black hole", "wormhole", "alien", "UFO",
            "extraterrestrial", "NASA",
            "space station", "space shuttle", "spacecraft", "rocket", "satellite",
            "telescope", "planetarium"
    );
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String pattern = "\\b(" + String.join("|", spaceWords) + ")\\b";
        return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(s).find();
    }
}
