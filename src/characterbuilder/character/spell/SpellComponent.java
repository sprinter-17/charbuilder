package characterbuilder.character.spell;

public enum SpellComponent {
    VERBAL("V"),
    SOMATIC("S"),
    MATERIAL("M"),;

    private final String abbreviation;

    private SpellComponent(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

}
