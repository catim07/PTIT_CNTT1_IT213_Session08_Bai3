package com.rikkei.b3.dto;

import java.time.LocalDateTime;

public record CiStatusDto(
    String pipelineName,
    String jdkVersion,
    String buildTool,
    boolean testsPassed,
    String artifactGenerated,
    LocalDateTime timestamp
) {}
