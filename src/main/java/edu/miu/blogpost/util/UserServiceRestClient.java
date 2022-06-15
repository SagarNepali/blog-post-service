package edu.miu.blogpost.util;

import edu.miu.blogpost.dto.User;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "blog-user-service",  path = "/api/users")
public interface UserServiceRestClient {

    @GetMapping
    List<User> getAll();

    @GetMapping("/{id}")
    User getById(@PathVariable("id") Long id);
}
