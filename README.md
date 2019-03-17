# AsycnGraphQLSpringBoot
This project fetch author and Comments Asynchronously.Track logs to see that (Thread name) 
URL : http://localhost:8082/graphiql?operationName=AllArticles&query=query%20AllArticles%20%7B%0A%20%20articles%20%7B%0A%20%20%20%20id%0A%20%20%20%20title%0A%20%20%20%20author%20%7B%0A%20%20%20%20%20%20id%0A%20%20%20%20%20%20username%0A%20%20%20%20%7D%0A%20%20%20%20comments%20%7B%0A%20%20%20%20%20%20id%0A%20%20%20%20%20%20text%0A%20%20%20%20%7D%0A%20%20%7D%0A%7D%0A&variables=%7B%0A%20%20%22articleId%22%3A%20%221%22%0A%7D

We used completedFuture.supplyAsync  in Fetchers
Logs will be like this :

ForkJoinPool.commonPool-worker- for each article is different with Comments as well as Author also

2019-03-17 13:46:52.037 [ INFO] [12093-(ForkJoinPool.commonPool-worker-1)] [com.core.graphql.resolver.object.article.GraphQLDataFetchers.lambda$null$1(GraphQLDataFetchers.java:42)]  Fetching all the information for Article.....>>>>>><<<<<<>>>>>><<<<>>>>>
2019-03-17 13:46:52.169 [ INFO] [12093-(ForkJoinPool.commonPool-worker-2)] [com.core.graphql.resolver.object.article.GraphQLDataFetchers.lambda$null$3(GraphQLDataFetchers.java:69)]  Fetching Author for Article :: 1
2019-03-17 13:46:52.170 [ INFO] [12093-(ForkJoinPool.commonPool-worker-3)] [com.core.graphql.resolver.object.article.GraphQLDataFetchers.lambda$null$5(GraphQLDataFetchers.java:78)]  Fetching Comments for Article :: 1
2019-03-17 13:46:52.171 [ INFO] [12093-(ForkJoinPool.commonPool-worker-5)] [com.core.graphql.resolver.object.article.GraphQLDataFetchers.lambda$null$5(GraphQLDataFetchers.java:78)]  Fetching Comments for Article :: 2
2019-03-17 13:46:52.171 [ INFO] [12093-(ForkJoinPool.commonPool-worker-4)] [com.core.graphql.resolver.object.article.GraphQLDataFetchers.lambda$null$3(GraphQLDataFetchers.java:69)]  Fetching Author for Article :: 2


Check project SpringGraphQL for more understanding from spring boot starter for graphql point of view.
Spring boot starter for graph ql does not support async in Resolver,So we used basic core functionality in data fether.

The CompletableFuture is only useful when you have  AsyncExecutionStrategy in creating 
GraphQL.newGraphQL(graphQLSchema).queryExecutionStrategy(new AsyncExecutionStrategy()).build().

By default queryExecutionStrategy is "AsyncExecutionStrategy" hence our code is working there are three other Strategies also.
