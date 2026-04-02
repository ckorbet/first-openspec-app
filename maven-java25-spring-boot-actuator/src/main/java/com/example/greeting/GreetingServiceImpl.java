/*
 * Copyright © Carlos Torres (catorres).
 * All rights reserved.
 * This work is the intellectual property of Carlos Torres (catorres) and may not be
 * copied, reproduced, distributed, or used in any form without explicit written
 * permission from the author.
 */

package com.example.greeting;

import org.springframework.stereotype.Service;

/**
 * Implementation of the GreetingService that generates personalized greeting messages.
 * 
 * This stateless service takes a name and returns a formatted greeting string.
 * The implementation preserves the exact casing of the provided name in the output.
 */
@Service
public class GreetingServiceImpl implements GreetingService {

    /**
     * Generates a personalized greeting message for the provided name.
     *
     * @param theName The name of the person to greet. Case is preserved as provided.
     * @return A greeting message in format: "Hello {name}! Good to see you again"
     */
    @Override
    public String greeting(String theName) {
        return String.format("Hello %s! Good to see you again", theName);
    }
}
