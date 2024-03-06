package hooytekken.skeleton.app.model.components.PlayerEntity;

public interface ICombat {
    boolean punch(Player that, int dmg);

    boolean kick(Player that, int dmg);

    boolean block(Player that, int incomingAttack);
}
