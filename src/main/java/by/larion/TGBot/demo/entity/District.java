package by.larion.TGBot.demo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum District {
    Октябрьский (1), Ленинский (2), Гродненский (3);

    private final int id;
}
