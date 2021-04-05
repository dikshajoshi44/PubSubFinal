package com.dikshaJ.pubSub.PubSub.Service;

import com.dikshaJ.pubSub.PubSub.DTO.Message;

public interface Publisher {
    void publish(Message message);
}
