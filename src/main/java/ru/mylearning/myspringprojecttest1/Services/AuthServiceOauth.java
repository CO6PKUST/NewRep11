package ru.mylearning.myspringprojecttest1.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.mylearning.myspringprojecttest1.Dtos.OauthCodeDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceOauth {
    private final static String CLIENT_ID = "888245132190-ur9ifn150cl4ralmnank5on5riaqdv28.apps.googleusercontent.com";
    private final static String CLIENT_SECRET = "client-secret=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";
    private final static String REDIRECT_URI = "http://localhost:8080/login/oauth2/code/google";

    public ResponseEntity<?> getAuthToken(@RequestBody OauthCodeDto oauthCodeDto){
        log.info("метод getAuthToken класса AuthServiceOauth");
        return ResponseEntity.ok(getTokenOauth(oauthCodeDto.getCode()));
    }

    private String getTokenOauth(String code){
        log.info("метод etTokenOauth класса AuthServiceOauth");
        WebClient webClient = WebClient.create("https://oauth2.googleapis.com");

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("client_id", CLIENT_ID);
        formData.add("client_secret", CLIENT_SECRET);
        formData.add("redirect_uri", REDIRECT_URI);
        formData.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        return webClient.post()
                .uri("/token")
                .body(BodyInserters.fromFormData(formData))
                .headers(h -> h.addAll(headers))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


}
