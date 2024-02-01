package ru.mylearning.myspringprojecttest1.Services;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import ru.mylearning.myspringprojecttest1.Dtos.OauthCodeDto;
import ru.mylearning.myspringprojecttest1.Dtos.UserGoogleResponseDto;
import io.jsonwebtoken.Jwts;
import ru.mylearning.myspringprojecttest1.Exceptions.AppError;
import ru.mylearning.myspringprojecttest1.utils.JwtTokenUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceOauth {
    private final static String CLIENT_ID = "888245132190-ur9ifn150cl4ralmnank5on5riaqdv28.apps.googleusercontent.com";
    private final static String CLIENT_SECRET = "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";
    private final static String REDIRECT_URI = "http://localhost:8080/login/oauth2/code/google";

    private final static String RESOURCE_URL_GOOGLE = "https://oauth2.googleapis.com";


    private final UserService userService;
    private final UserOauthService userOauthService;

    public ResponseEntity<?> getAuthToken(@RequestBody OauthCodeDto oauthCodeDto){
        log.info("метод getAuthToken класса AuthServiceOauth");
        return ResponseEntity.ok(getTokenOauth(java.net.URLDecoder.decode(oauthCodeDto.getCode(), StandardCharsets.UTF_8)));
    }

    private ResponseEntity<?> getTokenOauth(String code){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData= new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("client_id", CLIENT_ID);
        formData.add("client_secret", CLIENT_SECRET);
        formData.add("redirect_uri", REDIRECT_URI);
        formData.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        ResponseEntity<UserGoogleResponseDto> responseEntity = restTemplate.postForEntity(
                RESOURCE_URL_GOOGLE + "/token", request , UserGoogleResponseDto.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()){
            String idToken = responseEntity.getBody().getId_token();
            String[] chunks = idToken.split("\\.");
            String payloadStr = new String(Base64.getUrlDecoder().decode(chunks[1]));

            JSONObject jsonObject = new JSONObject(payloadStr);
            String email = jsonObject.getString("email");

            if(userService.findByEmail(email).isPresent()){
                return ResponseEntity.ok(userOauthService.createAuthTokenByEmail(email));
            }
            return ResponseEntity.ok(userOauthService.createAuthTokenByEmail(userOauthService.createNewUser(jsonObject).getEmail()));
        }
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "пока не придумал"), HttpStatus.BAD_REQUEST);
    }





}
