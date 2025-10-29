package islands.model.student;

import islands.model.TileColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static islands.model.student.TestHelperMethods.makeModel;
import static org.junit.jupiter.api.Assertions.*;

class GameModelImplementationTest {
    GameModelImplementation model;

    @BeforeEach
    public void setup() {
        model = new GameModelImplementation(3);
    }


    @Test
    public void getBoardStringWorks3() {
        assertEquals("WW\nBn\n", model.getBoardString());
    }


    @Test
    public void testBoardInitialization() {
        assertEquals(3, model.getSize());
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                assertNull(model.getTileColor(row, col), "Board should be empty at start");
            }
        }
    }

    @Test
    public void testGetTileColor() {
        assertNull(model.getTileColor(0, 0), "Board should be empty at start");
        model.makePlay(0, 0, TileColor.WHITE);
        assertSame(TileColor.WHITE, model.getTileColor(0, 0));
        model.makePlay(1, 0, TileColor.BLACK);
        assertSame(TileColor.BLACK, model.getTileColor(1, 0));
    }

    @Test
    public void testCanPlay() {
        assertTrue(model.canPlay(0, 0));
        model.makePlay(0, 0, TileColor.WHITE);
        assertFalse(model.canPlay(0, 0));
        model.makePlay(1, 0, TileColor.BLACK);
        assertFalse(model.canPlay(1, 0));
        assertThrows(IllegalArgumentException.class, () -> model.makePlay(model.getSize() + 1, 0, TileColor.BLACK));
        assertThrows(IllegalArgumentException.class, () -> model.makePlay(0, model.getSize() + 1, TileColor.WHITE));
        assertThrows(IllegalArgumentException.class, () -> model.makePlay(-1, 0, TileColor.BLACK));
        assertThrows(IllegalArgumentException.class, () -> model.makePlay(0, -1, TileColor.WHITE));
    }

    @Test
    public void testMakePlay() {

    }


}
