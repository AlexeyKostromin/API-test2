package api.services;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModulePath {

    TEST_API_POSTS("/posts"),
    TEST_API_COMMENTS("/comments"),
    TEST_API_USERS("/users");

    private final String name;

}
