package com.news_provider.Service;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class NewsService {
    @Value("${news.api_key}")
    private String newsApiKey;

    public List<String> getNews() throws Exception {
        NewsApiClient client = new NewsApiClient(this.newsApiKey);
        List<String> headers = new ArrayList<>();
        CompletableFuture<List<String>> future = new CompletableFuture<>();

        client.getEverything(
            new EverythingRequest.Builder()
                .q("ukraine")
                .language("en")
                .build()
            ,
            new NewsApiClient.ArticlesResponseCallback() {
                @Override
                public void onSuccess(ArticleResponse response) {
                    headers.addAll(
                        response.getArticles()
                            .stream()
                            .map(a -> a.getTitle().concat(". Glory to Ukraine!"))
                            .toList()
                    );
                    future.complete(headers);
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println(ex.getMessage());
                }
            }
        );

        return future.get();
    }
}