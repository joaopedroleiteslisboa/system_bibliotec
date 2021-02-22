package com.system.bibliotec.service.resttemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

public class RequestResponseLoggingInterceptorRestTemplate implements ClientHttpRequestInterceptor {

    public static final Logger requestLogger = LoggerFactory
            .getLogger(RequestResponseLoggingInterceptorRestTemplate.class);
    public static final Logger responseLogger = LoggerFactory
            .getLogger(RequestResponseLoggingInterceptorRestTemplate.class);

    private String caminhoLog;

    public RequestResponseLoggingInterceptorRestTemplate(String caminhoLog) {
        this.caminhoLog = caminhoLog;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(request, response);
        return response;
    }

    protected void logRequest(HttpRequest request, byte[] body) throws FileNotFoundException {

        StringBuilder builder = new StringBuilder("Enviando ").append(request.getMethod()).append(" enviando para ")
                .append(request.getURI());


        String bodyText = new String(body, determineCharset(request.getHeaders()));
        builder.append(": [").append(bodyText).append("]");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String dataHora = dateFormat.format(new Date());

        String hostName = (request.getURI() != null && request.getURI().getHost() != null)
                ? request.getURI().getHost().toString()
                : "";

        String fileName = "request".concat("-").concat(hostName) + "-" + dataHora + "-" + ".json";
        String currentFolder = format.format(new Date());
        String basePath = caminhoLog + File.separator + currentFolder;
        File basePathFile = new File(basePath);

        if (!basePathFile.exists()) {
            basePathFile.mkdirs();
        }

        setAcessoFile(basePathFile);
        File jsonFile = new File(basePath + File.separator + fileName);
        setAcessoFile(jsonFile);
        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(jsonFile), StandardCharsets.UTF_8))) {
            // É AutoCloseable

            out.write(builder.toString());

        } catch (IOException e) {

            e.printStackTrace();
        }


    }

    protected void logResponse(HttpRequest request, ClientHttpResponse response) {

        try {
            StringBuilder builder = new StringBuilder(" Response da Requisição \"").append(response.getRawStatusCode()).append(" ")
                    .append(response.getStatusText()).append("\" Resposta da requisição para ").append(request.getMethod())
                    .append(" requisição para ").append(request.getURI());
            HttpHeaders responseHeaders = response.getHeaders();
            long contentLength = responseHeaders.getContentLength();
            if (contentLength != 0) {
                if (hasTextBody(responseHeaders) && !isMockedResponse(response)) {
                    String bodyText = StreamUtils.copyToString(response.getBody(), determineCharset(responseHeaders));
                    builder.append(": [").append(bodyText).append("]");
                } else {
                    if (contentLength == -1) {
                        builder.append(" Corpo da requisição invalido ou vazio ");
                    } else {
                        builder.append(" Corpo da requisição com tamanho: ").append(contentLength);
                    }
                    MediaType contentType = responseHeaders.getContentType();
                    if (contentType != null) {
                        builder.append(" O tipo do conteúdo é ").append(contentType);
                    } else {
                        builder.append(" O tipo do conteúdo da requisição é invalido ou não informado");
                    }
                }

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                String dataHora = dateFormat.format(new Date());

                String hostName = (request.getURI() != null && request.getURI().getHost() != null)
                        ? request.getURI().getHost().toString()
                        : "";

                String fileName = "response" + "-" + hostName + "-" + dataHora + ".json";
                String currentFolder = format.format(new Date());
                String basePath = caminhoLog + File.separator + currentFolder;
                File basePathFile = new File(basePath);

                if (!basePathFile.exists()) {
                    basePathFile.mkdirs();
                }

                setAcessoFile(basePathFile);
                File jsonFile = new File(basePath + File.separator + fileName);
                setAcessoFile(jsonFile);
                try (BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(jsonFile), StandardCharsets.UTF_8))) {
                    // É AutoCloseable

                    out.write(builder.toString());

                } catch (IOException e) {

                    e.printStackTrace();
                }

            }

            responseLogger.debug(builder.toString());
        } catch (IOException e) {
            responseLogger.warn("Falha para registrar o log da resposta da Requisição: {} requisição para {}", request.getMethod(), request.getURI(),
                    e);
        }

    }

    private void setAcessoFile(File basePathFile) {
        basePathFile.setReadable(true, false);
        basePathFile.setExecutable(true, false);
        basePathFile.setWritable(true, false);
    }

    protected boolean hasTextBody(HttpHeaders headers) {
        MediaType contentType = headers.getContentType();
        if (contentType != null) {
            if ("text".equals(contentType.getType())) {
                return true;
            }
            String subtype = contentType.getSubtype();
            if (subtype != null) {
                return "xml".equals(subtype) || "json".equals(subtype) || subtype.endsWith("+xml")
                        || subtype.endsWith("+json");
            }
        }
        return false;
    }

    protected Charset determineCharset(HttpHeaders headers) {
        MediaType contentType = headers.getContentType();
        if (contentType != null) {
            try {
                Charset charSet = contentType.getCharset();
                if (charSet != null) {
                    return charSet;
                }
            } catch (UnsupportedCharsetException e) {

            }
        }
        return StandardCharsets.UTF_8;
    }

    private boolean isMockedResponse(ClientHttpResponse response) {
        return "MockClientHttpResponse".equals(response.getClass().getSimpleName());
    }


}