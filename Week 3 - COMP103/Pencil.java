
/**
 * Write a description of class Pencil here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
/**
 * Write a description of class Pencil here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import ecs100.*;
import java.util.*;
import java.awt.Color;

/** Pencil */
public class Pencil {
    private double lastX;
    private double lastY;
    private List<Stroke> currentStroke;
    private Stack<List<Stroke>> currentStrokes = new Stack<>();
    private Stack<List<Stroke>> undoStack = new Stack<>();

    /**
     * Setup the GUI
     */
    public void setupGUI() {
        UI.setMouseMotionListener(this::doMouse);
        UI.addButton("Quit", UI::quit);
        UI.addButton("Undo", this::doUndo);
        UI.addButton("Redo", this::doRedo);
        UI.setLineWidth(3);
        UI.setDivider(0.0);
    }

    /**
     * Respond to mouse events
     */
    public void doMouse(String action, double x, double y) {
        if (action.equals("pressed")) {
            lastX = x;
            lastY = y;
            currentStroke = new ArrayList<>(); //start a new stroke
        } else if (action.equals("dragged")) {
            UI.drawLine(lastX, lastY, x, y);
            currentStroke.add(new Stroke(lastX, lastY, x, y)); 
            lastX = x;
            lastY = y;
        } else if (action.equals("released")) {
            UI.drawLine(lastX, lastY, x, y);
            currentStroke.add(new Stroke(lastX, lastY, x, y)); // Save the last segment
            currentStrokes.push(currentStroke); // Save the complete stroke
            undoStack.clear(); // Clear redo stack after a new stroke is added
        }
    }

    /**
     * Undo the last complete stroke
     */
    public void doUndo() {
        if (!currentStrokes.isEmpty()) {
            List<Stroke> undoneStroke = currentStrokes.pop(); // Remove the last stroke
            undoStack.push(undoneStroke); // Save it for redo
            UI.clearGraphics(); // Clear the canvas
            redrawCurrent(); // Redraw all remaining strokes
        }
    }
    
    public void doRedo(){
        if (!undoStack.isEmpty()) {
            List<Stroke> redoneStroke = undoStack.pop(); // Get the last undone stroke
            currentStrokes.push(redoneStroke); // Add it back to current strokes
            UI.clearGraphics(); // Clear the canvas
            redrawCurrent(); // Redraw all strokes
        }
    }

    /**
     * Redraw all strokes
     */
    public void redrawCurrent() {
        for (List<Stroke> stroke : currentStrokes) {
            for (Stroke s : stroke) {
                s.draw();
            }
        }
    }

    public void display() {
        Color col = Color.red;
        Stroke s1 = new Stroke(100, 200, 150, 350);
        s1.draw();
    }

    public static void main(String[] arguments) {
        new Pencil().setupGUI();
    }
}
