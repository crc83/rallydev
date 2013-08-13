package com.rallydev.intellij.task;

import com.intellij.tasks.Comment;
import com.intellij.tasks.Task;
import com.intellij.tasks.TaskType;
import com.intellij.tasks.impl.BaseRepository;
import com.intellij.tasks.impl.BaseRepositoryImpl;
import com.intellij.util.xmlb.annotations.Tag;
import com.rallydev.intellij.config.RallyConfig;
import com.rallydev.rest.RallyRestApi;
import org.jetbrains.annotations.Nullable;
import org.sbelei.rally.domain.BasicEntity;
import org.sbelei.rally.provider.ProviderFasade;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

@Tag("Rally")
public class RallyRepository extends BaseRepositoryImpl {


    public String workspaceId = "41593629";
    public String projectId = "9216950819";
//    public String iterationId;
//    private boolean filterByProject;
//    private boolean filterByWorkspace;

    public RallyRestApi client;
    ProviderFasade provider;

    @SuppressWarnings("unused")
    public RallyRepository() {
        URI uri = null;
        try {
            uri = new URI("https://rally1.rallydev.com");
        } catch (URISyntaxException uie) {
            uie.printStackTrace();
        }
        client = new RallyRestApi(
            uri,//                new URI(RallyConfig.getInstance().url),
            "sbelei@softserveinc.com",//                RallyConfig.getInstance().userName,
            "S123b9876"//                RallyConfig.getInstance().password
        );
        provider = new ProviderFasade(client);
        provider.setProjectId(projectId);
        provider.setWorkspaceId(workspaceId);
        provider.setUserLogin(RallyConfig.getInstance().userName);
        provider.setUseCurrentIteration(true);
        provider.setOnlyMine(true);
    }

    public RallyRepository(RallyRepository other) {
        super(other);
        this.workspaceId = other.workspaceId;
        this.projectId = other.projectId;
    }

    @Override
    public BaseRepository clone() {
        return new RallyRepository(this);
    }

    @Override
    public Task[] getIssues(@Nullable String query, int max, long since) throws Exception {
        URI uri = null;
        try {
            uri = new URI("https://rally1.rallydev.com");
        } catch (URISyntaxException uie) {
            uie.printStackTrace();
        }
        client = new RallyRestApi(
                uri,//                new URI(RallyConfig.getInstance().url),
                "sbelei@softserveinc.com",//                RallyConfig.getInstance().userName,
                "S123b9876"//                RallyConfig.getInstance().password
        );
        provider = new ProviderFasade(client);
        provider.setProjectId(projectId);
        provider.setWorkspaceId(workspaceId);
        provider.setUserLogin(RallyConfig.getInstance().userName);
        provider.setUseCurrentIteration(true);
        provider.setOnlyMine(true);

        Collection<BasicEntity> rallyTasks = provider.fetchStoriesAndDefects();
        Task[] result = new Task[rallyTasks.size()];
        int i = 0;
        for (BasicEntity entity : rallyTasks) {
            Task task = new RallyTask(entity);
            result[i] = task;
            i++;
        }
        return result;
    }

    @Nullable
    @Override
    public Task findTask(String id) throws Exception {
        return null;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void testConnection() throws Exception {
//        new ConnectionTest(getClient()).doTest();
    }

    public RallyRestApi getClient() throws MalformedURLException {
        return client;
    }

    //Url is used in the server list, overriding to return a display name instead.
    @Override
    public String getUrl() {
        return RallyConfig.getInstance().url + " (" + workspaceId +")";
    }

}
