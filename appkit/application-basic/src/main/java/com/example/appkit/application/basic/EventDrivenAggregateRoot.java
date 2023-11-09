package com.example.appkit.application.basic;

public interface EventDrivenAggregateRoot<Event> {
    EventDrivenAggregateRoot<Event> applyEvent(Event event);
}
