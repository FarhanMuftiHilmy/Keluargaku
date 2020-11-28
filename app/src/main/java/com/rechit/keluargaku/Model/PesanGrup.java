package com.rechit.keluargaku.Model;

public class PesanGrup {
    String message;
    String sender;
    String key;

    public PesanGrup(String message, String sender) {
        this.message = message;
        this.sender = sender;
        this.key = key;
    }

    public PesanGrup() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return sender;
    }

    public void setName(String name) {
        this.sender = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "PesanGrup{" +
                "message='" + message + '\'' +
                ", name='" + sender + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}

