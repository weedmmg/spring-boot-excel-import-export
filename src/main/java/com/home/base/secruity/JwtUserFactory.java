package com.home.base.secruity;

import java.util.HashSet;
import java.util.Set;

import java.util.stream.Collectors;

import com.home.entity.HomeUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public final class JwtUserFactory {

    private JwtUserFactory() {
    }

  public static JwtUser create(HomeUser user) {
    Set<String> roles = new HashSet<>();
    roles.add("ROLE_USER");
        return new JwtUser(user, mapToGrantedAuthorities(
            roles) );
    }

    private static Set<GrantedAuthority> mapToGrantedAuthorities(Set<String> authorities) {
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }
}
