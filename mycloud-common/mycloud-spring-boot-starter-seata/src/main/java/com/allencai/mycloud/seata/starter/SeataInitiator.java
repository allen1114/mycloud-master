package com.allencai.mycloud.seata.starter;

import io.seata.common.XID;
import io.seata.common.thread.NamedThreadFactory;
import io.seata.common.util.NetUtil;
import io.seata.core.rpc.netty.RpcServer;
import io.seata.core.rpc.netty.ShutdownHook;
import io.seata.server.UUIDGenerator;
import io.seata.server.coordinator.DefaultCoordinator;
import io.seata.server.metrics.MetricsManager;
import io.seata.server.session.SessionHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SeataInitiator implements SmartLifecycle {

    private boolean running;
    private SeataInitiatorParameter parameter;

    public SeataInitiator(SeataInitiatorParameter parameter) {
        this.parameter = parameter;
    }

    private static final int MIN_SERVER_POOL_SIZE = 100;
    private static final int MAX_SERVER_POOL_SIZE = 500;
    private static final int MAX_TASK_QUEUE_SIZE = 20000;
    private static final int KEEP_ALIVE_TIME = 500;


    private static final ThreadPoolExecutor WORKING_THREADS =
            new ThreadPoolExecutor(MIN_SERVER_POOL_SIZE, MAX_SERVER_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(MAX_TASK_QUEUE_SIZE), new NamedThreadFactory("ServerHandlerThread", MAX_SERVER_POOL_SIZE),
                    new ThreadPoolExecutor.CallerRunsPolicy());

    public void start() {

        running = true;
        MetricsManager.get().init();
        RpcServer rpcServer = new RpcServer(WORKING_THREADS);
        rpcServer.setListenPort(parameter.getPort());
        UUIDGenerator.init(parameter.getServerNode());
        try {
            SessionHolder.init("");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            System.exit(-1);
        }

        DefaultCoordinator coordinator = new DefaultCoordinator(rpcServer);
        coordinator.init();
        rpcServer.setHandler(coordinator);
        // register ShutdownHook
        ShutdownHook.getInstance().addDisposable(coordinator);

        //127.0.0.1 and 0.0.0.0 are not valid here.
        if (NetUtil.isValidIp(parameter.getHost(), false)) {
            XID.setIpAddress(parameter.getHost());
        } else {
            XID.setIpAddress(NetUtil.getLocalIp());
        }
        XID.setPort(rpcServer.getListenPort());

        try {
            rpcServer.init();
        } catch (Throwable e) {
            log.error("rpcServer init error:{}", e.getMessage(), e);
            System.exit(-1);
        }

        System.exit(0);
    }


    @Override
    public void stop() {
        ShutdownHook.getInstance().destroyAll();
        running = false;
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable runnable) {
        runnable.run();
    }

    @Override
    public int getPhase() {
        return 0;
    }

}
