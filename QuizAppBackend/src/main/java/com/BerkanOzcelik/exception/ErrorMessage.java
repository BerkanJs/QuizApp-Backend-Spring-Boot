package com.BerkanOzcelik.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private String ofStatic;
    private MessageType messageType;
    public ErrorMessage(MessageType messageType , String ofStatic){
        this.messageType=messageType;
        this.ofStatic=ofStatic;
    }

    public String prepareErrorMessage(){
        StringBuilder builder = new StringBuilder();
        builder.append(messageType.getMessage());
        if(this.ofStatic!=null){
            builder.append(":").append(ofStatic);
        }
        return builder.toString();
    }




    
}
