package com.rallydev.intellij.task

import com.rallydev.intellij.task.ui.RepositoryEditor
import com.rallydev.intellij.task.ui.RepositoryEditorImpl
import spock.lang.Specification

/**
 * Created with IntelliJ IDEA.
 * User: sbelei
 * Date: 25.05.13
 * Time: 15:06
 * To change this template use File | Settings | File Templates.
 */
class RepositoryEditorSpec extends Specification {

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
}
