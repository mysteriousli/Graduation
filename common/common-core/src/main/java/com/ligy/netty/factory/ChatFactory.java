package com.ligy.netty.factory;


import com.ligy.netty.domain.BaseFactory;
import com.ligy.netty.enums.ChatEnums;
import org.springframework.stereotype.Component;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author lgy
 */
@Component
public class ChatFactory {
    private static final Map<String, BaseFactory> FACTORY_MAP = new ConcurrentHashMap<>();

    private static class ChatFactoryHolder {
        private static final ChatFactory INSTANCE = new ChatFactory();
    }

    public static ChatFactory getInstance() {
        return ChatFactoryHolder.INSTANCE;
    }

    public BaseFactory getFactoryMap(String serverPort, ChatEnums chatEnums) {
        return FACTORY_MAP.computeIfAbsent(serverPort, $ -> initChat(serverPort, chatEnums));
    }

    public BaseFactory initChat(String serverPort, ChatEnums chatEnums) {
        BaseFactory baseFactory = null;
        switch (chatEnums) {
            case SUPER:
                baseFactory = new SuperChat();
                break;
            case POPULAR:
                baseFactory = new PopularChat();
                break;
            default:
                break;
        }
        if (baseFactory!=null) {
            baseFactory.initChat(serverPort);
        }
        return baseFactory;
    }

    public void removeChat(String serverPort){
        FACTORY_MAP.remove(serverPort);
    }
}
