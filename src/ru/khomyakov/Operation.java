package ru.khomyakov;

enum Operation {
    ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/");

    private String operate;

    Operation(String operate) {
        this.operate = operate;
    }

    public String getOperate() {
        return operate;
    }
}
