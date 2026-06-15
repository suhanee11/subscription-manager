package com.suhanee.subscriptionmanager.service;


import com.suhanee.subscriptionmanager.entity.Subscription;
import com.suhanee.subscriptionmanager.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository repo;

    @Scheduled(cron = "0 0 9 * * ?")
    public void checkSubs(){
        LocalDate today=LocalDate.now();
        LocalDate renew=today.plusDays(3);

        List<Subscription> subs= repo.findAll();

        for(Subscription sub:subs){
        if(sub.getRenewDate().equals(renew)) {
            System.out.println("Reminder:" + sub.getName()+" subscription expires in 3 days");
            System.out.println("Today: " + today);
            System.out.println("Target renew: " + renew);
            System.out.println("renew date " + sub.getRenewDate());
        }
        }
    }
    public List<Subscription> getAllSubscription(){
        return repo.findAll();
    }
    public Subscription getSubscriptionById(Long id){
        return repo.findById(id).orElse(null);
    }

    public Subscription addSubscription(Subscription sub){
        if(sub.getType().equalsIgnoreCase("monthly")){
            sub.setRenewDate(sub.getStartDate().plusMonths(1));
        }
        if(sub.getType().equalsIgnoreCase("yearly")){
            sub.setRenewDate(sub.getStartDate().plusYears(1));
        }
        return repo.save(sub);
    }
    public void deleteSubscription(Long id){
          repo.deleteById(id);
    }

    public Subscription updateSubscription(Long id,Subscription sub){
        if(sub.getType().equalsIgnoreCase("monthly")){
            sub.setRenewDate(sub.getStartDate().plusMonths(1));
        }
        if(sub.getType().equalsIgnoreCase("yearly")){
            sub.setRenewDate(sub.getStartDate().plusYears(1));
        }
          sub.setId(id);
          return repo.save(sub);
    }

    public Double monthlyCost(){
        List<Subscription> sub=repo.findAll();

        Double sumCost=0.0;
        for(Subscription subs:sub){
            if(subs.getType().equalsIgnoreCase("yearly")){
                sumCost+=subs.getCost()/12;

            }else{
                sumCost+=subs.getCost();
            }
        }
        return sumCost;
    }

    public List<Subscription> expiringSubs(){
        List<Subscription> sub=repo.findAll();
        List<Subscription> expiring=new ArrayList<>();
        LocalDate today= LocalDate.now();
        LocalDate renew=today.plusDays(3);
        for(Subscription subs:sub){
            LocalDate RenewDate=subs.getRenewDate();
            if(!RenewDate.isBefore(today) && !RenewDate.isAfter(renew)){
                expiring.add(subs);

            }
        }
        return expiring;
    }
}
