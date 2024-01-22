package ao.tdados.ms.microservice_spring_boot_users.enums;

public enum UserRoles {
    ROOT("root"),
    ADMIN("admin"),

    USER("user");

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;

    UserRoles(String role) {
        this.role = role;
    }


}
