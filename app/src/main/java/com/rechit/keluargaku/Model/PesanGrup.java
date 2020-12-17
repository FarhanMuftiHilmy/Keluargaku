package com.rechit.keluargaku.Model;

public class PesanGrup {
    private String message;
    private String sender;
    private String key;
    private boolean isseen;

    public PesanGrup(String message, String sender, boolean isseen) {
        this.message = message;
        this.sender = sender;
        this.key = key;
        this.isseen = isseen;
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

    public boolean Isseen() {
        return isseen;
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

