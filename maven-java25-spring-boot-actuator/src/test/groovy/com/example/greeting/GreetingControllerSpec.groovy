/*
 * Copyright © Carlos Torres (catorres).
 * All rights reserved.
 * This work is the intellectual property of Carlos Torres (catorres) and may not be
 * copied, reproduced, distributed, or used in any form without explicit written
 * permission from the author.
 */

package com.example.greeting

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(GreetingController)
class GreetingControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @MockBean
    GreetingService greetingService

    def "GET /greet with theName=Carlos returns 200 OK with greeting message"() {
        given:
        greetingService.greeting('Carlos') >> 'Hello Carlos! Good to see you again'

        when:
        def response = mockMvc.perform(get('/greet').param('theName', 'Carlos'))

        then:
        response.andExpect(status().isOk())
                .andExpect(content().contentType('text/plain;charset=UTF-8'))
                .andExpect(content().string('Hello Carlos! Good to see you again'))
    }

    def "GET /greet with theName=Alice returns 200 OK with greeting message"() {
        given:
        greetingService.greeting('Alice') >> 'Hello Alice! Good to see you again'

        when:
        def response = mockMvc.perform(get('/greet').param('theName', 'Alice'))

        then:
        response.andExpect(status().isOk())
                .andExpect(content().contentType('text/plain;charset=UTF-8'))
                .andExpect(content().string('Hello Alice! Good to see you again'))
    }

    def "GET /greet delegates to mocked GreetingService"() {
        given:
        def testName = 'TestName'
        greetingService.greeting(testName) >> 'Hello TestName! Good to see you again'

        when:
        mockMvc.perform(get('/greet').param('theName', testName))

        then:
        1 * greetingService.greeting(testName)
    }

    def "GET /greet returns 200 OK with service-provided message"() {
        given:
        greetingService.greeting('Echo') >> 'Hello Echo! Good to see you again'

        when:
        def response = mockMvc.perform(get('/greet').param('theName', 'Echo'))

        then:
        response.andExpect(status().isOk())
    }

    def "Response content-type is text/plain"() {
        given:
        greetingService.greeting('TestUser') >> 'Hello TestUser! Good to see you again'

        when:
        def response = mockMvc.perform(get('/greet').param('theName', 'TestUser'))

        then:
        response.andExpect(content().contentType('text/plain;charset=UTF-8'))
    }

    def "Controller depends on GreetingService interface not concrete implementation"() {
        expect:
        greetingService instanceof GreetingService
    }
}
