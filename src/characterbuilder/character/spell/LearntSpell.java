package characterbuilder.character.spell;

public class LearntSpell {

    private final Spell spell;
    private final boolean prepared;

    public LearntSpell(Spell spell, boolean prepared) {
        this.spell = spell;
        this.prepared = prepared;
    }

    public Spell getSpell() {
        return spell;
    }

    public int getLevel() {
        return spell.getLevel();
    }

    public boolean isSpell(Spell spell) {
        return this.spell.equals(spell);
    }

    public boolean isPrepared() {
        return prepared;
    }

    @Override
    public int hashCode() {
        return spell.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass()
            && equalsLearntSpell((LearntSpell) obj);
    }

    protected boolean equalsLearntSpell(LearntSpell other) {
        return this.prepared == other.prepared && this.spell == other.spell;
    }
}
