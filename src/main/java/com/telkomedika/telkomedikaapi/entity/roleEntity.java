package com.telkomedika.telkomedikaapi.entity;

import com.telkomedika.telkomedikaapi.model.permission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@RequiredArgsConstructor
public enum roleEntity {

    USER(Collections.emptySet()),
    ADMIN(
            Collections.emptySet()
//            Set.of(
//                    ADMIN_READ,
//                    ADMIN_UPDATE,
//                    ADMIN_DELETE,
//                    ADMIN_CREATE,
//                    MANAGER_READ,
//                    MANAGER_UPDATE,
//                    MANAGER_DELETE,
//                    MANAGER_CREATE
//            )
    ),
    MANAGER(
            Collections.emptySet()
//            Set.of(
//                    MANAGER_READ,
//                    MANAGER_UPDATE,
//                    MANAGER_DELETE,
//                    MANAGER_CREATE
//            )
    )

    ;

    @Getter
    private final Set<permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}