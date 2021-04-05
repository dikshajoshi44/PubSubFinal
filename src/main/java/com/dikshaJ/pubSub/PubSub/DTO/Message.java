package com.dikshaJ.pubSub.PubSub.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String topic;
    private String payload;

    public Message(String topic, String payload){
        this.topic = topic;
        this.payload = payload;

    }
}
