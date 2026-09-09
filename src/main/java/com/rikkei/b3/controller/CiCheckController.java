package com.rikkei.b3.controller;

import com.rikkei.b3.dto.ApiResponse;
import com.rikkei.b3.dto.CiStatusDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/ci")
public class CiCheckController {

    @GetMapping("/status")
    public ApiResponse<CiStatusDto> getCiStatus() {
        CiStatusDto status = new CiStatusDto(
            "GitHub Actions CI Pipeline",
            "Java 17 OpenJDK",
            "Gradle 8.5",
            true,
            "b3-0.0.1-SNAPSHOT.jar",
            LocalDateTime.now()
        );
        return ApiResponse.success("CI Pipeline configuration active and verified", status);
    }
}
