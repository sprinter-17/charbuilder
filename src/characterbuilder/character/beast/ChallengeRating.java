package characterbuilder.character.beast;

public enum ChallengeRating {
    CR0,
    CR1_8,
    CR1_4,
    CR1_2,
    CR1,
    CR2,
    CR3,
    CR4;

    public static ChallengeRating valueOf(int cr) {
        switch (cr) {
            case 1:
                return CR1;
            case 2:
                return CR2;
            case 3:
                return CR3;
            case 4:
                return CR4;
            default:
                throw new IllegalArgumentException("No CR " + cr);
        }
    }
}
