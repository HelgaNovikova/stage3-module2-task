package com.mjc.school.command;

import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.command.CommandExecutor;

import java.util.Map;

public class CommandImpl implements Command {

    private final String handler;
    private final Object body;
    private final Map<String, Object> params;
    private final CommandExecutor executor;

    public CommandImpl(String handler, Object body, Map<String, Object> params, CommandExecutor executor) {
        this.handler = handler;
        this.body = body;
        this.params = params;
        this.executor = executor;
    }

    public CommandImpl(String handler, CommandExecutor executor) {
        this(handler, null, Map.of(), executor);
    }

    public CommandImpl(String handler, Object body, CommandExecutor executor) {
        this(handler, body, Map.of(), executor);
    }

    public CommandImpl(String handler, Map<String, Object> params, CommandExecutor executor) {
        this(handler, null, params, executor);
    }

    @Override
    public <R> R execute() {
        return executor.execute(handler, body, params);
    }
}
