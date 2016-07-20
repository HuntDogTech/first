package cn.huntdog.first.controller;

import cn.huntdog.first.entity.Greeting;
import cn.huntdog.first.entity.HelloMessage;
import cn.huntdog.first.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by JonDai on 2016/7/12.
 */
@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

}
