package com.suhanee.subscriptionmanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.suhanee.subscriptionmanager.entity.Subscription;


public interface
SubscriptionRepository extends JpaRepository <Subscription,Long>{
}
