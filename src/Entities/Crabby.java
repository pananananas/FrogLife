package Entities;

import static Utilities.Constants.EnemyConstants.*;

import Main.Game;

public class Crabby extends Enemy {
    
        public Crabby(float x, float y) {
            super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
            initHitbox(x, y, (int) Game.ENEMY_SCALE * 22, (int) Game.ENEMY_SCALE * 19);
        }
    
        public void update(int[][] levelData) {
            super.update(levelData);
        }
    
        public void render(java.awt.Graphics g) {
            super.render(g);
        }
    
}
