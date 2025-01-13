package com.orbiktech.codespacescompanion;

import fi.iki.elonen.NanoHTTPD;

import java.util.List;
import java.util.Map;

public class HttpServer extends NanoHTTPD {

    private final LoginController loginController;

    public HttpServer(int port, LoginController loginController) {
        super(port);
        this.loginController = loginController;
    }

    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        System.out.println("[HTTP Server] New Request: " + uri);

        if (uri.equals("/callback")) {
            return handleAuthCallback(session);
        }
        else {
            return newFixedLengthResponse(
                    Response.Status.NOT_FOUND,
                    "text/html",
                    "<h1>404 Not Found"
            );
        }
    }

    private Response handleAuthCallback(IHTTPSession session) {
        Map<String, List<String>> params = session.getParameters();
        String code = params.containsKey("code") ? params.get("code").getFirst() : null;

        if (code != null) {
            loginController.handleOAuthCallback(code);

            return newFixedLengthResponse(
                    Response.Status.OK,
                    "text/html",
                    "<h1>GitHub Login Successful!</h1><p>You can close this tab.</p>"
            );
        }
        else {
            return newFixedLengthResponse(
                    Response.Status.BAD_REQUEST,
                    "text/html",
                    "<h1>Error: Missing Authorization Code</h1>"
            );
        }
    }
}
