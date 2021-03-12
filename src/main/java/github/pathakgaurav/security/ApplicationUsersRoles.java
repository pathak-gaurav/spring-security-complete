package github.pathakgaurav.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static github.pathakgaurav.security.ApplicationPermission.*;

public enum ApplicationUsersRoles {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE));

    private final Set<ApplicationPermission> permissions;

    ApplicationUsersRoles(Set<ApplicationPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationPermission> getPermissions() {
        return permissions;
    }
}
