package com.bs.spring.common.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chattingpage")
public class ChattingController {
    @GetMapping
    public String chattingPage(){
        return "chatting/chattingpage";
    }
}
