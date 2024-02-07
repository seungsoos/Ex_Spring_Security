package com.sp.fc.web.student;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;


/**
 * Login -> UserNamePasswordFilter -> StudentManager에서 토큰을 통해 인증을한다.
 * 인증후 권한을 부여개념
 */

@Component
public class StudentManager implements AuthenticationProvider, InitializingBean {

    private HashMap<String, Student> studentDB = new HashMap<>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        if (studentDB.containsKey(token.getName())) {
            Student student = studentDB.get(token.getName());

            return StudentAuthenticationToken.builder()
                    .principal(student)
                    .details(student.getUsername())
                    .authenticated(true)
                    .build();
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == UsernamePasswordAuthenticationToken.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Set.of(
                new Student("hone", "홍길동", Set.of(new SimpleGrantedAuthority("ROLE_USER"))),
                new Student("kang", "강아지", Set.of(new SimpleGrantedAuthority("ROLE_USER"))),
                new Student("rang", "호랑이", Set.of(new SimpleGrantedAuthority("ROLE_USER")))
        ).forEach(s ->
                studentDB.put(s.getId(), s)
        );
    }
}
