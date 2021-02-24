package com.dosx.javase.gateway.filter;

import com.dosx.javase.common.utils.UniResponse;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.nio.charset.StandardCharsets;


import reactor.core.publisher.Mono;

/**
 * 权限验证过滤器
 *
 * @author lucky us
 */
@Component
public class AccessFilter implements GlobalFilter, Ordered
{

    final RedisTemplate redisTemplate;

    @Autowired
    public AccessFilter(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {

        String token = exchange.getRequest().getQueryParams().getFirst("access_token");

        // 验证token
        if(null == token)
        {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            Gson gson = new Gson();
            String message = gson.toJson(UniResponse.error());
            DataBuffer buffer = response.bufferFactory().wrap(message.getBytes());
            return response.writeWith(Mono.just(buffer));
        }



        return chain.filter(exchange);
    }

    @Override
    public int getOrder()
    {
        return 0;
    }
}
