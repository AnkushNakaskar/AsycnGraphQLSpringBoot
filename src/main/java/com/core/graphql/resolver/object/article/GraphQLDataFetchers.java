package com.core.graphql.resolver.object.article;

import com.core.graphql.bean.article.Article;
import com.core.graphql.repo.article.ArticleRepository;
import com.core.graphql.repo.comment.CommentRepository;
import com.core.graphql.repo.profile.ProfileRepository;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class GraphQLDataFetchers {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private CommentRepository commentRepository;

    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String articleId = dataFetchingEnvironment.getArgument("id");
            Optional<Article> optionalArticle = articleRepository.findById(Long.parseLong(articleId));
            return optionalArticle.get();
        };
    }

    public DataFetcher getAllArticleDataFetcher() {

        return environment -> CompletableFuture.supplyAsync(
                ()->{
                    log.info("Fetching all the information for Article.....>>>>>><<<<<<>>>>>><<<<>>>>>");
                    List<Article> list = articleRepository.findAll();
                    return list;
                });

//        return dataFetchingEnvironment -> {
//            List<Article> list = articleRepository.findAll();
//            return list;
//        };
    }

    public DataFetcher getAuthorDataFetcher() {
//        dataFetchingEnvironment -> CompletableFuture.supplyAsync(
//                ()->{
//                    Article article = dataFetchingEnvironment.getSource();
//                    log.info("Fetching Author for Article :: "+article.getId());
//                    return profileRepository.findById(article.getAuthorId());
//                }
//        );
//        return dataFetchingEnvironment -> {
//            Article article = dataFetchingEnvironment.getSource();
//            log.info("Fetching Author for Article :: "+article.getId());
//            return profileRepository.findById(article.getAuthorId());
//        };
        return environment -> CompletableFuture.supplyAsync(
                ()->{
                    Article article = environment.getSource();
                    log.info("Fetching Author for Article :: "+article.getId());
                    return profileRepository.findById(article.getAuthorId());
                });
    }
    public DataFetcher getCommentsDataFetcher() {

        return environment -> CompletableFuture.supplyAsync(
                ()->{
                    Article article = environment.getSource();
                    log.info("Fetching Comments for Article :: "+article.getId());
                    return commentRepository.findByArticleId(article.getAuthorId());
                });

//        return dataFetchingEnvironment -> {
//            Article article = dataFetchingEnvironment.getSource();
//            log.info("Fetching Comments for Article :: "+article.getId());
//            return commentRepository.findByArticleId(article.getAuthorId());
//        };
    }

}
