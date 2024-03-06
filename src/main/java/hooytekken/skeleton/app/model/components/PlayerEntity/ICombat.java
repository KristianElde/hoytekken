package hooytekken.skeleton.app.model.components.PlayerEntity;

public interface ICombat {
    void punch(Player that, int dmg);

    void kick(Player that, int dmg);

    void block(Player that, int incomingAttack);
}
