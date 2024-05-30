package hugo.dev.matchdiary.exception;

public class BusinessRuleException extends RuntimeException{
    public BusinessRuleException(){
        super("not found");
    }
    public BusinessRuleException(String message){super(message);}
}
