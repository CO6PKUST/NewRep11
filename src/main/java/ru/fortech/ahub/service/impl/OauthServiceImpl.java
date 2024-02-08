package ru.fortech.ahub.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import ru.fortech.ahub.repository.UserRepository;
import ru.fortech.ahub.exception.AppError;
import ru.fortech.ahub.service.dto.OauthCodeDto;
import ru.fortech.ahub.service.dto.UserGoogleResponseDto;
import ru.fortech.ahub.service.OauthService;
import ru.fortech.ahub.service.UserOauthService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class OauthServiceImpl implements OauthService {
    private final static String CLIENT_ID = "888245132190-ur9ifn150cl4ralmnank5on5riaqdv28.apps.googleusercontent.com";
    private final static String CLIENT_SECRET = "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";
    private final static String REDIRECT_URI = "http://localhost:8080/login/oauth2/code/google";
    private final static String RESOURCE_URL_GOOGLE = "https://oauth2.googleapis.com";

    private final UserOauthService userOauthService;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> getAuthToken(@RequestBody OauthCodeDto oauthCodeDto) {
        return ResponseEntity.ok(getTokenFromCode(java.net.URLDecoder.decode(oauthCodeDto.getCode(), StandardCharsets.UTF_8)));
    }

    private ResponseEntity<?> getTokenFromCode(String code) {

        ResponseEntity<UserGoogleResponseDto> responseEntity = getResponseToken(code);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String idToken = responseEntity.getBody().getId_token();
            String[] chunks = idToken.split("\\.");
            String payloadStr = new String(Base64.getUrlDecoder().decode(chunks[1]));

            JSONObject jsonObject = new JSONObject(payloadStr);
            String email = jsonObject.getString("email");

            if (userRepository.findByEmail(email).isPresent()) {
                return ResponseEntity.ok(userOauthService.createAuthTokenByEmail(email));
            }
            return ResponseEntity.ok(userOauthService.createAuthTokenByEmail(userOauthService.createNewUser(jsonObject).getEmail()));
        }
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "response error"), HttpStatus.BAD_REQUEST);
    }
    private ResponseEntity<UserGoogleResponseDto> getResponseToken(String code){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("client_id", CLIENT_ID);
        formData.add("client_secret", CLIENT_SECRET);
        formData.add("redirect_uri", REDIRECT_URI);
        formData.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        return restTemplate.postForEntity(RESOURCE_URL_GOOGLE + "/token", request, UserGoogleResponseDto.class);
    }


}