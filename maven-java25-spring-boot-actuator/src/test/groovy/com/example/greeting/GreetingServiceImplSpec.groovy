/*
 * Copyright © Carlos Torres (catorres).
 * All rights reserved.
 * This work is the intellectual property of Carlos Torres (catorres) and may not be
 * copied, reproduced, distributed, or used in any form without explicit written
 * permission from the author.
 */

package com.example.greeting

import spock.lang.Specification

class GreetingServiceImplSpec extends Specification {

    GreetingService greetingService

    def setup() {
        greetingService = new GreetingServiceImpl()
    }

    def "greeting returns correct message with name"() {
        when:
        def result = greetingService.greeting('Carlos')

        then:
        result == 'Hello Carlos! Good to see you again'
    }

    def "greeting with different name returns correct message"() {
        when:
        def result = greetingService.greeting('Alice')

        then:
        result == 'Hello Alice! Good to see you again'
    }

    def "greeting preserves case of provided name"() {
        when:
        def result = greetingService.greeting('john')

        then:
        result == 'Hello john! Good to see you again'
        !result.contains('John')
    }

    def "greeting is stateless and returns same result for same input"() {
        when:
        def result1 = greetingService.greeting('Test')
        def result2 = greetingService.greeting('Test')

        then:
        result1 == result2
        result1 == 'Hello Test! Good to see you again'
    }
}
