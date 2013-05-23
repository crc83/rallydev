package com.rallydev.intellij.task.ui

import com.google.gson.JsonSyntaxException
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.TestDialog
import com.rallydev.intellij.BaseContainerSpec
import com.rallydev.intellij.SpecUtils
import com.rallydev.intellij.wsapi.ApiResponse
import com.rallydev.intellij.wsapi.RallyClient
import org.apache.commons.httpclient.auth.InvalidCredentialsException
import spock.lang.Shared

class TestConnectionButtonListenerSpec extends BaseContainerSpec {

    List<String> messages
    @Shared
    RepositoryEditor mockForm
    @Shared
    RallyClient mockClient

    def setup() {
        messages = []
        mockForm = Mock(RepositoryEditor)
        mockForm.showErrorDialog(_ ,_ ) >> {msg, title ->
            messages << msg}
        mockForm.showMessageDialog(_ ,_ ) >> {msg, title ->
            messages << msg}
        mockClient = Mock(RallyClient)
    }

    def "Message on success"() {
        given:
        1 * mockClient.makeRequest(_) >> { new ApiResponse(SpecUtils.minimalResponseJson) }
        mockForm.getClient() >> { mockClient }

        and:
        TestConnectionButtonListener listener = new TestConnectionButtonListener(mockForm)

        when:
        listener.actionPerformed(null)

        then:
        messages.first().toLowerCase().contains('success')
    }

    def "Message for auth failure"() {
        given:
        1 * mockClient.makeRequest(_) >> { throw new InvalidCredentialsException() }
        mockForm.getClient() >> { mockClient }

        and:
        TestConnectionButtonListener listener = new TestConnectionButtonListener(mockForm)

        when:
        listener.actionPerformed(null)

        then:
        messages.first().toLowerCase().contains('invalid credentials')
    }

    def "Message for bad response"() {
        given:
        1 * mockClient.makeRequest(_) >> { throw new JsonSyntaxException('Wrong') }

        mockForm.getClient() >> { mockClient }

        and:
        TestConnectionButtonListener listener = new TestConnectionButtonListener(mockForm)

        when:
        listener.actionPerformed(null)

        then:
        messages.first().toLowerCase().contains('url')
        messages.first().toLowerCase().contains('incorrect')
    }

}
