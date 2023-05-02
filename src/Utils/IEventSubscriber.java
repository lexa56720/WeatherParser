package Utils;

public interface IEventSubscriber<T>
{
    public void EventRaised(T data,Object Sender);
}
