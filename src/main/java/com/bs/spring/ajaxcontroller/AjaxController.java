package com.bs.spring.ajaxcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
@RestController //@Controller+@ResponseBody
@RequestMapping("/ajax")
public class AjaxController {
    @RequestMapping("/basicAjax")
    public String basicAjax(){

        Runnable r =()->{
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        r.run();


        //viewResolver
        return "ajax/basicAjax";
    }


    @RequestMapping("/dataAjax")
//    @ResponseBody//ResponseBody를 viewResolver가지 말고 쓴다.
    public List<String> dataAjax(){

        Runnable r =()->{
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        r.run();



        List<String> names = List.of("오민현","야도란");
        return names;
    }
}
