package hugo.dev.matchdiary.exception;

public class NotFoundException extends RuntimeException{
        public NotFoundException(){
            super("not found");
        }
        public NotFoundException(String message){super(message);}
}
