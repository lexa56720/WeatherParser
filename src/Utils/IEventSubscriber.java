package Utils;

public interface IEventSubscriber<T>
{
    void EventRaised(T data,Object Sender);
}
