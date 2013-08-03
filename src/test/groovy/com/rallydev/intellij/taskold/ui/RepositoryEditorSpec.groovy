package com.rallydev.intellij.taskold.ui

import spock.lang.Unroll

class RepositoryEditorSpec {

    def "Form correctly initialized after createComponent"() {
        given:
        RepositoryEditor form = new RepositoryEditorImpl()

        when:
        form.createComponent()

        then:
        form.url.text == config.url
        form.userName.text == config.userName
        form.password.text == config.password
    }

    def "isModified false when initially loaded"() {
        given:
        RepositoryEditor form = new RepositoryEditorImpl()
        form.createComponent()

        expect:
        !form.modified
    }

    @Unroll
    def "isModified changes with field values"() {
        when:
        RepositoryEditor form = new RepositoryEditorImpl()
        form.createComponent()
        form[field].text = newValue

        then:
        form.isModified()

        where:
        field << ['url', 'userName', 'password']
        newValue << ['http://yahoo.com', 'bob', 'newPassword']
    }

    def "Apply changes config values"() {
        given:
        String url = 'http://yahoo'
        String userName = 'newUserName'
        String password = 'newPassword'

        and:
        RepositoryEditor form = new RepositoryEditorImpl()
        form.createComponent()
        form.url.text = url
        form.userName.text = userName
        form.password.text = password

        when:
        form.apply()

        then:
        config.url == url
        config.userName == userName
        config.password == password
    }

    def "Reset sets to initial config values"() {
        RepositoryEditor form = new RepositoryEditorImpl()
        form.createComponent()
        form.url.text = 'http://yahoo.com'
        form.userName.text = 'newUserName'
        form.password.text = 'newPassword'

        when:
        form.reset()

        then:
        form.url.text == config.url
        form.userName.text == config.userName
        form.password.text == config.password
    }

}
