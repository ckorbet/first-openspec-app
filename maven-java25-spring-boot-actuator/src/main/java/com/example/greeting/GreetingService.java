/*
 * Copyright © Carlos Torres (catorres).
 * All rights reserved.
 * This work is the intellectual property of Carlos Torres (catorres) and may not be
 * copied, reproduced, distributed, or used in any form without explicit written
 * permission from the author.
 */

package com.example.greeting;

/**
 * Service interface for generating personalized greeting messages.
 * 
 * Defines the contract for greeting generation implementation.
 * Implementations should produce greeting messages in the format:
 * "Hello {name}! Good to see you again"
 */
public interface GreetingService {

    /**
     * Generates a personalized greeting message for the provided name.
     *
     * @param theName The name of the person to greet. Case is preserved as provided.
     * @return A greeting message in format: "Hello {name}! Good to see you again"
     */
    String greeting(String theName);
}
