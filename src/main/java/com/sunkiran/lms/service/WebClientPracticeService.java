package com.sunkiran.lms.service;

import com.sunkiran.lms.dto.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebClientPracticeService {
    private final WebClient.Builder webClientBuilder;

    public void getPostM1() {
        WebClient webClient = webClientBuilder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();

        Post post = webClient.get()
                .uri("/posts/1")
                .retrieve()
                .bodyToMono(Post.class)
                .block();

        System.out.println("This is server response : " + post);
    }

    public void postPostM1() {
        WebClient webClient = webClientBuilder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();

        Post post = new Post();
        post.setUserId(1);
        post.setTitle("My First Post");
        post.setBody("This is my first post");

        Post post1 = webClient.post()
                .uri("/posts")
                .bodyValue(post)
                .retrieve()
                .bodyToMono(Post.class)
                .block();

        System.out.println("This is server response : " + post1);
    }

    public void putPostM1() {
        WebClient webClient = webClientBuilder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();

        Post post = new Post();
        post.setId(1);
        post.setUserId(1);
        post.setTitle("My Second Post");
        post.setBody("This is my Second post");

        webClient.put()
                .uri("/posts/1")
                .bodyValue(post)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    public void deletePostM1() {
        WebClient webClient = webClientBuilder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();

        webClient.delete()
                .uri("/posts/1")
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
    
    public void getAllPostM1() {
        WebClient webClient = webClientBuilder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();

        List<Post> posts = webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(Post.class)
                .collectList()
                .block();

    }
}
