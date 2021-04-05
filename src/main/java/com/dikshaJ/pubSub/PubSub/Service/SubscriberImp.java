package com.dikshaJ.pubSub.PubSub.Service;

public class SubscriberImp extends Subscriber{

    PubSubService pubSubService = PubSubService.getInstance();


    public void addSubscriber(String topic){
        pubSubService.addSubscriberForTopic(topic, this);
    }

    public void removeSubscriber(String topic){
        pubSubService.removeSubscriberForTopic(topic, this);
    }

    public void getMessagesForSubscriberOfTopic(String topic){
        pubSubService.getMessagesForSubscriberOfTopic(topic, this);
    }
}
