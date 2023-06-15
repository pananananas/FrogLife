package Utilities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Entities.Crabby;
import Main.Game;

import static Utilities.Constants.EnemyConstants.*;

public class LoadSave {


    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS  = "outside_sprites.png";
    public static final String LEVEL_ONE_DATA  = "level_one_data.png";
    public static final String BG_IMAGE  = "background_image.png";
    public static final String CRAB_ATLAS  = "crabby_sprite.png";
    public static final String STATUS_BAR  = "health_bar.png";
    
    public static BufferedImage getSpriteAtlas(String fileName) {
        BufferedImage image = null;
        InputStream is = LoadSave.class.getResourceAsStream("/Resources/" + fileName);
        try {
            image = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                ex.printStackTrace(); 
            }
        }
        return image;
    }

    public static ArrayList<Crabby> getCrabs() {
        BufferedImage image = getSpriteAtlas(LEVEL_ONE_DATA);
        ArrayList<Crabby> list = new ArrayList<>();

        for (int j = 0; j < image.getHeight(); j++) 
            for (int i = 0; i < image.getWidth(); i++) {
                Color color = new Color(image.getRGB(i, j));
                int value = color.getGreen();
                if (value == CRABBY ) // if 0
                    list.add(new Crabby(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
            }
        return list;
    }


    public static int[][] getLevelData() {
        int [][] lvlData = new int [Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage image = getSpriteAtlas(LEVEL_ONE_DATA);

        for (int j = 0; j < image.getHeight(); j++) 
            for (int i = 0; i < image.getWidth(); i++) {
                Color color = new Color(image.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48 )
                    value = 0;
                lvlData[j][i] = value;
            }
        for (int j = 0; j < lvlData.length; j++) {
            for (int i = 0; i < lvlData[0].length; i++) 
                System.out.print(lvlData[j][i] + " \t");
            System.out.println();
        }

        return lvlData;
    }
}
