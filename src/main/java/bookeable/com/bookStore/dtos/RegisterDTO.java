package bookeable.com.bookStore.dtos;

import bookeable.com.bookStore.enums.UserRole;

public record RegisterDTO(String email, String password, String name, UserRole role) {
}
