package com.dikshaJ.pubSub.PubSub.Service;

import com.dikshaJ.pubSub.PubSub.DTO.Message;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PubSubService {

    private Map<String, Set<Subscriber>> subscriberTopicMap = new HashMap<String, Set<Subscriber>>();
    private BlockingQueue<Message> messageQueue = new ArrayBlockingQueue<Message>(100);

    private static PubSubService instance;

    private PubSubService(){}

    public static PubSubService getInstance(){
        if(instance == null){
            instance = new PubSubService();
        }
        return instance;
    }

    public void addMessageToQueue(Message message){
        this.messageQueue.add(message);
    }

    public void addSubscriberForTopic(String topic, Subscriber sub){

        if(this.subscriberTopicMap.containsKey(topic)){
            Set<Subscriber> subscribers = this.subscriberTopicMap.get(topic);
            subscribers.add(sub);
            this.subscriberTopicMap.put(topic, subscribers);
        }else{
            Set<Subscriber> subscribers = new HashSet<Subscriber>();
            subscribers.add(sub);
            this.subscriberTopicMap.put(topic, subscribers);
        }
    }


    // Not Needed that much
    public void removeSubscriberForTopic(String topic, Subscriber sub){

        if(this.subscriberTopicMap.containsKey(topic)){
            Set<Subscriber> subscribers = this.subscriberTopicMap.get(topic);
            subscribers.remove(sub);
            this.subscriberTopicMap.put(topic, subscribers);
        }else{
            System.out.println("Not present inside subs list");
        }
    }

    public void broadCast(){
        if(this.messageQueue.isEmpty() || this.messageQueue == null){
            System.out.println("No messages from publishers to display");
        }else{
            while(!this.messageQueue.isEmpty()){
                Message message = this.messageQueue.remove();
                String topic = message.getTopic();

                Set<Subscriber> subscribersOfTopic = this.subscriberTopicMap.get(topic);

                for(Subscriber sub: subscribersOfTopic){
                    List<Message> subscriberMessages = sub.getSubscriberMessages();
                    subscriberMessages.add(message);
                    sub.setSubscriberMessages(subscriberMessages);
                }
            }
        }
    }

    //Not needed that much
    //send messages to PARTICULAR subscriber at any point of time
    public void getMessagesForSubscriberOfTopic(String topic, Subscriber sub){
            if(this.subscriberTopicMap == null || !this.subscriberTopicMap.isEmpty()){
                System.out.println("No messages for this topic for this sub");
            }else{

                while(!this.messageQueue.isEmpty()){
                    Message message = this.messageQueue.remove();


                    if(message.getTopic().equalsIgnoreCase(topic)) {
                        Set<Subscriber> subscribersOfTopic = this.subscriberTopicMap.get(topic);

                        for (Subscriber element : subscribersOfTopic) {
                            if (element.equals(sub)) {
                                List<Message> subscriberMessages = element.getSubscriberMessages();
                                subscriberMessages.add(message);
                                sub.setSubscriberMessages(subscriberMessages);
                            }
                        }
                    }
                }
            }
    }

}












