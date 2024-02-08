package com.sp.fc.web.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Teacher {
    private String id;
    private String username;
    private Set<GrantedAuthority> role;
}
