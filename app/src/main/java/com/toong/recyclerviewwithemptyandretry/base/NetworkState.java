package com.toong.recyclerviewwithemptyandretry.base;

import android.support.annotation.Nullable;

public class NetworkState {

    private NetworkState() {
    }

    public static class Loading extends NetworkState {

    }

    public static class Empty extends NetworkState {

    }

    public static class Failed extends NetworkState {
        @Nullable
        private String errorMessage;

        public Failed(@Nullable String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @Nullable
        public String getErrorMessage() {
            return errorMessage;
        }
    }

    public static class Success extends NetworkState {

    }

    public static class Complete extends NetworkState {

    }
}
