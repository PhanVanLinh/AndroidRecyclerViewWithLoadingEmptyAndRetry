package com.toong.recyclerviewwithemptyandretry.base;

public class NetworkState {

    private NetworkState() {
    }

    public static class Loading extends NetworkState {

    }

    public static class Empty extends NetworkState {

    }

    public static class Failed extends NetworkState {
        String errorMessage;

        public Failed(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

    public static class Success extends NetworkState {

    }

    public static class Complete extends NetworkState {

    }
}
