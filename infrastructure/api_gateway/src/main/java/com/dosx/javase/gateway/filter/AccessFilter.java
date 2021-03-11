package com.dosx.javase.gateway.filter;

import com.dosx.javase.common.utils.UniResponse;
import com.dosx.javase.gateway.service.TokenService;
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

import reactor.core.publisher.Mono;

/**
 * 权限验证过滤器
 *
 * @author lucky us
 */
@Component
public class AccessFilter implements GlobalFilter, Ordered
{

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    TokenService tokenService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {

        String token = exchange.getRequest().getHeaders().getFirst("access_token");

        // 验证token
        if(null == token)
        {
            return cutOnErrorWith(exchange, "无访问令牌, 请登录");
        }

        boolean tokenValid = false;
        try {
            tokenValid = tokenService.ifTokenValid(token);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (!tokenValid) {
            return cutOnErrorWith(exchange, "无效令牌, 请登录重试");
        }

        return chain.filter(exchange);
    }

    private Mono<Void> cutOnErrorWith(ServerWebExchange exchange, String errorMessage) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        Gson gson = new Gson();
        String message = gson.toJson(UniResponse.error().message(errorMessage));
        DataBuffer buffer = response.bufferFactory().wrap(message.getBytes());
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder()
    {
        return 0;
    }
}
