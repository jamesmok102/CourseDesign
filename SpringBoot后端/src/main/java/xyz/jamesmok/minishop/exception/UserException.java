package xyz.jamesmok.minishop.exception;

public class UserException extends RuntimeException{

    private String msgDes;

    public UserException(String message) {
        super(message);
        msgDes = message;
    }

    public String getMsgDes() {
        return msgDes;
    }

}
