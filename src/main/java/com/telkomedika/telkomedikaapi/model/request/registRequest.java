package com.telkomedika.telkomedikaapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class registRequest {
        private String name;
        private String username;
        private String email;
        private String password;
//        private roleEntity role;

    }
