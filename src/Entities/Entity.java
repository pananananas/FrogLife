/**
 * @file Entity.java
 * @brief This file contains the Entity class, which serves as a base for all entities in the game.
 */

package Entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
    /**
 * @class Entity
 * @brief This is an abstract class representing a game entity.
 */
public abstract class Entity {
    
    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;
    

  /**
     * @brief Entity constructor.
     * @param x The x-coordinate for the entity.
     * @param y The y-coordinate for the entity.
     * @param width The width of the entity.
     * @param height The height of the entity.
     */
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
		this.height = height;
    }

      /**
     * @brief Draws the hitbox for the entity.
     * @param g Graphics object used to draw the object.
     */
    protected void drawHitbox(Graphics g) {
        
        g.setColor(java.awt.Color.PINK);

        g.drawRect((int)hitbox.x, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }

       /**
     * @brief Initializes the hitbox for the entity.
     * @param x The x-coordinate for the hitbox.
     * @param y The y-coordinate for the hitbox.
     * @param width The width of the hitbox.
     * @param height The height of the hitbox.
     */
    protected void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

      /**
     * @brief Returns the hitbox for the entity.
     * @return The hitbox for the entity.
     */
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }


}
