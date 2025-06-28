package com.sunkiran.lms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunkiran.lms.dto.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestTemplatePracticeService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    // CRUD: GET/POST/PUT/PATCH/DELETE

    public void getPostM1() throws JsonProcessingException {
        //getForObject() : It returns the object directly.
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        String response = restTemplate.getForObject(url, String.class);
        Post post = objectMapper.readValue(response, Post.class);
        System.out.println("This is server response : " + response);
        System.out.println("This is Object mapper response : " + post);
    }

    public void getPostM2() throws JsonProcessingException {
        //getForObject() : It returns the object directly.
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        Post response = restTemplate.getForObject(url, Post.class);
        System.out.println("This is server response in post : " + response);
    }

    public void getPostM3() throws JsonProcessingException {
        //getForEntity() : Returns Response Entity (Wrapper around my data)
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        ResponseEntity<Post> postResponse = restTemplate.getForEntity(url, Post.class);
        Post post = postResponse.getBody();
        System.out.println("This is server response in post : " + post);
    }

    public void postPostM1() throws JsonProcessingException {
        String url = "https://jsonplaceholder.typicode.com/posts";
        Post post = new Post();
        post.setUserId(1);
        post.setTitle("My First Post");
        post.setBody("This is my first post");

        ResponseEntity<Post> postResponse = restTemplate.postForEntity(url, post, Post.class);
        Post createdPost = postResponse.getBody();
        System.out.println("This is server response in post : " + createdPost);

    }

    public void putPostM1() throws JsonProcessingException {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        Post post = new Post();
        post.setId(1);
        post.setUserId(1);
        post.setTitle("My Second Post");
        post.setBody("This is my Second post");

        restTemplate.put(url, post);
    }

    public void deletePostM1() throws JsonProcessingException {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        restTemplate.delete(url);
        System.out.println("This is server response in post : " + url);
    }

    public void exchangePostM1() throws JsonProcessingException {
        String url = "https://jsonplaceholder.typicode.com/posts/9999999";
        try {
            ResponseEntity<Post> postResponse = restTemplate.exchange(url, HttpMethod.GET, null, Post.class);
            Post post = postResponse.getBody();
            System.out.println("This is server response in post : " + post);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("Error occurred while calling the server : {}", e.getMessage());
            log.error("Error occurred while calling the server :", e);
        } catch (Exception e) {
            log.error("Error occurred while calling the server : {}", e.getMessage());
        }

    }

    public void exchangePostM2() throws JsonProcessingException {
        Post post = new Post();
        post.setUserId(1);
        post.setTitle("My First Post");
        post.setBody("This is my first post");


        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");

        HttpEntity<Post> request = new HttpEntity<>(post, headers);


        String url = "https://jsonplaceholder.typicode.com/posts/1";
        ResponseEntity<Post> postResponse = restTemplate.exchange(url, HttpMethod.POST, request, Post.class);

    }


    //TODO : Execute() Assignment explore this method.


}
