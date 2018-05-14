package event;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by mp on 2016/11/16.
 * 事件监听实现类
 */
public class GameEventMrgImpl implements GameEventMrg {

    /**
     * 事件和事件监听管理器-Map数据结构
     */
    private Map<Class<? extends GameEvent>, List<GameEventListener>> eventListenerMap = new HashMap<>();


    /**
     * 为某个事件,添加监听
     * @param eventType
     * @param listener
     */
    @Override
    public void addEventListener(Class<? extends GameEvent> eventType, GameEventListener listener) {
        List<GameEventListener> listenerList = eventListenerMap.get(eventType);
        if (listenerList == null) {
            listenerList = new LinkedList<>();
            eventListenerMap.put(eventType, listenerList);
        }
        listenerList.add(listener);
    }

    /**
     * 为某个事件,移除监听
     * @param eventType
     * @param listener
     */
    @Override
    public void rmoveEventListener(Class<? extends GameEvent> eventType, GameEventListener listener) {
        List<GameEventListener> listenerList = eventListenerMap.get(eventType);
        if (listenerList == null) {
            throw new IllegalStateException("要移除的listener尚未注册" + eventType.getName() + "@" + listener.getClass().getName());
        }
        listenerList.remove(listener);
    }

    /**
     * 获取某个事件上,被监听个数
     * @param eventType
     * @return
     */
    @Override
    public int getEventListenerNum(Class<? extends GameEvent> eventType) {
        List<GameEventListener> listenerList = eventListenerMap.get(eventType);
        if (listenerList == null) {
            return 0;
        } else {
            return listenerList.size();
        }
    }

    /**
     * 执行某一个事件上所有的监听
     * @param event
     * @return
     */
    @Override
    public void runEvent(GameEvent event) {
        List<GameEventListener> listenerList = eventListenerMap.get(event.getClass());
        if (listenerList == null) {
            System.out.println("此事件上未注册任何监听" + event.getClass().getName());
        }
        for (GameEventListener listener : listenerList) {
            listener.onEvent(event);
        }
    }
}
