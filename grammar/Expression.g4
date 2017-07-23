/******************************************************
 * A multi-line Javadoc-like comment about my grammar *
 ******************************************************/
grammar Expression;

@header {
package characterbuilder.utils;
import java.util.function.BiFunction;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Race;
}

@parser::members {
private Character character;
private String previousValue;
private static final List<String> errors = new ArrayList<>();

public static String eval(String expression, Character character) {
    return eval(expression, character, null);
}

public static String eval(String expression, Character character, String previousValue) {
    BaseErrorListener errorListener = new BaseErrorListener() {
        @Override
        public void syntaxError(Recognizer<?,?> recognizer, Object offendingSymbol, 
            int line, int charPositionInLine, String msg, RecognitionException e) {
            errors.add(msg);
        }
    };
    errors.clear();
    ExpressionLexer lexer = new ExpressionLexer(CharStreams.fromString(expression));
    lexer.removeErrorListeners();
    lexer.addErrorListener(errorListener);
    ExpressionParser parser = new ExpressionParser(new CommonTokenStream(lexer));
    parser.removeErrorListeners();
    parser.addErrorListener(errorListener);
    parser.character = character;
    parser.previousValue = previousValue;
    String value = parser.expression().value;
    if (errors.isEmpty())
        return value;
    else
        return errors.stream().collect(Collectors.joining(", ", "[", "]"));
}
}

expression returns [String value] 
    : s=string_expression EOF 
        {$value = $s.value; }
    ;

string_expression returns [String value]
    : i=int_expression  
        {$value = String.valueOf($i.value);}
    | IF LEFT b=bool_expression SPACE? COLON SPACE? sv=string_expression RIGHT
        {$value = $b.value ? $sv.value : ""; }
    | IF LEFT b=bool_expression SPACE? COLON SPACE? sv=string_expression SPACE? 
            COLON SPACE? dv=string_expression RIGHT
        {$value = $b.value ? $sv.value : $dv.value; }
    | MAX LEFT v=int_expression SPACE t=max_terms RIGHT  
        {int value = $v.value;
         $value = $t.value.entrySet().stream()
            .filter(e -> value >= e.getKey())
            .max(Map.Entry.comparingByKey())
            .map(Map.Entry::getValue)
            .orElse("");}
    | PLURAL LEFT v1=string_expression SPACE? COMMA SPACE? v2=string_expression RIGHT
        {$value = "1".equals(previousValue) || "one".equals(previousValue) ? $v1.value : $v2.value;}
    | w=WORD            
        {$value = $w.text;}
    | w=WORD s=SPACE   
        {$value = $w.text + $s.text;}
    | v1=string_expression v2=string_expression
        {$value = $v1.value + $v2.value;}
    ;

max_terms returns [Map<Integer,String> value]
    : c=const_val COLON s=string_expression 
        {$value = new HashMap<>(); $value.put($c.value, $s.value);}
    | c=const_val COLON s=string_expression COMMA SPACE? m=max_terms
        {$m.value.put($c.value, $s.value); $value = $m.value;}
    ;

int_expression returns [int value]
    : c=const_val       
        {$value = $c.value;}
    | HP
        {$value = character.getIntAttribute(AttributeType.HIT_POINTS);}
    | LEVEL             
        {$value = character.getLevel();}
    | PROF
        {$value = character.getProficiencyBonus();}
    | SPEED
        {$value = character.getAttribute(AttributeType.RACE, Race.class).getSpeed();}
    | a=attribute
        {$value = character.getIntAttribute($a.value);}
    | a=attribute MOD
        {$value = character.getModifier($a.value);}
    | v1=int_expression SPACE? TIMES SPACE? v2=int_expression
        {$value = $v1.value * $v2.value;}
    | v1=int_expression SPACE? DIVUP SPACE? v2=int_expression 
        {$value = (int)Math.ceil((double)$v1.value / $v2.value);}
    | v1=int_expression SPACE? DIVDN SPACE? v2=int_expression 
        {$value = $v1.value / $v2.value;}
    | v1=int_expression SPACE? PLUS SPACE? v2=int_expression 
        {$value = $v1.value + $v2.value;}
    | v1=int_expression SPACE? MINUS SPACE? v2=int_expression 
        {$value = $v1.value - $v2.value;}
    | LEFT SPACE? v=int_expression SPACE? RIGHT
        {$value = $v.value;}
    ;

bool_expression returns [boolean value]
    : v1=int_expression SPACE? o=operator SPACE? v2=int_expression
        {$value = $o.value.apply($v1.value, $v2.value);}
    ;

operator returns [BiFunction<Integer,Integer,Boolean> value]
    : GT {$value = (i1, i2) -> i1 > i2;}
    | LT {$value = (i1, i2) -> i1 < i2;}
    | GE {$value = (i1, i2) -> i1 >= i2;}
    | LE {$value = (i1, i2) -> i1 <= i2;}
    | EQ {$value = (i1, i2) -> i1 == i2;}
    | NE {$value = (i1, i2) -> i1 != i2;}
    ;

attribute returns [AttributeType value]
    : STR {$value = AttributeType.STRENGTH;}
    | DEX {$value = AttributeType.DEXTERITY;}
    | CON {$value = AttributeType.CONSTITUTION;}
    | INT {$value = AttributeType.INTELLIGENCE;}
    | WIS {$value = AttributeType.WISDOM;}
    | CHR {$value = AttributeType.CHARISMA;}
    ;

const_val returns [int value]
    : c=CONST {$value = Integer.valueOf($c.text);}
    ;

MAX   : 'max';
LEFT  : '(';
RIGHT : ')';
COMMA : ',';
COLON : ':';
IF    : 'i' 'f';
GT    : '>';
LT    : '<';
GE    : '>=';
LE    : '<=';
EQ    : '=';
NE    : '<>';
PLURAL: 'plural';
HP    : '$hp';
LEVEL : '$level';
SPEED : '$speed';
PROF  : '$prof';
STR   : '$str';
DEX   : '$dex';
CON   : '$con';
INT   : '$int';
WIS   : '$wis';
CHR   : '$chr';
MOD   : '_mod';
CONST : [1-9] [0-9]*;
TIMES : '*';
DIVUP : '//';
DIVDN : '/';
PLUS  : '+';
MINUS : '-'; 
SPACE : ' '+;
WORD  : [A-Za-z%.;]+;
