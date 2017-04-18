package com.redhat.demo.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.web.Router;

import com.redhat.demo.engine.TradeRecommendationsEngine;


public class TradeRecommendationsService extends AbstractVerticle {

    @Override
    public void start() {
        SharedData engineData = vertx.sharedData();
        TradeRecommendationsEngine engine = new TradeRecommendationsEngine(engineData);

        Router router = Router.router(vertx);

        router.get("/").handler(engine::nextRecommendation);

        vertx.createHttpServer()
          .requestHandler(router::accept)
          .listen(8080);

    } // start
   
}
