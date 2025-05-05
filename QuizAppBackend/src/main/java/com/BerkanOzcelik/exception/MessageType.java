package com.BerkanOzcelik.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("1004","kayıt bulunamadı"),
	TOKEN_IS_EXPIRED("1005","tokenin süresi doldu"),
	USERNAME_NOT_FOUND("1006","username bulunamadı"),
	USERNAME_OR_PASSWORD_INVALID("1007","kullanıcı veya şifre hatalı"),
	REFRESH_TOKEN_NOT_FOUND("1009","refresh token bulunamadı"),
	REFRESH_TOKEN_IS_EXPIRED("1010","refresh token bitti"),
    INVALID_INPUT("1011","User, Department, or Category cannot be null"),
	GENERAL_EXCEPTION("9999","genel bir hata oluştu");


    private String code;

    private String message;

    MessageType(String code , String message){
        this.code=code;
        this.message=message;
    }



}
