package github.pathakgaurav.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static github.pathakgaurav.security.ApplicationPermission.COURSE_READ;
import static github.pathakgaurav.security.ApplicationPermission.COURSE_WRITE;
import static github.pathakgaurav.security.ApplicationPermission.STUDENT_READ;
import static github.pathakgaurav.security.ApplicationPermission.STUDENT_WRITE;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
    ADMIN_TRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));

    private final Set<ApplicationPermission> permissions;

    ApplicationUserRole(Set<ApplicationPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> grantedAuthorities() {
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = getPermissions().stream().map(
                permission -> new SimpleGrantedAuthority(permission.name())).collect(Collectors.toSet());
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return simpleGrantedAuthorities;
    }
}
