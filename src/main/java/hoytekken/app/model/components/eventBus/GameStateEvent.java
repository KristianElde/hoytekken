package hoytekken.app.model.components.eventBus;

import hoytekken.app.model.components.GameState;

public record GameStateEvent(GameState oldState, GameState newState) implements IEvent{
}
