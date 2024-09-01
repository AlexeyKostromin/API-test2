package api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostBodyOptions {
    FIRST_BODY_TEXT("Discuss the importance of a strong online presence for businesses and individuals. Provide tips on creating a professional website, optimizing for search engines, and building a social media following."),
    SECOND_BODY_TEXT("Explore the challenges of online marketing and offer strategies for overcoming them. Cover topics like content creation, advertising, and analytics."),
    THIRD_BODY_TEXT("Discuss the ethical considerations of online behavior and the importance of responsible digital citizenship.");

    private final String value;
}
