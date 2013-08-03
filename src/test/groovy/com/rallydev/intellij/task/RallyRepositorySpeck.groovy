package com.rallydev.intellij.task

import com.rallydev.intellij.config.RallyConfig
import com.rallydev.intellij.taskold.RallyRepository
import com.rallydev.intellij.wsapi.RallyClient
import spock.lang.Specification

/**
 * Created with IntelliJ IDEA.
 * User: sbelei
 * Date: 05.07.13
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */
class RallyRepositorySpeck extends Specification{

    RallyRepository repo;
    RallyClient client;
    RallyConfig config;

    def setup(){
        config = new RallyConfig();
        config.url = "https://rally1.rallydev.com";
        config.userName ="sbelei@softserveinc.com";
        config.password="B123s9876";

        client = new RallyClient(new URL(config.url),
            config.userName,
            config.password);

        repo = new RallyRepository();
        repo.client = client;
    }

    def cleanup(){

    }


    def "Workspace list retrieved correctly"(){
        when:
            repo.testConnection();
        then:
            notThrown(Exception)
    }
}
