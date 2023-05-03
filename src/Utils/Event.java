package Utils;

import java.util.LinkedList;

public class Event<U>
{
    private LinkedList Subscribers = new LinkedList<IEventSubscriber<U>>();

    public void Subscribe(IEventSubscriber<U> sub)
    {
        if (!Subscribers.contains(sub))
            Subscribers.add(sub);
    }

    public void UnSubscribe(IEventSubscriber<U> sub)
    {
        Subscribers.remove(sub);
    }

    public void NotifyAll(U data,Object Sender)
    {
        for (var sub : Subscribers)
            ((IEventSubscriber<U>) sub).EventRaised(data,Sender);
    }
}
