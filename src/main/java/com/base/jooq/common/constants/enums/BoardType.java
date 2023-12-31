package com.base.jooq.common.constants.enums;

public enum BoardType {

    NOTICE("게시판"),
    NORMAL("일반"),
    AD("광고");

    private final String name;

    public String getName() {
        return name;
    }

    BoardType(String name) {
         this.name = name;
    }
}
