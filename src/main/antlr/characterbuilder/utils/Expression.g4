/******************************************************
 * This grammar is designed to allow descriptions of
 * abilities to include values derived from characters
 ******************************************************/
grammar Expression;

@header {
    package characterbuilder.utils;
    import java.util.function.BiFunction;
    import java.util.function.BinaryOperator;
    import java.util.Comparator;
    import java.util.List;
    import java.util.ArrayList;
    import java.util.Map;
    import java.util.Optional;
    import java.util.HashMap;
    import java.util.stream.Collectors;
    import characterbuilder.character.Character;
    import characterbuilder.character.attribute.*;
    import characterbuilder.character.characterclass.*;
    import characterbuilder.character.spell.SpellCasting;
    import static characterbuilder.character.attribute.AttributeType.*;
    import characterbuilder.utils.EvaluationContext;
}

@parser::members {
    private EvaluationContext context;
    private static final List<String> errors = new ArrayList<>();

    private Character character() {
        if (context.getCharacter().isPresent())
            return context.getCharacter().get();
        errors.add("Attempt to use character context when none present");
        return null;
    }

    public static String eval(String expr, EvaluationContext context) {
        BaseErrorListener errorListener = new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?,?> recognizer, Object offendingSymbol, 
                int line, int charPositionInLine, String msg, RecognitionException e) {
                errors.add(msg);
            }
        };
        errors.clear();
        ExpressionLexer lexer = new ExpressionLexer(CharStreams.fromString(expr));
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);
        ExpressionParser parser = new ExpressionParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        parser.context = context;
        String value = parser.expr().value;
        if (errors.isEmpty())
            return value;
        else
            return errors.stream().collect(Collectors.joining(", ", "[ *ERROR* ", "]"));
    }

    private Optional<DraconicAncestry> draconicAncestry() {
        if (character().hasAttribute(DRACONIC_ANCESTRY))
            return Optional.of(character().getAttribute(DRACONIC_ANCESTRY, DraconicAncestry.class));
        else
            return Optional.empty();
    }
}

expr returns [String value] 
    : string_expr EOF 
        {$value = $string_expr.value; }
    ;

string_expr returns [String value]
    : '$draconic_breath' { $value = draconicAncestry()
        .map(DraconicAncestry::getBreathWeapon).orElse("Draconic Breath");}
    | '$draconic_damage' { $value = draconicAncestry()
        .map(DraconicAncestry::getDamage).map(DamageType::toString).orElse("Damage");}
    | '$magic_school'
        {$value = character().getAttribute(ARCANE_TRADITION).toString();}
    | 'if(' bool_expr SPACE? ':' SPACE? string_expr ')'
        {$value = $bool_expr.value ? $string_expr.value : ""; }
    | 'if(' bool_expr SPACE? ':' SPACE? str1=string_expr SPACE? ':' SPACE? str2=string_expr ')'
        {$value = $bool_expr.value ? $str1.value : $str2.value; }
    | 'max(' int_expr SPACE max_terms ')'  
        {int value = $int_expr.value;
         $value = $max_terms.value.entrySet().stream()
            .filter(e -> value >= e.getKey())
            .max(Map.Entry.comparingByKey())
            .map(Map.Entry::getValue)
            .orElse("");}
    | 'plural(' singular=string_expr SPACE? ',' SPACE? plural=string_expr ')'
        {$value = context.isPlural() ? $plural.value : $singular.value;}
    | 'bonus(' SPACE? int_expr SPACE? ')'
        {$value = $int_expr.value != 0 ? String.format("%+d", $int_expr.value) : "";}
    | '&' string_expr ';'
        {$value = "&" + $string_expr.value + ";";}
    | WORD            
        {$value = $WORD.text;}
    | int_expr
        {$value = String.valueOf($int_expr.value);}
    | SPACE   
        {$value = $SPACE.text;}
    | str1=string_expr str2=string_expr
        {$value = $str1.value + $str2.value;}
    ;

max_terms returns [Map<Integer,String> value]
    : CONST ':' string_expr 
        {$value = new HashMap<>(); $value.put($CONST.int, $string_expr.value);}
    | CONST ':' string_expr ',' SPACE? max_terms
        {$max_terms.value.put($CONST.int, $string_expr.value); $value = $max_terms.value;}
    ;

int_expr returns [int value]
    : CONST    {$value = $CONST.int;}
    | '$splev' {$value = (character().getLevel() + 7) / 6;}
    | '$hp'    {$value = character().getIntAttribute(AttributeType.HIT_POINTS);}
    | '$level' {$value = character().getLevel();}
    | character_class '_level'
               {$value = character().getLevel($character_class.value);}
    | '$prof'  {$value = character().getProficiencyBonus();}
    | '$speed' {$value = character().getAttribute(RACE, Race.class).getSpeed();}
    | 'max(' SPACE? int1=int_expr SPACE? ',' SPACE? int2=int_expr SPACE? ')'
        {$value = Math.max($int1.value, $int2.value);}
    | '$spell_mod'
        {$value = context.getSpell().getModifier(character());}
    | '$spell_dc' 
        {$value = 8 + context.getSpell().getModifier(character());}
    | attr
        {$value = character().getIntAttribute($attr.value);}
    | attr '_mod'
        {$value = character().getModifier($attr.value);}
    | int1=int_expr SPACE? int_op SPACE? int2=int_expr
        {$value = $int_op.value.apply($int1.value, $int2.value);}
    | '(' SPACE? int_expr SPACE? ')'
        {$value = $int_expr.value;}
    ;

character_class returns [CharacterClass value]
    : '$barbarian'  {$value = CharacterClass.BARBARIAN;}
    | '$bard'       {$value = CharacterClass.BARD;}
    | '$cleric'     {$value = CharacterClass.CLERIC;}
    | '$druid'      {$value = CharacterClass.DRUID;}
    | '$fighter'    {$value = CharacterClass.FIGHTER;}
    | '$monk'       {$value = CharacterClass.MONK;}
    | '$paladin'    {$value = CharacterClass.PALADIN;}
    | '$ranger'     {$value = CharacterClass.RANGER;}
    | '$rogue'      {$value = CharacterClass.ROGUE;}
    | '$sorcerer'   {$value = CharacterClass.SORCERER;}
    | '$warlock'    {$value = CharacterClass.WARLOCK;}
    | '$wizard'     {$value = CharacterClass.WIZARD;}
    ;

bool_expr returns [boolean value]
    : int1=int_expr SPACE? bool_op SPACE? int2=int_expr
        {$value = $bool_op.value.apply($int1.value, $int2.value);}
    ;

int_op returns [BinaryOperator<Integer> value]
    : '*'  {$value = (i1, i2) -> i1 * i2;}
    | '/^' {$value = (i1, i2) -> (int)Math.ceil((double)i1 / i2);}
    | '/'  {$value = (i1, i2) -> i1 / i2;}
    | '+'  {$value = (i1, i2) -> i1 + i2;}
    | '-'  {$value = (i1, i2) -> i1 - i2;}
    ;

bool_op returns [BiFunction<Integer,Integer,Boolean> value]
    : '>'  {$value = (i1, i2) -> i1 > i2;}
    | '<'  {$value = (i1, i2) -> i1 < i2;}
    | '>=' {$value = (i1, i2) -> i1 >= i2;}
    | '<=' {$value = (i1, i2) -> i1 <= i2;}
    | '='  {$value = (i1, i2) -> i1 == i2;}
    | '<>' {$value = (i1, i2) -> i1 != i2;}
    ;

attr returns [AttributeType value]
    : '$str' {$value = AttributeType.STRENGTH;}
    | '$dex' {$value = AttributeType.DEXTERITY;}
    | '$con' {$value = AttributeType.CONSTITUTION;}
    | '$int' {$value = AttributeType.INTELLIGENCE;}
    | '$wis' {$value = AttributeType.WISDOM;}
    | '$chr' {$value = AttributeType.CHARISMA;}
    ;

CONST : [0-9]+;
SPACE : ' '+;
WORD  : [A-Za-z%.;]+;
ENUM  : [A-Za-z][A-Za-z_]+;
