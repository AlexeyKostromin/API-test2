package api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostTitleOptions {
    FIRST_TITLE_TEXT("Navigating the Digital Landscape: Essential Tips for Online Success"),
    SECOND_TITLE_TEXT("Unlocking the Power of AI: How to Leverage Artificial Intelligence in Your Business"),
    THIRD_TITLE_TEXT("Building a Strong Personal Brand: Your Guide to Online Reputation Management");

    private final String value;
}
