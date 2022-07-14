package org.exeplm.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.exeplm.exception.IllegalTokenException;
import org.exeplm.utils.TaoResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 *   当网关出现异常会进这个类的handle方法
 */
@Configuration
public class GlobalExceptionConfiguration implements ErrorWebExceptionHandler {
    //日志对象
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionConfiguration.class);


    @Override //参数1: request response   ex:出现异常时异常对象
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
//        Map<String, Object> result = new HashMap<>();

        //1.获取响应对象
        ServerHttpResponse response = exchange.getResponse();

        //2.response是否结束  多个异常处理时候
        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        //2.设置响应头类型
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        //3.设置响应状态吗
        if (ex instanceof IllegalTokenException) {
            response.setStatusCode(HttpStatus.FORBIDDEN);
        } else {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //4.设置响应内容
        return response
                .writeWith(Mono.fromSupplier(() -> {
                    DataBufferFactory bufferFactory = response.bufferFactory();
//                    result.put("msg", ex.getMessage());
//                    result.put("code", 403);
//                    result.put("date",null);
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
//                        return bufferFactory.wrap(objectMapper.writeValueAsBytes(result));
                        TaoResult build = TaoResult.build(403, ex.getMessage());

                        return bufferFactory.wrap(objectMapper.writeValueAsBytes(build));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return null;
                    }
                }));
    }
}
