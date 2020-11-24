package com.rechit.keluargaku.Model;

public class PesanGrup {
    String message;
    String name;
    String key;

    public PesanGrup(String message, String name, String key) {
        this.message = message;
        this.name = name;
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}

