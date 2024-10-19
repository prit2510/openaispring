package com.openaispring.openaispring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
public class OpenaispringApplication {
//    @Value("${openai.key}")
// 	private String openaiApikey;
@Value("${groq.api.key}")
private String groqApiKey;

	public static void main(String[] args) {
		SpringApplication.run(OpenaispringApplication.class, args);
	}
	
	@Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + groqApiKey);
            return execution.execute(request, body);
        });
        return restTemplate;
    }
	// @Bean
	// public RestTemplate restTemplate() 
	// {
	// 	RestTemplate restTemplate = new RestTemplate();
	// 	restTemplate.getInterceptors().add(((request, body, execution) -> {
	// 		request.getHeaders().add("Authorization","Bearer "+openaiApikey);
	// 		return execution.execute(request, body);
	// 	}));
	// 	return restTemplate;
	// }

}
