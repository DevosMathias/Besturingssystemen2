package ui.controller;

import domain.model.ContactTracingService;

public class HandlerFactory {
    public RequestHandler getHandler(String handlerName, ContactTracingService model) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("ui.controller." + handlerName);
            // Java 9
            Object handlerObject = handlerClass.newInstance();
            // Java 10
            // Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setModel(model);

        } catch (Exception e) {
            throw new RuntimeException("Deze pagina bestaat niet!");
        }
        return handler;
    }
}
