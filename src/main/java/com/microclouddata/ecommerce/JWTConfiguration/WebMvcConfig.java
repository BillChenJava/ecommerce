package com.microclouddata.ecommerce.JWTConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final long MAX_AGES_SECS =3600;

    /**
     * 跨域支撑
     * @param registry
     * 作为一个后端开发，我们经常遇到的一个问题就是需要配置 CORS，好让我们的前端能够访问到我们的 API，并且不让其他人访问。而在 Spring 中，我们见过很多种 CORS 的配置，很多资料都只是告诉我们可以这样配置、可以那样配置，但是这些配置有什么区别？
     *
     * CORS 是什么
     * 首先我们要明确，CORS 是什么，以及规范是如何要求的。这里只是梳理一下流程，具体的规范请看 这里。
     *
     * CORS 全称是 Cross-Origin Resource Sharing，直译过来就是跨域资源共享。要理解这个概念就需要知道域、资源和同源策略这三个概念。
     *
     * 域，指的是一个站点，由 protocal、host 和 port 三部分组成，其中 host 可以是域名，也可以是 ip ；port 如果没有指明，则是使用 protocal 的默认端口
     * 资源，是指一个 URL 对应的内容，可以是一张图片、一种字体、一段 HTML 代码、一份 JSON 数据等等任何形式的任何内容
     * 同源策略，指的是为了防止 XSS，浏览器、客户端应该仅请求与当前页面来自同一个域的资源，请求其他域的资源需要通过验证。
     * 了解了这三个概念，我们就能理解为什么有 CORS 规范了：从站点 A 请求站点 B 的资源的时候，由于浏览器的同源策略的影响，这样的跨域请求将被禁止发送；为了让跨域请求能够正常发送，我们需要一套机制在不破坏同源策略的安全性的情况下、允许跨域请求正常发送，这样的机制就是 CORS。
     *
     * 预检请求
     * 在 CORS 中，定义了一种预检请求，即 preflight request，当实际请求不是一个 简单请求 时，会发起一次预检请求。预检请求是针对实际请求的 URL 发起一次 OPTIONS 请求，并带上下面三个 headers ：
     *
     * Origin：值为当前页面所在的域，用于告诉服务器当前请求的域。如果没有这个 header，服务器将不会进行 CORS 验证。
     * Access-Control-Request-Method：值为实际请求将会使用的方法
     * Access-Control-Request-Headers：值为实际请求将会使用的 header 集合
     * 如果服务器端 CORS 验证失败，则会返回客户端错误，即 4xx 的状态码。
     *
     * 否则，将会请求成功，返回 200 的状态码，并带上下面这些 headers：
     *
     * Access-Control-Allow-Origin：允许请求的域，多数情况下，就是预检请求中的 Origin 的值
     * Access-Control-Allow-Credentials：一个布尔值，表示服务器是否允许使用 cookies
     * Access-Control-Expose-Headers：实际请求中可以出现在响应中的 headers 集合
     * Access-Control-Max-Age：预检请求返回的规则可以被缓存的最长时间，超过这个时间，需要再次发起预检请求
     * Access-Control-Allow-Methods：实际请求中可以使用到的方法集合
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("HEAD","OPTIONS","GET","POST")
                .maxAge(MAX_AGES_SECS);
    }
}
