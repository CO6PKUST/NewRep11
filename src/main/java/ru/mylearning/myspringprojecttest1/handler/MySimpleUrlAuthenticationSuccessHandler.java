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
import ru.mylearning.myspringprojecttest1.Dtos.UserRegistrationDto;
import ru.mylearning.myspringprojecttest1.Mappers.MapOauth2ToUserRegistrationDto;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final MapOauth2ToUserRegistrationDto mapOauth2ToUserRegistrationDto = new MapOauth2ToUserRegistrationDto();



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();


        log.info("onAuthenticationSuccess");
//        UserRegistrationDto userRegistrationDto = mapOauth2ToUserRegistrationDto.mapToUserRegistrationDto(oAuth2User);
//        //clearAuthenticationAttributes(request);
//
//     //   String targetUrl = determineTargetUrl(authentication);
//
//        String targetUrl = UriComponentsBuilder.fromUriString("/authWithOauth2")
//                .queryParam("email", (String) oAuth2User.getAttribute("email"))
//                .build().toUriString();
//        log.info(targetUrl);
//
//
//
        redirectStrategy.sendRedirect(request, response, "/authWithOauth");


    }

//    protected String determineTargetUrl(final Authentication authentication) {
//
//        Map<String, String> roleTargetUrlMap = new HashMap<>();
//        roleTargetUrlMap.put("ROLE_USER", "/homepage.html");
//        roleTargetUrlMap.put("ROLE_ADMIN", "/console.html");
//
//        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        for (final GrantedAuthority grantedAuthority : authorities) {
//            String authorityName = grantedAuthority.getAuthority();
//            if(roleTargetUrlMap.containsKey(authorityName)) {
//                return roleTargetUrlMap.get(authorityName);
//            }
//        }
//
//        throw new IllegalStateException();
//    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }



}
