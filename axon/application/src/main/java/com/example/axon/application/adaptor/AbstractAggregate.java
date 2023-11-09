package com.example.axon.application.adaptor;

import com.example.appkit.application.basic.EventDrivenAggregateRoot;
import com.example.appkit.application.basic.IdObject;
import fj.Unit;
import fj.data.Either;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractAggregate<AggregateRoot extends EventDrivenAggregateRoot<Event>, Id extends IdObject, Event> {
    private AggregateRoot aggregateRoot;

    public AggregateRoot getAggregate() {
        return aggregateRoot;
    }

    @AggregateIdentifier
    public Id getAggregateId() {
        return getAggregateRootId(getAggregate());
    }

    @EventSourcingHandler
    public void on(Event event) {
        if (aggregateRoot != null) {
            aggregateRoot = (AggregateRoot) aggregateRoot.applyEvent(event);
        } else {
            if (isConstructEvent(event)) {
                aggregateRoot = newAggregateRootByEvent(event);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    protected abstract Id getAggregateRootId(AggregateRoot aggregate);

    protected abstract AggregateRoot newAggregateRootByEvent(Event event);

    protected abstract boolean isConstructEvent(Event event);

    protected void apply(Function<AggregateRoot, Event> action) {
        var event = action.apply(getAggregate());
        AggregateLifecycle.apply(event);
    }

    protected void apply(Event event) {
        AggregateLifecycle.apply(event);
    }

    protected void apply(Event event, MetaData metaData) {
        AggregateLifecycle.apply(event, metaData);
    }

    protected <Error> Either<Error, Event> apply(Function<AggregateRoot, Either<Error, Event>> action, Consumer<Error> ifLeft) {
        var result = action.apply(getAggregate());
        result.left().foreach((error) -> {
            ifLeft.accept(error);
            return Unit.unit();
        });
        result.right().foreach((event) -> {
            AggregateLifecycle.apply(event);
            return Unit.unit();
        });

        return result;
    }

    protected <Error> void applyOrThrow(
            Function<AggregateRoot, Either<Error, Event>> action,
            Function<Error, RuntimeException> ifLeft
    ) {
        var result = action.apply(getAggregate());
        result.either(
                left -> {
                    var exception = ifLeft.apply(left);
                    throw exception;
                },
                right -> {
                    apply(right);
                    return null;
                }
        );
    }
}
