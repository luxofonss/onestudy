package com.quyennv.lms.filter;

import com.quyennv.lms.constant.RequestKeyConstant;
import com.quyennv.lms.constant.UrlConstant;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.UUID;

import static com.quyennv.lms.constant.RequestKeyConstant.THREAD_REQUEST_ID;
import static com.quyennv.lms.constant.RequestKeyConstant.X_REQUEST_ID;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class AppCorsFilter implements Filter {

    @SneakyThrows
    @SuppressWarnings("unchecked")
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        // Check uri
        if (request.getRequestURI().contains(UrlConstant.HEALTH_CHECK_URL)) {
            chain.doFilter(req, res);
            return;
        }
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        String requestId = "";

        try {
            if (isMultipart) {
                MultipartResolver resolver =
                        new CommonsMultipartResolver(request.getSession().getServletContext());
                MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);

                requestId = multipartRequest.getHeader(X_REQUEST_ID);
                if (requestId == null || requestId.isEmpty()) {
                    requestId = UUID.randomUUID().toString();
                }
                ThreadContext.put(THREAD_REQUEST_ID, requestId);

//                multipartRequest.setAttribute();
//                multipartRequest.setAttribute(CommonJsonKey.OPS_USER_NAME,
//                        accessTokenRequestDto.getUser_name());
                multipartRequest.setAttribute(RequestKeyConstant.REQUEST_BODY, multipartRequest);
                multipartRequest.setAttribute(RequestKeyConstant.REQUEST_ID, requestId);
                chain.doFilter(multipartRequest, response);
                ThreadContext.clearAll();
            } else {
            ApiKeyVerifyRequestWrapper requestWrapper = new ApiKeyVerifyRequestWrapper(request);
            JSONParser parser = new JSONParser();
            JSONObject dataRequest = ObjectUtils.isEmpty(requestWrapper.getBody())
                ? new JSONObject()
                : (JSONObject) parser.parse(requestWrapper.getBody());
            requestId = requestWrapper.getHeader(X_REQUEST_ID);
            if (requestId == null || requestId.isEmpty()) {
                requestId = UUID.randomUUID().toString();
            }
            ThreadContext.put(THREAD_REQUEST_ID, requestId);
            dataRequest.put(RequestKeyConstant.REQUEST_ID, requestId);
            request.setAttribute(RequestKeyConstant.REQUEST_ID, requestId);

            dataRequest.put(RequestKeyConstant.API_KEY, requestWrapper.getHeader(RequestKeyConstant.X_API_KEY));
            request.setAttribute(RequestKeyConstant.API_KEY, requestWrapper.getHeader(RequestKeyConstant.X_API_KEY));

            dataRequest.put(RequestKeyConstant.API_SECRET, requestWrapper.getHeader(RequestKeyConstant.X_API_SECRET));
            request.setAttribute(RequestKeyConstant.API_SECRET, requestWrapper.getHeader(RequestKeyConstant.X_API_SECRET));

            dataRequest.put(RequestKeyConstant.URI, request.getRequestURI());
            request.setAttribute(RequestKeyConstant.URI, requestWrapper.getHeader(RequestKeyConstant.URI));

            request.setAttribute(RequestKeyConstant.REQUEST_PARAMETERS, req.getParameterMap());
            request.setAttribute(RequestKeyConstant.REQUEST_BODY, dataRequest);
            requestWrapper.setBody(dataRequest.toString());

            chain.doFilter(requestWrapper, res);
            ThreadContext.clearAll();
            }
        } catch (Exception e) {
            log.error(e.toString(), e);
            response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
            ThreadContext.clearAll();
        } catch (Throwable e) {
            log.error(e.toString(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ThreadContext.clearAll();
        }
    }
}