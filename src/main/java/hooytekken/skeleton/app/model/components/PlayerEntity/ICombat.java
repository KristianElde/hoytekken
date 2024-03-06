package hooytekken.skeleton.app.model.components.PlayerEntity;

public interface ICombat {
    void punch(Player that);

    void kick(Player that);

    void block(int incomingAttack);
}
