package github.pathakgaurav.security;

public enum ApplicationPermission {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String applicationPermission;

    ApplicationPermission(String applicationPermission) {
        this.applicationPermission = applicationPermission;
    }

    public String getApplicationPermission() {
        return applicationPermission;
    }
}
