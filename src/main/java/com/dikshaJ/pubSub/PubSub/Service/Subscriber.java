package com.dikshaJ.pubSub.PubSub.Service;

import com.dikshaJ.pubSub.PubSub.DTO.Message;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public abstract class Subscriber {

    private List<Message> subscriberMessages =  new ArrayList<Message>();

    public abstract void addSubscriber(String topic);

    public abstract void removeSubscriber(String topic);

    public abstract void getMessagesForSubscriberOfTopic(String topic);

    public void printMessages(){
        for(Message message : subscriberMessages){
            System.out.println("Message Topic -> "+ message.getTopic() + " : " + message.getPayload());
        }
    }
}
