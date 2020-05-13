package com.dzl.blog2.dto.user;

import lombok.Data;

/**
 * Created by rajeevkumarsingh on 19/08/17.
 */
@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
