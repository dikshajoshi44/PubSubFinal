package com.dikshaJ.pubSub.PubSub.Service;

import com.dikshaJ.pubSub.PubSub.DTO.Message;

public class PublisherImp implements Publisher{

    PubSubService pubSubService = PubSubService.getInstance();

    public void publish(Message message){
        pubSubService.addMessageToQueue(message);
    }
}
