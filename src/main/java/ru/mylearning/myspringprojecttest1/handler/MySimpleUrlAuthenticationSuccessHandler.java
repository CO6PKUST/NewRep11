package ru.mylearning.myspringprojecttest1.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import ru.mylearning.myspringprojecttest1.Services.UserOauth2Service;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final UserOauth2Service userOauth2Service;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String targetUrl = UriComponentsBuilder
                .fromUriString("/loginPage")
                .queryParam("token", userOauth2Service.getAuthToken(oAuth2User))

                .build().toUriString();

        redirectStrategy.sendRedirect(request, response, targetUrl);
        clearAuthenticationAttributes(request);

    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
