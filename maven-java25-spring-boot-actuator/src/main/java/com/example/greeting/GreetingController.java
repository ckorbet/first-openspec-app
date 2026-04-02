/*
 * Copyright © Carlos Torres (catorres).
 * All rights reserved.
 * This work is the intellectual property of Carlos Torres (catorres) and may not be
 * copied, reproduced, distributed, or used in any form without explicit written
 * permission from the author.
 */

package com.example.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * REST controller for greeting endpoints.
 * 
 * Provides HTTP endpoints for generating personalized greeting messages.
 * All endpoints are documented via OpenAPI 3.0 annotations for Swagger UI integration.
 */
@RestController
@RequestMapping("/greet")
public class GreetingController {

    private final GreetingService greetingService;

    /**
     * Constructs the GreetingController with dependency injection of the GreetingService.
     *
     * @param greetingService The GreetingService implementation (injected by Spring)
     */
    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    /**
     * GET endpoint that returns a personalized greeting message.
     *
     * @param theName The name of the person to greet (required query parameter)
     * @return A greeting message in format: "Hello {name}! Good to see you again"
     */
    @GetMapping
    @Operation(
        summary = "Get greeting message",
        description = "Returns a personalized greeting for the provided name"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful greeting message",
            content = @Content(
                mediaType = "text/plain",
                schema = @Schema(
                    type = "string",
                    example = "Hello Carlos! Good to see you again"
                )
            )
        )
    })
    public String getGreeting(
        @RequestParam("theName")
        @Parameter(
            description = "Name of the person to greet",
            example = "Carlos"
        )
        String theName
    ) {
        return greetingService.greeting(theName);
    }
}
