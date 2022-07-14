package org.exeplm.filterFactory;

import org.exeplm.exception.IllegalTokenException;
import org.exeplm.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class TokenGatewayFilterFactory extends AbstractGatewayFilterFactory<TokenGatewayFilterFactory.Config> {

    @Autowired
    private RedisUtil redisUtil;

//    需要实现构造方法
    public TokenGatewayFilterFactory() {
        super(Config.class);
    }

    public static final String PARTS_KEY = "parts";
    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
                List<String> token = queryParams.get("token");
                if (token==null){
                   throw new IllegalTokenException("未携带token");
                }
                 if (!redisUtil.hasKey(token.get(0))){
                     throw new IllegalTokenException("非法token!");
                 }
                //通过过滤器
                return chain.filter(exchange);
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(PARTS_KEY);
    }

    public static class Config {

        private int parts;

        public int getParts() {
            return parts;
        }

        public void setParts(int parts) {
            this.parts = parts;
        }

    }
}
