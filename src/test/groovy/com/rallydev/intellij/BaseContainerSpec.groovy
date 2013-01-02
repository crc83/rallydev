package com.rallydev.intellij

import com.intellij.mock.MockApplication
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.util.text.StringUtil
import com.intellij.testFramework.UsefulTestCase
import com.rallydev.intellij.config.RallyConfig
import org.picocontainer.MutablePicoContainer
import spock.lang.Shared
import spock.lang.Specification

abstract class BaseContainerSpec extends Specification {

    @Shared
    RallyConfig config

    def setup() {
        ApplicationManager.setApplication(new MockApplication(myTestRootDisposable), myTestRootDisposable)
        MutablePicoContainer picoContainer = ((MutablePicoContainer) ApplicationManager.application.getPicoContainer())

        config = new RallyConfig(url: 'http://google.com', userName: 'matt', password: 'monkey')
        picoContainer.registerComponentInstance(RallyConfig.name, config)
    }

    //From com.intellij.testFramework.UsefulTestCase
    protected final Disposable myTestRootDisposable = new Disposable() {
        @Override
        public void dispose() {}

        @Override
        public String toString() {
            String testName = UsefulTestCase.getTestName(this.class.name, false)
            return BaseContainerSpec + (StringUtil.isEmpty(testName) ? "" : ".test" + testName)
        }
    }

}
