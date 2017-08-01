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
    import java.util.HashMap;
    import java.util.stream.Collectors;
    import characterbuilder.character.Character;
    import characterbuilder.character.attribute.*;
    import characterbuilder.character.ability.SpellCasting;
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
}

expr returns [String value] 
    : string_expr EOF 
        {$value = $string_expr.value; }
    ;

string_expr returns [String value]
    : int_expr  
        {$value = String.valueOf($int_expr.value);}
    | '$breath' {$value = character().getAttribute(DRACONIC_ANCESTORY, 
                    DraconicAncestory.class).getBreathWeapon();}
    | 'if(' bool_expr SPACE? ':' SPACE? string_expr ')'
        {$value = $bool_expr.value ? $string_expr.value : ""; }
    | 'if(' bool_expr SPACE? ':' SPACE? str1=string_expr SPACE? 
            ':' SPACE? str2=string_expr ')'
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
    | WORD            
        {$value = $WORD.text;}
    | WORD SPACE   
        {$value = $WORD.text + $SPACE.text;}
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
    | '$hp'    {$value = character().getIntAttribute(AttributeType.HIT_POINTS);}
    | '$level' {$value = character().getLevel();}
    | '$prof'  {$value = character().getProficiencyBonus();}
    | '$speed' {$value = character().getAttribute(RACE, Race.class).getSpeed();}
    | spell_expr '_mod' 
        {$value = $spell_expr.value.getModifier(character());}
    | spell_expr '_dc' 
        {$value = $spell_expr.value.getSaveDC(character());}
    | attr
        {$value = character().getIntAttribute($attr.value);}
    | attr '_mod'
        {$value = character().getModifier($attr.value);}
    | int1=int_expr SPACE? int_op SPACE? int2=int_expr
        {$value = $int_op.value.apply($int1.value, $int2.value);}
    | '(' SPACE? int_expr SPACE? ')'
        {$value = $int_expr.value;}
    ;

spell_expr returns [SpellCasting value]
    : '$spell' {$value = character().getAttribute(SPELLCASTING, SpellCasting.class);}
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

CONST : [1-9] [0-9]*;
SPACE : ' '+;
WORD  : [A-Za-z%.;]+;
