package com.tanveer.journalApp.service;


import com.tanveer.journalApp.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;


import java.util.stream.Stream;

public class UserArgumentsSource implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
            Arguments.of(User.builder().userName("Radhe").password("Shyam").build()),
            Arguments.of(User.builder().userName("Bhola").password("Ram").build())
        );
    }
}
