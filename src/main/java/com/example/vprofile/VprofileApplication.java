package com.example.vprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class VprofileApplication {
    public static void main(String[] args) {
        SpringApplication.run(VprofileApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "<h1>Hello user — this is a Spring Boot project</h1>" +
               "<p>This service demonstrates a simple web API and deployment flow.</p>" +
               "<h3>Basic flow</h3>" +
               "<ol>" +
               "  <li>Client request hits a Spring Boot controller (this page).</li>" +
               "  <li>Configuration is injected via environment variables (see <code>/status</code>).</li>" +
               "  <li>Optional dependencies run in Kubernetes: MySQL, Memcached, RabbitMQ, Elasticsearch.</li>" +
               "  <li>App is containerized with Docker and deployed to Kubernetes (Minikube).</li>" +
               "</ol>" +
               "<h3>Useful endpoints</h3>" +
               "<ul>" +
               "  <li><a href='/health'>/health</a> — health check</li>" +
               "  <li><a href='/profile'>/profile</a> — sample user JSON</li>" +
               "  <li><a href='/status'>/status</a> — current config/environment</li>" +
               "</ul>";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/profile")
    public Map<String, String> profile() {
        Map<String, String> user = new HashMap<>();
        user.put("username", "admin_vp");
        user.put("email", "admin@vprofile.com");
        user.put("role", "admin");
        user.put("bio", "This is a demo user for vProfile app.");
        return user;
    }

    @GetMapping("/status")
    public Map<String, String> status() {
        Map<String, String> status = new HashMap<>();
        status.put("app", "vProfile Java App");
        status.put("db_host", System.getenv().getOrDefault("SPRING_DATASOURCE_URL", "not set"));
        status.put("cache_active_host", System.getenv().getOrDefault("MEMCACHED_ACTIVE_HOST", "not set"));
        status.put("cache_standby_host", System.getenv().getOrDefault("MEMCACHED_STANDBY_HOST", "not set"));
        status.put("rabbitmq_host", System.getenv().getOrDefault("RABBITMQ_ADDRESS", "not set"));
        status.put("elasticsearch_host", System.getenv().getOrDefault("ELASTICSEARCH_HOST", "not set"));
        return status;
    }
} 