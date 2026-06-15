package com.suhanee.subscriptionmanager.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.suhanee.subscriptionmanager.service.SubscriptionService;
import com.suhanee.subscriptionmanager.entity.Subscription;

import javax.management.Notification;
import java.util.*;

@RequestMapping("/api")


@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;


    @GetMapping("/subscriptions")
    public List<Subscription> GetAllSubs(){
        return service.getAllSubscription();
    }

    @GetMapping("/{id}")
    public Subscription GetById(@PathVariable Long id){
        return service.getSubscriptionById(id);
    }

    @PostMapping("/subscriptions")
    public Subscription AddSubscription(@RequestBody Subscription sub){
        return service.addSubscription(sub);
    }

    @PutMapping("/subscriptions/{id}")
    public Subscription UpdateSubscription(@PathVariable Long id,@RequestBody Subscription sub){
        return service.updateSubscription(id,sub);
    }

    @DeleteMapping("/subscriptions/{id}")
    public void DeleteSubscription(@PathVariable Long id){
        service.deleteSubscription(id);
    }

    @GetMapping("/test-notif")
    public void notifications(){
        service.checkSubs();

    }

    @GetMapping("/Subscription/monthlyCost")
    public Double MonthlyCost(){ return service.monthlyCost(); }

    @GetMapping("/Subscription/expiring")
    public List<Subscription> expiring(){ return service.expiringSubs();}

}
