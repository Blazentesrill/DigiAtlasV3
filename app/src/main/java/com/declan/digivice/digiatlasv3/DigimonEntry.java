package com.declan.digivice.digiatlasv3;

import java.util.List;

public class DigimonEntry {

    public String id;
    public String name;
    public String stage;

    // NEW: parents (used for "Evolves from")
    public List<String> evolvesFrom;

    // Existing: children
    public List<EvolutionEdge> evolvesTo;
}